package com.shop.phonestore.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GIOHANG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAGH")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH", nullable = false, unique = true)
    private KhachHang khachHang;

    @Column(name = "NGAYTAO")
    @Builder.Default
    private LocalDateTime ngayCapNhat = LocalDateTime.now();
}
