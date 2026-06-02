package com.shop.phonestore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.LoaiHang;
import com.shop.phonestore.repository.LoaiHangRepository;

@Service
@RequiredArgsConstructor
public class LoaiHangService {

    private final LoaiHangRepository loaiHangRepository;

    public List<LoaiHang> findAll() {
        return loaiHangRepository.findAll();
    }

    public Optional<LoaiHang> findById(Long id) {
        return loaiHangRepository.findById(id);
    }

    public LoaiHang save(LoaiHang loaiHang) {
        return loaiHangRepository.save(loaiHang);
    }

    public void deleteById(Long id) {
        loaiHangRepository.deleteById(id);
    }
}
