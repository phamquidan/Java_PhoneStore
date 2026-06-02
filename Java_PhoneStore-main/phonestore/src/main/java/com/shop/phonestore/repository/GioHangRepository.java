package com.shop.phonestore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.GioHang;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> findByKhachHangId(Long khachHangId);
}
