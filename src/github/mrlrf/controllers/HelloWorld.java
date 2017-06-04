package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.DblpConferenceService;
import github.mrlrf.model.DblpConference;
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
    private DblpConferenceService dblpConferenceService;

    @RequestMapping("/helloworld")
    public String hello(HttpServletRequest request, Model model) {
        List<DblpConference> conferences = dblpConferenceService.getConference();
        for (DblpConference dblpConference : conferences) {
            System.out.println(dblpConference.getConference_name());
        }
        model.addAttribute("name", conferences.get(0).getConference_name());
        return "/admin/index";
    }
}
