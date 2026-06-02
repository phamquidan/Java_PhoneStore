package com.shop.phonestore.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.phonestore.entity.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByLoaiHangId(Long loaiHangId);

    List<SanPham> findByTenSanPhamContainingIgnoreCase(String keyword);

    Page<SanPham> findByTenSanPhamContainingIgnoreCase(String keyword, Pageable pageable);

    Page<SanPham> findByLoaiHangId(Long loaiHangId, Pageable pageable);

    Page<SanPham> findByTenSanPhamContainingIgnoreCaseAndLoaiHangId(String keyword, Long loaiHangId, Pageable pageable);
}
