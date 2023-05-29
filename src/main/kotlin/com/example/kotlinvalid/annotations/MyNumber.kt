package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyNumberValidator::class])
annotation class MyNumber(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val min: Int = 0,
    val max: Int = 0
)

class MyNumberValidator: ConstraintValidator<MyNumber, Int> {
    private var min: Int = 0
    private var max: Int = 0
    override fun initialize(constraintAnnotation: MyNumber) {
        min = constraintAnnotation.min
        max = constraintAnnotation.max
    }

    override fun isValid(value: Int?, context: ConstraintValidatorContext?): Boolean {
        return value?.let { it in min..max } ?: false
    }
}
