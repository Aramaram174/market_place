package com.example.marketplace.utils.test

import com.example.marketplace.data.db.model.MultiselectAdapterItem
import com.example.marketplace.data.db.model.Filter
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.data.db.model.MultiselectAdapterItems

val imageUrlList = listOf(
    "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
)

val imageUrlList1 = listOf(
    "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
)

val imageUrlList2 = listOf(
    "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
)

val imageImageSliderItem = listOf(
    HomeData.ImageSliderItem(
        "ImageSlider",
        0,
        0,
        "",
        "",
        0,
        0,
        0,
        "",
        "people",
        1,
        1,
        1,
        "",
        "",
        1,
        1,
        1,
        "",
        imageUrlList,
        ""
    ),
    HomeData.ImageSliderItem(
        "ImageSlider",
        0,
        0,
        "",
        "",
        0,
        0,
        0,
        "",
        "people",
        1,
        1,
        1,
        "",
        "",
        1,
        1,
        1,
        "",
        imageUrlList,
        ""
    ),
    HomeData.ImageSliderItem(
        "ImageSlider",
        0,
        0,
        "",
        "",
        0,
        0,
        0,
        "",
        "people",
        1,
        1,
        1,
        "",
        "",
        1,
        1,
        1,
        "",
        imageUrlList,
        ""
    )
)

val imageCircleImageSlider = listOf(
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    ),
    HomeData.CircleImageSliderItem(
        "ImageSlider",
        "male",
        imageUrlList1,
    )
)

val homeList = listOf(
    HomeData.ImageSlider(
    imageImageSliderItem
    ),
    HomeData.CircleImageSlider(
    imageCircleImageSlider
    ),
    HomeData.ImageBanner("ImageBanner", "famale", imageUrlList2)
)

val filterList = listOf(
    Filter(1,"Category", ""),
    Filter(2,"Product type", ""),
    Filter(3,"Style", ""),
    Filter(4,"Color", ""),
    Filter(5,"Brand", ""),
    Filter(6,"Size", ""),
    Filter(7,"Price", ""),
)

val categoryList = listOf(
    MultiselectAdapterItem(1,"Shoes"),
    MultiselectAdapterItem(2,"Clothing"),
    MultiselectAdapterItem(3,"Belt"),
    MultiselectAdapterItem(4,"Bag"),
    MultiselectAdapterItem(5,"Hat"),
    MultiselectAdapterItem(6,"Accessorises")
)

val productTypeList = listOf<MultiselectAdapterItem>()
val styleList = listOf<MultiselectAdapterItem>()

val colorList = listOf(
    MultiselectAdapterItem(1,"Black"),
    MultiselectAdapterItem(2,"Blue"),
    MultiselectAdapterItem(3,"Brown"),
    MultiselectAdapterItem(4,"Brunette"),
    MultiselectAdapterItem(5,"Copper"),
    MultiselectAdapterItem(6,"Gold"),
    MultiselectAdapterItem(7,"Gray"),
    MultiselectAdapterItem(8,"Green"),
    MultiselectAdapterItem(9,"Multi"),
    MultiselectAdapterItem(10,"Navy"),
    MultiselectAdapterItem(11,"Neutral"),
    MultiselectAdapterItem(12,"Orange"),
    MultiselectAdapterItem(13,"Pink"),
    MultiselectAdapterItem(14,"Purple"),
    MultiselectAdapterItem(15,"Red"),
    MultiselectAdapterItem(16,"Silver"),
    MultiselectAdapterItem(17,"White"),
    MultiselectAdapterItem(18,"Yellow"),
)

val sizeList = listOf(
    MultiselectAdapterItem(1,"XXS"),
    MultiselectAdapterItem(2,"XS"),
    MultiselectAdapterItem(3,"S"),
    MultiselectAdapterItem(4,"M"),
    MultiselectAdapterItem(5,"L"),
    MultiselectAdapterItem(6,"XL"),
    MultiselectAdapterItem(7,"XXL"),
    MultiselectAdapterItem(8,"XXXL"),
    MultiselectAdapterItem(9,"4XL"),
    MultiselectAdapterItem(10,"5XL")
)

val brandList = listOf(
    MultiselectAdapterItem(1,"Armani Exchange"),
    MultiselectAdapterItem(2,"Guess"),
    MultiselectAdapterItem(3,"Tommy Hilfiger"),
    MultiselectAdapterItem(4,"Adidas"),
    MultiselectAdapterItem(5,"Puma"),
    MultiselectAdapterItem(6,"Rebook"),
    MultiselectAdapterItem(7,"Nike"),
    MultiselectAdapterItem(8,"Giorgio Armani"),
    MultiselectAdapterItem(9,"Michel Kors"),
    MultiselectAdapterItem(10,"Viachislav Constantin"),
    MultiselectAdapterItem(11,"Levis"),
    MultiselectAdapterItem(12,"Asos"),
    MultiselectAdapterItem(13,"Pull & Bear"),
    MultiselectAdapterItem(14,"Bershka")
)
