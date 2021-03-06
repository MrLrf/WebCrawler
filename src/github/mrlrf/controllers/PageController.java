package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.DblpConferenceService;
import github.mrlrf.Services.interfaces.PaperService;
import github.mrlrf.model.DblpConference;
import github.mrlrf.model.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static github.mrlrf.controllers.DblpSpiderController.conferenceSpider;
import static github.mrlrf.controllers.DblpSpiderController.paperSpider;

/**
 * 页面controller,原生的springMVC框架无法直接访问WEB-INF里面的页面
 * 通过这个controller访问WEB-INF里的页面
 *
 * @Author lirf
 * @Date 2017/6/2 9:38
 */
@Controller
public class PageController {
    @Autowired
    private DblpConferenceService dblpConferenceService;
    @Autowired
    private PaperService paperService;

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/test")
    public String test() {
        Map<String, String> urls = new HashMap<>();
        urls.put("resys", "http://dblp.uni-trier.de/db/conf/recsys/");
        urls.put("SIGMOD", "http://dblp.uni-trier.de/db/conf/sigmod/");
        List<DblpConference> conferences = conferenceSpider(urls);

        int index = 1;
        Iterator<DblpConference> conferenceIterator = conferences.iterator();
        while (conferenceIterator.hasNext()) {
            System.out.println("conference:" + index++);
            DblpConference dblpConference = conferenceIterator.next();
            //System.out.println(dblpConference);
            dblpConferenceService.insertConference(dblpConference);

            List<Paper> papers = paperSpider(dblpConference.getConference_id(), dblpConference.getContent_url());

            System.out.println();
            int paperIndex = 1;
            Iterator<Paper> paperIterator = papers.iterator();
            while (paperIterator.hasNext()) {
                System.out.println("paper:" + paperIndex++);
                Paper paper = paperIterator.next();
                //System.out.println(paper);
                paperService.insertPaper(paper);
            }

        }
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

    @RequestMapping("/tables")
    public String tables() {
        return "/tables";
    }

    @RequestMapping("/papers")
    public String papers() {
        return "/papers";
    }

    @RequestMapping("/uploadForm")
    public String uploadForm() {
        return "/fkit/uploadForm";
    }
}
