package com.phat.hotcine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeDTO {
    private Integer maVe;
    private Integer maLichChieu;
    private Integer maNguoiDung;
    private Date ngayGioBan;
    private double giaVe;
    private String viTriGhe;

   }
