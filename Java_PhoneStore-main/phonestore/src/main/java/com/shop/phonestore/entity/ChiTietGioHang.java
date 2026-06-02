package com.shop.phonestore.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CHITIETGIOHANG")
@IdClass(ChiTietGioHangId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietGioHang implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGH", nullable = false)
    private GioHang gioHang;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAHG", nullable = false)
    private SanPham sanPham;

    @Column(name = "SOLUONG", nullable = false)
    @Builder.Default
    private Integer soLuong = 1;
}
