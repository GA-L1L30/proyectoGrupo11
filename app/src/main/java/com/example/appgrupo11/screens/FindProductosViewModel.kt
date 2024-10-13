package com.example.appgrupo11.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.data.Category
import androidx.compose.runtime.State
import com.example.appgrupo11.R
import com.example.appgrupo11.ui.theme.AppColors


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
                backgroundColor =  AppColors.Green
            ),

            Category(
                imageRes = R.drawable.oil,
                title = "Cooking Oil & Ghee",
                backgroundColor = AppColors.Orange
            ),

            Category(
                imageRes = R.drawable.meat,
                title = "Meat & Fish",
                backgroundColor = AppColors.Red
            ),

            Category(
                imageRes = R.drawable.bakery,
                title = "Bakery & Snacks",
                backgroundColor = AppColors.Violet
            ),

            Category(
                imageRes = R.drawable.eggs,
                title = "Dairy & Eggs",
                backgroundColor = AppColors.Yellow
            ),

            Category(
                imageRes = R.drawable.beverages,
                title = "Beverages",
                backgroundColor = AppColors.Blue
            ),

            Category(
                imageRes = R.drawable.fruitsvegetables,
                title = "Frash Fruits & Vegetables",
                backgroundColor = AppColors.DarkViolet
            ),

            Category(
                imageRes = R.drawable.oil,
                title = "Cooking Oil & Ghee",
                backgroundColor = AppColors.Pink
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