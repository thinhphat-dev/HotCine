package com.phat.hotcine.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Phim")
public class Phim {
    @Id
    @Column(name = "Maphim")
    private Integer maPhim;

    @Column(name = "Tenphim")
    private String tenPhim;

    @Column(name = "Daodien")
    private String daoDien;

    @Column(name = "Dienvien")
    private String dienVien;

    @Column(name = "gioithieu")
    private String gioiThieu;

    @Column(name = "Thoiluong")
    private Integer thoiLuong;

    @Column(name = "ngayramat")
    private Date ngayRaMat;

    @Column(name = "quocgia")
    private String quocGia;

    @Column(name = "poster")
    private String poster;

    @ManyToOne
    @JoinColumn(name = "Matheloai")
    private TheLoai theloai;

    @Column(name = "trailer")
    private String trailer;

    @OneToMany(mappedBy = "phim")
    private List<LichChieu> lichChieus;

    @OneToMany(mappedBy = "phim")
    private List<BinhLuan> binhLuans;

  }
