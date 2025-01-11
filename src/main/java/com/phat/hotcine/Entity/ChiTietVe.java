package com.phat.hotcine.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Chitietve")
public class ChiTietVe {
    @Id
    @Column(name = "Machitietve")
    private Integer maChiTietVe;

    @ManyToOne
    @JoinColumn(name = "Mave")
    private Ve ve;

    @Column(name = "Soluong")
    private Integer soLuong;

    @ManyToOne
    @JoinColumn(name = "Madoan")
    private DoAn doAn;

 }
