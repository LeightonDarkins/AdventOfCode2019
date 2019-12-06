package util

open class InputReader {
    open fun readInt(): Int {
        println("Enter an Int:")

        return readLine()!!.toInt()
    }
}