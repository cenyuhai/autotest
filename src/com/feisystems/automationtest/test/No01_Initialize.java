package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class No01_Initialize {
	
	private SeleniumWrapper selenium;
	Logger logger = TestUtils.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {
		clean();
		selenium = new SeleniumWrapper();
	}
	
	public void clean() {

		RavenDbAPI.deleteCollectionByName("organizationunits");
		RavenDbAPI.deleteCollectionByName("systemsettings");
		RavenDbAPI.deleteCollectionByName("useraccounts");
		RavenDbAPI.deleteCollectionByName("roles");
		RavenDbAPI.deleteCollectionByName("userrolesets");
		RavenDbAPI.deleteCollectionByName("dynamicpermissions");
		RavenDbAPI.deleteCollectionByName("organizationunitrolesets");
		
	}

	@Test
	public void testInitial() throws Exception {
		selenium.open(selenium.baseUrl + "Systems/SystemSetting/Config/");
		selenium.type("id=Version", "1.0");
		selenium.type("id=SiteInfo", "QC");
		selenium.type("id=SystemOrganizationUnitName", "SystemOU");
		selenium.type("id=SystemOrganizationUnitShortName", "SOU");
		selenium.type("id=SystemAdministratorFirstName", "System");
		selenium.type("id=SystemAdministratorLastName", "Administrator");
		selenium.type("id=SystemAdministratorSsoId", "system");
		selenium.click("id=btnCreateConfig");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnLogin");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.data-element > #OverwriteExistedLookups");
		selenium.click("id=btnCreateAllNecessaryData");
		selenium.comfirmAlert();
		selenium.waitElementClickableById("btnCreateNotificationIndexes");
		selenium.comfirmAlert();
		selenium.waitForPageToLoad("30000");
		selenium.click("id=CacheRegionsToBeCleared_Lookups");
		selenium.click("id=CacheRegionsToBeCleared_Security");
		selenium.click("id=CacheRegionsToBeCleared_AppResource");
		selenium.click("id=btnRefreshCache");
		selenium.comfirmAlert();
		selenium.sleepSeconds(3);
		selenium.click("_topmenulink_Menu");
		selenium.click("_topmenulink_Menu_Administration");
		selenium.click("id=_topmenulink_OrganizationUnits_OrganizationUnit_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnOrganizationUnit");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_listlink_OrganizationUnits_OrganizationUnit_Details_organizationunits_system");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnitRoleSet_List");//set ou roleset
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_listlink_OrganizationUnits_OrganizationUnitRoleSet_Details_organizationunitrolesets_organizationunits_system");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditRoles");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=cbFunctionRoles");
		selenium.click("id=cbSystemRoles");
		selenium.clickElementsByName("AvailableOrganizationUnitRoleSetRoles.Value");
		selenium.click("id=btnEditOrganizationUnitRoleSetRoles");
		selenium.waitForElementDisplay("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditProgramTypes");
		selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditProgramTypes");
		selenium.sleepSeconds(2);
		selenium.waitForPageToLoad("30000");
		selenium.click("id=cbAll");
		selenium.sleepSeconds(1);
		selenium.click("id=btnEditOrganizationUnitRoleSetProgramType");
		selenium.waitForPageToLoad("30000");
		//selenium.waitForElementClickable("id=_leftnavlink_OrganizationUnits_OrganizationUnit_StaffList");
		selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_StaffList");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_listlink_OrganizationUnits_Staff_Details_staffs_systemadministrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_leftnavlink_Users_UserRoleSet_Manage");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Users_UserRoleSet_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.clickElementsByName("AvailableUserRoleSetRoles.Value");
		selenium.click("id=btnEditUserRoleSet");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_Details");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
