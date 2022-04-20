package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.Product

class ProductDetailActivityViewModel:ViewModel() {
    var product = Product(name = "",value = "",description = "")
}