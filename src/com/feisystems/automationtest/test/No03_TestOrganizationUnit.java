package com.feisystems.automationtest.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No03_TestOrganizationUnit {
	static Logger logger = TestUtils.getLogger(No03_TestOrganizationUnit.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();
	
	public void clean() {

		List<String> ouIds = RavenDbAPI.getCollectionEntityId("organizationunits");
		for (String id : ouIds) {
			if(!id.equals("organizationunits/system"))
				RavenDbAPI.deleteDocumentById(id);
		}
		
		RavenDbAPI.deleteCollectionByName("providerprofiles");
	}

	@Test
	public void testOUCreate() throws Exception {
		try {
			selenium.open(selenium.baseUrl);
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnLogin");
			clean();
			createProvider();
			selenium.open(selenium.baseUrl + "OrganizationUnits/OrganizationUnit/List/");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=OrganizationUnit_BusinessName", "Organization1");
			selenium.type("id=OrganizationUnit_ShortName", "org1");
			selenium.type("id=OrganizationUnit_DbaName", "dba1");
			selenium.type("id=OrganizationUnit_WebsiteUrl", "www.fei.com");
			selenium.type("id=OrganizationUnit_TaxIdentifier_Value",
					"827439091");
			selenium.type("id=OrganizationUnit_ContactInfo_ContactName", "org1");
			selenium.type("id=OrganizationUnit_ContactInfo_Email",
					"ou1@fei.com");
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
			selenium.click("id=unique1");
			selenium.click("id=unique2");
			selenium.click("id=unique3");
			selenium.click("id=btnSaveAndContinue");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnCreateOrganizationUnitAttribute");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=OrganizationUnitIdentifier_IdentifierType_Id", "label=Provider ID");
			selenium.type("id=OrganizationUnitIdentifier_IdentifierNumber", "providerid1");
			selenium.type("id=OrganizationUnitIdentifier_ExpireDate", TestUtils.getEndDayString());
			selenium.click("id=btnSaveAndContinue");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id", "label=Home");
			selenium.type("id=OrganizationUnitPhone", "1082743909");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.click("id=btnSaveAndContinue");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitPhone_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id", "label=Mobile");
			selenium.type("id=OrganizationUnitPhone", "1861031036");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.click("id=btncreateOrganizationUnitPhone");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=OrganizationUnit_BusinessName", "branchou1");
			selenium.type("id=OrganizationUnit_ShortName", "brou1");
			selenium.type("id=OrganizationUnit_DbaName", "brdba1");
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
			selenium.select("id=OrganizationUnitIdentifier_IdentifierType_Id", "label=Provider ID");
			selenium.type("id=OrganizationUnitIdentifier_IdentifierNumber", "providerid2");
			selenium.type("id=OrganizationUnitIdentifier_ExpireDate", TestUtils.getEndDayString());
			selenium.click("id=btnSaveAndContinue");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id", "label=Work");
			selenium.type("id=OrganizationUnitPhone", "1082743909");
			selenium.type("id=OrganizationUnitPhone_ext", "010");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.click("id=btnSaveAndContinue");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[6]/div/h4");
			selenium.click("//table[@class='inline-dataTable dataTable']/tbody/tr[1]/td[6]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[6]/div/h4");
			selenium.click("//table[@class='inline-dataTable dataTable']/tbody/tr[1]/td[6]/a[2]");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.click("id=_topnavlink_OrganizationUnits_OrganizationUnit_List");
			selenium.waitForPageToLoad("30000");
			selenium.type(
					"id=OrganizationUnitSearchCriteria_OrganizationUnitName",
					"organization1");
			selenium.click("id=btnOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='OrganizationUnitSummaryGrid']/tbody/tr[1]/td[6]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[6]/div/h4");
			selenium.click("link=Remove");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");			
			selenium.click("id=addExist");
			selenium.sleepSeconds(3);
			selenium.click("id=OrganizationUnitSearchCriteria.OrganizationUnitName");
			selenium.type(
					"id=OrganizationUnitSearchCriteria.OrganizationUnitName",
					"branchou1");
			
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
			selenium.click("css=td > input[type=\"checkbox\"]");
			selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-right > input.js-selectBtn");
			selenium.waitForPageToLoad("30000");
			selenium.sleepSeconds(2);
			selenium.click("//div[@id='ltssPanelBar']/div[6]/div/h4");
			selenium.click("link=Remove");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");

			selenium.click("//div[@id='ltssPanelBar']/div[7]/div/h4");
			selenium.click("id=RegisterProviderProfile");
			selenium.sleepSeconds(3);
			selenium.click("id=ProviderProfileSearchCriteria.ProviderName");
			selenium.type("id=ProviderProfileSearchCriteria.ProviderName",
					"ouProvider");
			selenium.click("css=#searchWindow1 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
			selenium.click("css=td > input[type=\"checkbox\"]");
			selenium.click("css=#searchWindow1 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-right > input.js-selectBtn");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.click("//div[@id='ltssPanelBar']/div[7]/div/h4");
			selenium.clickByFuzzyLinkText("Unlink");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnitRoleSet_List");
			selenium.waitForPageToLoad("30000");
			//selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_StaffList");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_List");
			selenium.waitForPageToLoad("30000");

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void testOUEdit() throws Exception {
		try {
			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.waitForPageToLoad("30000");
			selenium.type(
					"id=OrganizationUnitSearchCriteria_OrganizationUnitName",
					"organization1");
			selenium.click("id=btnOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.clickByFuzzyLinkText("Organization Unit*");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=OrganizationUnit_ShortName", "orgedit");
			selenium.type("id=OrganizationUnit_DbaName", "dbaedit");
			selenium.type("id=OrganizationUnit_ContactInfo_ContactName", "ouedit");
			selenium.click("id=btnEditOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_EditJurisdictions");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique4");
			selenium.click("id=unique5");
			selenium.click("id=btnEditOrganizationUnitJurisdiction");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_leftnavlink_OrganizationUnits_OrganizationUnit_StaffList");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_leftnavlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_OrganizationUnits_OrganizationUnit_EditAttributes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=cbAll");
			selenium.click("id=btnEditOrganizationUnitAttribute");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[4]/div/h4");
			selenium.click("//table[@id='DataTables_Table_0']/tbody/tr[1]/td[5]/a[contains(text(),'Edit')]");
			selenium.select("id=OrganizationUnitIdentifier_IdentifierType_Id", "label=Provider Base Number");
			selenium.type("id=OrganizationUnitIdentifier_IdentifierNumber", "ProviderBaseNumber");
			selenium.type("id=OrganizationUnitIdentifier_ExpireDate", TestUtils.getFutureDay(100));
			selenium.click("id=btnEditOrganizationUnitIdentifier");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='ltssPanelBar']/div[5]/div/h4");
			selenium.click("//table[@id='DataTables_Table_1']/tbody/tr[1]/td[4]/a[contains(text(),'Edit')]");
			selenium.click("id=OrganizationUnitPhone_IsPrimary");
			selenium.select("id=OrganizationUnitPhone_PhoneType_Id", "label=Work");
			selenium.click("id=btnEditOrganizationUnitPhone");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=btnEnableOrDisableOrganizationUnit");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableOrganizationUnit");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableOrganizationUnit");
			selenium.click("id=btnNo");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_List");
			selenium.waitForPageToLoad("30000");

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	@Test
	public void testRoleSetAdd() throws Exception {
		try {
			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.type(
					"id=OrganizationUnitSearchCriteria_OrganizationUnitName",
					"organization1");
			selenium.click("id=btnOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.clickByFuzzyLinkText("Organization Unit*");
			selenium.waitForPageToLoad("30000");
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
			selenium.click("id=roles_staffreadonly");
			selenium.click("id=btnCreateOrganizationUnitRoleSetRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique1");
			selenium.click("id=unique4");
			selenium.click("id=btnCreateOrganizationUnitRoleSetProgramType");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void testRoleSetEdit() throws Exception {
		try {
			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.type(
					"id=OrganizationUnitSearchCriteria_OrganizationUnitName",
					"organization1");
			selenium.click("id=btnOrganizationUnit");
			selenium.waitForPageToLoad("30000");
			selenium.clickByFuzzyLinkText("Organization Unit*");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_leftnavlink_OrganizationUnits_OrganizationUnitRoleSet_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='DataTables_Table_0']/tbody/tr[1]/td[3]/a");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_Edit");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=Name", "Administratoredit");
			selenium.click("id=btnEditOrganizationUnitRoleSet");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditJurisdictions");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique3");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEditOrganizationUnitRoleSetJurisdiction");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditRoles");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=cbFunctionRoles");
			selenium.click("id=cbSystemRoles");
			selenium.clickElementsByName("AvailableOrganizationUnitRoleSetRoles.Value");
			selenium.click("id=btnEditOrganizationUnitRoleSetRoles");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_EditProgramTypes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			selenium.click("id=btnEditOrganizationUnitRoleSetProgramType");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=expandCollapseAll");
			selenium.click("id=btnEnableOrDisableOrganizationUnitRoleSet");
			selenium.click("id=btnNo");
			selenium.click("id=btnEnableOrDisableOrganizationUnitRoleSet");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnEnableOrDisableOrganizationUnitRoleSet");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnitRoleSet_List");
			selenium.waitForPageToLoad("30000");
			selenium.sleepSeconds(2);
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_List");
			selenium.waitForPageToLoad("30000");

			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	public void createProvider() {

		No02_TestProvider.createProvider(selenium, "ouProvider");
		
	}

}
