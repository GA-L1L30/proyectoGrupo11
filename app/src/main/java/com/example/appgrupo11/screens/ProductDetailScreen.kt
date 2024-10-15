package com.example.appgrupo11.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.composables.ExpandableList
import com.example.appgrupo11.composables.PrimaryButton
import com.example.appgrupo11.data.ExpandableListItem
import com.example.appgrupo11.data.getProductDescriptionList
import com.example.appgrupo11.ui.theme.SecondaryColorButton

@Composable
fun ProductDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Product Detail", containerColor = Color.White, navigationIcon = { Icon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            ) },
                navController = navController
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize(),
        ) {
            ProductDetailContent()
        }
    }
}

@Composable
fun ProductDetailContent() {
    Column {
        ProductImageContainer()
        ProductTitle()
        ProductPriceContainer()
        ProductDescription()
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = "Add to Basket", onClick = { /*TODO*/ })
    }

}

@Composable
fun ProductImageContainer() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = SecondaryColorButton,
            shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)
        )
        .padding(top = 10.dp, start = 25.dp, end = 25.dp, bottom = 25.dp)
        , horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.IosShare, contentDescription = null)
            }
        }
        AsyncImage(model = "https://s3-alpha-sig.figma.com/img/3834/f4b9/c7c2628935f610ab8527d0b21e102632?Expires=1729468800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=aVecUSfctTJ5dNWLsQnmT2lC~P6NUIlJJy7F3Q5njv0QNMuJ5bb6BC-KNU8GRUZEO63lM1aLuODeuG3UOyxT-eEfTm1kO~uOeU7TB7uZ3FiqiXN68azO9s3oRfIKd1NUSturstilTohK4gTv0Iuegf~d-3oDQint8zeZt7-dkUsnZhb2xccNS8m0tQV5YVFFrNqhFjfP7K1c16wVCvhtTxJ6UIeVa6h4vWNTfh3voFhszuPUrCUcSsx--kLs85VOxqf7iKwQd~q0x38EyJwPIyfxJM8c~HVQyWtuWci6GJ6xVVMEFaxUJSlgqO1q4rSKIx5F0mIdi4aVijoYfUgwvg__", contentDescription = null, modifier = Modifier.size(width = 330.dp, height = 200.dp))
        DotsExample()
    }
}
@Composable
fun DotsExample() {
    var currentIndex by remember { mutableStateOf(0) } // Índice del punto activo

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val itemCount = 3

        for (index in 0 until itemCount) {
            val isSelected = index == currentIndex
            Button(
                onClick = { currentIndex = index },
                modifier = Modifier
                    .size(width = if(isSelected) 25.dp else 12.dp, height = 12.dp)
                    .padding(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if(isSelected) Color(0xFF53B175) else Color.Gray)
            ) {
                Text(
                    text = "",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ProductTitle() {
    Column(modifier = Modifier.padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = "Naturel Red Apple",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "1kg, Price", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight(600))
            }
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null, tint = Color.Gray)
        }
    }
}

@Composable
fun IncreaseQuantityButton() {
    var quantity by rememberSaveable { mutableIntStateOf(1) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Remove, contentDescription = null, tint = Color.Gray)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.border(0.5.dp, Color.LightGray, RoundedCornerShape(18.dp))) {
            Text(text = quantity.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp))
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = null, tint = Color(0xFF53B175))
        }
    }
}

@Composable
fun ProductPriceContainer() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 20.dp, start = 5.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IncreaseQuantityButton()
        ProductPrice()
    }
}

@Composable
fun ProductPrice() {
    Column {
        Text(
            text = "$4.99",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}

@Composable
fun ProductDescription() {
    val items: List<ExpandableListItem> = getProductDescriptionList()
    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
        ExpandableList(modifier = Modifier.padding(top = 30.dp), items = items)
    }
}



