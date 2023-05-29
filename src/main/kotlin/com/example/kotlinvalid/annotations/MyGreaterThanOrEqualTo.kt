package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import org.springframework.beans.BeanWrapperImpl
import java.time.LocalDate
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ReportAsSingleViolation
@Constraint(validatedBy = [MyGreaterThanOrEqualToValidator::class])
annotation class MyGreaterThanOrEqualTo(
    val message: String = "message",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val before: String,
    val after: String
)

class MyGreaterThanOrEqualToValidator: ConstraintValidator<MyGreaterThanOrEqualTo, Any> {
    private lateinit var beforeName: String
    private lateinit var afterName: String

    override fun initialize(constraintAnnotation: MyGreaterThanOrEqualTo) {
        beforeName = constraintAnnotation.before
        afterName = constraintAnnotation.after
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)

        val before = beanWrapper.getPropertyValue(beforeName) as LocalDate?
        val after = beanWrapper.getPropertyValue(afterName) as LocalDate?

        if (before == null || after == null) return true
        return before.isBefore(after) || before == after
    }
}
