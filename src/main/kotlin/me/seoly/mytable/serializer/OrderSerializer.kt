package me.seoly.mytable.serializer

import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.core.model.type.OrderType
import java.time.LocalDateTime

class OrderSerializer {

    class Request {

        data class Create (
            val storeId: Long,
            val tableId: Long,
            val at: LocalDateTime,
            val numOfPeople: Int,
            val type: OrderType,
            val state: OrderStateType,
            val details: OrderSimple,
        )

        data class OrderState (
            val state: OrderStateType = OrderStateType.RESERVED
        )
    }

    class Response {
        open class Default: CommonSerializer.ResponseBase() {
            var customerId: Long = 0
            var storeId: Long = 0
            var tableId: Long = 0
            lateinit var at: String
            var numOfPeople: Int = 0
            lateinit var type: OrderType
            lateinit var state: OrderStateType
            var totalPrice: Int = 0
        }

        class WithDetails: Default() {
            lateinit var details: OrderDetail
        }
    }

    class OrderDetail {

        var menuList: MutableList<Menu> = mutableListOf()
        var totalPrice = 0

        class Menu {
            var menuId: Long = 0
            lateinit var name: String
            var unitPrice = 0
            var quantity = 1
            var optionList: MutableList<Option> = mutableListOf()

            class Option {
                var optionId: Long = 0
                lateinit var name: String
                var totalPrice = 0
                val itemList: MutableList<Item> = mutableListOf()

                class Item {
                    var itemId: Long = 0
                    lateinit var name: String
                    var unitPrice = 0
                    var quantity = 1
                }
            }
        }
    }

    class OrderSimple {
        val menuList: List<Menu> = emptyList()

        class Menu {
            var menuId: Long = 0
            var quantity = 1
            val optionList: List<Option> = emptyList()

            class Option {
                val optionId: Long = 0
                val itemList: List<Item> = emptyList()

                class Item {
                    val itemId: Long = 0
                    var quantity = 1
                }
            }
        }
    }
}