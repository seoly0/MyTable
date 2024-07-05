package me.seoly.mytable.controller
import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.serializer.OrderSerializer
import me.seoly.mytable.service.OrderService
import org.springframework.web.bind.annotation.*

@Tag(name = "주문")
@RequestMapping("/v1/user")
@RestController
class OrderController (
    private val orderService: OrderService,
) {

    @PostMapping("/customer/my/order")
    fun postCustomerOrder(
        @RequestBody body: OrderSerializer.Request.Create,
    ) = orderService.createCustomerOrder(1, body)

    @GetMapping("/store/{storeId}/order/list")
    fun getStoreOrderList(
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderList(storeId)

    @GetMapping("/store/{storeId}/order/pending/list")
    fun getStoreOrderPendingList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.PENDING)

    @GetMapping("/store/{storeId}/order/reserved/list")
    fun getStoreOrderReservedList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.RESERVED)

    @GetMapping("/store/{storeId}/order/processing/list")
    fun getStoreOrderProcessingList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.PROCESSING)

    @GetMapping("/store/{storeId}/order/completed/list")
    fun getStoreOrderCompletedList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.COMPLETED)

    @GetMapping("/store/{storeId}/order/noshow/list")
    fun getStoreOrderNoShowList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.NO_SHOW)

    @GetMapping("/store/{storeId}/order/canceled/list")
    fun getStoreOrderCanceledList (
        @PathVariable storeId: Long,
    ) = orderService.serveStoreOrderListByState(storeId, OrderStateType.CANCELED)

    @GetMapping("/customer/{customerId}/order/list")
    fun getCustomerOrderList(
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderList(customerId)

    @GetMapping("/customer/{customerId}/order/pending/list")
    fun getCustomerOrderPendingList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.PENDING)

    @GetMapping("/customer/{customerId}/order/reserved/list")
    fun getCustomerOrderReservedList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.RESERVED)

    @GetMapping("/customer/{customerId}/order/processing/list")
    fun getCustomerOrderProcessingList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.PROCESSING)

    @GetMapping("/customer/{customerId}/order/completed/list")
    fun getCustomerOrderCompletedList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.COMPLETED)

    @GetMapping("/customer/{customerId}/order/noshow/list")
    fun getCustomerOrderNoShowList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.NO_SHOW)

    @GetMapping("/customer/{customerId}/order/canceled/list")
    fun getCustomerOrderCanceledList (
        @PathVariable customerId: Long,
    ) = orderService.serveCustomerOrderListByState(customerId, OrderStateType.CANCELED)

    @GetMapping("/store/{storeId}/order/{orderId}")
    fun getStoreOrder(
        @PathVariable storeId: Long,
        @PathVariable orderId: Long,
    ) = orderService.serveStoreOrderDetail(storeId, orderId)

    @PatchMapping("/store/{storeId}/order/{orderId}/state")
    fun patchStoreOrder(
        @PathVariable storeId: Long,
        @PathVariable orderId: Long,
        @RequestBody body: OrderSerializer.Request.OrderState,
    ) = orderService.patchState(orderId, storeId, null, body)

    @GetMapping("/customer/{customerId}/order/{orderId}")
    fun getCustomerOrder(
        @PathVariable customerId: Long,
        @PathVariable orderId: Long,
    ): Nothing = TODO()

    @PatchMapping("/customer/{customerId}/order/{orderId}/state")
    fun patchCustomerOrder(
        @PathVariable customerId: Long,
        @PathVariable orderId: Long,
        @RequestBody body: OrderSerializer.Request.OrderState,
    ) = orderService.patchState(orderId, null, customerId, body)

    @GetMapping("/customer/{customerId}/store/{storeId}/order/script")
    fun getCustomerOrderScriptList(
        @PathVariable customerId: Long,
        @PathVariable storeId: Long,
    ) = orderService.getPreviousOrderScripts(storeId, customerId)
}