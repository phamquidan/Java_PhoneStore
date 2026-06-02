package com.shop.phonestore.service;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.shop.phonestore.entity.TaiKhoan;
import com.shop.phonestore.repository.TaiKhoanRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(tenDangNhap)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tim thay tai khoan"));

        String authority = "ROLE_" + taiKhoan.getRole().name();
        return new User(
                taiKhoan.getTenDangNhap(),
                taiKhoan.getMatKhau(),
                taiKhoan.getKichHoat(),
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority(authority)));
    }
}
