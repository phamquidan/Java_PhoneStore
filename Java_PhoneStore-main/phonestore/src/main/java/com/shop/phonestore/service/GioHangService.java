package com.shop.phonestore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.ChiTietGioHang;
import com.shop.phonestore.entity.ChiTietGioHangId;
import com.shop.phonestore.entity.GioHang;
import com.shop.phonestore.entity.SanPham;
import com.shop.phonestore.repository.ChiTietGioHangRepository;
import com.shop.phonestore.repository.GioHangRepository;
import com.shop.phonestore.repository.SanPhamRepository;

@Service
@RequiredArgsConstructor
public class GioHangService {

    private final GioHangRepository gioHangRepository;
    private final ChiTietGioHangRepository chiTietGioHangRepository;
    private final SanPhamRepository sanPhamRepository;

    public Optional<GioHang> findByKhachHangId(Long khachHangId) {
        return gioHangRepository.findByKhachHangId(khachHangId);
    }

    public List<ChiTietGioHang> findItemsByGioHangId(Long gioHangId) {
        return chiTietGioHangRepository.findByGioHangId(gioHangId);
    }

    @Transactional
    public void addItem(GioHang gioHang, Long sanPhamId, Integer soLuong) {
        ChiTietGioHangId id = new ChiTietGioHangId(gioHang.getId(), sanPhamId);
        Optional<ChiTietGioHang> existingItemOpt = chiTietGioHangRepository.findById(id);
        if (existingItemOpt.isPresent()) {
            ChiTietGioHang item = existingItemOpt.get();
            item.setSoLuong(item.getSoLuong() + soLuong);
            chiTietGioHangRepository.save(item);
        } else {
            SanPham sanPham = sanPhamRepository.findById(sanPhamId).orElseThrow();
            ChiTietGioHang item = ChiTietGioHang.builder()
                    .gioHang(gioHang)
                    .sanPham(sanPham)
                    .soLuong(soLuong)
                    .build();
            chiTietGioHangRepository.save(item);
        }
        gioHang.setNgayCapNhat(LocalDateTime.now());
        gioHangRepository.save(gioHang);
    }

    @Transactional
    public void updateQuantity(GioHang gioHang, Long sanPhamId, String action) {
        ChiTietGioHangId id = new ChiTietGioHangId(gioHang.getId(), sanPhamId);
        Optional<ChiTietGioHang> itemOpt = chiTietGioHangRepository.findById(id);
        if (itemOpt.isPresent()) {
            ChiTietGioHang item = itemOpt.get();
            if ("increase".equals(action)) {
                item.setSoLuong(item.getSoLuong() + 1);
                chiTietGioHangRepository.save(item);
            } else if ("decrease".equals(action) && item.getSoLuong() > 1) {
                item.setSoLuong(item.getSoLuong() - 1);
                chiTietGioHangRepository.save(item);
            }
            gioHang.setNgayCapNhat(LocalDateTime.now());
            gioHangRepository.save(gioHang);
        }
    }

    @Transactional
    public void removeItem(GioHang gioHang, Long sanPhamId) {
        ChiTietGioHangId id = new ChiTietGioHangId(gioHang.getId(), sanPhamId);
        if (chiTietGioHangRepository.existsById(id)) {
            chiTietGioHangRepository.deleteById(id);
            gioHang.setNgayCapNhat(LocalDateTime.now());
            gioHangRepository.save(gioHang);
        }
    }
}
