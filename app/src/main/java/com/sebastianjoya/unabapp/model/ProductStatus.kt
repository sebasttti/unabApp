package com.sebastianjoya.unabapp.model

enum class ProductStatus(val value:Int) {

    AVAILABLE(1), SENT(2), SOLD(3);

    fun description():String{
        return when(this){
            AVAILABLE->"Producto Disponible"
            SENT->"Producto Enviado"
            SOLD->"Producto Vendido"
        }
    }
}