package my.blog.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CglApplication

fun main(args: Array<String>) {
    SpringApplication.run(CglApplication::class.java, *args)
}
