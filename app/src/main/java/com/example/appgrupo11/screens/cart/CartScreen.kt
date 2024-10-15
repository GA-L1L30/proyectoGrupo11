package com.example.appgrupo11.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.composables.PrimaryButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.example.appgrupo11.ui.theme.AppColors


@Composable
fun CartScreen() {
    val cartViewModel: CartViewModel = viewModel()
    val totalAmount by cartViewModel.totalAmount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ){
            
            items(cartViewModel.cartItems.size){ index ->
                val item = cartViewModel.cartItems[index]
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                CartItemRow(item = item, cartViewModel = cartViewModel)
            }
        }

        //Boton para ir a checkout
        PrimaryButton ("\"Go to Checkout  \$${String.format("%.2f", totalAmount)}\""
            ,onClick = { /*Accion de ir a Checkot*/ })
    }


}


@Composable
fun QuantitySelector(
    quantity : Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
){

    //Controles de cantidad y precio
    Row(
        verticalAlignment =  androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = {/*Accion disminuir*/}) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Decrease"
            )
        }

        //Mostrar cantidad actual
        Text(text = "$quantity", fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))

        IconButton(onClick = {/*Accion para aumentar*/}){
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Increase",
                tint = AppColors.LightGreen
            )
        }
    }
}


@Composable
fun CartItemRow(item: Product, cartViewModel: CartViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =  8.dp)
    ){
        //Imagen del Producto
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop

        )

        Column(modifier = Modifier.weight(1f)) {
            //Nombre del producto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = item.title, fontSize = 18.sp)
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            }


            //Descripcion
            Text(text = item.description, fontSize = 14.sp,color = Color.Gray)

            Box( modifier = Modifier.fillMaxWidth().height(20.dp)){
                Text(
                    text =  "$${item.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp).align(Alignment.CenterEnd),
                    textAlign = TextAlign.End
                )
            }


            //Control de cantidad
            QuantitySelector(
                quantity = item.quantity,
                onIncrease = {cartViewModel.updateQuantity(item, item.quantity +1 ) },
                onDecrease = {cartViewModel.updateQuantity(item, item.quantity -1 ) },
            )


        }
    }
}
