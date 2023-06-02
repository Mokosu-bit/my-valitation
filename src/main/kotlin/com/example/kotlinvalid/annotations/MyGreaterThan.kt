package com.example.kotlinvalid.annotations

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.BeanWrapperImpl
import java.time.LocalDate
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [MyGreaterThanValidator::class])
annotation class MyGreaterThan(
    val fields: Array<MyFieldPair>,
    val message: String = "message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

annotation class MyFieldPair(
    val x: String,
    val y: String,
    val message: String = "{x}は{y}以上である必要があります",
)

class MyGreaterThanValidator : ConstraintValidator<MyGreaterThan, Any> {
    private lateinit var fieldPairs: Array<MyFieldPair>

    override fun initialize(constraintAnnotation: MyGreaterThan) {
        fieldPairs = constraintAnnotation.fields
    }


    /**
     * 配列の中身を順番に比較していく
     * xがyより長い判定する
     */
    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {
        var isValid = true
        context.disableDefaultConstraintViolation()
        for (fieldPair in fieldPairs) {
            val xValue = value::class.memberProperties.first { it.name == fieldPair.x }.getter.call(value)
            val yValue = value::class.memberProperties.first { it.name == fieldPair.y }.getter.call(value)
            if (xValue is Comparable<*> && yValue is Comparable<*>) {
                @Suppress("UNCHECKED_CAST")
                val comparison = (xValue as Comparable<Any>).compareTo(yValue)
                if (comparison < 0) {
                    context.buildConstraintViolationWithTemplate(
                        fieldPair.message.replace("{x}", fieldPair.x).replace("{y}", fieldPair.y)
                    )?.addPropertyNode(fieldPair.x)?.addConstraintViolation()
                    isValid = false
                }
            }
        }
        return isValid
    }
}