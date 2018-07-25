package com.wangxingxing.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.widgets.Rectangle

/**
 * 基础语法
 */
class BasicGrammarActivity : AppCompatActivity() {

    //顶层变量
    val PI = 3.14
    var y = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_grammar)

        init()
    }

    private fun init() {
        print("sum of 3 and 5 is ")
        println(sum1(3, 5))

        println("sum of 19 and 23 is ${sum2(19, 23)}")

        printSum(-1, 8)
        printSum2(10, 66)

        definedVal()

        definedVar()

        stringTemplate()

        println("max of 0 and 42 is ${maxOf(0, 42)}")

        println("max of 0 and 42 is ${maxOf2(0, 42)}")

        printProduct("6", "7")
        printProduct("a", "7")
        printProduct("a", "b")

        printProduct2("6", "7")
        printProduct2("a", "7")
        printProduct2("a", "b")

        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))

        forOfLoop()
        forOfLoop2()

        whileOfLoop()

        println(describe(1))
        println(describe("Hello"))
        println(describe(1000L))
        println(describe(2))
        println(describe("other"))

        rangeIn()
        rangeOut()
        rangeFor()
        rangeFor2()

        forOfList()
    }

    //带有两个Int参数、返回Int的函数
    private fun sum1(a: Int, b: Int): Int {
        return a + b
    }

    //将函数表达式作为函数体、返回值类型自动推断的函数
    private fun sum2(a: Int, b: Int) = a + b

    //函数返回无意义的值
    private fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    //Unit返回类型可以省略
    private fun printSum2(a: Int, b: Int) {
        println("sum of $a and $b is ${a + b}")
    }

    //只读局部变量
    fun definedVal() {
        //一次赋值（只读）的局部变量
        val a: Int = 1 //立即赋值
        val b = 2 //自动推断出Int类型
        //如果没有初始值类型不能省略明确赋值
        val c: Int
        c = 3
        println("a = $a, b = $b, c = $c")
    }

    //可变变量
    fun definedVar() {
        var x = 5 //自动推断出Int类型
        x += 1
        println("x = $x")
    }

    //使用字符串模板
    fun stringTemplate() {
        var a = 1
        //模板中的简单名称：
        val s1 = "a is $a"

        a = 2
        //模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        println(s2)
    }

    //使用条件表达式
    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    //使用if作为表达式
    fun maxOf2(a: Int, b: Int) = if (a > b) a else b

    //使用返回可空值的函数============================================================================
    fun parseInt(str: String): Int? {
        return str.toIntOrNull()
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)
        //直接使用x*y会导致编译错误
        if (x != null && y != null) {
            //在空检测后，x和y会自动转换为非空值（non-nullable）
            println(x * y)
        } else {
            println("either '$arg1' or '$arg2' is not a number")
        }
    }

    fun printProduct2(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)
        if (x == null) {
            println("Wrong number format int arg1:'$arg1'")
            return
        }
        if (y == null) {
            println("Wrong number format int arg1:'$arg2'")
            return
        }
        println(x * y)
    }

    //使用类型检测及自动类型转换=======================================================================
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //obj在该条件分支内自动转换成String
            return obj.length
        }
        return null
    }

    fun getStringLength2(obj: Any): Int? {
        if (obj !is String) return null
        //obj在这一分支自动转换为String
        return obj.length
    }

    fun getStringLength3(obj: Any): Int? {
        //obj在&&右边自动转换成String类型
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    fun printLength(obj: Any) {
        println("'$obj' string1 length is ${getStringLength(obj) ?: "...err, not a string"}")
        println("'$obj' string2 length is ${getStringLength2(obj) ?: "...err, not a string"}")
        println("'$obj' string3 length is ${getStringLength3(obj) ?: "...err, not a string"}")
    }

    //使用for循环====================================================================================
    fun forOfLoop() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
    }

    fun forOfLoop2() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    //使用while循环
    fun whileOfLoop() {
        val items = listOf("apple", "banana", "kiwifruit")
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    //使用when表达式
    fun describe(obj: Any): String =
            when (obj) {
                1          -> "One"
                "Hello"    -> "Greeting"
                is Long    -> "Long"
                !is String -> "Not a string"
                else       -> "Unknown"
            }

    //使用区间=======================================================================================
    //使用in运算符来检测某个数字是否在指定区间内
    fun rangeIn() {
        val x = 10
        val y = 9
        if (x in 1..y+1) {
            println("fits in range")
        }
    }

    //检测某个数字是否在指定区间外
    fun rangeOut() {
        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is out of valid list indices range too")
        }
    }

    //区间迭代
    fun rangeFor() {
        for (x in 1..5) {
            print(x)
        }
    }

    //数列迭代
    fun rangeFor2() {
        for (x in 1..10 step 2) {
            print(x)
        }
        println()
        for (x in 9 downTo 0 step 3) {
            print(x)
        }
    }

    //使用集合=======================================================================================
    //对集合进行迭代
    fun forOfList() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
    }

    //使用in运算符来判断集合内是否包含某实例
    fun whenOfList() {
        val items = setOf("apple", "banana", "kiwifruit")
        when {
            "orange" in items -> println("juicy")
            "apple" in items -> println("apple is fine too")
        }
    }

    //使用lambda表达式来过滤（filter）和映射（map）集合
    fun filterAndMapOfList() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }

    //创建基本类及其实例
    fun createEntity() {
        //不需要new关键字
        val rectangle = Rectangle()
        val triangle = Triple(3.0, 4.0, 5.0)
    }
}
