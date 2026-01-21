package unearthly.exception

object CatchException {

    fun catch(context: String, block: () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            handle(context, e)
        }
    }

    private fun handle(context: String, e: Exception) {
        println("§cError: $context")
        println("§c${e::class.simpleName}: ${e.message}")
        e.printStackTrace()
    }

}