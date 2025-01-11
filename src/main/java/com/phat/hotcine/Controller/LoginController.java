package com.phat.hotcine.Controller;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.Entity.GoogleAccount;
import com.phat.hotcine.Entity.NguoiDung;
import com.phat.hotcine.Util.CookieService;
import com.phat.hotcine.Util.GoogleLogin;
import com.phat.hotcine.Util.RandomCodeGenerator;
import com.phat.hotcine.Util.SessionService;
import com.phat.hotcine.Util.mail.MailerService;
import com.phat.hotcine.services.LoginService;
import com.phat.hotcine.services.servicesImp.LoginServiceImp;
import com.phat.hotcine.services.servicesImp.NguoiDungServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    SessionService sessionService;
    @Autowired
    MailerService mailerService;
    @Autowired
    CookieService cookieService;
    @Autowired
    NguoiDungServiceImp nguoiDungServiceImp;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    static String code="1";
    @GetMapping("signin")
    public String signinShow(@ModelAttribute("user") NguoiDung user) {
        return loginServiceImp.fillFormLogin(user);
    }

    @PostMapping("signin")
    public String Login(@ModelAttribute("user") NguoiDung user, Model model) {
        return loginServiceImp.doLogin(model, user);
    }
    @PostMapping("signup")
    public String signup(@ModelAttribute("user") NguoiDung user, Model model) {
        return loginServiceImp.doSignUp(model, user);
    }
    @GetMapping("signup")
    public String signupshow(@ModelAttribute("user") NguoiDung user) {
        return "dangky";
    }

    @GetMapping("forgot")
    public String forgotshow(@ModelAttribute("user") NguoiDung user) throws ServletException, IOException {
        return "quenmatkhau";
    }

    @ResponseBody
    @PostMapping("forgot/send")
    public String send(@RequestParam String email) {
        code = new RandomCodeGenerator().generateRandomCode();
        try {
            mailerService.queue(mailerService.taoEmail(email, code));
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }

    @PostMapping("forgot/change")
    public String change(@ModelAttribute("user") NguoiDung user,@RequestParam String codeGuiDi,@RequestParam String email,@RequestParam String matKhauMoi,Model model) {
        if (code.equals(codeGuiDi)) {
            NguoiDungDTO nguoiDungDTO = loginServiceImp.getNguoiDungByEmail(email);
            nguoiDungDTO.setMatKhau(matKhauMoi);
            cookieService.add("email", email, 2);
            if (nguoiDungServiceImp.save(nguoiDungDTO)){
                System.out.println("thanh cong");
            }
            return loginServiceImp.fillFormLogin(user);
        }else {
            System.out.println("sai code");
            model.addAttribute("email", email);
            return "quenmatkhau";

        }

    }

    @GetMapping("signout")
    public String signout(@ModelAttribute("user") NguoiDung user) {
        sessionService.remove("nguoidung");
        return "redirect:/login/signin";
    }

}

