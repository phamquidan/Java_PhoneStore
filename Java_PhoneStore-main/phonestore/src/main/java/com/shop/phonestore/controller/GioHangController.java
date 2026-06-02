package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.GioHang;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.repository.GioHangRepository;
import com.shop.phonestore.repository.TaiKhoanRepository;
import com.shop.phonestore.service.GioHangService;

@Controller
@RequestMapping("/gio-hang")
@RequiredArgsConstructor
public class GioHangController {

    private final GioHangService gioHangService;
    private final TaiKhoanRepository taiKhoanRepository;
    private final GioHangRepository gioHangRepository;

    @GetMapping
    public String view(java.security.Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    var gioHangOpt = gioHangService.findByKhachHangId(kh.getId());
                    GioHang gioHang;
                    if (gioHangOpt.isPresent()) {
                        gioHang = gioHangOpt.get();
                    } else {
                        gioHang = GioHang.builder()
                                .khachHang(kh)
                                .build();
                        gioHang = gioHangRepository.save(gioHang);
                    }
                    model.addAttribute("gioHang", gioHang);
                    model.addAttribute("items", gioHangService.findItemsByGioHangId(gioHang.getId()));
                    return "gio-hang-view";
                }
            }
        }
        model.addAttribute("gioHang", null);
        model.addAttribute("items", java.util.Collections.emptyList());
        return "gio-hang-view";
    }

    @GetMapping("/thanh-toan")
    public String checkout(java.security.Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    var gioHangOpt = gioHangService.findByKhachHangId(kh.getId());
                    if (gioHangOpt.isPresent()) {
                        var items = gioHangService.findItemsByGioHangId(gioHangOpt.get().getId());
                        if (items.isEmpty()) {
                            return "redirect:/gio-hang";
                        }
                        java.math.BigDecimal total = items.stream()
                            .map(item -> item.getSanPham().getGia().multiply(java.math.BigDecimal.valueOf(item.getSoLuong())))
                            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
                        model.addAttribute("user", kh);
                        model.addAttribute("items", items);
                        model.addAttribute("total", total);
                        return "checkout";
                    }
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/them")
    public String addToCart(java.security.Principal principal, @RequestParam Long sanPhamId, @RequestParam(defaultValue = "1") Integer soLuong) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    var gioHangOpt = gioHangService.findByKhachHangId(kh.getId());
                    GioHang gioHang;
                    if (gioHangOpt.isPresent()) {
                        gioHang = gioHangOpt.get();
                    } else {
                        gioHang = GioHang.builder()
                                .khachHang(kh)
                                .build();
                        gioHang = gioHangRepository.save(gioHang);
                    }
                    gioHangService.addItem(gioHang, sanPhamId, soLuong);
                    return "redirect:/gio-hang";
                }
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/cap-nhat")
    public String updateQuantity(java.security.Principal principal, @RequestParam Long sanPhamId, @RequestParam String action) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    var gioHangOpt = gioHangService.findByKhachHangId(kh.getId());
                    if (gioHangOpt.isPresent()) {
                        gioHangService.updateQuantity(gioHangOpt.get(), sanPhamId, action);
                    }
                }
            }
        }
        return "redirect:/gio-hang";
    }

    @PostMapping("/xoa")
    public String removeItem(java.security.Principal principal, @RequestParam Long sanPhamId) {
        if (principal != null) {
            String username = principal.getName();
            var taiKhoanOpt = taiKhoanRepository.findByTenDangNhap(username);
            if (taiKhoanOpt.isPresent()) {
                KhachHang kh = taiKhoanOpt.get().getKhachHang();
                if (kh != null) {
                    var gioHangOpt = gioHangService.findByKhachHangId(kh.getId());
                    if (gioHangOpt.isPresent()) {
                        gioHangService.removeItem(gioHangOpt.get(), sanPhamId);
                    }
                }
            }
        }
        return "redirect:/gio-hang";
    }
}
