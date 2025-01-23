package com.ukrdroiddev.presentation.navigation

class EnvironmentStore {
    private val environmentProviders: MutableMap<String, () -> ScreenEnvironment> = mutableMapOf()

    operator fun set(route: String, environmentProvider: () -> ScreenEnvironment) {
        environmentProviders[route] = environmentProvider
    }

    operator fun get(route: String): (() -> ScreenEnvironment) {
        return environmentProviders[route] ?: { ScreenEnvironmentImpl.DEFAULT }
    }
}
