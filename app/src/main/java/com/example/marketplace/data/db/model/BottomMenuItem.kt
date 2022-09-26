package com.example.marketplace.data.db.model

import androidx.annotation.StringDef
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

//    @StringDef(
//        "labeled",
//        "unlabeled",
//        "auto",
//        "selected"
//    )

//<attr name="labelVisibilityMode">
//    <!-- Label behaves as "labeled" when there are 3 items or less, or "selected" when there are
//    4 items or more. -->
//    <enum name="auto" value="-1"/>
//    <!-- Label is shown on the selected navigation item. -->
//    <enum name="selected" value="0"/>
//    <!-- Label is shown on all navigation items. -->
//    <enum name="labeled" value="1"/>
//    <!-- Label is not shown on any navigation items. -->
//    <enum name="unlabeled" value="2"/>
//</attr>

data class BottomNavigationView(
    @SerializedName("item_type")
    @Expose
    val itemType: Int,
    @SerializedName("bottom_menu_items")
    @Expose
    val bottomMenuItems: List<BottomMenuItem>
)

data class BottomMenuItem(
    @SerializedName("item_id")
    @Expose
    val itemId: Int,
    @SerializedName("order")
    @Expose
    val order: Int,
    @SerializedName("title_res_id")
    @Expose
    val titleResId: String,
    @SerializedName("icon_res_id")
    @Expose
    val iconResId: String)