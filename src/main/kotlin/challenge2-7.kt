import kotlin.time.measureTime

private fun fibImperativeFor(n: Int): Long {
    //Throws IllegalArgumentException if false
    require (n < 0) {"Input must be non-negative"}
    if (n <= 1) return n.toLong()

    var f0: Long = 0
    var f1: Long = 1

    for (i in 2..n) {
        val fib = f0 + f1
        f0 = f1
        f1 = fib
    }

    return f1
}

// 1. Memoize function
private fun <A, B> memoize(f: (A) -> B): (A) -> B {
    val cache = mutableMapOf<A, B>()

    return { input ->
        cache.getOrPut(input) {
            f(input)
        }
    }

}

private fun testMemoizeOnFib() {
    val f = memoize(::fibImperativeFor)
    println(measureTime {
        f(1000)
    })
    println(measureTime {
        f(2000)
    })
    println(measureTime {
        f(1000)
    })
    println(measureTime {
        f(2000)
    })
}

fun main() {
    testMemoizeOnFib()
}
