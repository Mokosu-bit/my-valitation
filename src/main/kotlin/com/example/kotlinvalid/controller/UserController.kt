package com.example.kotlinvalid.controller

import com.example.kotlinvalid.entity.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController {
    @GetMapping
    fun index(model: Model): String {
        val user = User(null,
            email = null,
            content = null,
            zipCode = null,
            clientCd = null,
            userCd = null,
            templateCd = null,
            clientShopCd = null,
            jobFrameCd = null,
            postCd = null,
            description = null,
            katakana = null,
            date = null,
            tel = null,
            halfSizeNumber = null,
            create = null,
            update = null
        )
        model.addAttribute("user", user)
        return "users/index"
    }

    @PostMapping
    fun create(@ModelAttribute @Validated user: User, result: BindingResult): String {
        if (result.hasErrors()) {
            return "users/index"
        }
        println("成功")
        return "redirect:/users"
    }
}