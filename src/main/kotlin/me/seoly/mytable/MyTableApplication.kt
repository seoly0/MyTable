package me.seoly.mytable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyTableApplication

fun main(args: Array<String>) {
    runApplication<MyTableApplication>(*args)
}
