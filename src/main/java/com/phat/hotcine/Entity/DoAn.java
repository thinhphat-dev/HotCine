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
@Table(name = "Doan")
public class DoAn {
    @Id
    @Column(name = "Madoan")
    private Integer maDoAn;

    @Column(name = "Tendoan")
    private String tenDoAn;

    @Column(name = "Mieuta")
    private String mieuTa;

    @Column(name = "Gia")
    private double gia;

    @OneToMany(mappedBy = "doAn")
    private List<ChiTietVe> chiTietVes;
}
