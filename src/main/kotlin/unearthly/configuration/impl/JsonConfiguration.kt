package unearthly.configuration.impl

import kotlinx.serialization.json.*
import unearthly.configuration.Configuration
import unearthly.exception.CatchException
import java.io.File

class JsonConfiguration(override val file: File) : Configuration {

    private var json = JsonObject(emptyMap())

    override fun load() {
        CatchException.catch("Json load: ${file.name}") {
            if (!file.exists()) file.createNewFile()
            json = Json.parseToJsonElement(file.readText()).jsonObject
        }
    }

    override fun save() {
        CatchException.catch("Json save: ${file.name}") {
            file.writeText(
                Json { prettyPrint = true }.encodeToString(JsonObject.serializer(), json)
            )
        }
    }

    fun get(path: String): JsonElement? = json[path]

}