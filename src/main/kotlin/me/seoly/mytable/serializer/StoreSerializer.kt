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
        class Default: CommonSerializer.ResponseBase() {
            lateinit var name: String
        }

        class Opening {
            lateinit var dayOfWeek: DayOfWeek
            lateinit var openTime: String
            lateinit var closeTime: String
            lateinit var lastOrderTime: String
        }
    }
}