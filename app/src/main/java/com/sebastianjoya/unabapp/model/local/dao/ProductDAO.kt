package com.sebastianjoya.unabapp.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sebastianjoya.unabapp.model.entity.Product


@Dao
interface ProductDAO {

    @Query("select * from Product")
    fun getAll(): List<Product>

    @Query("select * from Product where `key`=:keyValue")
    fun getByKey(keyValue:Int): Product

    @Insert
    fun add(myProduct: Product)

    @Update
    fun update(myProduct: Product)

    @Delete
    fun delete(myProduct: Product)
}