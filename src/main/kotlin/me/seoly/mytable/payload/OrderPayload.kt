package me.seoly.mytable.payload

import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.core.model.type.OrderType
import java.time.LocalDateTime

class OrderPayload {

    class Request {

        data class Create (
            val storeId: Long,
            val tableId: Long,
            val at: LocalDateTime,
            val numOfPeople: Int,
            val type: OrderType,
            val state: OrderStateType,
            val details: OrderDescribe,
        )

        data class OrderState (
            val state: OrderStateType = OrderStateType.RESERVED
        )
    }

    class Response {
        class Default: CommonPayload.ResponseBase() {
            var customerId: Long = 0
            var storeId: Long = 0
            var tableId: Long = 0
        }
    }

    class OrderDescribe {
        val menuList: List<MenuDescribe> = emptyList()

        class MenuDescribe {
            val menuId: Long = 0
            var quantity = 1
            val optionList: List<OptionDescribe> = emptyList()

            class OptionDescribe {
                val optionId: Long = 0
                val itemList: List<ItemDescribe> = emptyList()

                class ItemDescribe {
                    val itemId: Long = 0
                    var quantity = 1
                }
            }
        }
    }

}