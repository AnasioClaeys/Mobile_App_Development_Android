package com.example.android_gameapplication.ui.layoutComponents

/**
 * Enum representing the different types of navigation used in the application.
 * This enum helps in determining the appropriate navigation layout based on the screen size or other criteria.
 */
enum class GameAppNavigationType {
    /** Represents the use of bottom navigation typically used in smaller or mobile screens. */
    BOTTOM_NAVIGATION,

    /** Represents the use of a navigation rail suitable for medium-sized screens or tablet devices. */
    NAVIGATION_RAIL,

    /** Represents the use of a permanent navigation drawer, often used in larger screens or desktop environments. */
    PERMANENT_NAVIGATION_DRAWER,
}
