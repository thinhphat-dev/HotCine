package com.phat.hotcine.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LichChieuDTO {
    private Integer maLichChieu;
    private Integer maPhim;
    private Integer maPhong;
    private Date ngayGioChieu;

   }
