package com.example.calculatoroftime

class TimeCalculator(private val string: String) {

    private fun parseUntil(string: String, char: Char) : Pair<Int, String> {

        val parsedString = string.substringBefore(char).trim()
        val cutString = string.substringAfter(char).trim()


        return if (parsedString == string.trim()) Pair(0,string) else Pair(parsedString.toInt(),cutString)
    }

    private val parsedHour = parseUntil(string,'h')
    private val parsedMinutes = parseUntil(parsedHour.second, 'm')
    private val parsedSeconds = parseUntil(parsedMinutes.second, 's')

    val sum = {n1: Int, n2:Int -> n1 + n2}
    val dif = {n1: Int, n2:Int -> n1 - n2}

    val hours = parsedHour.first
    val minutes = parsedMinutes.first
    val seconds = parsedSeconds.first
    val summarySeconds = hours * 3600 + minutes * 60 + seconds

    fun operation(time: TimeCalculator, operator: (Int, Int) -> Int) : String {

        val summarySecondsResult = operator(this.summarySeconds,time.summarySeconds)

        val secondsResult = summarySecondsResult % 60
        val minutesResult = summarySecondsResult / 60 % 60
        val hoursResult = summarySecondsResult / 3600

       return if (hoursResult != 0) "${hoursResult}h${minutesResult}m${secondsResult}s"
            else if (minutesResult != 0) "${minutesResult}m${secondsResult}s"
            else "${secondsResult}s"

    }


}