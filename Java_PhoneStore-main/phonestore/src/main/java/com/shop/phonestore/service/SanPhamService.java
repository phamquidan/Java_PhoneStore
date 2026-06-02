package com.shop.phonestore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.SanPham;
import com.shop.phonestore.repository.SanPhamRepository;

@Service
@RequiredArgsConstructor
public class SanPhamService {

    private final SanPhamRepository sanPhamRepository;

    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    public Optional<SanPham> findById(Long id) {
        return sanPhamRepository.findById(id);
    }

    public List<SanPham> findByLoai(Long loaiHangId) {
        return sanPhamRepository.findByLoaiHangId(loaiHangId);
    }

    public List<SanPham> search(String keyword) {
        return sanPhamRepository.findByTenSanPhamContainingIgnoreCase(keyword);
    }

    public Page<SanPham> searchAndFilter(String keyword, Long loaiHangId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasLoai = loaiHangId != null;

        if (hasKeyword && hasLoai) {
            return sanPhamRepository.findByTenSanPhamContainingIgnoreCaseAndLoaiHangId(keyword.trim(), loaiHangId, pageable);
        }
        if (hasKeyword) {
            return sanPhamRepository.findByTenSanPhamContainingIgnoreCase(keyword.trim(), pageable);
        }
        if (hasLoai) {
            return sanPhamRepository.findByLoaiHangId(loaiHangId, pageable);
        }
        return sanPhamRepository.findAll(pageable);
    }

    public SanPham save(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    public void deleteById(Long id) {
        sanPhamRepository.deleteById(id);
    }
}
