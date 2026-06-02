package com.shop.phonestore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.entity.NhanVien;
import com.shop.phonestore.entity.Role;
import com.shop.phonestore.entity.TaiKhoan;
import com.shop.phonestore.repository.KhachHangRepository;
import com.shop.phonestore.repository.NhanVienRepository;
import com.shop.phonestore.repository.TaiKhoanRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TaiKhoanRepository taiKhoanRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedUsers() {
        return args -> {
            if (taiKhoanRepository.findByTenDangNhap("admin").isEmpty()) {
                NhanVien nhanVien = nhanVienRepository.save(NhanVien.builder()
                        .hoTen("Quan Tri Vien")
                        .email("admin@shop.vn")
                        .soDienThoai("0900000001")
                        .chucVu("ADMIN")
                        .build());

                taiKhoanRepository.save(TaiKhoan.builder()
                        .tenDangNhap("admin")
                        .matKhau(passwordEncoder.encode("123456"))
                        .role(Role.ADMIN)
                        .kichHoat(true)
                        .nhanVien(nhanVien)
                        .build());
            }

            if (taiKhoanRepository.findByTenDangNhap("user").isEmpty()) {
                KhachHang khachHang = khachHangRepository.save(KhachHang.builder()
                        .hoTen("Khach Hang Mac Dinh")
                        .email("user@shop.vn")
                        .soDienThoai("0900000002")
                        .diaChi("TP.HCM")
                        .build());

                taiKhoanRepository.save(TaiKhoan.builder()
                        .tenDangNhap("user")
                        .matKhau(passwordEncoder.encode("user123"))
                        .role(Role.USER)
                        .kichHoat(true)
                        .khachHang(khachHang)
                        .build());
            }
        };
    }
}
