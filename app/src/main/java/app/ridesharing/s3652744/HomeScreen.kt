package app.ridesharing.s3652744

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// COLORS

val RideBlue = Color(0xFF3159C9)
val RideBackground = Color(0xFFF5F7FC)
val RideText = Color(0xFF252B3B)
val RideGray = Color(0xFF858B99)

// RIDE MODEL

data class Ride(
    val pickup: String,
    val destination: String,
    val date: String,
    val time: String,
    val seats: Int,
    val cost: Int
)

// SAMPLE RIDES

val sampleRides = listOf(
    Ride(
        "Campus Main Gate",
        "City Railway Station",
        "22 Nov 2026",
        "09:30 AM",
        3,
        50
    ),
    Ride(
        "University Hostel",
        "Shopping Mall",
        "22 Nov 2026",
        "11:00 AM",
        2,
        40
    ),
    Ride(
        "Library Block",
        "Bus Station",
        "23 Nov 2026",
        "08:00 AM",
        1,
        30
    )
)

// MAIN SCREEN

@Composable
fun CampusRideHomeScreen(
    rides: List<Ride> = sampleRides,
    onFindRide: (String, String, String) -> Unit = { _, _, _ -> },
    onOfferRide: () -> Unit = {},
    onMyRides: () -> Unit = {}
) {

    var pickup by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var travelDate by remember { mutableStateOf("") }

    Scaffold(
        containerColor = RideBackground,

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Search, null)
                    },
                    label = {
                        Text("Find Rides")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onOfferRide,
                    icon = {
                        Icon(Icons.Default.AddCircle, null)
                    },
                    label = {
                        Text("Offer Ride")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onMyRides,
                    icon = {
                        Icon(Icons.Default.DirectionsCar, null)
                    },
                    label = {
                        Text("My Rides")
                    }
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // HEADER

            item {

                Column {

                    Text(
                        "Campus Ride",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = RideBlue
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        "Hello, Student! 👋",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RideText
                    )

                    Text(
                        "Find a comfortable ride with fellow students.",
                        fontSize = 12.sp,
                        color = RideGray
                    )
                }
            }

            // SEARCH FORM

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            "Find Your Ride",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = RideText
                        )

                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = pickup,
                            onValueChange = { pickup = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Pickup Location") },
                            leadingIcon = {
                                Icon(Icons.Default.MyLocation, null)
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(Modifier.height(10.dp))

                        OutlinedTextField(
                            value = destination,
                            onValueChange = { destination = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Destination") },
                            leadingIcon = {
                                Icon(Icons.Default.LocationOn, null)
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(Modifier.height(10.dp))

                        OutlinedTextField(
                            value = travelDate,
                            onValueChange = { travelDate = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Travel Date") },
                            leadingIcon = {
                                Icon(Icons.Default.CalendarToday, null)
                            },
                            singleLine = true,
                            placeholder = {
                                Text("e.g. 22 Nov 2026")
                            },
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = {
                                onFindRide(
                                    pickup,
                                    destination,
                                    travelDate
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RideBlue
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                "Find a Ride",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // OFFER RIDE BUTTON

            item {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8EDFF)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                "Have an empty seat?",
                                fontWeight = FontWeight.Bold,
                                color = RideText
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                "Share your ride with students.",
                                fontSize = 12.sp,
                                color = RideGray
                            )
                        }

                        Button(
                            onClick = onOfferRide,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RideBlue
                            )
                        ) {
                            Text("Offer Ride")
                        }
                    }
                }
            }

            // AVAILABLE RIDES TITLE

            item {

                Text(
                    "Available Rides",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RideText
                )
            }

            // RIDE LIST

            if (rides.isEmpty()) {

                item {
                    EmptyRides()
                }

            } else {

                items(rides) { ride ->

                    RideItem(ride)
                }
            }
        }
    }
}

// RIDE CARD

@Composable
fun RideItem(ride: Ride) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier = Modifier.padding(15.dp)
        ) {

            Text(
                "${ride.pickup} → ${ride.destination}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = RideText
            )

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column {

                    Text(
                        "Date & Time",
                        fontSize = 11.sp,
                        color = RideGray
                    )

                    Text(
                        "${ride.date}, ${ride.time}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        "Available Seats",
                        fontSize = 11.sp,
                        color = RideGray
                    )

                    Text(
                        "${ride.seats} Seats",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = RideBackground
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    "Estimated Cost",
                    fontSize = 12.sp,
                    color = RideGray
                )

                Text(
                    "₹${ride.cost}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RideBlue
                )
            }
        }
    }
}

// EMPTY STATE

@Composable
fun EmptyRides() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                Icons.Default.DirectionsCar,
                contentDescription = null,
                tint = RideBlue,
                modifier = Modifier.size(40.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "No rides available",
                fontWeight = FontWeight.Bold,
                color = RideText
            )

            Text(
                "Available rides will appear here.",
                color = RideGray,
                fontSize = 12.sp
            )
        }
    }
}

// PREVIEW

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CampusRidePreview() {

    MaterialTheme {

        CampusRideHomeScreen()
    }
}

// EMPTY PREVIEW

@Preview(showBackground = true)
@Composable
fun CampusRideEmptyPreview() {

    MaterialTheme {

        CampusRideHomeScreen(
            rides = emptyList()
        )
    }
}
