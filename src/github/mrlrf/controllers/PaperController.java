package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/15 20:39
 */
@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping("/getAllPaper")
    public @ResponseBody List<Map<String, String>> getAllPaper () {
        //ModelAndView model = new ModelAndView();

        List<Map<String, String>> papers = paperService.getPaper();

        return papers;
    }
}
