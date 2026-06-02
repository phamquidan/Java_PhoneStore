package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String tenDangNhap, @RequestParam String matKhau,
            @RequestParam String hoTen, @RequestParam String email, @RequestParam String soDienThoai) {
        KhachHang khachHang = KhachHang.builder()
                .hoTen(hoTen)
                .email(email)
                .soDienThoai(soDienThoai)
                .build();
        authService.register(tenDangNhap, matKhau, khachHang);
        return "redirect:/login";
    }
}
