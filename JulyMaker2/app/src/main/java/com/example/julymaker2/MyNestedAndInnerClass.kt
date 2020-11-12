package com.example.julymaker2

class MyNestedAndInnerClass {

    private val one = 1

    private fun returnOne(): Int{
        return one
    }

    //Clase anidada (Nested Class)
    class MyNestedClass{
        private val two = 2

        fun sum (first:Int, second:Int):Int{
            return first + second
        }
    }

    //Clase interna (Inner Class)
    inner class MyInnerClass{
        fun sumTwo (number:Int):Int{
            return number + one + returnOne()
        }
    }
}