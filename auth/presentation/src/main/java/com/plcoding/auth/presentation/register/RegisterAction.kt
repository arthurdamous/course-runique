package com.plcoding.auth.presentation.register

sealed interface RegisterAction {
    data object OnRegisterClick : RegisterAction
    data object OnLoginClick : RegisterAction
    data object OnTogglePasswordVisibilityClick : RegisterAction
}