package com.example.kotlinvalid.validations

import com.example.kotlinvalid.annotations.MyRequired
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class MyRequiredValidator: ConstraintValidator<MyRequired, String> {
    override fun initialize(constraintAnnotation: MyRequired) {}

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return !value.isNullOrEmpty()
    }
}