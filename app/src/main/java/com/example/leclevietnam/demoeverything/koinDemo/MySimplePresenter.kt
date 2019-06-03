package com.example.leclevietnam.demoeverything.koinDemo

class MySimplePresenter(val helloRepository: HelloRepository) {
    fun sayHello() = "${helloRepository.giveHello()} helloooooo"
}