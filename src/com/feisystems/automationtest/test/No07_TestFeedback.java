package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class No07_TestFeedback {
	static Logger logger = TestUtils.getLogger(No07_TestFeedback.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();
	

	@Test
	public void testFeedbackCreate() throws Exception {
		try{
			selenium.open(selenium.baseUrl);
			selenium.click("id=btnLogin");
			selenium.click("link=glob:Menu*");
			selenium.click("link=glob:Feedback*");		
			selenium.click("id=_topmenulink_Feedbacks_ErrorReport_Create");
			selenium.waitForElementDisplay("id=errorReportConcernType");
			selenium.click("id=errorReportConcernType");
			selenium.select("id=errorReportConcernType", "label=System Error");	
			selenium.click("id=errorReportSeverityType");
			selenium.select("id=errorReportSeverityType", "label=Normal");
			selenium.click("id=errorReportMessage");	
			selenium.type("id=errorReportMessage", "system error");
			selenium.click("id=errorReportComments");
			selenium.type("id=errorReportComments", "normal");
			selenium.click("//input[@value='Send']");
		}catch(Exception ex) {
			  logger.error(ex);
			  throw ex;
		}
		
	}
	
	@Test
	public void testFeedbackProcess() throws Exception {
		try{
			selenium.open(selenium.baseUrl + "Feedbacks/ErrorReport/List/");
			selenium.select("id=ReportSearchCriteria_ReportStatus", "label=Pending");
			selenium.click("id=btnSearchFeedbacks");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[12]/a");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.float-right > li > form > input[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Feedbacks_ErrorReport_List");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ReportSearchCriteria_ReportStatus", "label=In Progress");
			selenium.click("id=btnSearchFeedbacks");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[12]/a");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.float-right > li > form > input[type=\"submit\"]");
			selenium.type("css=#formResolutionWorkflowAction > #comment", "resolved");
			selenium.select("id=resolutionCategoryId", "label=Resolved");
			selenium.click("css=#formResolutionWorkflowAction > div.fancybox-dialog-controls > #btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Feedbacks_ErrorReport_List");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ReportSearchCriteria_ReportStatus", "label=Resolved");
			selenium.click("id=btnSearchFeedbacks");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[12]/a");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Feedbacks_ErrorReport_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("link=Go to error page");
			selenium.waitForPageToLoad("30000");
			selenium.stop();
		}catch(Exception ex) {
			  logger.error(ex);
			  throw ex;
		}
	}


}
