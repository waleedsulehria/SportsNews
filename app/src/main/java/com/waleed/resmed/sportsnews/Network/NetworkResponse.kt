package com.waleed.resmed.sportsnews.Network

import retrofit2.Response

class NewsNetworkResponse<T> {

    var data : T? = null
        private set
    var responseCode : ResponseCode = ResponseCode.UNKNOWN
        private set
    var message : String = ""
        private set

    companion object{
        fun <T> map( response : Response<T> ) : NewsNetworkResponse<T> {
            val newsResp = NewsNetworkResponse<T>()
            response.body()?.let {
                newsResp.data = it
            }
           newsResp.responseCode =  when(response.code()){
                200 -> ResponseCode.OK
                500 -> ResponseCode.SERVER_ERROR
                400 -> ResponseCode.BAD_REQUEST
                502 -> ResponseCode.BAD_GATEWAY
                504 -> ResponseCode.GATEWAY_TIMEOUT
                else -> ResponseCode.UNKNOWN
            }
            newsResp.message = response.message()
            return newsResp
        }
    }

    enum class ResponseCode(val code : Int) {
        OK(200),
        SERVER_ERROR(500),
        BAD_REQUEST(400),
        BAD_GATEWAY(502),
        GATEWAY_TIMEOUT(504),
        UNKNOWN(-1)
    }
}