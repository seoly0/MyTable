package me.seoly.mytable.serializer

import me.seoly.mytable.core.model.type.OptionConstraint

class OptionSerializer {

    class Request {

        class Create {
            var name: String = ""
            var required: Boolean = false
            lateinit var constraint: OptionConstraint
            var constraintNumber: Int = 0
            var default: Long? = 0

            var itemList = emptyList<AddItem>()
        }

        class AddItem {
            var name: String = ""
            var price: Int = 0
        }
    }

    class Response {

        open class Default: CommonSerializer.ResponseBase() {
            var name: String = ""
            var required: Boolean = false
            lateinit var constraint: OptionConstraint
            var constraintNumber: Int = 0
            var default: Long? = 0
        }

        class WithItem: Default() {
//            var name: String = ""
//            var required: Boolean = false
//            lateinit var constraint: OptionConstraint
//            var constraintNumber: Int = 0
//            var default: Long? = 0

            var itemList = emptyList<Item>()
        }

        class Item: CommonSerializer.ResponseBase() {
            var name: String = ""
            var price: Int = 0
        }
    }
}