package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.BeanWrapperImpl
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyNotEqualValidator::class])
annotation class MyNotEqual(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val x: String = "",
    val y: String = ""
)

class MyNotEqualValidator: ConstraintValidator<MyNotEqual, Any> {
    private lateinit var x: String
    private lateinit var y: String

    override fun initialize(constraintAnnotation: MyNotEqual) {
        x = constraintAnnotation.x
        y = constraintAnnotation.y
    }

    /**
     * xとyが異なる文字列であるかどうかを判定する
     */
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)
        val xValue = beanWrapper.getPropertyValue(x) ?: return true
        val yValue = beanWrapper.getPropertyValue(y) ?: return true

        return xValue != yValue
    }
}
