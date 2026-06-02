package com.shop.phonestore.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.SanPham;
import com.shop.phonestore.service.SanPhamService;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@RequiredArgsConstructor
public class SanPhamApiController {

    private final SanPhamService sanPhamService;

    @GetMapping
    public ResponseEntity<List<SanPham>> getProducts(
            @RequestParam(required = false) Long maLoai,
            @RequestParam(required = false) String q) {
        
        if (q != null && !q.trim().isEmpty()) {
            return ResponseEntity.ok(sanPhamService.search(q));
        } else if (maLoai != null) {
            return ResponseEntity.ok(sanPhamService.findByLoai(maLoai));
        }
        return ResponseEntity.ok(sanPhamService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanPham> getById(@PathVariable Long id) {
        return sanPhamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SanPham> create(@RequestBody SanPham sanPham) {
        SanPham created = sanPhamService.save(sanPham);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanPham> update(@PathVariable Long id, @RequestBody SanPham sanPham) {
        return sanPhamService.findById(id)
                .map(existing -> {
                    existing.setTenSanPham(sanPham.getTenSanPham());
                    existing.setMoTa(sanPham.getMoTa());
                    existing.setDvt(sanPham.getDvt());
                    existing.setSoLuongTon(sanPham.getSoLuongTon());
                    existing.setGiaNhap(sanPham.getGiaNhap());
                    existing.setGia(sanPham.getGia());
                    existing.setAnhUrl(sanPham.getAnhUrl());
                    existing.setLoaiHang(sanPham.getLoaiHang());
                    existing.setNhaSanXuat(sanPham.getNhaSanXuat());
                    existing.setNgayCapNhat(java.time.LocalDateTime.now());
                    existing.setTrangThai(sanPham.getTrangThai());
                    
                    SanPham updated = sanPhamService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (sanPhamService.findById(id).isPresent()) {
            sanPhamService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
