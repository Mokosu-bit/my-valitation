package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyTemplateCdValidator::class])
annotation class MyTemplateCd(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyTemplateCdValidator: ConstraintValidator<MyTemplateCd, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.length == 0 || value?.matches(Regex("^T[a-zA-Z0-9]{10}$")) ?: false
    }
}
