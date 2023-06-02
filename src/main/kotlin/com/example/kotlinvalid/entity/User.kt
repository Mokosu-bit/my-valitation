package com.example.kotlinvalid.entity

import com.example.kotlinvalid.annotations.*
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

@MyRequiredIf(field = "email", target = "name", message = "{target}が入力されている場合、{field}は必須です")
//@MyEqual(x = "pwd", y = "pwdConfirm", message = "{x}と{y}が一致しません")
//@MyNotEqual(x = "pwd", y = "pwdConfirm", message = "{x}と{y}が異なる必要があります")
//@MyGreaterThanOrEqualTo(x = "sum1", y = "sum2", message = "{x}は{y}以上である必要があります")
@MyGreaterThan(
    fields = [
        MyFieldPair(x = "sum1", y = "sum2", message = "{x}は{y}より大きい必要があります"),
        MyFieldPair(x = "pwd", y = "pwdConfirm", message = "{x}は{y}より長い必要があります"),
        MyFieldPair(x = "update", y = "create", message = "{x}は{y}より未来である必要があります")
    ]
)

data class User(
    val name: String?,
    @MyMailAddress("形式が正しくありません(例：sample@example.com)")
    val email: String?,
    val pwd: String?,
    val pwdConfirm: String?,
    @MyLength(min = "0", max = "5", message = "{max}文字以下で入力してください")
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
    @MyLength(min = "0", max = "10", message = "{max}文字以下で入力してください")
    val description: String?,
    @MyFullWidthKatakana("カタカナで入力してください")
    val katakana: String?,
    @MyDate("形式が正しくありません")
    val date: LocalDate?,
    @MyTelWithHyphen("形式が正しくありません")
    val tel: String?,
    val halfSizeNumber: Int?,
    val sum1: Int?,
    val sum2: Int?,
    val create: LocalDate?,
    val update: LocalDate?
)