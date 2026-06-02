# PhoneStore MVC Structure Guide

## Muc tieu
- Quan ly file gon, de tim, de onboard.
- Chuan hoa phat trien theo mo hinh MVC.
- Tranh de logic nghiep vu trong controller.

## Cau truc de xuat
```text
phonestore/
  src/main/java/com/shop/phonestore/
    controller/   # xu ly request, mapping URL, tra ve view
    service/      # business logic
    repository/   # truy cap du lieu JPA
    model/        # entity va domain object
    config/       # security, mvc config, bean config
  src/main/resources/
    templates/    # view Thymeleaf
    static/       # css, js, images
    application.properties
```

## Quy uoc theo tang
- controller:
  - Chi xu ly input/output, validate co ban.
  - Khong chua truy van DB truc tiep.
- service:
  - Chua luat nghiep vu.
  - Neu thao tac nhieu bang trong mot use case, uu tien `@Transactional`.
- repository:
  - Interface JPA, custom query methods.
- model:
  - Entity + mapping bang.
  - Han che logic nghiep vu phuc tap.

## Quy uoc dat ten
- Controller: `*Controller`
- Service: `*Service`
- Repository: `*Repository`
- Entity/Model: danh tu domain (`SanPham`, `DonHang`, ...)
- Template:
  - User: `san-pham-list.html`, `gio-hang-view.html`
  - Admin: `admin-dashboard.html`, `san-pham-admin-list.html`

## Quan ly file workspace
- Script thu cong dat trong `scripts/`.
- Tai lieu dat trong `docs/`.
- Khong luu file build (`target/`) vao source.
- Khong luu report tam (vi du completion report) trong root.
