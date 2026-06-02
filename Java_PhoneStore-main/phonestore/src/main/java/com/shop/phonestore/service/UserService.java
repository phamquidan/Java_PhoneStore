package com.shop.phonestore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.repository.KhachHangRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final KhachHangRepository khachHangRepository;

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> findById(Long id) {
        return khachHangRepository.findById(id);
    }

    public Optional<KhachHang> findByEmail(String email) {
        return khachHangRepository.findByEmail(email);
    }

    public KhachHang save(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }
}
