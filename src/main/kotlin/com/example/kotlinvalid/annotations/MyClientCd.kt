package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyClientCdValidator::class])
annotation class MyClientCd(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyClientCdValidator: ConstraintValidator<MyClientCd, String> {
    override fun initialize(constraintAnnotation: MyClientCd) {}

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.length == 0 || value?.matches(Regex("^00\\d{10}$")) ?: false
    }
}
