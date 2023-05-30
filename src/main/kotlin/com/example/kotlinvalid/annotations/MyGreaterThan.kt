package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.BeanWrapperImpl
import java.time.LocalDate
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyGreaterThanValidator::class])
annotation class MyGreaterThan(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val x: String = "",
    val y: String = ""
)

class MyGreaterThanValidator: ConstraintValidator<MyGreaterThan, Any> {
    private lateinit var x: String
    private lateinit var y: String

    override fun initialize(constraintAnnotation: MyGreaterThan) {
        x = constraintAnnotation.x
        y = constraintAnnotation.y
    }

    /**
     * xがyより大きいかどうかを判定する
     */
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)
        val xValue = beanWrapper.getPropertyValue(x)
        val yValue = beanWrapper.getPropertyValue(y)

        /**
         * x,yがInt型の場合
         * xがyより大きいかどうかを判定する
         */
        if (xValue is Int && yValue is Int) {
            return xValue > yValue
        }
        /**
         * x,yがLocalDate型の場合
         * xがyより大きいかどうかを判定する
         */
        if (xValue is LocalDate && yValue is LocalDate) {
            return xValue.isAfter(yValue)
        }
        /**
         * x,yがString型の場合
         * xがyより大きいかどうかを判定する
         */
        if (xValue is String && yValue is String) {
            return xValue > yValue
        }
        return true
    }
}
