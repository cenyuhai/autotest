package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class No06_TestUserRoles {
	static Logger logger = TestUtils.getLogger(No06_TestUserRoles.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();

	public void clean() {
		RavenDbAPI.deleteDocumentById("roles/administrator");
	}
	
	@Test
	public void testUserRolesCreate() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Security/UserRole/List/");
			selenium.click("id=btnLogin");
			clean();
			selenium.click("id=_contentlink_Security_UserRole_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Name", "Administrator");
			selenium.type("id=DisplayName", "Administrator");
			selenium.type("id=Comments", "Administrator");
			selenium.click("id=btnCreateUserRole");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.click("id=unique2");
			selenium.click("id=unique10");
			selenium.click("id=unique12");
			selenium.click("id=unique14");
			selenium.click("id=unique22");
			selenium.click("id=unique23");
			selenium.click("id=unique25");
			selenium.click("id=btnEditUserRoleRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Security_UserRole_List");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	@Test
	public void testUserRolesEdit() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Security/UserRole/List/");
			selenium.type("id=Name", "Administrator");
			selenium.click("id=btnSearchUserRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_listlink_Security_UserRole_Details_roles_administrator");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Security_UserRole_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=DisplayName", "Administratoredit");
			selenium.type("id=Comments", "Administratoredit");
			selenium.click("id=btnEditUserRole");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Security_UserRole_EditRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique29");
			selenium.click("id=unique28");
			selenium.click("id=btnEditUserRoleRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.sleepSeconds(3);
			selenium.click("id=btnNo");
			selenium.sleepSeconds(3);
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.sleepSeconds(3);
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.sleepSeconds(3);
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Security_UserRole_List");
			selenium.waitForPageToLoad("30000");
			
			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

}
