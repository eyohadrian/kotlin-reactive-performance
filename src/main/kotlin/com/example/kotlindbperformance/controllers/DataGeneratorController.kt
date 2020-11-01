package com.example.kotlindbperformance.controllers

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/generate")
class DataGeneratorController() {

   /* @GetMapping
    fun get(@RequestParam("test") test: Int) {
        println(test)
    }
*/
    @PostMapping
    fun get(@RequestBody entityDTO: EntityDTO) {
        print("${entityDTO.firstKey} ${entityDTO.secondKey}")
    }

    data class EntityDTO(val firstKey: String, val secondKey: String)
}