package com.example.kotlinvalid.entity

import com.example.kotlinvalid.annotations.*
import java.io.File
import java.time.LocalDate

@MyRequiredIf(field = "email", target = "name", message = "{target}が入力されている場合、{field}は必須です")
@MyEqual(x = "pwd", y = "pwdConfirm", message = "{x}と{y}が一致しません")
//@MyNotEqual(x = "pwd", y = "pwdConfirm", message = "{x}と{y}が異なる必要があります")
//@MyGreaterThanOrEqualTo(x = "sum1", y = "sum2", message = "{x}は{y}以上である必要があります")
@MyGreaterThan(x = "update", y = "create", message = "{x}は{y}より必要があります")
data class User(
    val name: String?,
    @MyMailAddress("形式が正しくありません(例：sample@example.com)")
    val email: String?,
    val pwd: String?,
    val pwdConfirm: String?,
    @MyLength(min = "1", max = "5", message = "1文字以上5文字以下で入力してください")
    val content: String?,
    @MyZipCode("形式が正しくありません(例：000-0000)")
    val zipCode: String?,
    @MyClientCd("形式が正しくありません(例：000000000001)",)
    val clientCd: String?,
    @MyUserCd("形式が正しくありません(例：100000000001)")
    val userCd: String?,
    @MyTemplateCd("形式が正しくありません(例：TAa00000001)")
    val templateCd: String?,
    @MyClientShopCd("形式が正しくありません(例：SAa00000001)")
    val clientShopCd: String?,
    @MyJobFrameCd("形式が正しくありません(例：FAa00000001)")
    val jobFrameCd: String?,
    @MyPostCd("形式が正しくありません(例：1001000001)")
    val postCd: String?,
    @MyLength(min = "5", max = "10", message = "5文字以上10文字以下で入力してください")
    val description: String?,
    @MyFullWidthKatakana("カタカナで入力してください")
    val katakana: String?,
    @MyDate("形式が正しくありません")
    val date: LocalDate?,
    @MyTelWithHyphen("形式が正しくありません")
    val tel: String?,
    @MyNumber(min = 4, max = 15, message = "{min}文字以上{max}文字以下で入力してください")
    val halfSizeNumber: Int?,
    val sum1: Int?,
    val sum2: Int?,
    val create: LocalDate?,
    val update: LocalDate?
)