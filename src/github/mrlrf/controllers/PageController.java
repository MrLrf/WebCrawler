package github.mrlrf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面controller,原生的springMVC框架无法直接访问WEB-INF里面的页面
 * 通过这个controller访问WEB-INF里的页面
 *
 * @Author lirf
 * @Date 2017/6/2 9:38
 */
@Controller
public class PageController {
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/test")
    public String test() {
        return "/admin/test";
    }

    @RequestMapping("/index2")
    public String index2() {
        return "/index2";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
}
