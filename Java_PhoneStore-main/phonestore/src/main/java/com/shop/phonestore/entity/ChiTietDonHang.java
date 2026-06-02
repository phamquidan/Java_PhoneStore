package com.shop.phonestore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CHITIETHD")
@IdClass(ChiTietDonHangId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietDonHang implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAHD", nullable = false)
    private DonHang donHang;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAHG", nullable = false)
    private SanPham sanPham;

    @Column(name = "SOLUONG", nullable = false)
    private Integer soLuong;

    @Column(name = "GIABAN", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGia;
}
