package day5

data class Instruction(
    val opCode: Int,
    val firstParamMode: Int,
    val secondParamMode: Int,
    val thirdParamMode: Int
)