package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class No13_TestCreateDynamicResource {
	static Logger logger = TestUtils.getLogger(No10_TestWaiveRegistry.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();


	@Test
	public void testCreateDynamicResource() throws Exception {
		selenium.open(selenium.baseUrl + "Systems/AppResource/List/");
		selenium.click("btnLogin");
		selenium.click("id=_contentlink_Systems_AppResource_Create");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=AppResource_Name", "_Root_Account_Authorize");
		selenium.type("id=AppResource_Value", "Root_Account_Authorize");
		selenium.type("id=AppResource_Comment", "Root_Account_Authorize");
		selenium.click("id=btnCreateProfileForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Visual Mode Create");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=AppResource_Name", "Root_Home_Index");
		selenium.runScript("$(window.frames[0].document).find('body').text('Root_Home_Index')");
		selenium.type("id=AppResource_Comment", "Root_Home_Index");
		selenium.click("id=btnCreateProfileForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Systems_AppResource_Create");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=AppResource_Name", "_Root_Home_ReleaseNotes");
		selenium.type("id=AppResource_Value", "Root_Home_ReleaseNotes");
		selenium.type("id=AppResource_Comment", "Root_Home_ReleaseNotes");
		selenium.click("id=btnCreateProfileForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_listlink_Systems_AppResource_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=AppResource_Value", "_Root_Account_Authorize_edit");
		selenium.click("id=btnEditProfileForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Visual Mode Edit')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnEditProfileForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("_topmenulink_Menu");
		selenium.click("_topmenulink_Menu_Tools");
		selenium.click("id=_topmenulink_Systems_Deployment_DeploymentList");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=CacheRegionsToBeCleared_AppResource");
		selenium.click("id=btnRefreshCache");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
