package com.mvvmhilt

/**
 * Created by navdeepera04
 */
sealed class DataStatus {
    object Success : DataStatus()
    object Fail : DataStatus()
    object EmptyResponse : DataStatus()
}
