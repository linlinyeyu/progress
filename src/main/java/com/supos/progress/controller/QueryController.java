package com.supos.progress.controller;

import com.alibaba.fastjson.JSONObject;
import com.supos.progress.excel.AnalysisExcel;
import com.supos.progress.redmine.query.IssueQuery;
import com.taskadapter.redmineapi.RedmineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private IssueQuery query;
    @RequestMapping(value = "/time",method = RequestMethod.GET)
    public Map<String, Object> getExcel(){
        List<JSONObject> list = AnalysisExcel.readXlsx("./excel/test.xlsx");
        Map<String,Object> res = new HashMap<>();
        res.put("errcode",0);
        res.put("data",list);
        return res;
    }

    @RequestMapping(value = "/redmine",method = RequestMethod.GET)
    public Map<String, Object> getRedmine() throws RedmineException {
        Map<String,Object> map = query.query();
        Map<String,Object> res = new HashMap<>();
        res.put("errcode",0);
        res.put("data",map);
        return res;
    }
}

