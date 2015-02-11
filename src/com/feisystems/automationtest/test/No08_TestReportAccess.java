package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class No08_TestReportAccess {
	static Logger logger = TestUtils.getLogger(No08_TestReportAccess.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();

	@Test
	public void testReportaccessCreate() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Systems/ReportAcl/List/");
			selenium.click("id=btnLogin");
			selenium.click("id=_Systems_ReportAcl_Create");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("id=FolderDdl");
			selenium.sleepSeconds(3);
			selenium.select("id=FolderDdl", "label=Agency Provider");
			selenium.selectByIdAndIndex("ReportDdl", 1);
			selenium.type("id=ReportAcl_Description", "agency");
			selenium.click("id=roles_administrator");
			selenium.click("id=roles/administrator_AccessType_lookupitems/reportaccesstypes/-1");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ReportAcl_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=FolderDdl", "label=Assessment");
			selenium.waitForElementDisplay("id=FolderDdl");
			selenium.sleepSeconds(3);
			selenium.click("id=IsApplyAll");
			selenium.type("id=ReportAcl_Description", "Assessment all");
			selenium.click("id=roles_administrator");
			selenium.click("id=roles/administrator_AccessType_lookupitems/reportaccesstypes/-1");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	@Test
	public void testReportaccessEdit() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Systems/ReportAcl/List/");
			selenium.waitForElementDisplay("id=Category");
			//selenium.sleepSeconds(3);
			selenium.select("id=Category", "label=Agency Provider");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("_Systems_ReportAcl_Edit");
			selenium.click("id=_Systems_ReportAcl_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=ReportAcl_Description", "agency edit");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@class='float-left']/button[@type='submit']");
			selenium.runScript("$('#_Systems_ReportAcl_Delete:eq(0)').click()");
			selenium.sleepSeconds(3);
			selenium.click("id=btnNo");
			selenium.refresh();
			selenium.runScript("$('#_Systems_ReportAcl_Delete:eq(0)').click()");
			selenium.waitForElementDisplay("id=btnYes");
			selenium.click("id=btnYes");
			selenium.sleepSeconds(3);
			selenium.runScript("$('#_Systems_ReportAcl_Deactivate').click()");
			selenium.sleepSeconds(3);
			selenium.click("id=btnNo");
			selenium.refresh();
			selenium.runScript("$('#_Systems_ReportAcl_Deactivate:eq(0)').click()");
			selenium.waitForElementDisplay("id=btnYes");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.runScript("$('#_Systems_ReportAcl_Activate:eq(0)').click()");
			selenium.waitForElementDisplay("id=btnYes");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			
			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

}
