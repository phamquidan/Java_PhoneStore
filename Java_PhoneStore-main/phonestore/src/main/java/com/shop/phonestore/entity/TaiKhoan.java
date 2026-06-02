package com.shop.phonestore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAIKHOAN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATK")
    private Long id;

    @Column(name = "TENDANGNHAP", nullable = false, unique = true, length = 50)
    private String tenDangNhap;

    @Column(name = "MATKHAU", nullable = false, length = 255)
    private String matKhau;

    @Enumerated(EnumType.STRING)
    @Column(name = "VAITRO", nullable = false, length = 30)
    private Role role;

    @Column(name = "NGAYTAO")
    @Builder.Default
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "TRANGTHAI")
    @Builder.Default
    private Boolean kichHoat = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH")
    private KhachHang khachHang;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANV")
    private NhanVien nhanVien;
}
