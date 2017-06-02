package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.ZhihuQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/26 14:40
 */
@Controller
public class HelloWorld {
    @Autowired
    private ZhihuQuestionService zhihuQuestionService;

    @RequestMapping("/helloworld")
    public String hello() {
        System.out.println(zhihuQuestionService.loadZhQuestion());
        return "/admin/index";
    }
}
