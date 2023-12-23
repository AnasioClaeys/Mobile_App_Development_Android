package com.example.android_gameapplication.ui.layoutComponents

/**
 * Enum representing the possible destinations within the app.
 * These destinations are used for navigation and to manage the current view state.
 */
enum class Destinations {
    /** Destination for the Start/Home page. */
    Start,

    /** Destination for the Search page. */
    Search,

    /** Destination for the Detail page of a game. */
    DetailPage,

    /** Destination for the List page showing popular games of all time. */
    ListPagePopularGamesAllTime,

    /** Destination for the List page showing popular games of this year. */
    ListPagePopularGamesOfThisYear,
}
