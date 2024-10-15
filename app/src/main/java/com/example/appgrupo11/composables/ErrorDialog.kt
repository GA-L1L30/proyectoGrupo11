package com.example.appgrupo11.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ErrorDialog(onDismiss: () -> Unit, navController: NavController) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Column() {
                Row {
                    IconButton(onClick = { onDismiss() }) {
                        Icon(Icons.Filled.Close, contentDescription = null)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(model = "https://s3-alpha-sig.figma.com/img/d63c/5d32/7c2fbf3c2c9fe3b5c406b2c89dba8b85?Expires=1730073600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=JolYYOJ7zFQV-ttra7Jw-MYsIoSzQayAH15CfkdgPX3WeM6ZQ11cdECg~JLZ8xFhLtb5IriX9gzwPwROnmJpGiCwClh1bdCTJvWA~1-Tfsz~E92vKt2LsU8u9FmWX3fgMC5RwWSV73ds86diB29AjlY-kJZ9F~w4I6mue29ZWezo4ZLCy2MKd~216iTwl-uFVWfiF6Q9-SXz7URLSpBAaK1PPEXSs6-cbPfBMVj6yUWK~vgiasa10uLK5ecovpgEU3Yy1jfxs0T9PxTh2hKa2vYbxl1AVxr1Mad7nParzCn1ycDszHmy0AnVo07vSwNXtLoeYoCr4UgQDSgHGqkiVQ__", contentDescription = null, modifier = Modifier.size(width = 330.dp, height = 200.dp))
                    Text(text = "Oops! Order Failed", fontWeight = FontWeight(500), fontSize = 26.sp)

                }
            }
        },
        text = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Something went tembly wrong.")
            }
        },
        confirmButton = {
            Column {
                PrimaryButton(text = "Please Try again", onClick = { onDismiss() })
                GrayButton(text = "Back to home", onClick = { navController.navigate("home")}, textColor = Color.Black, containerColor = Color.Transparent)
            }
        },
        containerColor = Color.White
    )
}
