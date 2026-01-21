package unearthly

class ConfigSection(private val map: MutableMap<String, Any?> = mutableMapOf()) {

    fun getString(path: String, def: String? = null): String? = map[path] as? String ?: def

    fun getInt(path: String, def: Int = 0): Int = (map[path] as? Number)?.toInt() ?: def

    fun getBoolean(path: String, def: Boolean = false): Boolean = map[path] as? Boolean ?: def

    fun getDouble(path: String, def: Double = 0.0): Double = (map[path] as? Number)?.toDouble() ?: def

    fun getSection(path: String): ConfigSection = ConfigSection(map.getOrPut(path) { mutableMapOf<String, Any?>() } as MutableMap<String, Any?>)

    fun set(path: String, value: Any?) {
        map[path] = value
    }

    fun raw(): Map<String, Any?> = map
}