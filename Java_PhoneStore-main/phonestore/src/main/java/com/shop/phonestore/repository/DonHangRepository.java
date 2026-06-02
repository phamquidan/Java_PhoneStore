package com.shop.phonestore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.DonHang;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByKhachHangId(Long khachHangId);
}
