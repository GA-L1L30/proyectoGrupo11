import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(onNavigateToHome: () -> Unit) {

    var selectedZone by remember { mutableStateOf("") }
    var expandedZone by remember { mutableStateOf(false) }

    var selectedArea by remember { mutableStateOf("") }
    var expandedArea by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .height(170.dp)
                    .width(225.dp)
            )
            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Select Your Location",
                fontSize = 24.sp,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "Switch on your location to stay in tune with\n" +
                        "what’s happening in your area",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Dropdown de Zonas
            ExposedDropdownMenuBox(
                expanded = expandedZone,
                onExpandedChange = { expandedZone = it }
            ) {
                OutlinedTextField(
                    value = selectedZone.ifEmpty { "Select your zone" },
                    onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    label = {
                        Text(
                            text = "Your Zone",
                            fontSize = 14.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Arrow"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Transparent,
                        containerColor = Color.Transparent,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Black,

                    ),
                    singleLine = true
                )

                ExposedDropdownMenu(
                    expanded = expandedZone,
                    onDismissRequest = { expandedZone = false }
                ) {
                    listOf("CABA", "GBA").forEach { zone ->
                        DropdownMenuItem(
                            text = { Text(zone) },
                            onClick = {
                                selectedZone = zone
                                expandedZone = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown de Áreas
            ExposedDropdownMenuBox(
                expanded = expandedArea,
                onExpandedChange = { expandedArea = it }
            ) {
                OutlinedTextField(
                    value = selectedArea.ifEmpty { "Select your area" },
                    onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    label = {
                        Text(
                            text = "Your Area",
                            fontSize = 14.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Arrow"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Transparent,
                        containerColor = Color.Transparent,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Black,

                    ),
                    singleLine = true
                )

                ExposedDropdownMenu(
                    expanded = expandedArea,
                    onDismissRequest = { expandedArea = false }
                ) {
                    listOf("CABALLITO", "CABA", "AVELLANEDA").forEach { area ->
                        DropdownMenuItem(
                            text = { Text(area) },
                            onClick = {
                                selectedArea = area
                                expandedArea = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Submit",
                onClick = {
                    onNavigateToHome()
                }
            )
        }
    }
}



