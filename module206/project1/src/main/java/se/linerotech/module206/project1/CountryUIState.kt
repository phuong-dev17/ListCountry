package se.linerotech.module206.project1

sealed class CountryUIState() {
    object Loading: CountryUIState()
    data class Loaded(val country: List<CountryData>): CountryUIState()
    object Failure: CountryUIState()
}
