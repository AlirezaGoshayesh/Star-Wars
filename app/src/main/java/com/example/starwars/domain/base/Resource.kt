package com.example.starwars.domain.base

import com.example.starwars.domain.exceptions.ErrorModel

sealed class Resource<out R> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorModel: ErrorModel) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorModel=$errorModel]"
            Loading -> "Loading"
        }
    }

}
