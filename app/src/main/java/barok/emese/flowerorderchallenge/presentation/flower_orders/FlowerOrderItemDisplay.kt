package barok.emese.flowerorderchallenge.presentation.flower_orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.presentation.ui.theme.LightPurple
import barok.emese.flowerorderchallenge.R

@Composable
fun FlowerOrderItemDisplay(
    flowerOrder: FlowerOrder,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = LightPurple,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Column(
            modifier = modifier.padding(start = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = flowerOrder.description,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.user_details, flowerOrder.userName),
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.price_details, flowerOrder.price),
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }

}