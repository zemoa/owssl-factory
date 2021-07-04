package zemoa

object SystemInfo {
    fun javaVersion(): String {
        return System.getProperty("java.version")
    }

    fun javafxVersion(): String {
        return System.getProperty("javafx.version")
    }
}
