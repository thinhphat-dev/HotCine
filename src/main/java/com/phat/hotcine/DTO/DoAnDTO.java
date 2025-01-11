package com.phat.hotcine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoAnDTO {
    private Integer maDoAn;
    private String tenDoAn;
    private String mieuTa;
    private double gia;

    }
