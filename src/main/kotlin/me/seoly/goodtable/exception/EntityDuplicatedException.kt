package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

class EntityDuplicatedException(
    override val message: String = "중복된 대상입니다.",
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
): ApplicationException()