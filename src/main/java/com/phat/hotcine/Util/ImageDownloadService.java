package com.phat.hotcine.Util;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageDownloadService {

    public void downloadImage(String url,String tenHinh) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        Path path = Paths.get("src/main/resources/static/img/nguoidung");
        Files.copy(inputStream, path.resolve(tenHinh), StandardCopyOption.REPLACE_EXISTING);

    }
}