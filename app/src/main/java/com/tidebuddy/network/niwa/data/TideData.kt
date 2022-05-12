package com.tidebuddy.network.niwa.data

data class TideData(
    val metadata: TideMetadata,
    val values: List<TideValue>
)
