package com.phat.hotcine.Controller;

import com.google.gson.Gson;
import com.phat.hotcine.DTO.PhimDTO;
import com.phat.hotcine.Repository.PhimRepository;
import com.phat.hotcine.services.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    PhimService phimService;
    @RequestMapping("/search")
    public String searchAjax( @RequestParam("txt") String searchText) {
        if (!searchText.isEmpty()) {
            List<PhimDTO> productList = phimService.getAllPhimByKeywords("%" + searchText + "%");
            return new Gson().toJson(productList);
        }
//        sessionService.set("kw", searchText);
        return "false";
    }
}
