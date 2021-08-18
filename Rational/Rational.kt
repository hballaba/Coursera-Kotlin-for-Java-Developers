import java.math.BigInteger

class Rational(
    var numerator: BigInteger,
    var denominator: BigInteger) //cannot be 0
    : Comparable<Rational>
{

    init {

        require(denominator != 0.toBigInteger())//if zero throws IllegalArgumentException
        if(denominator < BigInteger.ZERO){
            denominator *= -1.toBigInteger()
            numerator *= -1.toBigInteger()
        }


    }

    operator fun minus(o: Rational) : Rational {
        return Rational(
            numerator = this.numerator * o.denominator - o.numerator * this.denominator,
            denominator =  this.denominator * o.denominator)
    }

    operator fun plus(o: Rational) :Rational {
        return Rational(
            numerator = o.numerator * this.denominator + this.numerator * o.denominator,
            denominator =  this.denominator * o.denominator)
    }

    operator fun times(o: Rational) :Rational {
        return Rational(
            numerator = o.numerator * this.numerator,
            denominator =  this.denominator * o.denominator)
    }

    operator fun div(o: Rational) :Rational {
        return Rational(
            numerator = this.numerator * o.denominator,
            denominator =  o.numerator * this.denominator)
    }

    operator fun unaryMinus() :Rational = Rational(-numerator, denominator)

    override fun compareTo(o: Rational): Int {
        return (this.numerator * o.denominator).compareTo(o.numerator * this.denominator)
    }

    override fun equals(other: Any?): Boolean {
        if(other is Rational) {
            val diff1 = this.numerator.gcd(this.denominator)
            val diff2 = other.numerator.gcd(other.denominator)
            val first = Rational(this.numerator / diff1, this.denominator/ diff1)
            val second = Rational(other.numerator / diff2, other.denominator / diff2)
            return first.numerator == second.numerator &&
                   first.denominator == second.denominator
        }
        return false
    }

    override fun toString(): String {
        val diff = this.numerator.gcd(this.denominator)
        if(this.denominator / diff == 1.toBigInteger())
                return this.numerator.toString()
        return "${this.numerator/diff}/${this.denominator/diff}"
    }
}

infix fun Int.divBy(o: Int): Rational {
    return Rational(numerator = this.toBigInteger(), denominator = o.toBigInteger())
}
infix fun Long.divBy(o: Long): Rational {
    return Rational(numerator = this.toBigInteger(), denominator = o.toBigInteger())
}
infix fun BigInteger.divBy(o: BigInteger): Rational {
    return Rational(numerator = this, denominator = o)
}

fun String.toRational() :Rational {
    if('/' in this) {
        val numerator = this.substringBefore('/').toBigInteger()
        val denominator = this.substringAfter('/').toBigInteger()
        val diff = numerator.gcd(denominator)
        return Rational(numerator / diff, denominator / diff)
    }
    return Rational(this.toBigInteger(), BigInteger.ONE)
}

fun main() {


    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println("dif = $difference")
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}
