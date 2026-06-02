package com.shop.phonestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LOAIHANG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOAI")
    private Long id;

    @Column(name = "TENLOAI", nullable = false, unique = true, length = 100)
    private String tenLoai;
}
