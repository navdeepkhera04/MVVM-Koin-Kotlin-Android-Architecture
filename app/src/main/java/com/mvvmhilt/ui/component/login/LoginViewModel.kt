package com.mvvmhilt.ui.component.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvmhilt.data.DataRepository
import com.mvvmhilt.data.Resource
import com.mvvmhilt.data.dto.login.LoginRequest
import com.mvvmhilt.data.dto.login.LoginResponse
import com.mvvmhilt.data.error.CHECK_YOUR_FIELDS
import com.mvvmhilt.data.error.PASS_WORD_ERROR
import com.mvvmhilt.data.error.USER_NAME_ERROR
import com.mvvmhilt.ui.base.BaseViewModel
import com.mvvmhilt.utils.RegexUtils.isValidEmail
import com.mvvmhilt.utils.SingleEvent
import com.mvvmhilt.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by navdeepera04
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<Resource<LoginResponse>>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = loginLiveDataPrivate

    /** Error handling as UI **/

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun doLogin(userName: String, passWord: String) {
        val isUsernameValid = isValidEmail(userName)
        val isPassWordValid = passWord.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(PASS_WORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(USER_NAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = Resource.DataError(CHECK_YOUR_FIELDS)
        } else {
            viewModelScope.launch {
                loginLiveDataPrivate.value = Resource.Loading()
                wrapEspressoIdlingResource {
                    dataRepository.doLogin(loginRequest = LoginRequest(userName, passWord)).collect {
                        loginLiveDataPrivate.value = it
                    }
                }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}
