package com.mvvmhilt.usecase.errors

import com.mvvmhilt.data.error.Error
import com.mvvmhilt.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by navdeepera04
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
