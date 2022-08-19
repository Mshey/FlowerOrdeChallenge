package barok.emese.flowerorderchallenge.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import barok.emese.flowerorderchallenge.presentation.ui.theme.DarkerLightPurple

@Composable
fun SingleSelectDialog(
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    submitButtonText: String,
    onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {

    val selectedOption = remember { mutableStateOf(defaultSelected) }

    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = title)

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(modifier = Modifier.height(150.dp)) {
                        items(optionsList) {
                            MyRadioButton(it, optionsList[selectedOption.value]) { selectedValue ->
                                selectedOption.value = optionsList.indexOf(selectedValue)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            onSubmitButtonClick.invoke(selectedOption.value)
                            onDismissRequest.invoke()
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(backgroundColor = DarkerLightPurple)
                    ) {
                        Text(text = submitButtonText)
                    }
                }
            }
        }
    }
}