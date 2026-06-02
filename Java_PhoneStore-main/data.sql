
USE QL_CUAHANGDIENTHOAI
GO

-- =========================
-- LOAI HANG
-- =========================

INSERT INTO LOAIHANG(TENLOAI)
VALUES
(N'Điện thoại Android'),
(N'iPhone'),
(N'Máy tính bảng'),
(N'Đồng hồ thông minh'),
(N'Tai nghe Bluetooth'),
(N'Phụ kiện điện thoại');

-- =========================
-- NHA SAN XUAT
-- =========================

INSERT INTO NHASANXUAT(TENNSX, SDT, EMAIL, DIACHI)
VALUES
(N'Samsung', '0901111111', 'samsung@gmail.com', N'Hàn Quốc'),
(N'Apple', '0902222222', 'apple@gmail.com', N'Mỹ'),
(N'Xiaomi', '0903333333', 'xiaomi@gmail.com', N'Trung Quốc'),
(N'Oppo', '0904444444', 'oppo@gmail.com', N'Trung Quốc'),
(N'Vivo', '0905555555', 'vivo@gmail.com', N'Trung Quốc');

-- =========================
-- NHAN VIEN
-- =========================

INSERT INTO NHANVIEN
(HOTEN, NGAYSINH, GIOITINH, DIACHI, SDT, EMAIL, CHUCVU)
VALUES
(N'Nguyễn Văn Admin', '1995-01-01', N'Nam', N'Hồ Chí Minh', '0911111111', 'admin@shop.com', N'Quản trị'),
(N'Trần Thị Nhân Viên', '1998-05-10', N'Nữ', N'Hồ Chí Minh', '0922222222', 'staff@shop.com', N'Nhân viên');

-- =========================
-- KHACH HANG
-- =========================

INSERT INTO KHACHHANG
(HOTEN, NGAYSINH, GIOITINH, DIACHI, SDT, EMAIL)
VALUES
(N'Lê Văn A', '2000-01-01', N'Nam', N'Hà Nội', '0933333333', 'khach1@gmail.com'),
(N'Nguyễn Thị B', '2001-02-02', N'Nữ', N'Đà Nẵng', '0944444444', 'khach2@gmail.com'),
(N'Phạm Văn C', '1999-03-03', N'Nam', N'Cần Thơ', '0955555555', 'khach3@gmail.com');

-- =========================
-- TAI KHOAN
-- =========================

INSERT INTO TAIKHOAN
(TENDANGNHAP, MATKHAU, VAITRO, MANV)
VALUES
('admin', '$2a$10$1CmDPFk/XadXuffkk2.AquQWe2CAfa.ITG7l2CgNOkF5BCYJhZcX2', 'ADMIN', 1),
('staff01', '$2a$10$1CmDPFk/XadXuffkk2.AquQWe2CAfa.ITG7l2CgNOkF5BCYJhZcX2', 'STAFF', 2);

INSERT INTO TAIKHOAN
(TENDANGNHAP, MATKHAU, VAITRO, MAKH)
VALUES
('khach01', '$2a$10$1CmDPFk/XadXuffkk2.AquQWe2CAfa.ITG7l2CgNOkF5BCYJhZcX2', 'USER', 1),
('khach02', '$2a$10$1CmDPFk/XadXuffkk2.AquQWe2CAfa.ITG7l2CgNOkF5BCYJhZcX2', 'USER', 2);

-- =========================
-- SAN PHAM
-- =========================

INSERT INTO HANGHOA
(
    TENHG,
    MOTA,
    DVT,
    SOLUONGTON,
    GIANHAP,
    GIABAN,
    HINHANH,
    MALOAI,
    MANSX
)
VALUES
(
    N'Samsung Galaxy S24 Ultra',
    N'Điện thoại flagship Samsung',
    N'Cái',
    20,
    25000000,
    29990000,
    N's24ultra.jpg',
    1,
    1
),

(
    N'iPhone 15 Pro Max',
    N'Điện thoại cao cấp Apple',
    N'Cái',
    15,
    30000000,
    34990000,
    N'iphone15promax.jpg',
    2,
    2
),

(
    N'Xiaomi 14',
    N'Điện thoại Xiaomi hiệu năng cao',
    N'Cái',
    30,
    18000000,
    21990000,
    N'xiaomi14.jpg',
    1,
    3
),

(
    N'Oppo Reno 11',
    N'Điện thoại Oppo camera đẹp',
    N'Cái',
    25,
    7000000,
    8990000,
    N'opporeno11.jpg',
    1,
    4
),

(
    N'Vivo V30',
    N'Điện thoại Vivo pin khỏe',
    N'Cái',
    18,
    9000000,
    10990000,
    N'vivov30.jpg',
    1,
    5
),

(
    N'Apple Watch Series 9',
    N'Đồng hồ thông minh Apple',
    N'Cái',
    12,
    8500000,
    10990000,
    N'applewatchs9.jpg',
    4,
    2
),

(
    N'AirPods Pro 2',
    N'Tai nghe Apple chống ồn',
    N'Cái',
    40,
    4500000,
    5990000,
    N'airpodspro2.jpg',
    5,
    2
);

-- =========================
-- HINH SAN PHAM
-- =========================

INSERT INTO HINHSANPHAM(MAHG, DUONGDAN)
VALUES
(1, N's24_1.jpg'),
(1, N's24_2.jpg'),
(2, N'iphone15_1.jpg'),
(2, N'iphone15_2.jpg'),
(3, N'xiaomi14_1.jpg'),
(4, N'oppo11_1.jpg'),
(5, N'vivo30_1.jpg');

-- =========================
-- HOA DON
-- =========================

INSERT INTO HOADON
(
    TONGTIEN,
    TRANGTHAI,
    PHUONGTHUC_THANHTOAN,
    MAKH,
    MANV
)
VALUES
(
    29990000,
    N'Hoàn thành',
    N'Tiền mặt',
    1,
    2
),

(
    34990000,
    N'Hoàn thành',
    N'Chuyển khoản',
    2,
    2
);

-- =========================
-- CHI TIET HOA DON
-- =========================

INSERT INTO CHITIETHD
(MAHD, MAHG, SOLUONG, GIABAN)
VALUES
(1, 1, 1, 29990000),
(2, 2, 1, 34990000);

-- =========================
-- NHA CUNG CAP
-- =========================

INSERT INTO NHACUNGCAP
(TENNCC, SDT, EMAIL, DIACHI)
VALUES
(N'Công ty Mobile Việt', '0906666666', 'mobileviet@gmail.com', N'Hồ Chí Minh'),
(N'Công ty Apple Center', '0907777777', 'applecenter@gmail.com', N'Hà Nội');

-- =========================
-- PHIEU NHAP
-- =========================

INSERT INTO PHIEUNHAP
(TONGTIEN, MANCC, MANV)
VALUES
(50000000, 1, 1),
(60000000, 2, 1);

-- =========================
-- CHI TIET PHIEU NHAP
-- =========================

INSERT INTO CHITIETPN
(MAPN, MAHG, SOLUONG, GIANHAP)
VALUES
(1, 1, 10, 25000000),
(2, 2, 10, 30000000);

-- =========================
-- GIO HANG
-- =========================

INSERT INTO GIOHANG(MAKH)
VALUES
(1),
(2);

-- =========================
-- CHI TIET GIO HANG
-- =========================

INSERT INTO CHITIETGIOHANG
(MAGH, MAHG, SOLUONG)
VALUES
(1, 1, 1),
(1, 7, 2),
(2, 2, 1);
