package me.seoly.mytable.exception

import org.springframework.http.HttpStatus

class StoreClosedException (
    override val message: String = "식당이 운영중이 아닙니다.",
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
): ApplicationException()