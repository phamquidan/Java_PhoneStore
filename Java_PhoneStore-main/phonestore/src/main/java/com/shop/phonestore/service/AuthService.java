package com.shop.phonestore.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.entity.Role;
import com.shop.phonestore.entity.TaiKhoan;
import com.shop.phonestore.repository.KhachHangRepository;
import com.shop.phonestore.repository.TaiKhoanRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final KhachHangRepository khachHangRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TaiKhoan register(String tenDangNhap, String matKhau, KhachHang khachHang) {
        if (taiKhoanRepository.findByTenDangNhap(tenDangNhap).isPresent()) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại!");
        }
        khachHangRepository.save(khachHang);
        TaiKhoan taiKhoan = TaiKhoan.builder()
                .tenDangNhap(tenDangNhap)
                .matKhau(passwordEncoder.encode(matKhau))
                .role(Role.USER)
                .kichHoat(true)
                .khachHang(khachHang)
                .build();
        return taiKhoanRepository.save(taiKhoan);
    }

    public Optional<TaiKhoan> login(String tenDangNhap, String matKhau) {
        return taiKhoanRepository.findByTenDangNhap(tenDangNhap)
                .filter(t -> passwordEncoder.matches(matKhau, t.getMatKhau()));
    }
}
