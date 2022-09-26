package com.example.marketplace.data.db.model

sealed class HomeData {

    data class ImageSlider(
        val imageSliderItem: List<ImageSliderItem>
    ) : HomeData()

    data class ImageSliderItem(
        val name: String,
        val width: Int,
        val height: Int,
        val title: String,
        val titleTextColor: String,
        val titleTextSize: Int,
        val titleTextStyle: Int,
        val titleTextLine: Int,
        val subTitle: String,
        val subTitleTextColor: String,
        val subTitleTextSize: Int,
        val subTitleTextStyle: Int,
        val subTitleTextLine: Int,
        val description: String,
        val descriptionTextColor: String,
        val descriptionTextSize: Int,
        val descriptionTextStyle: Int,
        val descriptionTextLine: Int,
        val backgroundColor: String,
        val imageUrl: List<String>,
        val deepLink: String
    )

    data class CircleImageSlider(
        val circleImageSliderItem: List<CircleImageSliderItem>
    ) : HomeData()

    data class CircleImageSliderItem(
        val name: String,
        val gender: String,
        val imageUrl: List<String>
    )

    data class ImageBanner(
        val name: String,
        val page: String,
        val imageUrl: List<String>,
    ) : HomeData()

    data class ProductSlider(
        val width: Int,
        val height: Int,
        val title: String,
        val titleTextColor: String,
        val titleTextSize: Int,
        val titleTextStyle: Int,
        val titleTextLine: Int,
        val backgroundColor: String,
        val products: List<Product>,
        val buttonName: String,
        val buttonColor: String,
        val deepLink: String
    ) : HomeData()
}