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
    val x: String,
    val y: String
)

class MyGreaterThanOrEqualToValidator: ConstraintValidator<MyGreaterThanOrEqualTo, Any> {
    private lateinit var x: String
    private lateinit var y: String

    override fun initialize(constraintAnnotation: MyGreaterThanOrEqualTo) {
        x = constraintAnnotation.x
        y = constraintAnnotation.y
    }

    /**
     * xがy以上であるかどうかを判定する
     */
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)
        val xValue = beanWrapper.getPropertyValue(x)
        val yValue = beanWrapper.getPropertyValue(y)
        /**
         * x,yがLocalDate型の場合
         * xがy以上であるかどうかを判定する
         */
        if (xValue is LocalDate && yValue is LocalDate) {
            return xValue.isAfter(yValue) || xValue.isEqual(yValue)
        }
        /**
         * x,yがInt型の場合
         * xがy以上であるかどうかを判定する
         */
        if (xValue is Int && yValue is Int) {
            return xValue >= yValue
        }
        /**
         * x,yがString型の場合
         * xがy以上であるかどうかを判定する
         */
        if (xValue is String && yValue is String) {
            return xValue >= yValue
        }
        return true
    }
}
