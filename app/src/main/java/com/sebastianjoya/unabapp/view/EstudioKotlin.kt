package com.sebastianjoya.unabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sebastianjoya.unabapp.R

class EstudioKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudio_kotlin)

        //Variables - constantes - tipos de datos

        /*
        Las variables se declaran con var
         */

        var name = "Sebastian"
        var height = 1.7
        var professional = true
        var age = 34

        /*
         * Las constantes se declaran con val
         */

        val fiancee = "Marly Nova"

        /*Datos fuertemente tipados */
        var brand:String = "Huawei"

        //Estructuras de control
        if(brand == "Huawei"){
            println("Tu pc es de marca Huawei")
        }

        /**
         * Obtener variables desde el xml de Strings
         */
        val nameString:String = getString(R.string.name)

        /**
        * When es como switch
         */
        var typeUser = 1
        when(typeUser){
            1 -> { println("Opción 1")}
            in 2..4 -> {println("Opción 2 a 4")}
            else -> {println("Opción Diferente")}
        }

        /**
         * Estructuras de datos
         */

        //Una lista es inmutable, un array no

        var productos:ArrayList<String> = arrayListOf()

        productos.add("Monitor")
        productos.add("Pantalla")
        productos.add("Teclado")

        val teclado = productos[2]
        val pantalla = productos.get(2)

        //prpductos.removeAt(2) para borrar el elemento 2

        /**
         * foreach
         */
        productos.forEach{
            println(it)
        }

        //Maps o Diccionarios
        //Tipo de datos desconocido : Any
        val mascotas = mutableMapOf<String,String>("primera" to "Maya")
        mascotas["segunda"] = "Kimi"
        mascotas.put("tercera","Molly")

        for (prod: String in productos){}






    }
}