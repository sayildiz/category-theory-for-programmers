fun <T> id(x: T): T = x

fun <A, B, C> composition(f: (B) -> C, g: (A) -> B): (A) -> C = { a -> f(g(a)) }
infix fun <A, B, C> ((B) -> C).compose(g: (A) -> B): (A) -> C = { a -> this(g(a)) }

fun main() {
    val addTwo: (Int) -> Int = { it + 2 }
    val multiplyByThree: (Int) -> Int = { it * 3 }
    val composedFunction = addTwo compose multiplyByThree
    val addTwoComposeId = addTwo compose ::id
    val idComposeAddTwo: (Int) -> Int = { x: Int -> id(x) } compose addTwo

    println("==== Test if composition respects id function")
    println("addTwo ° id (5) = ${addTwoComposeId(5)}")
    println("id ° addTwo (5) = ${idComposeAddTwo(5)}")
    println()
    println("==== Test addTwo compose multByThree")
    println("addTwo ° multByThree (5) = ${composedFunction(5)}")
}