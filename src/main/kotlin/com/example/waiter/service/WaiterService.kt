package com.example.waiter.service

import com.example.waiter.model.Request
import com.example.waiter.model.entity.Popsicole
import com.example.waiter.repository.PopsicolesRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class WaiterService(
    private val popsicolesRepository: PopsicolesRepository,
) {
    private val logger = KotlinLogging.logger {}

    fun getAvailablePopsicoles(): List<Popsicole>? {
        return popsicolesRepository.findByAvailable(true).also {
            logger.info {
                "getAvailablePopsicoles: trying to get available popsicoles in menu"
            }
        }
    }

    fun showAllPopsicoles(): List<Popsicole>? {
        return popsicolesRepository.findAll().also {
            logger.info {
                "showAllPopsicoles: trying to get all popsicoles in menu"
            }
        }
    }

    fun savePopsicoleList(request: List<Request>) {
        var count = 0
        request.forEach { popsicole ->
            val newPopsicole = Popsicole(
                null,
                popsicole.available,
                popsicole.flavor,
                popsicole.weight,
                popsicole.description,
                popsicole.amount
            )
            count++
            popsicolesRepository.save(newPopsicole)
        }
        logger.info {
            "savePopsicoleList: saved $count popsicoles in dataBase"
        }
    }
}
