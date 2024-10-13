package com.example.appgrupo11.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.GrayButton
import com.example.appgrupo11.data.AccountOption
import com.example.appgrupo11.data.getAccountOptions
import com.example.appgrupo11.ui.theme.LightGrayEmail

@Composable
fun AccountScreen(accountOptions: List<AccountOption> = getAccountOptions()) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 20.dp), verticalArrangement = Arrangement.SpaceBetween) {
        UserInformation()
        AccountOptionList(accountOptions)
        GrayButton(text = "Log Out", leftIcon = { Icon(imageVector = Icons.AutoMirrored.Outlined.Input, tint = Color(0xFF53B175), contentDescription = null) }, onClick = {})
    }


}

@Composable
fun AccountOptionList(options: List<AccountOption>) {
    Column {
        options.forEachIndexed { index, accountOption ->
            if (index == 0) {
                HorizontalDivider()
            }
            AccountOptionItem(accountOption)
        }
        DarkModeButton(onChange = {})
        HorizontalDivider()
    }
}

@Composable
fun AccountOptionItem(item: AccountOption) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.White
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
    )
    HorizontalDivider()
}

@Composable
fun UserInformation() {
    Row(modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.padding(end = 20.dp)){
            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data("https://s3-alpha-sig.figma.com/img/8d96/4bb3/075c91474d08dfc85b7f95eee4fa0ae6?Expires=1729468800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SITTp3d8~LJW7dmVJX~HMdTQIEYpCaLxyUxB5bCCPhUYpnd1ey6oJtu7sp526BuXVCRJsZx44J7XfH4FQ4C0awPkEQTVpdj6qZOJQzA8PY8KSlWBZTzZzSv1ZBHf17UwVSKA3jCfOytR4loSm7T8~e~Lnp99zL9-BKEiIUV5AUNVofHMThrNpf6X13FKbAeRmFMFTuf8f~0O3CuBsTiETX9v-6hXZlzYHTMBHim27j5Do9ODNt5nhKlabfbvjvHv0CCWPBIfBOxJgVwsjOmZGjaxOClddpO2zIYnhpTrRWsdb3vXnVgM5-YmHi2AEsO5r33s~lBdRXgTC4qI2M6L8g__")
                .transformations(CircleCropTransformation()).build(),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp))
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
fun DarkModeButton(onChange: () -> Unit) {
    ListItem(colors = ListItemDefaults.colors(
        containerColor = Color.White
    ),headlineContent = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.dark_mode), fontWeight = FontWeight(weight = 500), modifier = Modifier.padding(start = 40.dp))
            Switch(checked = false, onCheckedChange = { onChange() })
        }
    })


}