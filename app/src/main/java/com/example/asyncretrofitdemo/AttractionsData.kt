package com.example.taipeitourv1.DataClass

data class AttractionsData(
    val name: String,
    val introduction: String,
    val address: String,
    val modified: String,
    val url: String,
    val images: ArrayList<ImagesUrlData>
)
