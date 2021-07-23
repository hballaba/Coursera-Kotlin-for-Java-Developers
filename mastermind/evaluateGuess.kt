package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    if(secret.length != 4 || guess.length != 4)
        return Evaluation(0, 0)
    var rightPosition = 4
    var wrongPosition = 0
    var str1 = ""
    var str2 = ""
    for(i in 0..3){
        if(secret[i] != guess[i]){
            str1 += secret[i]
            str2 += guess[i]
            rightPosition--;
        }
    }
    for(i in 0 until str1.length){
//        println(str2.contains(str1[i]))
        if(str2.contains(str1[i])) {
            var t =str2.indexOf(str1[i])
            str2 = str2.removeRange(t, t+1)
            wrongPosition++
        }
    }
//    println("str1 $str1| str2 $str2")

    return Evaluation(rightPosition, wrongPosition)
}
