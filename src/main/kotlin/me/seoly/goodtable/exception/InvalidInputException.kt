package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

class InvalidInputException(
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String = "유효하지 않은 입력입니다."
): ApplicationException()