package org.d3if4092.ass3.model

data class Convert(
    val id : String,
    val rupiah : String,
    val hasilJepang : String,
) {
    constructor() : this("", "", "")
}
