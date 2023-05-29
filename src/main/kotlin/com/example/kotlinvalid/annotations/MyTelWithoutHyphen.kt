package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyTelWithoutHyphenValidator::class])
annotation class MyTelWithoutHyphen(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyTelWithoutHyphenValidator: ConstraintValidator<MyTelWithoutHyphen, String> {
    override fun initialize(constraintAnnotation: MyTelWithoutHyphen?) {
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
       return value?.length == 0 || value?.matches(Regex("^\\d{10,11}$")) ?: false
    }
}