package com.supos.progress;

import com.alibaba.fastjson.JSONObject;
import com.supos.progress.excel.AnalysisExcel;
import com.supos.progress.redmine.query.IssueQuery;
import com.taskadapter.redmineapi.RedmineException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgressApplicationTests {
	@Autowired
	private IssueQuery query;

	@Test
	public void contextLoads() throws RedmineException {
		query.query();
	}

	@Test
    public void analysisExcel(){
	    String path = "./excel/test.xlsx";
        List<JSONObject> text = AnalysisExcel.readXlsx(path);
        System.out.println(text);
    }
}
