package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

class InvalidTimeException(
    override val message: String = "잘못된 시간입니다.",
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
): ApplicationException()