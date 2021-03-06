package com.reidx.web.controller;

import com.reidx.Constants;
import com.reidx.entity.Resource;
import com.reidx.repository.ResourceRepository;
import com.reidx.service.ResourceService;
import com.reidx.util.Configuration;
import com.reidx.util.Page;
import com.reidx.vo.ResourceCount;
import com.reidx.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private Configuration configuration;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String welcome(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,
                          HttpServletRequest request, SearchParam param) {
        int offset = (pageNumber - 1) * Constants.DEFAULT_PAGE_SIZE;
        Page page = new Page();
        page.setUrl(request.getRequestURI() + param.buildUrl());
        page.setPageNo(pageNumber);
        long count = this.resourceService.getCount(param);
        page.setCount(count);
        page.setRowPerPage(Constants.DEFAULT_PAGE_SIZE);
        page.setShowNum(10);

        List<Resource> resources = this.resourceService.findByPage(offset, Constants.DEFAULT_PAGE_SIZE, param);

        model.addAttribute("resources", resources);
        model.addAttribute("title", "舆情浏览");
        model.addAttribute("pageList", page.pageList());
        model.addAttribute("sourceTypes", configuration.getSourceTypes());
        model.addAttribute("count", count);
        return "list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") String id, Model model) {
        Resource resource = this.resourceRepository.findOne(id);
        model.addAttribute("title", resource.getTitle());
        model.addAttribute("resource", resource);
        model.addAttribute("sourceTypes", this.configuration.getSourceTypes());
        return "view";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String view(Model model) {
        List<ResourceCount> resourceCounts = this.resourceService.getResourceCount();
        StringBuffer buffer = new StringBuffer();
        buffer.append("[[");
        int i = 1;
        int count = resourceCounts.size();
        for (ResourceCount resourceCount : resourceCounts) {
            buffer.append("[");
            buffer.append("'" + this.configuration.getSourceTypes().get(resourceCount.getSourceType()) + "'");
            buffer.append(",");
            buffer.append(resourceCount.getCount());
            buffer.append("]");
            if (++i <= count) {
                buffer.append(",");
            }
        }
        buffer.append("]]");
        model.addAttribute("data", buffer);
        model.addAttribute("title", "舆情数据分布");
        return "chart";
    }

}
