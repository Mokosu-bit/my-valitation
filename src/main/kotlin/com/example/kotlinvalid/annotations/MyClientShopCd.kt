package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyClientShopCdValidator::class])
annotation class MyClientShopCd(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyClientShopCdValidator: ConstraintValidator<MyClientShopCd, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.length == 0 || value?.matches(Regex("^S[a-zA-Z0-9]{10}$")) ?: false
    }
}
