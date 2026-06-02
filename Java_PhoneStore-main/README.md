# 📱 PhoneStore - Hệ Thống Quản Lý Cửa Hàng Điện Thoại & RESTful API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2F%204.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![SQL Server](https://img.shields.io/badge/SQL%20Server-2019%2B-red.svg)](https://www.microsoft.com/sql-server)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**PhoneStore** là hệ thống quản lý bán hàng thiết bị di động toàn diện, được xây dựng trên nền tảng Spring Boot mạnh mẽ, kết xuất giao diện động bằng Thymeleaf tối giản cao cấp (theo phong cách thiết kế TechSophist) kết hợp cùng hệ thống **RESTful API chuẩn doanh nghiệp** phục vụ đa nền tảng (Mobile, Single Page Application).

---

## 🎯 Điểm Nổi Bật Của Hệ Thống

### 1. 🎨 Giao Diện Quản Trị TechSophist Cao Cấp (Figma-Aligned)

- **Thanh công cụ hợp nhất (`admin-header`)**: Tránh trùng lặp mã bằng cách gộp toàn bộ header của 6 trang quản trị vào một fragment Thymeleaf duy nhất.
- **Hộp thoại thông minh (Dropdown Cards)**: Thay thế hoàn toàn các hộp thoại mặc định của trình duyệt (`alert()`) bằng các dropdown trượt mượt mà (Chuông thông báo, Bánh răng cài đặt, Trung tâm trợ giúp) với hiệu ứng bóng đổ và hover đổi màu Teal (`#00687a`).
- **Bảng điều khiển trực quan (Dashboard UI)**: Tích hợp các biểu đồ hình cột Doanh thu (Bar Chart) bằng HTML/CSS nguyên bản và biểu đồ phân bổ doanh số (SVG Donut Chart) bóng bẩy.
- **Bộ lọc và phân trang tự động**: Tối ưu hóa bộ lọc Tab trạng thái đơn hàng và phân trang danh sách thời gian thực qua JavaScript.

### 2. ⚡ Hệ Thống RESTful API Chuẩn Doanh Nghiệp (Version 1.0)

- **Full CRUD Endpoints**: Cho phép lấy danh sách, tìm kiếm, thêm mới, sửa đổi và xóa các thực thể `SanPham`, `LoaiHang`, và `DonHang`.
- **Cơ chế Clean Code (Lombok Constructor Injection)**: Sử dụng `@RequiredArgsConstructor` thay cho `@Autowired` giúp bảo mật mã nguồn và dễ viết kiểm thử tự động.
- **Hỗ trợ kiểm thử không biên giới (No-CSRF Security Config)**: Tắt kiểm tra CSRF và mở quyền công khai đối với tiền tố `/api/**` để test API thuận tiện qua Postman/PowerShell mà không cần đăng nhập.

---

## 🏗️ Cấu Trúc Thư Mục Dự Án

```
d:\phonestore / D:\project
├── D:\project\project\                  # Dự án chạy thực tế (Active Project)
│   ├── src/main/java/phoneshop/com/project/
│   │   ├── config/                     # Cấu hình bảo mật SecurityConfig, DataInitializer
│   │   ├── controller/                 # Web MVC Controllers
│   │   │   └── api/                    # [MỚI] RESTful API Controllers (LoaiHang, SanPham, DonHang)
│   │   ├── entity/                     # JPA Entities (SanPham, LoaiHang, DonHang, etc.)
│   │   ├── repository/                 # Spring Data JPA Repositories
│   │   └── service/                    # Layer xử lý logic nghiệp vụ (Business Service)
│   ├── src/main/resources/
│   │   ├── templates/                  # Giao diện Thymeleaf HTML
│   │   ├── static/                     # Assets tĩnh (CSS, JS, Images)
│   │   └── application.properties       # Cấu hình cổng 8081 và database
│   └── pom.xml
└── d:\phonestore\                       # Thư mục gốc lưu trữ tài liệu & file cấu trúc
```

---

## 📋 Chi Tiết Danh Sách API Endpoints

### 📦 1. API Danh mục loại hàng (`/api/loai-hang`)

| Method     | Endpoint              | Mô tả                              | Định dạng dữ liệu (JSON) / Params  |
| :--------- | :-------------------- | :--------------------------------- | :--------------------------------- |
| **GET**    | `/api/loai-hang`      | Lấy danh sách tất cả các loại hàng | Không có                           |
| **GET**    | `/api/loai-hang/{id}` | Lấy chi tiết loại hàng theo ID     | Không có                           |
| **POST**   | `/api/loai-hang`      | Tạo mới một loại hàng mới          | `{"tenLoai": "Tên loại sản phẩm"}` |
| **PUT**    | `/api/loai-hang/{id}` | Cập nhật thông tin loại hàng       | `{"tenLoai": "Tên cập nhật"}`      |
| **DELETE** | `/api/loai-hang/{id}` | Xóa loại hàng theo ID              | Không có                           |

### 📱 2. API Sản phẩm (`/api/san-pham`)

| Method     | Endpoint                    | Mô tả                           | Định dạng dữ liệu (JSON) / Params |
| :--------- | :-------------------------- | :------------------------------ | :-------------------------------- |
| **GET**    | `/api/san-pham`             | Lấy danh sách sản phẩm          | Không có                          |
| **GET**    | `/api/san-pham?maLoai={id}` | Lọc sản phẩm theo mã loại hàng  | `maLoai` (RequestParam)           |
| **GET**    | `/api/san-pham?q={keyword}` | Tìm kiếm sản phẩm theo tên      | `q` (RequestParam)                |
| **GET**    | `/api/san-pham/{id}`        | Lấy chi tiết thông tin sản phẩm | Không có                          |
| **POST**   | `/api/san-pham`             | Thêm mới một sản phẩm           | JSON đối tượng `SanPham`          |
| **PUT**    | `/api/san-pham/{id}`        | Cập nhật thông tin sản phẩm     | JSON đối tượng `SanPham`          |
| **DELETE** | `/api/san-pham/{id}`        | Xóa sản phẩm theo ID            | Không có                          |

### 📝 3. API Đơn hàng (`/api/don-hang`)

| Method   | Endpoint                        | Mô tả                                 | Định dạng dữ liệu (JSON) / Params |
| :------- | :------------------------------ | :------------------------------------ | :-------------------------------- |
| **GET**  | `/api/don-hang`                 | Lấy danh sách toàn bộ các hóa đơn     | Không có                          |
| **GET**  | `/api/don-hang/{id}`            | Lấy thông tin đơn hàng theo ID        | Không có                          |
| **GET**  | `/api/don-hang/{id}/chi-tiet`   | Lấy chi tiết các sản phẩm đã mua      | Không có                          |
| **POST** | `/api/don-hang`                 | Tạo đơn hàng mới từ giỏ hàng hiện tại | `maKH` (RequestParam)             |
| **PUT**  | `/api/don-hang/{id}/trang-thai` | Cập nhật trạng thái đơn hàng          | `trangThai` (RequestParam)        |

---

## 🚀 Hướng Dẫn Cài Đặt & Sử Dụng

### 1. Cấu hình Database

Đảm bảo bạn đã khởi chạy SQL Server và import cơ sở dữ liệu thành công:

- Chạy file `QL_CUAHANGDIENTHOAI.sql` để tạo cấu trúc bảng.
- Chạy file `data.sql` để import dữ liệu dùng thử mẫu.

### 2. Cấu hình Kết nối (`application.properties`)

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=QL_CUAHANGDIENTHOAI;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=123456
server.port=8081
```

### 3. Build & Khởi Chạy Ứng Dụng

Mở PowerShell tại thư mục `D:\project\project` và gõ các lệnh sau:

- **Biên dịch sạch dự án**:

  ```powershell
  $env:JAVA_HOME="d:\jdk-21_windows-x64_bin\jdk-21.0.11"
  .\mvnw.cmd clean compile
  ```

- **Khởi chạy ứng dụng**:
  ```powershell
  $env:JAVA_HOME="d:\jdk-21_windows-x64_bin\jdk-21.0.11"
  .\mvnw.cmd spring-boot:run
  ```

---

## 🧪 Hướng Dẫn Kiểm Thử Bằng PowerShell

Sau khi ứng dụng khởi chạy thành công trên cổng `8081`, bạn có thể mở một cửa sổ PowerShell mới để gọi thử nghiệm:

- **Kiểm thử lấy danh sách sản phẩm (GET)**:

  ```powershell
  Invoke-RestMethod -Uri "http://localhost:8081/api/san-pham" -Method Get
  ```

- **Kiểm thử tìm kiếm sản phẩm theo tên (GET)**:

  ```powershell
  Invoke-RestMethod -Uri "http://localhost:8081/api/san-pham?q=iPhone" -Method Get
  ```

- **Kiểm thử thêm mới danh mục loại hàng (POST)**:

  ```powershell
  $body = @{ tenLoai = "Thiết bị đeo thông minh" } | ConvertTo-Json -Compress
  Invoke-RestMethod -Uri "http://localhost:8081/api/loai-hang" -Method Post -Body $body -ContentType "application/json; charset=utf-8"
  ```

- **Cập nhật trạng thái đơn hàng (PUT)**:
  ```powershell
  Invoke-RestMethod -Uri "http://localhost:8081/api/don-hang/1/trang-thai?trangThai=Hoàn thành" -Method Put
  ```

---

## 👤 Tài Khoản Mẫu (Dữ liệu thử nghiệm)

- **Tài khoản quản trị (Admin)**: `admin` / mật khẩu mặc định (mã hóa BCrypt).
- **Quyền hạn**:
  - Truy cập Web Portal: Đăng nhập trực tiếp tại đường dẫn `http://localhost:8081/login`.
  - Kiểm thử API: Mở hoàn toàn không giới hạn quyền thông qua cấu hình bảo mật `SecurityConfig.java`.
