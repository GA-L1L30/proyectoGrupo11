package com.example.appgrupo11.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.Input
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.GrayButton
import com.example.appgrupo11.data.AccountOption
import com.example.appgrupo11.data.getAccountOptions
import com.example.appgrupo11.ui.theme.AppColors
import com.example.appgrupo11.ui.theme.LightGrayEmail

@Composable
fun AccountScreen(
    accountOptions: List<AccountOption> = getAccountOptions(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 20.dp), verticalArrangement = Arrangement.SpaceBetween) {
        UserInformation()
        AccountOptionList(accountOptions, isDarkMode, onToggleDarkMode)
        Box(modifier = Modifier.padding(10.dp)) {
            GrayButton(
                text = "Log Out",
                leftIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Input,
                        tint = Color(0xFF53B175),
                        contentDescription = null
                    )
                },
                onClick = {},
                textColor = AppColors.LightGreen,
                containerColor = if(isDarkMode) AppColors.DarkViolet else AppColors.Gray
            )
        }
    }
}

@Composable
fun AccountOptionList(
    options: List<AccountOption>,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    Column {
        options.forEachIndexed { index, accountOption ->
            if (index == 0) {
                HorizontalDivider()
            }
            AccountOptionItem(accountOption, isDarkMode)
        }
        // Usar el estado y la función de cambio para DarkModeButton
        DarkModeButton(isDarkMode = isDarkMode, onChange = onToggleDarkMode)
        HorizontalDivider()
    }
}

@Composable
fun AccountOptionItem(item: AccountOption, isDarkMode: Boolean) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = if(isDarkMode) AppColors.DarkViolet else Color.White
        ),
        headlineContent = { Text(item.title, fontWeight = FontWeight(weight = 600)) },
        leadingContent = {
            Icon(
                item.leftIcon,
                contentDescription = null,
            )
        },
        trailingContent = {
            Icon(
                Icons.AutoMirrored.Outlined.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
        },
        modifier = Modifier.clickable {  }
    )
    HorizontalDivider()
}

@Composable
fun UserInformation() {
    Row(modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.padding(end = 20.dp)){
            Image(
                painter = painterResource(id = R.drawable.accountimage),
                contentDescription = "Imagen circular",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Column {
            Row {
                Text("Afsar Hossen", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(end = 5.dp))
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null, tint = Color(0xFF53B175))
            }
            Text("Imshuvo97@gmail.com", color = LightGrayEmail)
        }
    }
}

@Composable
fun DarkModeButton(isDarkMode: Boolean, onChange: (Boolean) -> Unit) {
    ListItem(colors = ListItemDefaults.colors(
        containerColor = if(isDarkMode) AppColors.DarkViolet else Color.White
    ), headlineContent = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.dark_mode), fontWeight = FontWeight(weight = 500), modifier = Modifier.padding(start = 40.dp))
            Switch(checked = isDarkMode, onCheckedChange = { onChange(it) })
        }
    })
}
