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
@Table(name = "Phongchieu")
public class PhongChieu {
    @Id
    @Column(name = "Maphong")
    private Integer maPhong;

    @Column(name = "Tenphong")
    private String tenPhong;

    @Column(name = "Succhua")
    private Integer sucChua;

    @OneToMany(mappedBy = "phongchieu")
    private List<LichChieu> lichChieus;
  }
