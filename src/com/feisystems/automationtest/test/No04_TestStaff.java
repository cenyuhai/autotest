package com.feisystems.automationtest.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No04_TestStaff {
	static Logger logger = TestUtils.getLogger(No04_TestStaff.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	public void clean() {
		List<String> ouIds = RavenDbAPI.getCollectionEntityId("organizationunits");
		for (String id : ouIds) {
			if (!id.equals("organizationunits/system"))
				RavenDbAPI.deleteDocumentById(id);
		}

		List<String> staffIds = RavenDbAPI.getCollectionEntityId("staffs");
		for (String id : staffIds) {
			if (!id.equals("staffs/systemadministrator"))
				RavenDbAPI.deleteDocumentById(id);
		}

		List<String> userIds = RavenDbAPI.getCollectionEntityId("useraccounts");
		for (String id : userIds) {
			if (!id.equals("useraccounts/staffs/systemadministrator"))
				RavenDbAPI.deleteDocumentById(id);
		}
	}

	@Test
	public void N1_testStaffCreate() throws Exception {
		try {

			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.click("id=btnLogin");
			clean();
			// create ou for this staff
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=OrganizationUnit_BusinessName", "staffOU");
			selenium.type("id=OrganizationUnit_ShortName", "staff1");
			selenium.type("id=OrganizationUnit_DbaName", "staffdba1");
			selenium.type("id=OrganizationUnit_WebsiteUrl", "www.fei.com");
			selenium.type("id=OrganizationUnit_TaxIdentifier_Value",
					"827439092");
			selenium.type("id=OrganizationUnit_ContactInfo_ContactName",
					"brou1");
			selenium.type("id=OrganizationUnit_ContactInfo_Email",
					"brou1@fei.com");
			selenium.type("id=OrganizationUnitPhone", "1082743909");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.type("id=OrganizationUnit_PrimaryAddress_AddressLine1",
					"ShangDi");
			selenium.type("id=OrganizationUnit_PrimaryAddress_AddressLine2",
					"Haidian");
			selenium.type("id=OrganizationUnit_PrimaryAddress_City", "Beijing");
			selenium.type("id=OrganizationUnit_PrimaryAddress_PostalCode",
					"10085");
			selenium.type(
					"id=OrganizationUnit_AlternativeAddress_AddressLine1",
					"ShangDi");
			selenium.type(
					"id=OrganizationUnit_AlternativeAddress_AddressLine2",
					"Haidian");
			selenium.type("id=OrganizationUnit_AlternativeAddress_City",
					"Beijing");
			selenium.type("id=OrganizationUnit_AlternativeAddress_PostalCode",
					"10085");
			selenium.click("id=btnCreateOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.check("id=cbAll");
			selenium.click("id=btnSaveAndContinue");
			selenium.waitForPageToLoad("30000");
			selenium.check("id=cbAll");
			selenium.click("id=btnCreateOrganizationUnitAttribute");
			selenium.waitForPageToLoad("30000");
			//Identifier Information
			selenium.select("id=OrganizationUnitIdentifier_IdentifierType_Id", "label=Provider ID");
			selenium.type("id=OrganizationUnitIdentifier_IdentifierNumber", "1082743909");
			selenium.type("id=OrganizationUnitIdentifier_ExpireDate", TestUtils.getEndDayString());
			selenium.click("id=btnSaveAndContinue");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id","label=Home");
			selenium.type("id=OrganizationUnitPhone", "1082743909");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.click("id=btnSaveAndContinue");

			// add roleset
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnitRoleSet_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Name", "Administrator");
			selenium.click("id=btnCreateOrganizationUnitRoleSet");
			selenium.sleepSeconds(3);
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=btnCreateOrganizationUnitRoleSetJurisdiction");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=cbFunctionRoles");
			/*
			 * selenium.click("id=unique4"); selenium.click("id=unique6");
			 * selenium.click("id=unique8");
			 */
			selenium.click("id=cbSystemRoles");
			selenium.clickElementsByName("AvailableOrganizationUnitRoleSetRoles.Value");
			selenium.click("id=btnCreateOrganizationUnitRoleSetRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.click("id=unique4");
			selenium.click("id=btnCreateOrganizationUnitRoleSetProgramType");
			selenium.waitForPageToLoad("30000");

			// create new ou for editing staff
			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=OrganizationUnit_BusinessName", "linkOU");
			selenium.type("id=OrganizationUnit_ShortName", "linkou");
			selenium.type("id=OrganizationUnit_DbaName", "linkOUdba1");
			selenium.type("id=OrganizationUnit_WebsiteUrl", "www.fei.com");
			selenium.type("id=OrganizationUnit_TaxIdentifier_Value",
					"827439092");
			selenium.type("id=OrganizationUnit_ContactInfo_ContactName",
					"brou1");
			selenium.type("id=OrganizationUnit_ContactInfo_Email",
					"brou1@fei.com");
			selenium.type("id=OrganizationUnitPhone", "1082743909");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.type("id=OrganizationUnit_PrimaryAddress_AddressLine1",
					"ShangDi");
			selenium.type("id=OrganizationUnit_PrimaryAddress_AddressLine2",
					"Haidian");
			selenium.type("id=OrganizationUnit_PrimaryAddress_City", "Beijing");
			selenium.type("id=OrganizationUnit_PrimaryAddress_PostalCode",
					"10085");
			selenium.type(
					"id=OrganizationUnit_AlternativeAddress_AddressLine1",
					"ShangDi");
			selenium.type(
					"id=OrganizationUnit_AlternativeAddress_AddressLine2",
					"Haidian");
			selenium.type("id=OrganizationUnit_AlternativeAddress_City",
					"Beijing");
			selenium.type("id=OrganizationUnit_AlternativeAddress_PostalCode",
					"10085");
			selenium.click("id=btnCreateOrganizationUnit");
			/*
			 * selenium.waitForPageToLoad("30000"); selenium.check("id=cbAll");
			 * selenium.click("id=btnSaveAndContinue");
			 * selenium.waitForPageToLoad("30000"); selenium.check("id=cbAll");
			 * selenium.click("id=btnCreateOrganizationUnitAttribute");
			 * selenium.waitForPageToLoad("30000");
			 * selenium.select("id=OrganizationUnitPhone_PhoneType_Id",
			 * "label=Work"); selenium.type("id=OrganizationUnitPhone",
			 * "1082743909"); selenium.type("id=OrganizationUnitPhone_ext",
			 * "010"); selenium.click("id=OrganizationUnitPhone_IsPrimary");
			 * selenium.click("id=btnSaveAndContinue");
			 */

			// create staff
			selenium.waitForElementClickable("id=_topnavlink_OrganizationUnits_Staff_List");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Staff_Prefix", "x");
			selenium.type("id=Staff_StaffName_FirstName", "xin");
			selenium.type("id=Staff_StaffName_LastName", "zhou");
			selenium.type("id=Staff_StaffName_Suffix", "z");
			selenium.type("id=Staff_BusinessTitle", "Admin");
			selenium.type("id=Staff_BusinessCredential", "RN");
			selenium.type("id=Staff_EmailAddress", "xin.zhou@fei.com");
			selenium.click("id=btnOrganizationUnitSearch");
			selenium.waitForElementDisplay("OrganizationUnitSearchCriteria.OrganizationUnitName");
			selenium.type(
					"OrganizationUnitSearchCriteria.OrganizationUnitName",
					"staffOU");
			selenium.sleepSeconds(2);
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
			selenium.waitForElementDisplay("//tbody/tr[1]/td[1]/a");
			selenium.click("//tbody/tr[1]/td[1]/a");
			selenium.sleepSeconds(2);
			//selenium.click("id=IsReceivingEmailAlertYes");
			selenium.click("id=StaffPhone_IsPrimary");
			selenium.select("id=StaffPhone_PhoneType_Id", "label=Work");
			selenium.type("id=phone_0", "82743909");
			selenium.click("id=btnSubmitCreateStaffForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_StaffPhone_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=StaffPhone_IsPrimary");
			selenium.select("id=StaffPhone_PhoneType_Id", "label=Mobile");
			selenium.type("id=StaffPhoneNumber", "1861031036");
			selenium.click("id=btnCreateStaff");
			selenium.waitForPageToLoad("30000");

			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_Users_UserRoleSet_Manage");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserAccount_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=SsoId", "xin");
			selenium.type("id=SsoEmail", "xin.zhou@fei.com");
			selenium.click("id=btnCreateUserAccount");
			selenium.waitForPageToLoad("30000");
			int timeout = 0;
			while (timeout < 10) {
				try {
					try {
						if (selenium
								.isVisible("_contentlink_Users_UserRoleSet_Create")) {
							selenium.waitForElementDisplay("_contentlink_Users_UserRoleSet_Create");
							selenium.click("id=_contentlink_Users_UserRoleSet_Create");
							break;
						}

					} catch (Exception ex) {
						logger.error(ex);
					}

					try {
						if (selenium
								.isVisible("//div[@class='error-msg-container active']")) {
							selenium.type("id=SsoId",
									"xin" + new Date().getTime());
							selenium.type("id=SsoEmail", "xin.zhou@fei.com");
							selenium.click("id=btnCreateUserAccount");
							selenium.waitForElementDisplay("_contentlink_Users_UserRoleSet_Create");
							selenium.click("id=_contentlink_Users_UserRoleSet_Create");
							// selenium.click("id=cbAll");
							/*
							 * selenium.selectByIdAndIndex(
							 * "OrganizationUnitRoleSetId", 1);
							 * selenium.runScript
							 * ("$('#OrganizationUnitRoleSetId').change()");
							 * selenium.select( "id=OrganizationUnitRoleSetId",
							 * "label=" + selenium
							 * .getSelectOptions("id=OrganizationUnitRoleSetId"
							 * )[1]);
							 */
							break;
						}
					} catch (Exception ex) {
						logger.error(ex);
					}

					Thread.sleep(1000);
				} catch (Exception ex) {
					logger.equals(ex);
				}
				timeout++;
			}

			selenium.waitForPageToLoad("30000");
			selenium.click("id=cbAll");

			// selenium.check("//input[@name='AvailableUserRoleSetRoles.Value'][1]");
			selenium.click("id=btnCreateUserRoleSet");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Staff_Prefix", "z1");
			selenium.type("id=Staff_StaffName_FirstName", "xin1");
			selenium.type("id=Staff_StaffName_LastName", "zhou1");
			selenium.type("id=Staff_StaffName_Suffix", "x1");
			selenium.type("id=Staff_BusinessTitle", "casemanager");
			selenium.type("id=Staff_BusinessCredential", "MSW");
			selenium.type("id=Staff_EmailAddress", "xin.zhou@fei.com");
			selenium.click("id=btnOrganizationUnitSearch");
			selenium.waitForElementDisplay("OrganizationUnitSearchCriteria.OrganizationUnitName");
			selenium.type(
					"OrganizationUnitSearchCriteria.OrganizationUnitName",
					"staffOU");
			selenium.sleepSeconds(2);
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");

			selenium.sleepSeconds(2);
			selenium.click("//tbody/tr[1]/td[1]/a[@class='selectResultBtn']");
			selenium.sleepSeconds(2);
			selenium.select("Supervisor",
					selenium.getSelectOptions("Supervisor")[1]);

			//selenium.click("id=IsReceivingEmailAlertYes");
			selenium.select("id=StaffPhone_PhoneType_Id", "label=Work");
			selenium.type("id=phone_0", "82743909");
			selenium.type("id=phone_0_ext", "010");
			selenium.click("id=btnSubmitCreateStaffForm");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_Users_UserRoleSet_Manage");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserAccount_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=SsoId", "xin1" + new Date().getTime());
			selenium.type("id=SsoEmail", "xin.zhou@fei.com");
			selenium.click("id=btnCreateUserAccount");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserRoleSet_Create");
			selenium.waitForPageToLoad("30000");
			// not exist
			/*
			 * if (isExistAccount) {
			 * selenium.selectByIdAndIndex("OrganizationUnitRoleSetId", 1);
			 * selenium.runScript("$('#OrganizationUnitRoleSetId').change()"); }
			 */
			selenium.click("id=cbAll");
			// selenium.check("//input[@name='AvailableUserRoleSetRoles.Value'][1]");
			selenium.click("id=btnCreateUserRoleSet");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_Users_UserDelegation_UserDelegationSummary");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserDelegation_ListUsersToDelegateMe");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.type(
					"//table[@id='tblStaffAuthToLoginAsMe']/tbody/tr[1]/td[4]/span/span/input",
					TestUtils.getNowString());
			selenium.type(
					"//table[@id='tblStaffAuthToLoginAsMe']/tbody/tr[1]/td[5]/span/span/input",
					TestUtils.getEndDayString());
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserDelegation_ListUserToBeDelegatedByMe");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.type(
					"//form[@id='mainForm']/table/tbody/tr[1]/td[4]/span/span/input",
					TestUtils.getNowString());
			selenium.type(
					"//form[@id='mainForm']/table/tbody/tr[1]/td[5]/span/span/input",
					TestUtils.getEndDayString());
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void N2_testStaffEdit() throws Exception {
		try {

			selenium.open(selenium.baseUrl + "OrganizationUnits/Staff/List/");
			selenium.type("id=StaffSearchCriteria_StaffName", "xin");
			selenium.waitForElementDisplay("id=btnSearchStaff");
			selenium.click("id=btnSearchStaff");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='StaffSummaryGrid']/tbody/tr[1]/td[6]/a");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Staff_Prefix", "z");
			selenium.type("id=Staff_StaffName_Suffix", "x");
			if(selenium.isVisible("id=IsReceivingEmailAlertNo")) {
				selenium.click("id=IsReceivingEmailAlertNo");
			}
			selenium.click("id=btnSaveStaff");
			selenium.click("//div[@id='ltssPanelBar']/div[2]/div/h4");
			selenium.click("//table[@id='DataTables_Table_0']/tbody/tr[1]/td[4]/a[contains(text(),'Edit')]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=StaffPhone_IsPrimary");
			selenium.type("id=StaffPhoneNumber_ext", "010");
			selenium.click("id=btnEditStaffPhone");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.runScript("$('.panelBarItem:eq(2) div:eq(1)').click()");
			selenium.sleepSeconds(1);
			selenium.runScript("$('#DataTables_Table_0 tbody tr:eq(0) a:eq(1)').click()");
			selenium.runScript("$('#btnYes').click()");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[3]/div/h4");
			selenium.waitForPageToLoad("30000");
			// selenium.click("//div[@id='ltssPanelBar']/div[4]/div/h4");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=btnEnableOrDisableStaff");
			selenium.click("//div[@id='divStaffActivationConfirm']/div[@class=\"fancybox-dialog-controls\"]/button[contains(text(),'Cancel')]");
			selenium.click("id=btnEnableOrDisableStaff");
			selenium.click("//div[@id='divStaffActivationConfirm']/div[@class=\"fancybox-dialog-controls\"]/button[contains(text(),'Yes')]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableStaff");
			selenium.click("//div[@id='divStaffActivationConfirm']/div[@class=\"fancybox-dialog-controls\"]/button[contains(text(),'Yes')]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_Users_UserDelegation_UserDelegationSummary");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserDelegation_ListUsersToDelegateMe");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserDelegation_ListUserToBeDelegatedByMe");
			selenium.waitForPageToLoad("30000");

			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_Users_UserRoleSet_Manage");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserAccount_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=SsoEmail", "xin.zhou@feisystems.com");
			selenium.click("id=btnEditUserAccount");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserRoleSet_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=cbAll");
			selenium.click("id=btnEditUserRoleSet");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Users_UserRoleSet_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.check("id=cbAll");
			selenium.click("id=btnEditUserRoleSet");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.click("id=btnNo");
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableUserRole");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_Staff_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void N3_testMyProfileEdit() throws Exception {
		try {

			selenium.click("id=_topmenulink_Account");
			selenium.click("id=_topmenulink_Account_Authorize");
			selenium.type("id=IdentityName", "!xin");
			selenium.click("btnLogin");
			selenium.sleepSeconds(2);
			selenium.open(selenium.baseUrl + "OrganizationUnits/MyProfile/Details/");
			selenium.waitForPageToLoad("30000");
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
	public void N4_testMyOUEdit() throws Exception {
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
					"linkOU");
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
