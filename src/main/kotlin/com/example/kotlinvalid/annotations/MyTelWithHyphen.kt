package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyTelWithHyphenValidator::class])
annotation class MyTelWithHyphen(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyTelWithHyphenValidator: ConstraintValidator<MyTelWithHyphen, String> {
    override fun initialize(constraintAnnotation: MyTelWithHyphen?) {
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value?.length == 0 || value?.matches(Regex("^(?=(?:\\d+-){1,19}\\d+$)[-\\d]{1,20}$")) ?: false
    }
}
