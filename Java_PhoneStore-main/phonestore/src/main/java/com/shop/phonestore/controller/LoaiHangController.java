package com.shop.phonestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.LoaiHang;
import com.shop.phonestore.service.LoaiHangService;

@Controller
@RequestMapping("/loai-hang/admin")
@RequiredArgsConstructor
public class LoaiHangController {

    private final LoaiHangService loaiHangService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("loaiHangs", loaiHangService.findAll());
        return "loai-hang-admin-list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("loaiHang", new LoaiHang());
        return "loai-hang-form";
    }

    @PostMapping("/save")
    public String save(LoaiHang loaiHang) {
        loaiHangService.save(loaiHang);
        return "redirect:/loai-hang/admin";
    }
}
