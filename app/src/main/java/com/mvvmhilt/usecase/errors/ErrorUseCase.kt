package com.mvvmhilt.usecase.errors

import com.mvvmhilt.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
