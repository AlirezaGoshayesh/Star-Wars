package com.example.starwars.domain

object Mapper {
    fun mapHeight(height: String): String {
        val cm = height.toIntOrNull()
        if (cm != null) {
            val feet = cm / 30.48
            val inches = (feet - feet.toInt()) * 12
            return "${feet.toInt()}' ${inches.toInt()} or $cm cm"
        } else
            return height
    }

    fun mapUrlToId(url: String): String {
        var newUrl = ""
        if (url.endsWith("/"))
            newUrl = url.removeSuffix("/")
        return newUrl.split("/").last()
    }
}