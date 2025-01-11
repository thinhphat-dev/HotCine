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
@Table(name = "Lichchieu")
public class LichChieu {
    @Id
    @Column(name = "Malichchieu")
    private Integer maLichChieu;

    @ManyToOne
    @JoinColumn(name = "Maphim")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "Maphong")
    private PhongChieu phongchieu;

    @Column(name = "Ngaygiochieu")
    private Date ngayGioChieu;

    @OneToMany(mappedBy = "maLichChieu")
    private List<Ve> ves;
}
