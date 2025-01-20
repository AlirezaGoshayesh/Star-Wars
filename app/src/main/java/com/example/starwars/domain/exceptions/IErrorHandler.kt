package com.example.starwars.domain.exceptions

import com.example.starwars.domain.exceptions.ErrorModel

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}