package com.shop.phonestore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.DonHang;
import com.shop.phonestore.entity.ChiTietDonHang;
import com.shop.phonestore.entity.KhachHang;
import com.shop.phonestore.repository.DonHangRepository;
import com.shop.phonestore.repository.ChiTietDonHangRepository;
import com.shop.phonestore.repository.GioHangRepository;
import com.shop.phonestore.repository.ChiTietGioHangRepository;
import com.shop.phonestore.repository.KhachHangRepository;
import com.shop.phonestore.repository.SanPhamRepository;

@Service
@RequiredArgsConstructor
public class DonHangService {

    private final DonHangRepository donHangRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;
    private final GioHangRepository gioHangRepository;
    private final ChiTietGioHangRepository chiTietGioHangRepository;
    private final KhachHangRepository khachHangRepository;
    private final SanPhamRepository sanPhamRepository;

    public List<ChiTietDonHang> findItemsByDonHangId(Long donHangId) {
        return chiTietDonHangRepository.findByDonHangId(donHangId);
    }

    public List<DonHang> findAll() {
        return donHangRepository.findAll();
    }

    public Page<DonHang> findPage(int page, int size) {
        return donHangRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }

    public Optional<DonHang> findById(Long id) {
        return donHangRepository.findById(id);
    }

    public List<DonHang> findByKhachHang(Long khachHangId) {
        return donHangRepository.findByKhachHangId(khachHangId);
    }

    public DonHang save(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Transactional
    public DonHang checkout(KhachHang khachHang, String hoTen, String soDienThoai, String email, String diaChi, String phuongThucThanhToan) {
        var gioHangOpt = gioHangRepository.findByKhachHangId(khachHang.getId());
        if (gioHangOpt.isEmpty()) {
            return null;
        }
        var gioHang = gioHangOpt.get();
        var items = chiTietGioHangRepository.findByGioHangId(gioHang.getId());
        if (items.isEmpty()) {
            return null;
        }

        // 1. Update customer profile details
        khachHang.setHoTen(hoTen);
        khachHang.setSoDienThoai(soDienThoai);
        khachHang.setEmail(email);
        khachHang.setDiaChi(diaChi);
        khachHangRepository.save(khachHang);

        // 2. Calculate total
        java.math.BigDecimal total = items.stream()
            .map(item -> item.getSanPham().getGia().multiply(java.math.BigDecimal.valueOf(item.getSoLuong())))
            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        // 3. Create order
        String orderStatus = "Chuyển khoản".equalsIgnoreCase(phuongThucThanhToan) ? "Chờ thanh toán" : "Hoàn thành";
        DonHang donHang = DonHang.builder()
                .khachHang(khachHang)
                .ngayDat(java.time.LocalDateTime.now())
                .tongTien(total)
                .trangThai(orderStatus)
                .phuongThucThanhToan(phuongThucThanhToan)
                .build();
        donHang = donHangRepository.save(donHang);

        // 4. Save order details and check/update stock levels
        for (var item : items) {
            var sanPham = item.getSanPham();
            if (sanPham.getSoLuongTon() < item.getSoLuong()) {
                throw new RuntimeException("Sản phẩm '" + sanPham.getTenSanPham() + "' không đủ số lượng hàng tồn kho! (Còn lại: " + sanPham.getSoLuongTon() + ")");
            }
            
            // Decrement stock
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() - item.getSoLuong());
            sanPhamRepository.save(sanPham);

            ChiTietDonHang ctdh = ChiTietDonHang.builder()
                    .donHang(donHang)
                    .sanPham(sanPham)
                    .soLuong(item.getSoLuong())
                    .donGia(sanPham.getGia())
                    .build();
            chiTietDonHangRepository.save(ctdh);
        }

        // 5. Clear cart
        chiTietGioHangRepository.deleteAll(items);

        return donHang;
    }
}
