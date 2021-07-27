package com.mvvmhilt.ui.base

import androidx.lifecycle.ViewModel
import com.mvvmhilt.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by navdeepera04
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
