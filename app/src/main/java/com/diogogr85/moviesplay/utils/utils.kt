package com.diogogr85.moviesplay.utils


object Utils {
    fun queryParams(vararg queryParams: Pair<String, String>): Map<String, String> {
        val queryMap = mutableMapOf<String, String>()
        queryParams.forEach {
            queryMap[it.first] = it.second
        }

        return queryMap
    }
}

