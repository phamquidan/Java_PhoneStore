package com.shop.phonestore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HANGHOA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHG")
    private Long id;

    @Column(name = "TENHG", nullable = false, length = 255)
    private String tenSanPham;

    @Column(name = "MOTA")
    private String moTa;

    @Column(name = "DVT", length = 20)
    private String dvt;

    @Column(name = "SOLUONGTON")
    @Builder.Default
    private Integer soLuongTon = 0;

    @Column(name = "GIANHAP", precision = 18, scale = 2)
    private BigDecimal giaNhap;

    @Column(name = "GIABAN", nullable = false, precision = 18, scale = 2)
    private BigDecimal gia;

    @Column(name = "HINHANH", length = 255)
    private String anhUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MALOAI", nullable = false)
    private LoaiHang loaiHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANSX", nullable = false)
    private NhaSanXuat nhaSanXuat;

    @Column(name = "NGAYTAO")
    @Builder.Default
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "NGAYCAPNHAT")
    @Builder.Default
    private LocalDateTime ngayCapNhat = LocalDateTime.now();

    @Column(name = "TRANGTHAI")
    @Builder.Default
    private Boolean trangThai = true;
}
