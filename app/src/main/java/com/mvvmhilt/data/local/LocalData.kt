package com.mvvmhilt.data.local

import android.content.Context
import android.content.SharedPreferences
import com.mvvmhilt.FAVOURITES_KEY
import com.mvvmhilt.SHARED_PREFERENCES_FILE_NAME
import com.mvvmhilt.data.Resource
import com.mvvmhilt.data.dto.login.LoginRequest
import com.mvvmhilt.data.dto.login.LoginResponse
import com.mvvmhilt.data.error.PASS_WORD_ERROR
import javax.inject.Inject

/**
 * Created by navdeepera04
 */

class LocalData @Inject constructor(val context: Context) {

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if (loginRequest == LoginRequest("navdeepkhera04@gmail.com", "navdeepKhera")) {
            return Resource.Success(LoginResponse("123", "Navdeep", "Khera",
                    "chiyoda", "25", "4500001", "Aichi",
                    "Japan", "navdeepkhera04@gmail.com"))
        }
        return Resource.DataError(PASS_WORD_ERROR)
    }

    fun getCachedFavourites(): Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        return Resource.Success(sharedPref.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf())
    }

    fun isFavourite(id: String): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val cache = sharedPref.getStringSet(FAVOURITES_KEY, setOf<String>()) ?: setOf()
        return Resource.Success(cache.contains(id))
    }

    fun cacheFavourites(ids: Set<String>): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

    fun removeFromFavourites(id: String): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        var set = sharedPref.getStringSet(FAVOURITES_KEY, mutableSetOf<String>())?.toMutableSet() ?: mutableSetOf()
        if (set.contains(id)) {
            set.remove(id)
        }
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        editor.putStringSet(FAVOURITES_KEY, set)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }
}

