package com.phat.hotcine.Controller;

import com.phat.hotcine.DTO.PhimDTO;
import com.phat.hotcine.Entity.TheLoai;
import com.phat.hotcine.Repository.TheLoaiRepository;
import com.phat.hotcine.services.PhimService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class adminController {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Autowired
    private PhimService phimService;

    @Autowired
    HttpServletRequest request;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("index")
    public String index() {
        return "/admin/index";
    }

    static String mess = "";
    static String color = "";
    @GetMapping("add")
    public String loadForm(Model model, @ModelAttribute PhimDTO phimDTO) {
        phimDTO.setNgayRaMat(new Date());
        model.addAttribute("phimDTO", phimDTO);
        model.addAttribute("message", mess);
        model.addAttribute("color", color);

        getAllDTOPhim(model);
        return "/admin/add-item";
    }

    @GetMapping("/show-info/{id}")
    public String showMovieInfo(@PathVariable("id") Integer id, Model model) {
        PhimDTO phimDTO = phimService.getPhimByID(id);
        model.addAttribute("phimDTO", phimDTO);
        getAllDTOPhim(model);
        return "/admin/add-item";
    }

    @PostMapping("add")
    public String addMovie(Model model, @Validated @ModelAttribute("phimDTO") PhimDTO phimDTO, BindingResult bindingResult, @RequestParam("ngayRaMat") String ngayRaMat, @RequestParam("poster") MultipartFile poster) throws ParseException {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError().getField().equalsIgnoreCase("ngayRaMat") && ngayRaMat.isEmpty()) {
                getAllDTOPhim(model);
                model.addAttribute("dateMessage", "Ngày ra mắt không được để trống");

            }
            if (poster.getOriginalFilename().isEmpty()) {
                getAllDTOPhim(model);
                model.addAttribute("posterMessage", "Poster không được để trống");
            } else {
                try {
                    Optional<TheLoai> theLoaiDTO = theLoaiRepository.findById(phimDTO.getMaTheLoai());
                    if (theLoaiDTO.isPresent()) {
                        phimDTO.setTheLoai(theLoaiDTO.get().getTenTheLoai());
                    }
                    phimDTO.setPoster(poster.getOriginalFilename());
                    phimDTO.setNgayRaMat(formatter.parse(ngayRaMat));
                    phimService.createPhim(phimDTO);
                    mess = "Thêm mới thành công!";
                    color ="green";
                    model.addAttribute("message", mess);
                    model.addAttribute("color", color);
                    getAllDTOPhim(model);

                } catch (Exception e) {
                    e.printStackTrace();
                    mess = "Thêm mới thất bại!";
                    color ="red";

                    model.addAttribute("message", mess);
                    model.addAttribute("color", color);

                }
            }
        }
        return "/admin/add-item";

    }

    @PostMapping("reset")
    public String resetMovie(Model model) {
        model.addAttribute("phimDTO", phimService.resetPhim());
        mess = "";
        model.addAttribute("message", mess);
        return "redirect:/admin/add";
    }

    @RequestMapping("/remove-info/{id}")
    public String removeMovie(@PathVariable("id") Integer id, Model model) {
        PhimDTO phimDTO = phimService.getPhimByID(id);
        if (phimDTO != null) {
            phimService.deletePhim(phimDTO);
            mess = "Xóa thành công";
            color ="green";

            model.addAttribute("message", mess);
            model.addAttribute("color", "color");

        } else {
            mess = "Xóa thất bại";
            color ="red";

            model.addAttribute("message", mess);
            model.addAttribute("color", color);

        }
        getAllDTOPhim(model);
        return "redirect:/admin/add";
    }

    @RequestMapping("/update-info/{id}")
    public String updateMovie(@PathVariable("id") Integer id, Model model, @Validated @ModelAttribute("phimDTO") PhimDTO phimDTO, BindingResult bindingResult, @RequestParam("ngayRaMat") String ngayRaMat, @RequestParam("poster") MultipartFile poster) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError().getField().equalsIgnoreCase("ngayRaMat") && ngayRaMat.isEmpty()) {
                getAllDTOPhim(model);
                model.addAttribute("dateMessage", "Ngày ra mắt không được để trống");
                return "redirect:/admin/show-info/"+id;

            }
            if (poster.getOriginalFilename().isEmpty()) {
                getAllDTOPhim(model);
                model.addAttribute("posterMessage", "Poster không được để trống");
                return "redirect:/admin/show-info/"+id;


            } else {
                try {
                    Optional<TheLoai> theLoaiDTO = theLoaiRepository.findById(phimDTO.getMaTheLoai());
                    if (theLoaiDTO.isPresent()) {
                        phimDTO.setTheLoai(theLoaiDTO.get().getTenTheLoai());
                    }
                    phimDTO.setPoster(poster.getOriginalFilename());
                    phimDTO.setNgayRaMat(formatter.parse(ngayRaMat));
                    phimService.updatePhim(phimDTO, id);
                    resetMovie(model);
                    mess = "Cập nhật thành công!";
                    color ="green";
                    model.addAttribute("message", mess);
                    model.addAttribute("color", color);
                    getAllDTOPhim(model);
                    return "/admin/add-item";
                } catch (Exception e) {
                    e.printStackTrace();
                    mess = "Cập nhật thất bại!";
                    color ="red";

                    model.addAttribute("message", mess);
                    model.addAttribute("color", color);

                    getAllDTOPhim(model);
                    return "/admin/add-item";
                }


            }
        }
        return "/admin/add-item";
    }
    @RequestMapping("/remove-info/")
    public String deleteNullMovie(Model model) {
        mess = "Xóa thất bại ! Vui lòng chọn phim cần xóa trước.";
        color ="red";
        model.addAttribute("message", mess);
        model.addAttribute("color", color);

        getAllDTOPhim(model);
        return "redirect:/admin/add";
    }
    @RequestMapping("/update-info/")
    public String updateNullMovie(Model model) {
        mess = "Cập nhật thất bại ! Vui lòng chọn phim cần cập nhật trước.";
        color ="red";
        model.addAttribute("message", mess);
        model.addAttribute("color", color);

        getAllDTOPhim(model);
        return "redirect:/admin/add";
    }
    public void getAllDTOPhim(Model model) {
        List<PhimDTO> list = phimService.getAllPhim();
        model.addAttribute("phimDTOList", list);
    }
}