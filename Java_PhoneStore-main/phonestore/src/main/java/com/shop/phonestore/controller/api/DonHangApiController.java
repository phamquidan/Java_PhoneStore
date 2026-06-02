package com.shop.phonestore.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.DonHang;
import com.shop.phonestore.entity.ChiTietDonHang;
import com.shop.phonestore.service.DonHangService;
import java.util.List;

@RestController
@RequestMapping("/api/don-hang")
@RequiredArgsConstructor
public class DonHangApiController {

    private final DonHangService donHangService;

    @GetMapping
    public ResponseEntity<List<DonHang>> getAll() {
        return ResponseEntity.ok(donHangService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonHang> getById(@PathVariable Long id) {
        return donHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/chi-tiet")
    public ResponseEntity<List<ChiTietDonHang>> getDetails(@PathVariable Long id) {
        List<ChiTietDonHang> items = donHangService.findItemsByDonHangId(id);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<DonHang> updateStatus(@PathVariable Long id, @RequestParam String trangThai) {
        return donHangService.findById(id)
                .map(existing -> {
                    existing.setTrangThai(trangThai);
                    DonHang updated = donHangService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
