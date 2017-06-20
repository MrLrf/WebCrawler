package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.DblpConferenceService;
import github.mrlrf.Services.interfaces.PaperService;
import github.mrlrf.model.DblpConference;
import github.mrlrf.model.Paper;
import github.mrlrf.model.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static github.mrlrf.controllers.DblpSpiderController.conferenceSpider;
import static github.mrlrf.controllers.DblpSpiderController.paperSpider;

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
    @Autowired
    private DblpConferenceService dblpConferenceService;

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
    public ResultData spiderByUrl (HttpServletRequest request, String url, String name) {
        ResultData resultData = new ResultData();
        resultData.setResult(false);

        Map<String, String> urls = new HashMap<>();
        urls.put(name, url);
        List<DblpConference> conferences = conferenceSpider(urls);

        int index = 1;
        Iterator<DblpConference> conferenceIterator = conferences.iterator();
        while (conferenceIterator.hasNext()) {
            System.out.println("conference:" + index++);
            DblpConference dblpConference = conferenceIterator.next();
            dblpConferenceService.insertConference(dblpConference);

            List<Paper> papers = paperSpider(dblpConference.getConference_id(), dblpConference.getContent_url());

            int paperIndex = 1;
            Iterator<Paper> paperIterator = papers.iterator();
            while (paperIterator.hasNext()) {
                System.out.println("paper:" + paperIndex++);
                Paper paper = paperIterator.next();
                //System.out.println(paper);
                paperService.insertPaper(paper);
            }

        }
        resultData.setResult(true);
        return resultData;
    }
}
