package com.shop.phonestore.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.LoaiHang;
import com.shop.phonestore.service.LoaiHangService;
import java.util.List;

@RestController
@RequestMapping("/api/loai-hang")
@RequiredArgsConstructor
public class LoaiHangApiController {

    private final LoaiHangService loaiHangService;

    @GetMapping
    public ResponseEntity<List<LoaiHang>> getAll() {
        return ResponseEntity.ok(loaiHangService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiHang> getById(@PathVariable Long id) {
        return loaiHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoaiHang> create(@RequestBody LoaiHang loaiHang) {
        LoaiHang created = loaiHangService.save(loaiHang);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoaiHang> update(@PathVariable Long id, @RequestBody LoaiHang loaiHang) {
        return loaiHangService.findById(id)
                .map(existing -> {
                    existing.setTenLoai(loaiHang.getTenLoai());
                    LoaiHang updated = loaiHangService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (loaiHangService.findById(id).isPresent()) {
            loaiHangService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
