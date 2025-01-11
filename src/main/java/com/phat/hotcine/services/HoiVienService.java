package com.phat.hotcine.services;

import com.phat.hotcine.DTO.HoiVienDTO;
import com.phat.hotcine.Entity.HoiVien;
import com.phat.hotcine.Repository.HoiVienRepository;
import com.phat.hotcine.services.servicesImp.HoiVienServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoiVienService implements HoiVienServiceImp {
    @Autowired
    HoiVienRepository hoiVienRepository;
    @Override
    public HoiVien findEntityById(Integer id) {
        return hoiVienRepository.findById(id).get();
    }

}
