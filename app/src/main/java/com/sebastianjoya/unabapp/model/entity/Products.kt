package com.sebastianjoya.unabapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sebastianjoya.unabapp.model.ProductStatus
import java.io.Serializable

@Entity()
class Product(
    @PrimaryKey(autoGenerate = true)
    @JvmField @Exclude
    @Expose(serialize = false,deserialize = false)
    var key:Int?=null,
    @Ignore
    @JvmField @Exclude
    @Expose(serialize = false,deserialize = false)
    var id:String="",
    @ColumnInfo(name= "name") //Para cambiar el nombre de la columna
    var name: String="",
    var value: String="",
    var description: String="",
    @JvmField @PropertyName("url_image")
    @SerializedName("url_image")
    var urlImage: String = "https://img2.freepng.es/20190523/ubz/kisspng-inventory-control-inventory-management-software-cl-5ce67fdca884a6.7345685315586098846903.jpg",
    var productStatus: ProductStatus = ProductStatus.AVAILABLE
):Serializable{
}