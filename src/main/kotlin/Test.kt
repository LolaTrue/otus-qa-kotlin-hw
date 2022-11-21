class Test : TestRunner {

    private fun Any.runMethodWithName(funName: String) {
        this::class.members
            .filter { it.name.startsWith(prefix = funName, ignoreCase = true) }
            .forEach {
                println("Start step: ${it.name}")
                it.call(this)
                println("End step: ${it.name}")
            }
    }

    override fun <T> runTest(steps: T, test: () -> Unit) {
        steps?.runMethodWithName("Before")

        println("Start test")
        test()
        println("End test")

        steps?.runMethodWithName("After")
    }
}

fun main() {
    val steps = Steps()
    val test = Test()

    test.runTest<Steps>(
        steps = steps,
        test =  ::testFunc
    )
}

fun testFunc() {
    println("Test")
}