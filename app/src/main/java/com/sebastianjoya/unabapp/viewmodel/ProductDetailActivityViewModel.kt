package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.Product

class ProductDetailActivityViewModel:ViewModel() {
    var product = Product(name = "",value = "")
}