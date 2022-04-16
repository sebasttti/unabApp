package com.sebastianjoya.unabapp.model.local.dao

import androidx.room.*
import com.sebastianjoya.unabapp.model.Product

@Dao
interface ProductDAO {

    @Query("select * from Product")
    fun getAll():ArrayList<Product>

    @Query("select * from Product where `key`=:keyValue")
    fun getByKey(keyValue:Int):Product

    @Insert
    fun add(myProduct:Product)

    @Update
    fun update(myProduct:Product)

    @Delete
    fun delete(myProduct:Product)
}