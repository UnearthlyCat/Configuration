package unearthly.configuration.impl

import org.tomlj.Toml
import unearthly.ConfigSection
import unearthly.configuration.Configuration
import unearthly.exception.CatchException
import java.io.File

class TomlConfiguration(override val file: File) : Configuration{

    private var root = ConfigSection()

    override fun load() {
        CatchException.catch("TOML load: ${file.name}") {
            if (!file.exists()) file.createNewFile()

            val result = Toml.parse(file.toPath())
            val map = mutableMapOf<String, Any?>()

            result.keySet().forEach { key ->
                map[key] = result.get(key)
            }

            root = ConfigSection(map)
        }
    }

    override fun save() {
        CatchException.catch("TOML save: ${file.name}") {
            val sb = StringBuilder()

            root.raw().forEach { (key, value) ->
                sb.append("$key = ${format(value)}\n")
            }

            file.writeText(sb.toString())
        }
    }

    fun get(): ConfigSection = root

    private fun format(value: Any?): String =
        when (value) {
            is String -> "\"$value\""
            else -> value.toString()
        }

}