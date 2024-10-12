package com.example.appgrupo11.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.data.Category
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.example.appgrupo11.R


class FindProductosViewModel: ViewModel(){
    private var _loading = mutableStateOf(false)
    val loading: State <Boolean> get () = _loading
    private var _allCategories = mutableStateOf<List<Category>>(emptyList())
    val allCategories: State<List<Category>> = _allCategories

    private var _filteredCategories = mutableStateOf<List<Category>>(emptyList())
    val filteredCategories: State<List<Category>> get()=_filteredCategories

    private var _selectedCategory = mutableStateOf<Category?>(null)
    val selectedCategory: MutableState<Category?> get() = _selectedCategory

    init{
        fetchCategories()
    }

    private fun fetchCategories(){
        _loading.value = true
        val categories = listOf(

            Category(
                imageRes = R.drawable.fruitsvegetables,
                title = "Frash Fruits & Vegetables",
                backgroundColor = Color(0x1A53B175)
            ),

            Category(
                imageRes = R.drawable.oil,
                title = "Cooking Oil & Ghee",
                backgroundColor = Color(0x1AF8A44C)
            ),

            Category(
                imageRes = R.drawable.meat,
                title = "Meat & Fish",
                backgroundColor = Color(0x40F7A593)
            ),

            Category(
                imageRes = R.drawable.bakery,
                title = "Bakery & Snacks",
                backgroundColor = Color(0x40D3B0E0)
            ),

            Category(
                imageRes = R.drawable.eggs,
                title = "Dairy & Eggs",
                backgroundColor = Color(0x40FDE598)
            ),

            Category(
                imageRes = R.drawable.beverages,
                title = "Beverages",
                backgroundColor = Color(0x40B7DFF5)
            ),

            Category(
                imageRes = R.drawable.fruitsvegetables,
                title = "Frash Fruits & Vegetables",
                backgroundColor = Color(0x26836AF6)
            ),

            Category(
                imageRes = R.drawable.oil,
                title = "Cooking Oil & Ghee",
                backgroundColor = Color(0x26D73B77)
            )
        )
        _allCategories.value = categories
        _filteredCategories.value = categories
        _loading.value = false
    }

    fun selectCategory(category:Category){
        _selectedCategory.value = category
        filterCategoryBySelected(category)
    }

    private fun filterCategoryBySelected(selected: Category){
        _filteredCategories.value = _allCategories.value.filter{
            category -> category.title == selected.title
        }
    }

}