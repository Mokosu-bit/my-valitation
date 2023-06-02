package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyDateTimeValidator::class])
annotation class MyDateTime(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyDateTimeValidator: ConstraintValidator<MyDateTime, LocalDateTime> {
    /**
     * ISO_ZONED_DATE_TIMEでバリデーション
     */
    override fun isValid(value: LocalDateTime?, context: ConstraintValidatorContext?): Boolean {
        return value?.toString().equals(value?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)) ?: false
    }
}