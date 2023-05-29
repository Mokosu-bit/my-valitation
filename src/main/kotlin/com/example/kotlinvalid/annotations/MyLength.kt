package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyLengthValidator::class])
annotation class MyLength(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val min: String = "",
    val max: String = ""
)

class MyLengthValidator: ConstraintValidator<MyLength, String> {
    private lateinit var min: String
    private lateinit var max: String

    override fun initialize(constraintAnnotation: MyLength) {
        min = constraintAnnotation.min
        max = constraintAnnotation.max
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.matches(Regex(".{$min,$max}")) ?: false
    }
}
