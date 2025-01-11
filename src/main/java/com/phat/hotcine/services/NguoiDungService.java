package com.phat.hotcine.services;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.DTO.PhimDTO;
import com.phat.hotcine.Entity.HoiVien;
import com.phat.hotcine.Entity.NguoiDung;
import com.phat.hotcine.Entity.Phim;
import com.phat.hotcine.Repository.NguoiDungRepository;
import com.phat.hotcine.services.servicesImp.HoiVienServiceImp;
import com.phat.hotcine.services.servicesImp.NguoiDungServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService implements NguoiDungServiceImp {
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    HoiVienServiceImp hoiVienServiceImp;
    @Override
    public boolean save(NguoiDungDTO nguoiDungDTO) {
        return nguoiDungRepository.save(DTOToEntity(nguoiDungDTO)).getMaNguoiDung()!=0;
    }
    private NguoiDung DTOToEntity(NguoiDungDTO nguoiDungDTO){
        NguoiDung nguoiDung =new NguoiDung();
        nguoiDung.setMaNguoiDung(nguoiDungDTO.getMaNguoiDung());
        nguoiDung.setMatKhau(nguoiDungDTO.getMatKhau());
        nguoiDung.setEmail(nguoiDungDTO.getEmail());
        try {
            nguoiDung.setNhanVien(nguoiDungDTO.getNhanVien());
        }catch (Exception e){
        }
        try {
            nguoiDung.setHoivien(hoiVienServiceImp.findEntityById(nguoiDungDTO.getMaHoiVien()));
        }catch (Exception e){
        }
        try {
            nguoiDung.setSoDienThoai(nguoiDungDTO.getSoDienThoai());
        }catch (Exception e){
        }
        nguoiDung.setTenKhachHang(nguoiDungDTO.getTenKhachHang());
        try {
            nguoiDung.setHinhAnh(nguoiDungDTO.getHinhAnh());
        }catch (Exception e){

        }
        return nguoiDung;
    }
}
