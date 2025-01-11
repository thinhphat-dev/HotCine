package com.phat.hotcine.Util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest req;

    public String getString(String name, String defaultValue) {
        String value = req.getParameter(name);
        return value != null ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        return Integer.parseInt(value);
    }

    public double getDouble(String name, double defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        return Double.parseDouble(value);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = getString(name, String.valueOf(defaultValue));
        if (value.equals("on")) {
            return true;
        }
        return Boolean.parseBoolean(value);
    }

    public Date getDate(String name, String pattern) {
        String value = getString(name, "");
        try {
            return new SimpleDateFormat(pattern).parse(value);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public File save(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                File saveFile = new File(dir, file.getOriginalFilename());
                file.transferTo(saveFile);
                return saveFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
