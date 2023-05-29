package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.cglib.core.Local
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyDateValidator::class])
annotation class MyDate(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class MyDateValidator: ConstraintValidator<MyDate, LocalDate> {
    override fun initialize(constraintAnnotation: MyDate) {
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean {
        return value?.toString().equals(value?.format(DateTimeFormatter.ISO_DATE)) ?: false
    }
}
