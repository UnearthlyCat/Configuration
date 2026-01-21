# Configuration
Framework for easy configurations creation

The goal of this project is to provide a simple and unified way to work with
configuration files

# Why?

Most configuration libraries either:
- serialize JVM classes into config files (`!!MyClass`)
- are hard to control or unsafe
- are tied to a specific platform

## Supported formats

Currently supported:

- YAML (`YamlConfiguration`)
- JSON (`JsonConfiguration`)
- TOML (`TomlConfiguration`)
- HOCON (`HoconConfiguration`)
- Properties (`PropertiesConfiguration`)

### Yaml exemple

```kotlin
val config = YamlConfig(File("config.yml"))
config.load()

val root = config.get()
root.set("server.port", 8080)
root.set("debug", true)

config.save()
```
