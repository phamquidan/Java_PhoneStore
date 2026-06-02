package com.shop.phonestore.entity;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietGioHangId implements Serializable {
    private Long gioHang;
    private Long sanPham;
}
