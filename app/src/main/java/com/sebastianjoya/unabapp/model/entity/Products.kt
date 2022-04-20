package com.sebastianjoya.unabapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebastianjoya.unabapp.model.ProductStatus
import java.io.Serializable

@Entity()
class Product(
    @PrimaryKey(autoGenerate = true)
    var key:Int?=null,
    @ColumnInfo(name= "name") //Para cambiar el nombre de la columna
    var name: String,
    var value: String,
    var description: String,
    var urlImage: String = "https://img2.freepng.es/20190523/ubz/kisspng-inventory-control-inventory-management-software-cl-5ce67fdca884a6.7345685315586098846903.jpg",
    var productStatus: ProductStatus = ProductStatus.AVAILABLE
):Serializable{
}