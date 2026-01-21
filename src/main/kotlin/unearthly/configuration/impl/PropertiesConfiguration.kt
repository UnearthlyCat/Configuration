package unearthly.configuration.impl

import unearthly.configuration.Configuration
import unearthly.exception.CatchException
import java.io.File
import java.util.Properties

class PropertiesConfiguration(override val file: File) : Configuration {

    private val props = Properties()

    override fun load() {
        CatchException.catch("Properties load: ${file.name}") {
            if (!file.exists()) file.createNewFile()
            file.inputStream().use { props.load(it) }
        }
    }

    override fun save() {
        CatchException.catch("Properties save: ${file.name}") {
            file.outputStream().use {
                props.store(it, "PropertiesConfig")
            }
        }
    }

    fun getString(key: String, def: String? = null): String? =
        props.getProperty(key, def)

    fun getInt(key: String, def: Int = 0): Int =
        props.getProperty(key)?.toIntOrNull() ?: def

    fun getBoolean(key: String, def: Boolean = false): Boolean =
        props.getProperty(key)?.toBoolean() ?: def

    fun set(key: String, value: Any) {
        props[key] = value.toString()
    }

}