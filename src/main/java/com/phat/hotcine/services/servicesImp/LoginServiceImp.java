package com.phat.hotcine.services.servicesImp;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.Entity.NguoiDung;
import org.springframework.ui.Model;

public interface LoginServiceImp {
    public String fillFormLogin(NguoiDung user);
    public NguoiDungDTO getNguoiDungByEmail(String email);
    public String doLogin(Model model, NguoiDung user);
    public String doSignUp(Model model, NguoiDung user);
    NguoiDungDTO findById(Integer id);

}
