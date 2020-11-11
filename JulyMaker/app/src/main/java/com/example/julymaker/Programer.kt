package com.example.julymaker

class Programer(val name:String,
                var edad:Int,
                val languages:Array<Language>,
                val friend: Array<Programer>? = null) {

    enum class Language{
        KOTLIN,
        Cpp,
        JAVA,
        TODO
    }

    fun code(){
        for(language in languages){
            println("Estoy programando en $language")
        }
    }
}