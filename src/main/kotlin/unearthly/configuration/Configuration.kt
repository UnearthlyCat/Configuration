package unearthly.configuration

import java.io.File

interface Configuration {
    val file: File

    fun load()
    fun save()

    fun reload() {
        load()
        save()
    }
}