package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.repository.TaiKhoanRepository;
import com.shop.phonestore.service.DonHangService;
import com.shop.phonestore.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DonHangService donHangService;
    private final TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/dashboard")
    public String dashboard(java.security.Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                model.addAttribute("user", kh);
                if (kh != null) {
                    model.addAttribute("orders", donHangService.findByKhachHang(kh.getId()));
                } else {
                    model.addAttribute("orders", java.util.Collections.emptyList());
                }
                return "user-dashboard";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(java.security.Principal principal, @RequestParam(required = false) Boolean success, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                model.addAttribute("user", taiKhoanOpt.get().getKhachHang());
                if (Boolean.TRUE.equals(success)) {
                    model.addAttribute("success", "Cập nhật thông tin thành công!");
                }
                return "user-profile";
            }
        }
        return "redirect:/login";
    }

    @org.springframework.web.bind.annotation.PostMapping("/profile/cap-nhat")
    public String updateProfile(
            java.security.Principal principal,
            @RequestParam String hoTen,
            @RequestParam String email,
            @RequestParam String soDienThoai,
            @RequestParam(required = false) String diaChi,
            @RequestParam(required = false) String ngaySinh) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    kh.setHoTen(hoTen);
                    kh.setEmail(email);
                    kh.setSoDienThoai(soDienThoai);
                    kh.setDiaChi(diaChi);
                    if (ngaySinh != null && !ngaySinh.isEmpty()) {
                        kh.setNgaySinh(java.time.LocalDate.parse(ngaySinh));
                    }
                    userService.save(kh);
                }
                return "redirect:/user/profile?success=true";
            }
        }
        return "redirect:/login";
    }
}
