package me.seoly.mytable.service

import com.fasterxml.jackson.databind.ObjectMapper
import me.seoly.mytable.core.model.entity.OrderEntity
import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.exception.EntityNotExistException
import me.seoly.mytable.serializer.OrderPayload
import me.seoly.mytable.repository.MenuRepository
import me.seoly.mytable.repository.OptionItemRepository
import me.seoly.mytable.repository.OptionRepository
import me.seoly.mytable.repository.OrderRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val menuRepository: MenuRepository,
    private val optionRepository: OptionRepository,
    private val itemRepository: OptionItemRepository,
    private val modelMapper: ModelMapper,
    private val objectMapper: ObjectMapper,
) {

    fun createCustomerOrder(
        customerId: Long,
        create: OrderPayload.Request.Create,
    ): OrderPayload.Response.WithDetails {

        val objectMapper = ObjectMapper()
        val entity = OrderEntity(
            at = create.at,
            customerId = customerId,
            storeId = create.storeId,
            tableId = create.tableId,
            state = create.state,
            type = create.type,
            numOfPeople = create.numOfPeople
        )

        val details = parseSimple(create.details, create.storeId)
        entity.totalPrice = details.totalPrice
        entity.details = objectMapper.writeValueAsString(details)


        orderRepository.save(entity)

        return createResponse(entity, details)
    }

    fun parseSimple(simple: OrderPayload.OrderSimple, storeId: Long): OrderPayload.OrderDetail {

        val menuIdList = simple.menuList.map { it.menuId }.distinct()
        val optionIdList = simple.menuList.map { it.optionList.map { o -> o.optionId } }.flatten().distinct()
        val itemIdList = fullFlatten<Long>(simple.menuList.map { it.optionList.map { o -> o.itemList.map { i -> i.itemId } } }).distinct()

        val menuEntityList = menuRepository.findAllByIdInAndStoreId(menuIdList, storeId)
        val optionEntityList = optionRepository.findAllByIdInAndStoreId(optionIdList, storeId)
        val itemEntityList = itemRepository.findAllByIdIn(itemIdList)

        val details = OrderPayload.OrderDetail()

        simple.menuList.forEach { menuSimple ->
            val menuDetail = OrderPayload.OrderDetail.Menu()
            val menuEntity = menuEntityList.find { it.id == menuSimple.menuId } ?: throw EntityNotExistException()
            menuDetail.menuId = menuEntity.id
            menuDetail.quantity = menuSimple.quantity
            menuDetail.name = menuEntity.name
            menuDetail.unitPrice = menuEntity.price
            details.menuList.add(menuDetail)
            menuSimple.optionList.forEach { optionSimple ->
                val optionDetail = OrderPayload.OrderDetail.Menu.Option()
                val optionEntity = optionEntityList.find { it.id == optionSimple.optionId } ?: throw EntityNotExistException()
                optionDetail.optionId = optionEntity.id
                optionDetail.name = optionEntity.name
                menuDetail.optionList.add(optionDetail)
                optionSimple.itemList.forEach { itemSimple ->
                    val itemDetail = OrderPayload.OrderDetail.Menu.Option.Item()
                    val itemEntity = itemEntityList.find { it.id == itemSimple.itemId } ?: throw EntityNotExistException()
                    itemDetail.itemId = itemEntity.id
                    itemDetail.quantity = itemSimple.quantity
                    itemDetail.name = itemEntity.name
                    itemDetail.unitPrice = itemEntity.price
                    optionDetail.totalPrice += itemDetail.unitPrice * itemDetail.quantity
                    optionDetail.itemList.add(itemDetail)
                }
                menuDetail.unitPrice += optionDetail.totalPrice
            }
            details.totalPrice += menuDetail.unitPrice * menuDetail.quantity
        }

        return details
    }
    fun createResponse(entity: OrderEntity, details: OrderPayload.OrderDetail? = null): OrderPayload.Response.WithDetails {

        val ret = OrderPayload.Response.WithDetails()
        ret.id = entity.id
        ret.createdAt = entity.createdAt.toString()
        ret.updatedAt = entity.updatedAt.toString()
        ret.customerId = entity.customerId
        ret.storeId = entity.storeId
        ret.tableId = entity.tableId
        ret.state = entity.state
        ret.type = entity.type
        ret.numOfPeople = entity.numOfPeople
        ret.totalPrice = entity.totalPrice
        ret.at = entity.at.toString()

        if (details != null) {
            ret.details = details
        }
        else {
            ret.details = objectMapper.readValue(entity.details, OrderPayload.OrderDetail::class.java)
        }

        return ret
    }

    fun serveStoreOrderList(storeId: Long): List<OrderPayload.Response.Default> {

        val orderList = orderRepository.findAllByStoreId(storeId)

        return orderList.map {
            modelMapper.map(it, OrderPayload.Response.Default::class.java)
        }
    }

    fun serveStoreOrderListByState(storeId: Long, state: OrderStateType): List<OrderPayload.Response.Default> {

        val orderList = orderRepository.findAllByStoreIdAndState(storeId, state)

        return orderList.map {
            modelMapper.map(it, OrderPayload.Response.Default::class.java)
        }
    }

    fun serveCustomerOrderList(customerId: Long): List<OrderPayload.Response.Default> {

        val orderList = orderRepository.findAllByCustomerId(customerId)

        return orderList.map {
            modelMapper.map(it, OrderPayload.Response.Default::class.java)
        }
    }

    fun serveCustomerOrderListByState(customerId: Long, state: OrderStateType): List<OrderPayload.Response.Default> {

        val orderList = orderRepository.findAllByCustomerIdAndState(customerId, state)

        return orderList.map {
            modelMapper.map(it, OrderPayload.Response.Default::class.java)
        }
    }

    fun <T> fullFlatten(list: List<Any>): List<T> {
        val result = mutableListOf<T>()

        for (element in list) {
            if (element is List<*>) {
                @Suppress("UNCHECKED_CAST")
                result.addAll(fullFlatten(element as List<Any>))
            } else {
                @Suppress("UNCHECKED_CAST")
                result.add(element as T)
            }
        }

        return result
    }

    @Transactional(readOnly = true)
    fun serveStoreOrderDetail(storeId: Long, orderId: Long): OrderPayload.Response.WithDetails {

        val order = orderRepository.findByIdAndStoreId(orderId, storeId) ?: throw EntityNotExistException()
        return createResponse(order)
    }

    fun patchState(
        orderId: Long,
        storeId: Long?,
        customerId: Long?,
        patch: OrderPayload.Request.OrderState
    ): OrderPayload.Response.WithDetails {

        val entity = orderRepository.findByIdAndStoreIdOrCustomerId(orderId, storeId, customerId)
        entity.state = patch.state
        orderRepository.save(entity)

        return createResponse(entity)
    }
}