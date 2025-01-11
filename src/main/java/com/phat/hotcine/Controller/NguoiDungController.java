package com.phat.hotcine.Controller;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.Util.SessionService;
import com.phat.hotcine.services.servicesImp.LoginServiceImp;
import com.phat.hotcine.services.servicesImp.NguoiDungServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("nguoidung")
public class NguoiDungController {
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    NguoiDungServiceImp nguoiDungServiceImp;
    @Autowired
    SessionService sessionService;
    @GetMapping("hoso")
    public String hoso(Model model) {
        if (sessionService.get("nguoidung")!=null) {
            model.addAttribute("nguoidung", sessionService.get("nguoidung"));

        }else{
            model.addAttribute("nguoidung", new NguoiDungDTO());
        }
        return "hoso";
    }
    @PostMapping("hoso/capnhat")
    public String capnhat( @ModelAttribute("nguoidung") NguoiDungDTO nguoiDungDTO, @RequestParam("photoa") MultipartFile photoa){
        System.out.println(photoa.getOriginalFilename());
        if (photoa.getOriginalFilename().length()>1) {
            nguoiDungDTO.setHinhAnh(loginServiceImp.findById(nguoiDungDTO.getMaNguoiDung()).getMaNguoiDung() + photoa.getOriginalFilename());
            try {
                Path path = Paths.get("src/main/resources/static/img/nguoidung");
                InputStream inputStream = photoa.getInputStream();
                Files.copy(inputStream, path.resolve(nguoiDungDTO.getHinhAnh()), StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
            }
        }else{
            nguoiDungDTO.setHinhAnh(loginServiceImp.findById(nguoiDungDTO.getMaNguoiDung()).getHinhAnh());
            System.out.println(loginServiceImp.findById(nguoiDungDTO.getMaNguoiDung()).toString()+"dadada");
        }
        System.out.println(nguoiDungDTO.toString());


        if (nguoiDungServiceImp.save(nguoiDungDTO)){
            sessionService.set("nguoidung", loginServiceImp.findById(nguoiDungDTO.getMaNguoiDung()));

            System.out.println("thanh cong");
        }

        return "hoso";
    }
}
