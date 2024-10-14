package com.example.appgrupo11.screens.cart


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.composables.PrimaryButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue




@Composable
fun CartScreen() {
    val cartViewModel: CartViewModel = viewModel()
    val totalAmount by cartViewModel.totalAmount


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){


        Text(
            text = "My Cart",
            fontSize = 24.sp,
        )


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ){

            items(cartViewModel.cartItems.size){ index ->
                val item = cartViewModel.cartItems[index]
                CartItemRow(item = item, cartViewModel = cartViewModel)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }


        PrimaryButton ("\"Go to Checkout  \$${String.format("%.2f", totalAmount)}\""
            ,onClick = { /*Accion de ir a Checkot*/ })
    }




}


@Composable
fun Divider(modifier: Modifier) {


}


@Composable
fun CartItemRow(item: Any, cartViewModel: CartViewModel) {


}


