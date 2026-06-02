package com.shop.phonestore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    Optional<KhachHang> findByEmail(String email);
    Optional<KhachHang> findBySoDienThoai(String soDienThoai);
}
