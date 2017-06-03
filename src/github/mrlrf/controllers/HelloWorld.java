package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.ZhihuQuestionService;
import github.mrlrf.model.ZhihuQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String hello(HttpServletRequest request, Model model) {
        List<ZhihuQuestion> questions = zhihuQuestionService.loadZhQuestion();
        for (ZhihuQuestion zhihuQuestion : questions) {
            System.out.println(zhihuQuestion.getQuestion_title());
        }
        model.addAttribute("title", questions.get(0).getQuestion_title());
        return "/admin/index";
    }
}
