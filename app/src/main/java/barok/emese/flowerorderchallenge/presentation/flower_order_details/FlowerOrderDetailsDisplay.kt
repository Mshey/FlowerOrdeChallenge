package barok.emese.flowerorderchallenge.presentation.flower_order_details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import barok.emese.flowerorderchallenge.R
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.presentation.SingleSelectDialog
import barok.emese.flowerorderchallenge.presentation.ui.theme.LightPurple
import barok.emese.flowerorderchallenge.presentation.ui.theme.DarkerLightPurple
import barok.emese.flowerorderchallenge.presentation.util.Convert

@Composable
fun FlowerOrderDetailsDisplay(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FlowerOrderDetailsViewModel = hiltViewModel()
) {
    // Dialog state Manager
    val dialogState = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.detail_page_title),
            modifier = modifier,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.width(10.dp))

        Card(
            backgroundColor = LightPurple,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = stringResource(id = R.string.id_details, viewModel.flowerOrder.value.id),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.description_details,
                        viewModel.flowerOrder.value.description
                    ),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.price_details,
                        viewModel.flowerOrder.value.price
                    ),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.user_details,
                        viewModel.flowerOrder.value.userName
                    ),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.deliver_to_details,
                        viewModel.flowerOrder.value.deliverTo
                    ),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.status_details,
                        viewModel.flowerOrder.value.orderStatus
                    ),
                    modifier = modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )

            }
        }

        Card(
            backgroundColor = LightPurple,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(50.dp), contentAlignment = Alignment.Center
            ) {
                if (viewModel.state.data != null) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            painter = painterResource(id = R.drawable.ic_location_distance),
                            contentDescription = null // decorative element
                        )
                        Text(
                            text = "${viewModel.state.data!!.second} ${viewModel.state.data!!.first}",
                            modifier = modifier.padding(10.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
                if (viewModel.state.isLoading) {
                    CircularProgressIndicator()
                }
                viewModel.state.error?.let { error ->
                    Text(
                        text = error,
                        modifier = modifier.padding(10.dp),
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        Button(
            onClick = {
                dialogState.value = true
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = DarkerLightPurple)
        ) {
            Text(text = stringResource(id = R.string.order_status_button))
        }

        if (dialogState.value) {
            val optionList = Convert.getNames<FlowerOrder.OrderStatus>()
            SingleSelectDialog(
                title = stringResource(id = R.string.order_status_dialog_title),
                optionsList = optionList,
                defaultSelected = optionList.indexOf(viewModel.flowerOrder.value.orderStatus.name),
                submitButtonText = stringResource(id = R.string.update_order_status_button),
                onSubmitButtonClick = {
                    // we can only change it on this screen, because for proper saving I would need a backend or local db
                    viewModel.flowerOrder.value.orderStatus =
                        FlowerOrder.OrderStatus.valueOf(optionList[it])
                },
                onDismissRequest = { dialogState.value = false }
            )
        }
    }

    BackHandler {
        navController.navigateUp()
    }
}