package com.kvlg.recipe.ui.list

/**
 * @author Konstantin Koval
 * @since 28.06.2021
 */

enum class FoodCategory(val value: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    MILK("Milk"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getFoodCategory(value: String) = FoodCategory.values().associateBy(FoodCategory::value)[value]