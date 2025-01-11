package com.phat.hotcine.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Nguoidung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Manguoidung")
    private Integer maNguoiDung;

    @Column(name = "Tenkhachhang")
    private String tenKhachHang;

    @Column(name = "Sodienthoai")
    private String soDienThoai;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Mahoivien")
    private HoiVien hoivien;

    @Column(name = "Nhanvien")
    private Boolean nhanVien;

    @Column(name = "Matkhau")
    private String matKhau;

    @OneToMany(mappedBy = "nguoiDung")
    private List<BinhLuan> binhLuans;

    @OneToMany(mappedBy = "maNguoiDung")
    private List<Ve> ves;

}
