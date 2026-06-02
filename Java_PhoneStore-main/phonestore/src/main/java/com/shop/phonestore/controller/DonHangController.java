package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.entity.DonHang;
import com.shop.phonestore.repository.TaiKhoanRepository;
import com.shop.phonestore.service.DonHangService;
import java.util.List;

@Controller
@RequestMapping("/don-hang")
@RequiredArgsConstructor
public class DonHangController {

    private final DonHangService donHangService;
    private final TaiKhoanRepository taiKhoanRepository;

    @GetMapping
    public String list(java.security.Principal principal, @RequestParam(defaultValue = "0") int page, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                var taiKhoan = taiKhoanOpt.get();
                if (taiKhoan.getRole() == com.shop.phonestore.entity.Role.ADMIN) {
                    var pageData = donHangService.findPage(page, 10);
                    model.addAttribute("donHangs", pageData.getContent());
                    model.addAttribute("currentPage", pageData.getNumber());
                    model.addAttribute("totalPages", pageData.getTotalPages());
                    model.addAttribute("hasPrevious", pageData.hasPrevious());
                    model.addAttribute("hasNext", pageData.hasNext());
                } else {
                    KhachHang kh = taiKhoan.getKhachHang();
                    if (kh != null) {
                        List<DonHang> customerOrders = donHangService.findByKhachHang(kh.getId());
                        model.addAttribute("donHangs", customerOrders);
                        model.addAttribute("currentPage", 0);
                        model.addAttribute("totalPages", 1);
                        model.addAttribute("hasPrevious", false);
                        model.addAttribute("hasNext", false);
                    } else {
                        model.addAttribute("donHangs", java.util.Collections.emptyList());
                        model.addAttribute("currentPage", 0);
                        model.addAttribute("totalPages", 1);
                        model.addAttribute("hasPrevious", false);
                        model.addAttribute("hasNext", false);
                    }
                }
                return "don-hang-list";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/{id}")
    public String detail(java.security.Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                var taiKhoan = taiKhoanOpt.get();
                var donHangOpt = donHangService.findById(id);
                if (donHangOpt.isPresent()) {
                    var donHang = donHangOpt.get();
                    if (taiKhoan.getRole() == com.shop.phonestore.entity.Role.ADMIN || 
                        (donHang.getKhachHang() != null && donHang.getKhachHang().getId().equals(taiKhoan.getKhachHang().getId()))) {
                        model.addAttribute("donHang", donHang);
                        model.addAttribute("items", donHangService.findItemsByDonHangId(id));
                        return "don-hang-detail";
                    } else {
                        return "redirect:/access-denied";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/{id}/invoice")
    public String invoice(java.security.Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                var taiKhoan = taiKhoanOpt.get();
                var donHangOpt = donHangService.findById(id);
                if (donHangOpt.isPresent()) {
                    var donHang = donHangOpt.get();
                    if (taiKhoan.getRole() == com.shop.phonestore.entity.Role.ADMIN || 
                        (donHang.getKhachHang() != null && donHang.getKhachHang().getId().equals(taiKhoan.getKhachHang().getId()))) {
                        model.addAttribute("donHang", donHang);
                        model.addAttribute("items", donHangService.findItemsByDonHangId(id));
                        return "don-hang-invoice";
                    } else {
                        return "redirect:/access-denied";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @org.springframework.web.bind.annotation.PostMapping("/tao")
    public String createOrder(
            java.security.Principal principal,
            @RequestParam String hoTen,
            @RequestParam String soDienThoai,
            @RequestParam String email,
            @RequestParam String diaChi,
            @RequestParam String phuongThucThanhToan) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                var taiKhoan = taiKhoanOpt.get();
                KhachHang kh = taiKhoan.getKhachHang();
                if (kh != null) {
                    DonHang donHang = donHangService.checkout(kh, hoTen, soDienThoai, email, diaChi, phuongThucThanhToan);
                    if (donHang != null) {
                        if ("Chuyển khoản".equalsIgnoreCase(phuongThucThanhToan)) {
                            return "redirect:/don-hang/" + donHang.getId() + "/chuyen-khoan";
                        } else {
                            return "redirect:/don-hang/" + donHang.getId();
                        }
                    }
                }
            }
        }
        return "redirect:/gio-hang";
    }

    @org.springframework.web.bind.annotation.GetMapping("/{id}/chuyen-khoan")
    public String chuyenKhoan(java.security.Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                var taiKhoan = taiKhoanOpt.get();
                var donHangOpt = donHangService.findById(id);
                if (donHangOpt.isPresent()) {
                    var donHang = donHangOpt.get();
                    if (taiKhoan.getRole() == com.shop.phonestore.entity.Role.ADMIN || 
                        (donHang.getKhachHang() != null && donHang.getKhachHang().getId().equals(taiKhoan.getKhachHang().getId()))) {
                        model.addAttribute("donHang", donHang);
                        return "chuyen-khoan";
                    } else {
                        return "redirect:/access-denied";
                    }
                }
            }
        }
        return "redirect:/login";
    }
}
