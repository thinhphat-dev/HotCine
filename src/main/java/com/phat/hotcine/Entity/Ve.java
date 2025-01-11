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
@Table(name = "Ve")
public class Ve {
    @Id
    @Column(name = "MaVe")
    private Integer maVe;

    @ManyToOne
    @JoinColumn(name = "MaLichChieu")
    private LichChieu maLichChieu;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung maNguoiDung;

    @Column(name = "NgayGioBan")
    private Date ngayGioBan;

    @Column(name = "GiaVe")
    private double giaVe;

    @Column(name = "ViTriGhe")
    private String viTriGhe;

    @OneToMany(mappedBy = "ve")
    private List<ChiTietVe> chiTietVes;

   }
