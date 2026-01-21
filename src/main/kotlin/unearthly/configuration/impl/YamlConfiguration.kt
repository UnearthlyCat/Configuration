package unearthly.configuration.impl

import org.yaml.snakeyaml.Yaml
import unearthly.ConfigSection
import unearthly.configuration.Configuration
import unearthly.exception.CatchException
import java.io.File

class YamlConfiguration(override val file: File) : Configuration {

    private val yaml = Yaml()
    private var root = ConfigSection()

    override fun load() {
        CatchException.catch("Yaml load: ${file.name}") {
            if (!file.exists()) file.createNewFile()

            val data = yaml.load<Map<String, Any?>>(file.readText()) ?: emptyMap()
            root = ConfigSection(data.toMutableMap())
        }
    }

    override fun save() {
        CatchException.catch("Yaml save: ${file.name}") {
            file.writeText(yaml.dump(root.raw()))
        }
    }

    fun get(): ConfigSection = root

}