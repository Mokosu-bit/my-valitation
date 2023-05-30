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
)

class MyRequiredIfValidator: ConstraintValidator<MyRequiredIf, Any> {

    private lateinit var field: String
    private lateinit var target: String

    /**
     * 初期化処理
     */
    override fun initialize(constraintAnnotation: MyRequiredIf) {
        field = constraintAnnotation.field
        target = constraintAnnotation.target
    }

    /**
     * バリデーション処理
     * targetで指定されたフィールドに値が入力されている場合、fieldは必須
     * targetで指定されたフィールドに値が入力されていない場合、fieldは必須ではない
     */
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val beanWrapper = BeanWrapperImpl(value)
        val targetValue = beanWrapper.getPropertyValue(target)?.toString()
        val fieldValue = beanWrapper.getPropertyValue(field)?.toString()

        if (targetValue.isNullOrBlank()) return true

        return fieldValue?.isNotEmpty() ?: true
    }
}
