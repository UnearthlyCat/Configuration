package unearthly.configuration.impl

import com.typesafe.config.ConfigFactory
import unearthly.ConfigSection
import unearthly.configuration.Configuration
import unearthly.exception.CatchException
import java.io.File

class HoconConfiguration(override val file: File) : Configuration {

    private var root = ConfigSection()

    override fun load() {
        CatchException.catch("HOCON load: ${file.name}") {
            if (!file.exists()) file.createNewFile()

            val config = ConfigFactory.parseFile(file)
            val map = mutableMapOf<String, Any?>()

            config.entrySet().forEach {
                map[it.key] = it.value.unwrapped()
            }

            root = ConfigSection(map)
        }
    }

    override fun save() {
        CatchException.catch("HOCON save: ${file.name}") {
            val sb = StringBuilder()

            root.raw().forEach { (key, value) ->
                sb.append("$key = $value\n")
            }

            file.writeText(sb.toString())
        }
    }

    fun get(): ConfigSection = root

}