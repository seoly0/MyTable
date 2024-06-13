package me.seoly.mytable.service

import com.fasterxml.jackson.databind.ObjectMapper
import me.seoly.mytable.core.model.entity.OrderEntity
import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.serializer.OrderPayload
import me.seoly.mytable.repository.MenuRepository
import me.seoly.mytable.repository.OrderRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val menuRepository: MenuRepository,
    private val modelMapper: ModelMapper,
) {

    fun createCustomerOrder(
        customerId: Long,
        create: OrderPayload.Request.Create,
    ): OrderPayload.Response.Default {

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

        val menuIds = create.details.menuList.map { it.menuId }
        val menuEntityList = menuRepository.findAllByStoreIdAndIdIn(storeId = entity.storeId, ids = menuIds)

        var totalPrice = 0
        create.details.menuList.forEach { menu ->
            val menuEntity = menuEntityList.find { entity -> menu.menuId == entity.id }
            val optionEntityList = menuEntity?.relations?.map { it.option }

            var menuPrice = menuEntity?.price ?: 0
            menu.optionList.forEach { option ->
                val optionEntity = optionEntityList?.find { entity -> option.optionId == entity.id }
                val itemEntityList = optionEntity?.itemList
                var optionPrice = 0
                option.itemList.forEach { item ->
                    val itemEntity = itemEntityList?.find { entity -> item.itemId == entity.id }
                    optionPrice += item.quantity * (itemEntity?.price ?: 0)
                }

                menuPrice += optionPrice
            }
            totalPrice += menuPrice * menu.quantity
        }

        entity.totalPrice = totalPrice

        val details = objectMapper.writeValueAsString(create.details)

        entity.details = details
        orderRepository.save(entity)

        return modelMapper.map(entity, OrderPayload.Response.Default::class.java)
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

    fun patchState(
        orderId: Long,
        storeId: Long?,
        customerId: Long?,
        patch: OrderPayload.Request.OrderState
    ): OrderPayload.Response.Default {

        val entity = orderRepository.findByIdAndStoreIdOrCustomerId(orderId, storeId, customerId)
        entity.state = patch.state
        orderRepository.save(entity)

        return modelMapper.map(entity, OrderPayload.Response.Default::class.java)
    }
}
