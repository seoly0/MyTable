package me.seoly.mytable.exception

import org.springframework.http.HttpStatus

class EntityNotExistException(
    override val message: String = "대상이 존재하지 않습니다.",
    override val status: HttpStatus =HttpStatus.NOT_FOUND,
) : ApplicationException()