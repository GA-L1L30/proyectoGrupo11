package com.example.appgrupo11.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgrupo11.composables.Search


@Composable
fun FindProductsScreen(){
    val viewModel: FindProductosViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp) ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(viewModel.loading.value) {
            CircularProgressIndicator()
        }else{
            Search("Search Store")

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viewModel.allCategories.value){
                        category ->
                    CategoryCard(
                        imageRes = category.imageRes,
                        title = category.title ,
                        backgroundColor = category.backgroundColor)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    imageRes: Int,
    title : String,
    backgroundColor: Color
){
    val darkBorder = backgroundColor.copy(alpha = 1f).darken()
    Card(
        modifier = Modifier
            .padding(top = 3.dp)
            .size(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, darkBorder, RoundedCornerShape(16.dp)),
        colors= CardDefaults.cardColors(containerColor = backgroundColor)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Color.darken(factor: Float = 0.2f):Color{
    return Color(
        red = this.red * (1- factor),
        green = this.green * (1 - factor),
        blue = this.blue * (1 - factor),
        alpha = this.alpha)
}