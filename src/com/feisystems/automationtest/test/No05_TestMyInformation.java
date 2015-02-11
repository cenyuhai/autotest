package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No05_TestMyInformation {
	static Logger logger = TestUtils.getLogger(No05_TestMyInformation.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	@Test
	public void N1_testMyProfileEdit() throws Exception {
		try {

			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/MyProfile/Details/");
			selenium.type("id=IdentityName", "!xin");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnLogin");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_MyProfile_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=IsReceivingEmailAlertYes");
			selenium.click("id=btnSubmitMyProfileForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=h4");
			selenium.click("id=_contentlink_OrganizationUnits_MyProfile_CreatePhone");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StaffPhone_PhoneType_Id",
					"label=Toll Free Phone");

			selenium.type("id=phone_0", TestUtils.getRandomNumber(10));
			selenium.type("id=phone_0_ext", "010");
			selenium.click("id=btnCreateStaffPhoneForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[2]/div/h4");
			selenium.sleepSeconds(3);
			selenium.click("xpath=(//a[contains(text(),'Edit')])[4]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=StaffPhone_IsPrimary");
			selenium.click("id=btnEditStaffPhoneForm");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.runScript("$('.panelBarItem:eq(2) div:eq(1)').click()");
			selenium.sleepSeconds(1);
			selenium.runScript("$('#DataTables_Table_0 tbody tr:eq(0) a:eq(1)').click()");
			selenium.click("btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=expandCollapseAll");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=_leftnavlink_Users_MyUserDelegation_UserDelegationSummary");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_MyUserDelegation_ListUsersToDelegateMe");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_MyProfile_Details");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void N2_testMyOUEdit() throws Exception {
		try {
			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/MyOrganizationUnit/Details/");

			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_MyOrganizationUnit_Edit");
			selenium.type("id=OrganizationUnit_ShortName", "myedit");
			selenium.type("id=OrganizationUnit_WebsiteUrl",
					"www.feisystems.com");
			try {
				if (selenium
						.isVisible("id=OrganizationUnit_AlternativeAddress_StateProvince_Id")) {
					selenium.select(
							"id=OrganizationUnit_AlternativeAddress_StateProvince_Id",
							"label=Michigan");
				}
			} catch (Exception ex) {
				logger.error(ex);
			}

			try {
				if (selenium
						.isVisible("id=OrganizationUnit_PrimaryAddress_AddressLine1")) {
					selenium.type(
							"id=OrganizationUnit_PrimaryAddress_AddressLine1",
							"address1");
				}
			} catch (Exception ex) {
				logger.error(ex);
			}

			try {
				if (selenium
						.isVisible("id=OrganizationUnit_PrimaryAddress_City")) {
					selenium.type("id=OrganizationUnit_PrimaryAddress_City",
							"maryland");
				}
			} catch (Exception ex) {
				logger.error(ex);
			}

			try {
				if (selenium
						.isVisible("id=OrganizationUnit_PrimaryAddress_PostalCode")) {
					selenium.type(
							"id=OrganizationUnit_PrimaryAddress_PostalCode",
							"55555");
				}
			} catch (Exception ex) {
				logger.error(ex);
			}

			selenium.click("id=btnEditOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=h4");
			selenium.click("css=h4");
			selenium.click("id=_contentlink_OrganizationUnits_MyOrganizationUnit_EditJurisdictions");
			selenium.waitForPageToLoad("30000");
			selenium.check("cbAll");
			selenium.click("id=btnSubmitOrganizationUnitJurisdictionForm");

			selenium.click("//div[@id='ltssPanelBar']/div[2]/div/h4");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.header.expanded > h4");
			selenium.click("//div[@id='ltssPanelBar']/div[3]/div/h4");
			selenium.click("id=_contentlink_OrganizationUnits_MyOrganizationUnit_EditAttributes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique6");
			selenium.click("id=btnSubmitOrganizationUnitAttributeForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[3]/div/h4");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.header.expanded > h4");
			selenium.click("id=_contentlink_OrganizationUnits_MyOrganizationUnit_CreatePhone");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id",
					"label=Toll Free Phone");
			selenium.type("id=OrganizationUnitPhone", "1861041046");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.click("id=btnCreatePhone");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[4]/div/h4");
			selenium.waitForPageToLoad("30000");
			selenium.click("xpath=(//a[contains(text(),'Edit')])[5]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.click("id=btnSubmitOrganizationUnitPhoneForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[4]/div/h4");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=addExist");
			selenium.sleepSeconds(3);
			selenium.type(
					"id=OrganizationUnitSearchCriteria.OrganizationUnitName",
					"branchou1");
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
			selenium.sleepSeconds(3);
			selenium.click("css=#searchResultTable0 > tbody > tr.odd > td > input[type=\"checkbox\"]");
			selenium.sleepSeconds(3);
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-right > input.js-selectBtn");
			selenium.waitForPageToLoad("30000");
			selenium.sleepSeconds(3);
			selenium.click("//div[@id='ltssPanelBar']/div[5]/div/h4");
			selenium.click("link=Organization Unit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_MyOrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");

			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}



}
