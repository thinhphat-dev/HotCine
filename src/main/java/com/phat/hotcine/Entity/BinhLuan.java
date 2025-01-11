package com.phat.hotcine.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "binhluan")
public class BinhLuan {
    @Id
    @Column(name = "Mabinhluan")
    private Integer maBinhLuan;

    @ManyToOne
    @JoinColumn(name = "Manguoidung")
    private NguoiDung nguoiDung;

    @Column(name = "binhluan")
    private String binhLuan;

    @ManyToOne
    @JoinColumn(name = "maphim")
    private Phim phim;

 }
