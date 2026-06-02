package com.shop.phonestore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.ChiTietDonHang;
import com.shop.phonestore.entity.ChiTietDonHangId;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, ChiTietDonHangId> {
    List<ChiTietDonHang> findByDonHangId(Long donHangId);
}
