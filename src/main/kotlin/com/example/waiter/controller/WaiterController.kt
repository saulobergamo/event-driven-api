package com.example.waiter.controller

import com.example.waiter.model.Request
import com.example.waiter.model.entity.Popsicole
import com.example.waiter.service.WaiterService
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "WaiterController")
@RequestMapping("/waiter")
class WaiterController(
    private val waiterService: WaiterService
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun getAvailableMenu(): List<Popsicole>? {
        return waiterService.getAvailablePopsicoles()
    }

    @GetMapping("/all")
    fun getAllPopsicoles(): List<Popsicole>? {
        return waiterService.showAllPopsicoles()
    }
    @Deprecated("Banco de dados deve ser manipulado apenas pelos microsserviços de estoque e compras")
    @PostMapping()
    fun placeOrder(@RequestBody request: List<Request>) {
        waiterService.savePopsicoleList(request)
        logger.info {
            "placeOrder: request sent successfully"
        }
    }
}
