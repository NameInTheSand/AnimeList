package com.ukrdroiddev.presentation.utils

import android.content.Context
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.NetworkError.FORBIDDEN
import com.ukrdroiddev.domain.utils.NetworkError.LIMIT
import com.ukrdroiddev.domain.utils.NetworkError.NO_INTERNET
import com.ukrdroiddev.domain.utils.NetworkError.REQUEST_TIMEOUT
import com.ukrdroiddev.domain.utils.NetworkError.SERVER_ERROR
import com.ukrdroiddev.domain.utils.NetworkError.UNAUTHORIZED
import com.ukrdroiddev.domain.utils.NetworkError.UNKNOWN
import com.ukrdroiddev.domain.utils.NetworkError.URL_NOT_FOUND
import com.ukrdroiddev.domain.utils.NetworkError.VALIDATION_ERROR
import com.ukrdroiddev.presentation.R

fun NetworkError.getErrorMessage(context: Context): String {
    return when (this) {
        REQUEST_TIMEOUT -> context.getString(R.string.request_timeout)
        NO_INTERNET -> context.getString(R.string.no_internet)
        SERVER_ERROR -> context.getString(R.string.server_error)
        URL_NOT_FOUND -> context.getString(R.string.url_not_found)
        VALIDATION_ERROR -> context.getString(R.string.validation_error)
        LIMIT -> context.getString(R.string.request_limit)
        FORBIDDEN -> context.getString(R.string.forbidden)
        UNAUTHORIZED -> context.getString(R.string.unauthorized)
        UNKNOWN -> context.getString(R.string.unknown_error)
    }
}