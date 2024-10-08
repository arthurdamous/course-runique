package com.plcoding.auth.presentation.register

import com.plcoding.core.presentation.ui.UiText

sealed class RegisterEvent {
    data object RegistrationSuccess : RegisterEvent()
    data class Error(val error: UiText) : RegisterEvent()
}
