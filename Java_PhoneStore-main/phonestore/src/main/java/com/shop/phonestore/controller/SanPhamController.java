package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.SanPham;
import com.shop.phonestore.service.LoaiHangService;
import com.shop.phonestore.service.SanPhamService;
import com.shop.phonestore.repository.NhaSanXuatRepository;

@Controller
@RequestMapping("/san-pham")
@RequiredArgsConstructor
public class SanPhamController {

    private final SanPhamService sanPhamService;
    private final LoaiHangService loaiHangService;
    private final NhaSanXuatRepository nhaSanXuatRepository;

    @GetMapping
    public String list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long loaiId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        var pageData = sanPhamService.searchAndFilter(q, loaiId, page, 8);
        model.addAttribute("sanPhams", pageData.getContent());
        model.addAttribute("currentPage", pageData.getNumber());
        model.addAttribute("totalPages", pageData.getTotalPages());
        model.addAttribute("hasPrevious", pageData.hasPrevious());
        model.addAttribute("hasNext", pageData.hasNext());
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("loaiId", loaiId);
        model.addAttribute("loaiHangs", loaiHangService.findAll());
        return "san-pham-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("sanPham", sanPhamService.findById(id).orElse(null));
        return "san-pham-detail";
    }

    @GetMapping("/admin")
    public String adminList(Model model) {
        java.util.List<SanPham> sanPhams = sanPhamService.findAll();
        long totalProducts = sanPhams.size();
        long outOfStock = sanPhams.stream().filter(sp -> sp.getSoLuongTon() == 0).count();
        long lowStock = sanPhams.stream().filter(sp -> sp.getSoLuongTon() > 0 && sp.getSoLuongTon() < 10).count();
        long inactive = sanPhams.stream().filter(sp -> !sp.getTrangThai()).count();

        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("outOfStock", outOfStock);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("inactive", inactive);
        return "san-pham-admin-list";
    }

    @GetMapping("/admin/form")
    public String form(Model model) {
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("loaiHangs", loaiHangService.findAll());
        model.addAttribute("nhaSanXuats", nhaSanXuatRepository.findAll());
        return "san-pham-form";
    }

    @GetMapping("/admin/form/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var sanPhamOpt = sanPhamService.findById(id);
        if (sanPhamOpt.isPresent()) {
            model.addAttribute("sanPham", sanPhamOpt.get());
            model.addAttribute("loaiHangs", loaiHangService.findAll());
            model.addAttribute("nhaSanXuats", nhaSanXuatRepository.findAll());
            return "san-pham-form";
        }
        return "redirect:/san-pham/admin";
    }

    @PostMapping("/admin/save")
    public String save(SanPham sanPham) {
        sanPhamService.save(sanPham);
        return "redirect:/san-pham/admin";
    }

    @GetMapping("/admin/xoa/{id}")
    public String delete(@PathVariable Long id) {
        sanPhamService.deleteById(id);
        return "redirect:/san-pham/admin";
    }
}
