package com.example.android_gameapplication.ui.detailpage

/**
 * Data class representing the state of the detail page overview.
 * Primarily used for tracking the expanded or collapsed states of different components on the page.
 *
 * @property expandedStates A map holding the expansion states of components, identified by a unique key (usually the title or identifier of the component).
 * Each entry in the map corresponds to a component on the detail page, with the Boolean value indicating whether that component is expanded (true) or collapsed (false).
 */
data class DetailpageOverviewState(
    val expandedStates: MutableMap<String, Boolean> = mutableMapOf(),

    )
