package com.example.appgrupo11.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.navigation.NavController
import com.example.appgrupo11.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.appgrupo11.data.Product
import com.example.appgrupo11.ui.theme.AppColors
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val cartViewModel: CartViewModel = viewModel()
    val totalAmount by cartViewModel.totalAmount

    //Para controlar si el BottomSheet esta desplegado o no
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 1.dp)
        ){
            
            items(cartViewModel.cartItems.size){ index ->
                val item = cartViewModel.cartItems[index]
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                CartItemRow(item = item, cartViewModel = cartViewModel)
            }
        }
        PrimaryButton(text = "Go to Checkout $${"%.2f".format(totalAmount)}", onClick = {
                coroutineScope.launch {
                    showBottomSheet = true
                    sheetState.show()
                }
        })
    }


    // ModalBottomSheet para mostrar el detalle de checkout
    if(showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                    showBottomSheet = false
                }
            },
            sheetState = sheetState,

            ) {
            CheckoutContent(totalAmount = totalAmount)
        }
    }
}

@Composable
fun Divider(modifier: Modifier) {
    //Divisor de elementos del carrito
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(vertical = 1.dp)
            .background(Color.Gray)
    )

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
        IconButton(onClick = {onDecrease()
        }) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Decrease"
            )
        }

        //Mostrar cantidad actual
        Text(text = "$quantity", fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))

        IconButton(onClick = {
            onIncrease()
        }){
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

            Box(modifier = Modifier.fillMaxWidth().height(20.dp)){
                Text(
                    text =  "$${item.price}",
                    fontSize = 16.sp,
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

@Composable
fun CheckoutContent(totalAmount: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Checkout", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Delivery", fontSize = 16.sp)
            Text("Select Method", fontSize = 16.sp, color = Color.Gray)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Payment", fontSize = 16.sp)
            Text("Select Method", fontSize = 16.sp, color = Color.Gray)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Promo Code", fontSize = 16.sp)
            Text("Pick discount", fontSize = 16.sp, color = Color.Gray)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total Cost", fontSize = 16.sp)
            // Usar el valor de totalAmount directamente
            Text("\$${"%.2f".format(totalAmount)}", fontSize = 16.sp, color = Color.Black)
        }

        PrimaryButton(text = "Place Order", onClick = {
            // Acción de confirmar el pedido
        })
    }
}



