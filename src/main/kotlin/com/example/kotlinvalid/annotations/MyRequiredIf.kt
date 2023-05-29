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
@Constraint(validatedBy = [MyRequiredIfValidator::class])
annotation class MyRequiredIf(
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val field: String,
    val target: String,
    val targetValue: Boolean
)

class MyRequiredIfValidator: ConstraintValidator<MyRequiredIf, Any> {

    private lateinit var field: String
    private lateinit var target: String
    private var targetValue: Boolean = true

    /**
     * 初期化処理
     */
    override fun initialize(constraintAnnotation: MyRequiredIf) {
        field = constraintAnnotation.field
        target = constraintAnnotation.target
        targetValue = constraintAnnotation.targetValue
    }

    /**
     * バリデーション処理
     * targetがtrueの場合、fieldが必須
     */
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)

        val targetValue = beanWrapper.getPropertyValue(target)
        val fieldValue = beanWrapper.getPropertyValue(field)

        if (targetValue == null || fieldValue == null) return true
        return if (targetValue == this.targetValue) {
            fieldValue.toString().isNotEmpty()
        } else {
            true
        }
    }
}
