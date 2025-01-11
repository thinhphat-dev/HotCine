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
@Table(name = "Hoivien")
public class HoiVien {
    @Id
    @Column(name = "Mahoivien")
    private Integer maHoiVien;

    @Column(name = "Tenhoivien")
    private String tenHoiVien;

    @OneToMany(mappedBy = "hoivien")
    private List<NguoiDung> nguoiDungs;
  }
