package com.shop.phonestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HINHSANPHAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HinhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHINH")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAHG", nullable = false)
    private SanPham sanPham;

    @Column(name = "DUONGDAN", nullable = false, length = 255)
    private String duongDan;
}
