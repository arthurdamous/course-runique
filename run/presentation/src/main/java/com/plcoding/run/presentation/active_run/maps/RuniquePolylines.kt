package com.plcoding.run.presentation.active_run.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline
import com.plcoding.core.domain.location.LocationTimestamp

@Composable
fun RuniquePolylines(locations: List<List<LocationTimestamp>>) {

    val polylines = remember(locations) {
        locations.map { locationTimestamps ->
            locationTimestamps.zipWithNext { timestamp1, timestamp2 ->
                PolylineUi(
                    location1 = timestamp1.location.location,
                    location2 = timestamp2.location.location,
                    color = PolylineColorCalculator.locationsToColor(
                        location1 = timestamp1,
                        location2 = timestamp2
                    )
                )
            }
        }
    }

    polylines.forEach { polylines ->
        polylines.forEach { polyline ->
            Polyline(
                points = listOf(
                    LatLng(polyline.location1.lat, polyline.location1.long),
                    LatLng(polyline.location2.lat, polyline.location2.long)
                ),
                color = polyline.color,
                jointType = JointType.BEVEL
            )
        }
    }

}