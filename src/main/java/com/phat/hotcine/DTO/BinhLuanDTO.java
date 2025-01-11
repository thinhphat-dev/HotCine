package com.phat.hotcine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinhLuanDTO {
    private Integer maBinhLuan;
    private Integer maNguoiDung;
    private Integer maPhim;
    private String binhLuan;
}
