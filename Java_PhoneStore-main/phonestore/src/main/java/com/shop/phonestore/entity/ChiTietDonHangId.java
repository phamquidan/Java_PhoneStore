package com.shop.phonestore.entity;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDonHangId implements Serializable {
    private Long donHang;
    private Long sanPham;
}
