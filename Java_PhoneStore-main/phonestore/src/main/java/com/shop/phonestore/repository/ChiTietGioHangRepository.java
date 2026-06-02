package com.shop.phonestore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.ChiTietGioHang;
import com.shop.phonestore.entity.ChiTietGioHangId;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, ChiTietGioHangId> {
    List<ChiTietGioHang> findByGioHangId(Long gioHangId);
}
