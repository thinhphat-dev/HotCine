package com.phat.hotcine.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhimDTO {
    private Integer maPhim;

    @NotNull(message = "Thể loại không được để trống")
    private Integer maTheLoai;

    @NotBlank(message = "Tên phim không được để trống")
    private String tenPhim;

    @NotBlank(message = "Đạo diễn không được để trống")
    private String daoDien;

    @NotBlank(message = "Diễn viên không được để trống")
    private String dienVien;

    @NotNull(message = "Thời lượng không được để trống")
    private Integer thoiLuong;

    @NotBlank(message = "Poster không được để trống")
    private String poster;

    @NotBlank(message = "Thể loại không được để trống")
    private String theLoai;

    @NotBlank(message = "Trailer không được để trống")
    private String trailer;

    @NotNull(message = "Ngày ra mắt không được để trống")
    private Date ngayRaMat;

    @NotBlank(message = "Quốc gia không được để trống")
    private String quocGia;

    @NotBlank(message = "Giới thiệu không được để trống")
    private String gioiThieu;
}