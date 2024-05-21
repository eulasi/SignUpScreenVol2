package com.challenge.signupsreenvol2.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _emailError.value = null
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _passwordError.value = null
    }

    fun validateInputs(): Boolean {
        var isValid = true

        if (_email.value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _emailError.value = "Invalid email"
            isValid = false
        }

        if (_password.value.isEmpty() || !isValidPassword(_password.value)) {
            _passwordError.value = "Password must be at least 8 characters long, and contain an uppercase letter, a lowercase letter, a digit, and a special character"
            isValid = false
        }

        return isValid
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$")
        return password.matches(passwordPattern)
    }
}
