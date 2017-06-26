package github.mrlrf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 17-6-25 下午4:33
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request,
                         @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println(description);
        if (!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/images/");
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.pathSeparator + filename));
            return "success";
        } else {
            return "error";
        }
    }
}