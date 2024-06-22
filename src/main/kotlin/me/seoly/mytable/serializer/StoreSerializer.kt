package me.seoly.mytable.serializer

import io.swagger.v3.oas.annotations.media.Schema
import java.time.DayOfWeek

class StoreSerializer {

    class Request {
        data class Create(
            val name: String,
            val address: String,
            val ownerId: Long,
            val bizNumber: String,
            val tel: String,
        )

        class PatchOpened(
            val opened: Boolean
        )

        data class Opening(
            val dayOfWeek: DayOfWeek,
            @Schema(example = "11:00")
            val openTime: String,
            @Schema(example = "24:00")
            val closeTime: String,
            @Schema(example = "23:30", description = "before close time")
            val lastOrderTime: String,
        )
    }

    class Response {
        open class Default: CommonSerializer.ResponseBase() {
            var name: String = ""
            var rating = 0
            var opened = false
        }

//        class WithOpened: Default() {
//            var isOpened = false
//        }

        class Opening {
            lateinit var dayOfWeek: DayOfWeek
            lateinit var openTime: String
            lateinit var closeTime: String
            lateinit var lastOrderTime: String
        }
    }

    class Filter {
        var name: String = ""
    }
}