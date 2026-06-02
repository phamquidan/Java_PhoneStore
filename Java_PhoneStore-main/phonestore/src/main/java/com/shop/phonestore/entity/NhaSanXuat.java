package com.shop.phonestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NHASANXUAT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhaSanXuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANSX")
    private Long id;

    @Column(name = "TENNSX", nullable = false, unique = true, length = 100)
    private String tenNhaSanXuat;

    @Column(name = "SDT", length = 11)
    private String soDienThoai;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "DIACHI", length = 255)
    private String diaChi;
}
