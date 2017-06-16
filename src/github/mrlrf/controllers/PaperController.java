package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.PaperService;
import github.mrlrf.model.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public ResultData getAllPaper (HttpServletRequest request, String page, String rows) {
        ResultData resultData = new ResultData();

        int realPage = Integer.parseInt(page);
        int realRows = Integer.parseInt(rows);
        List<Map<String, String>> papers = paperService.getPaperPage(realPage, realRows);

        resultData.setData(papers);
        return resultData;
    }

    @RequestMapping("/spiderByUrl")
    @ResponseBody
    public ResultData spiderByUrl (HttpServletRequest request, String url) {

    }
}
