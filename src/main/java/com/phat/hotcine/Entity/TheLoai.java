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
@Table(name = "Theloai")
public class TheLoai {
    @Id
    @Column(name = "Matheloai")
    private Integer maTheLoai;

    @Column(name = "Tentheloai")
    private String tenTheLoai;

    @OneToMany(mappedBy = "theloai")
    private List<Phim> phims;
    public TheLoai(Integer maTheLoai, String theLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = theLoai;
    }
  }
