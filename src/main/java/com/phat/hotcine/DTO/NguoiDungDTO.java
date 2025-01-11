package com.phat.hotcine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO {
    private Integer maNguoiDung;
    private String tenKhachHang;
    private String soDienThoai;
    private String email;
    private Integer maHoiVien;
    private Boolean nhanVien;
    private String matKhau;
    private String hinhAnh;

   }
