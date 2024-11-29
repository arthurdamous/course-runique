package com.plcoding.auth.presentation.util

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow

fun TextFieldState.textAsFlow() = snapshotFlow { text }