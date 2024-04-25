package uk.ac.tees.mad.d3614099.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.data.SignupViewModel
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.screens.profile.ProfileData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    loginViewModel: SignupViewModel = viewModel(),
    userProfile: ProfileData = ProfileData(
        name = "John Doe",
        profileImage = 0
    )
) {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome ${userProfile.name}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null
                        )
                        Text(
                            text = userProfile.location,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape
                        )
                        .clickable {
                            ScreenRouter.navigateTo(Screen.ProfileScreen)
                        },
                    contentScale = ContentScale.Crop
                )
            }

            LazyColumn(modifier = Modifier.weight(1.0f)) {
                items(roomDataList) { room ->
                    RoomCard(room = room) {
                        // Navigate to Detail Screen on click
                        ScreenRouter.navigateToDetailScreen(room.hashCode())
                    }
                }
            }
        }
    }
}

@Composable
fun RoomCard(room: RoomData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                ImagePager(images = room.images)
            }

            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Location : ${room.city}",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Text(
                    text = "Duration : ${room.rentalDuration}",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                )
            }


            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = room.ownerImage),
                    contentDescription = "Owner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = room.ownerName,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp,
//                    fontWeight = FontWeight(700),
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Text(
                    text = room.price,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(images: List<Int>) {

    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(state = pagerState) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Room Image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        val indicatorDots = Modifier.align(Alignment.BottomCenter)

        Row(
            modifier =
            indicatorDots
                .padding(bottom = 8.dp)
        ) {
            for (i in images.indices) {
                val isSelected = pagerState.currentPage == i
                val color =
                    if (isSelected) MaterialTheme.colorScheme.inverseSurface else Color.LightGray
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        SignupViewModel(),
//        userProfile = ProfileData(
//            name = "John Doe",
//            profileImage = 0
//        )
//    )
//}