package com.example.networklayer.interactors
sealed class DataState<out R> {
    class Loading<T>(): DataState<T>()
    class Success<T>(val data: T) : DataState<T>()
    object NoResult : DataState<Nothing>()
    data class Failure(val message: String) : DataState<Nothing>()
}