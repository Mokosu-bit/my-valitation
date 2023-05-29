package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyPostCdValidator::class])
annotation class MyPostCd(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyPostCdValidator: ConstraintValidator<MyPostCd, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.length == 0 || value?.matches(Regex("^1001\\d{6}$")) ?: false
    }
}
