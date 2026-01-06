package ru.korobeynikov.githubapiapplication.presentation

sealed class Error(open val message: String) {

    data class Http404(
        override val message: String,
    ) : Error(message)

    data class Http403(
        override val message: String,
    ) : Error(message)

    data class Other(
        override val message: String,
    ) : Error(message)

    data class noError(
        override val message: String,
    ) : Error(message)
}