package util

import java.io.File

class FileReader {
    fun getFile(fileName: String): File {
        return File("src/main/resources/$fileName")
    }
}