package com.phat.hotcine.services;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.DTO.PhimDTO;
import com.phat.hotcine.Entity.NguoiDung;
import com.phat.hotcine.Entity.Phim;
import com.phat.hotcine.Repository.NguoiDungRepository;
import com.phat.hotcine.Util.CookieService;
import com.phat.hotcine.Util.ParamService;
import com.phat.hotcine.Util.SessionService;
import com.phat.hotcine.services.servicesImp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    ParamService paramService;
    @Autowired
    CookieService cookieService;
    @Autowired
    SessionService sessionService;

    @Override
    public String fillFormLogin(NguoiDung user) {
        user.setEmail(cookieService.getValue("email"));
        user.setMatKhau(cookieService.getValue("matkhau"));
        return "dangnhap";
    }

    @Override
    public NguoiDungDTO getNguoiDungByEmail(String email) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
        try {
            nguoiDungDTO.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        } catch (Exception e) {

        }
        nguoiDungDTO.setEmail(nguoiDung.getEmail());
        nguoiDungDTO.setTenKhachHang(nguoiDung.getTenKhachHang());
        nguoiDungDTO.setMatKhau(nguoiDung.getMatKhau());
        try {
            nguoiDungDTO.setHinhAnh(nguoiDung.getHinhAnh());
        } catch (Exception e) {
        }
        try {
            nguoiDungDTO.setSoDienThoai(nguoiDung.getSoDienThoai());
        } catch (Exception e) {

        } try {
            nguoiDungDTO.setMaHoiVien(nguoiDung.getHoivien().getMaHoiVien());
        } catch (Exception e) {

        } try {
            nguoiDungDTO.setNhanVien(nguoiDung.getNhanVien());
        } catch (Exception e) {

        }
        System.out.println(nguoiDungDTO.toString()+"abcccc");
        return nguoiDungDTO;
    }

    @Override
    public String doLogin(Model model, NguoiDung user) {
        boolean rm = paramService.getBoolean("remember", false);
        if (rm) {
            cookieService.add("email", user.getEmail(), 2);
            cookieService.add("matkhau", user.getMatKhau(), 2);
        } else {
            cookieService.remove("email");
            cookieService.remove("matkhau");
        }
        try {
            System.out.println(user.getEmail());
            System.out.println(user.getMatKhau());
            NguoiDungDTO nguoiDungDTO = getNguoiDungByEmail(user.getEmail());
            System.out.println(nguoiDungDTO.toString()+"adadad");
            if (user.getMatKhau().equals(nguoiDungDTO.getMatKhau())) {
                sessionService.set("nguoidung", nguoiDungDTO);
                return "redirect:/home/index";
            } else {
                model.addAttribute("message", "Sai mật khẩu!!!");
                return "dangnhap";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Sai Email!!!");
            return "dangnhap";
        }
    }

    @Override
    public String doSignUp(Model model, NguoiDung user) {

        try {
            try {
                NguoiDungDTO nguoiDung = getNguoiDungByEmail(user.getEmail());
                model.addAttribute("message", "Email đã được dăng ký");
                return "dangky";
            } catch (Exception e) {
                user.setNhanVien(false);
                nguoiDungRepository.save(user);
                model.addAttribute("message", "Đăng ký thành công");
                return "dangnhap";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Đăng ký thất bai!!!");
            return "dangky";
        }
    }

    private NguoiDungDTO entityToDTO(NguoiDung nguoiDung) {
        NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
        nguoiDungDTO.setEmail(nguoiDung.getEmail());
        nguoiDungDTO.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        try {
            nguoiDungDTO.setNhanVien(nguoiDung.getNhanVien());
        } catch (Exception e) {
            nguoiDungDTO.setNhanVien(false);

        }
        nguoiDungDTO.setTenKhachHang(nguoiDung.getTenKhachHang());
        nguoiDungDTO.setMatKhau(nguoiDung.getMatKhau());
        try {
            nguoiDungDTO.setSoDienThoai(nguoiDung.getSoDienThoai());
        } catch (Exception e) {
        }
        try {
            nguoiDungDTO.setHinhAnh(nguoiDung.getHinhAnh());
        } catch (Exception e) {
        }
        try {
            nguoiDungDTO.setMaHoiVien(nguoiDung.getHoivien().getMaHoiVien());
        } catch (Exception e) {
        }
        return nguoiDungDTO;
    }

    @Override
    public NguoiDungDTO findById(Integer id) {
        return entityToDTO(nguoiDungRepository.findById(id).get());
    }
}
