package net.smlee.studywebmemo.controllers;

import net.smlee.studywebmemo.entities.MemoEntity;
import net.smlee.studywebmemo.models.PagingModel;
import net.smlee.studywebmemo.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private final MemoService memoService;

    @Autowired
    public HomeController(MemoService memoService) {
        this.memoService = memoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage,
                                 @RequestParam(value = "c", defaultValue = "content", required = false) String searchCriterion,
                                 @RequestParam(value = "q", defaultValue = "", required = false) String searchQuery) {
        ModelAndView mav = new ModelAndView("home/index");
        PagingModel pagingModel = new PagingModel(
                MemoService.PAGE_COUNT,
                this.memoService.getCount(searchCriterion, searchQuery),
                requestPage);
        MemoEntity[] memoEntities = this.memoService.getByPage(pagingModel, searchCriterion, searchQuery);
        mav.addObject("memos", memoEntities);
        mav.addObject("pagingModel", pagingModel);
        mav.addObject("searchCriterion", searchCriterion);
        mav.addObject("searchQuery", searchQuery);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postIndex(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage, MemoEntity memoEntity,
                                  @RequestParam(value = "c", defaultValue = "content", required = false) String searchCriterion,
                                  @RequestParam(value = "q", defaultValue = "", required = false) String searchQuery) {
        boolean result = this.memoService.write(memoEntity);
        ModelAndView mav = this.getIndex(requestPage, searchCriterion, searchQuery);
        mav.addObject("result", result);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody // 문자열로해서 응답을 받음, 없으면 true.html 찾으러감.
    public String deleteIndex(@RequestParam(value = "index") int index) {
        boolean result = this.memoService.deleteByIndex(index);
        return String.valueOf(result);
    }


    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseBody
    public String updateIndex(@RequestParam(value = "index") int index,
                              @RequestParam(value = "text")String text){
        boolean result = this.memoService.updateByIndex(index, text);
        return String.valueOf(result);
    }
}
