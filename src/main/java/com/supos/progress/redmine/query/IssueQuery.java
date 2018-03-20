package com.supos.progress.redmine.query;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueQuery {
    @Autowired
    private RedmineManager manager;

    public Map<String, Object> query() throws RedmineException {
        List<Issue> issues = manager.getIssueManager().getIssues("sprint4",null);
        List<Issue> group1 = new ArrayList<>();
        List<Issue> group2 = new ArrayList<>();
        List<Issue> group3 = new ArrayList<>();
        for (Issue issue:issues){
            if (issue.getAuthorName().equals("姚 炜")){
                group1.add(issue);
            }else if (issue.getAuthorName().equals("陈 吉平")){
                group2.add(issue);
            }else if (issue.getAuthorName().equals("陈 英杰")){
                group3.add(issue);
            }
        }

        double ratio1 = 0;
        double ratio2 = 0;
        double ratio3 = 0;
        for (Issue issue:group1){
            ratio1 += issue.getDoneRatio();
        }
        for (Issue issue:group2){
            ratio2 += issue.getDoneRatio();
        }

        for (Issue issue:group3){
            ratio3 += issue.getDoneRatio();
        }

        Map<String,Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00");
        map.put("姚炜",group1.size()==0?0:df.format(ratio1/group1.size()));
        map.put("陈吉平",group2.size()==0?0:df.format(ratio2/group2.size()));
        map.put("陈英杰",group3.size()==0?0:df.format(ratio3/group3.size()));
        return map;
    }
}
