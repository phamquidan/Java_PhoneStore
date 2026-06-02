package com.shop.phonestore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "KHACHHANG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKH")
    private Long id;

    @Column(name = "HOTEN", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "NGAYSINH")
    private LocalDate ngaySinh;

    @Column(name = "GIOITINH", length = 10)
    private String gioiTinh;

    @Column(name = "DIACHI", length = 255)
    private String diaChi;

    @Column(name = "SDT", nullable = false, unique = true, length = 11)
    private String soDienThoai;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "NGAYTAO")
    @Builder.Default
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "TRANGTHAI")
    @Builder.Default
    private Boolean trangThai = true;

    @OneToOne(mappedBy = "khachHang", fetch = FetchType.LAZY)
    private GioHang gioHang;

    @OneToOne(mappedBy = "khachHang", fetch = FetchType.LAZY)
    private TaiKhoan taiKhoan;
}
