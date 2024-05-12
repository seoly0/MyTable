package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

class EntityNotExistException(
    override val message: String = "대상이 존재하지 않습니다."
) : ApplicationException() {

    init {
        this.status = HttpStatus.NOT_FOUND
    }
}