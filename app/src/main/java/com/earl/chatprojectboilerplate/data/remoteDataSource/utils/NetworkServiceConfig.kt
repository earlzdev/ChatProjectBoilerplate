package com.earl.chatprojectboilerplate.data.remoteDataSource.utils

interface NetworkServiceConfig {

    companion object {
        private const val currentGeo = "http://ipwho.is/"
        private const val currentIp = "http://ip-api.com/json/"
    }

    sealed class Endpoints(val url: String) {
        class CurrentGeo(ip: String) : Endpoints("$currentGeo/$ip")
        class CurrentIp : Endpoints(currentIp)
    }
}