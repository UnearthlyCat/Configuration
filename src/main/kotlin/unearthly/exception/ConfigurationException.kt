package unearthly.exception

class ConfigurationException(
    message: String,
    cause: Throwable? = null
): RuntimeException(message, cause)