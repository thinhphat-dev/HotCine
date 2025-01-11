package com.phat.hotcine.Controller;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.Entity.GoogleAccount;
import com.phat.hotcine.Entity.NguoiDung;
import com.phat.hotcine.Entity.Phim;
import com.phat.hotcine.Repository.NguoiDungRepository;
import com.phat.hotcine.Util.GoogleLogin;
import com.phat.hotcine.Util.ImageDownloadService;
import com.phat.hotcine.Util.RandomCodeGenerator;
import com.phat.hotcine.Util.SessionService;
import com.phat.hotcine.services.PhimService;
import com.phat.hotcine.services.servicesImp.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    PhimService phimService;
    @Autowired
    SessionService sessionService;
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    private ImageDownloadService imageDownloadService;
    @GetMapping("index")
    public String index(Model model,@RequestParam(defaultValue = "0") String code) {
        if (!code.equals("0")){
            GoogleAccount acc = null;
            try {
                acc = processRequest(code);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                NguoiDungDTO nguoiDungDTO = loginServiceImp.getNguoiDungByEmail(acc.getEmail());
                sessionService.set("nguoidung", nguoiDungDTO);
            } catch (Exception e) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setEmail(acc.getEmail());
                nguoiDung.setTenKhachHang(acc.getName());
                nguoiDung.setMatKhau(new RandomCodeGenerator().generateRandomCode());
                nguoiDung.setNhanVien(false);
                try {
                    nguoiDungRepository.save(nguoiDung);
                    NguoiDungDTO nguoiDungDTO = loginServiceImp.getNguoiDungByEmail(acc.getEmail());
                    System.out.println(nguoiDungDTO.toString()+"tostring");
                    imageDownloadService.downloadImage(acc.getPicture(),nguoiDungDTO.getMaNguoiDung()+".jpg");
                    nguoiDung.setHinhAnh(nguoiDungDTO.getMaNguoiDung()+".jpg");
                    System.out.println(nguoiDung.getHinhAnh()+"hinhanh");

                    nguoiDungRepository.save(nguoiDung);
                    sessionService.set("nguoidung", loginServiceImp.getNguoiDungByEmail(acc.getEmail()));
                } catch (IOException s) {
                }

            }

        }
        model.addAttribute("listPhimtop6", phimService.getTop6Phim());
        model.addAttribute("listPhimALl", phimService.getAllPhim());
        if (sessionService.get("nguoidung")!=null) {
            model.addAttribute("nguoidung", sessionService.get("nguoidung"));
        }else{
            model.addAttribute("nguoidung", new NguoiDungDTO());

        }
        return "index";
    }

    protected GoogleAccount processRequest(String code )
            throws  IOException {
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        System.out.println(accessToken);
        GoogleAccount acc = gg.getUserInfo(accessToken);
        System.out.println(acc.toString());
        return acc;
        //check tk da dky chua
    }

    @GetMapping("privacy")
    public String privacy(Model model) {
        return "thongtinbaomat";
    }


    @GetMapping("phim")
    public String phim(Model model, @RequestParam(defaultValue = "0") Integer id) {
        model.addAttribute("listPhimALl", phimService.getAllPhim());
        if (sessionService.get("nguoidung")!=null) {
            model.addAttribute("nguoidung", sessionService.get("nguoidung"));
        }else{
            model.addAttribute("nguoidung", new NguoiDungDTO());

        }
        return "phim";
    }

    @GetMapping("chitietphim")
    public String chitietphim(Model model, @RequestParam int idPhim) {
        model.addAttribute("phim", phimService.getPhimByID(idPhim));
        model.addAttribute("listPhim", phimService.getAllPhim());
        if (sessionService.get("nguoidung")!=null) {
            model.addAttribute("nguoidung", sessionService.get("nguoidung"));
        }else{
            model.addAttribute("nguoidung", new NguoiDungDTO());

        }
        return "chitietphim";
    }

}
