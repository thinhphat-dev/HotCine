package com.phat.hotcine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietVeDTO {
    private Integer maChiTietVe;
    private Integer maVe;
    private Integer soLuong;
    private Integer maDoAn;
}
