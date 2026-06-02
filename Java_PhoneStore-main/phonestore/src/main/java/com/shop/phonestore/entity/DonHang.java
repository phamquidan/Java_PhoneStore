package com.shop.phonestore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HOADON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHD")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANV")
    private NhanVien nhanVien;

    @Column(name = "NGAYLAP", nullable = false)
    @Builder.Default
    private LocalDateTime ngayDat = LocalDateTime.now();

    @Column(name = "TONGTIEN", nullable = false, precision = 18, scale = 2)
    @Builder.Default
    private BigDecimal tongTien = BigDecimal.ZERO;

    @Column(name = "TRANGTHAI", nullable = false, length = 50)
    private String trangThai;

    @Column(name = "PHUONGTHUC_THANHTOAN", length = 50)
    private String phuongThucThanhToan;

    @OneToMany(mappedBy = "donHang", fetch = FetchType.LAZY)
    @Builder.Default
    private java.util.List<ChiTietDonHang> items = new java.util.ArrayList<>();
}
