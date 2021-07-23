package nicestring

fun String.isNice(): Boolean {
    var count = 0
    val str = this

    val one = listOf("bu", "ba", "be")
    val two = "aeiou"

    var countTmp = one.
    map {str.contains(it)} .
    count {it == true}
    count += if (countTmp > 0)  0 else 1

    val list = str.toList()
    countTmp = list.map{two.contains(it)}.
    count {it == true}
    count += if (countTmp >= 3)  1 else 0

    val pair = list.zipWithNext()
    countTmp = pair.map {it.first == it.second}.count{it == true}
    count += if (countTmp > 0)  1 else 0

    return if(count >= 2) true else false
}