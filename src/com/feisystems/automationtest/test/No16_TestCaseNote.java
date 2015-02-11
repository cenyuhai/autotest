package com.feisystems.automationtest.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No16_TestCaseNote {
	static Logger logger = TestUtils.getLogger(No16_TestCaseNote.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	public void clean() {
		List<String> ouIds = RavenDbAPI.getCollectionEntityId("organizationunits");
		for (String id : ouIds) {
			if(!id.equals("organizationunits/system"))
				RavenDbAPI.deleteDocumentById(id);
		}
		
		RavenDbAPI.deleteCollectionByName("clients");
		
		List<String> staffIds = RavenDbAPI.getCollectionEntityId("staffs");
		for (String id : staffIds) {
			if(!id.equals("staffs/systemadministrator"))
				RavenDbAPI.deleteDocumentById(id);
		}
		
		List<String> userIds = RavenDbAPI.getCollectionEntityId("useraccounts");
		for (String id : userIds) {
			if(!id.equals("useraccounts/staffs/systemadministrator"))
				RavenDbAPI.deleteDocumentById(id);
		}
		
		RavenDbAPI.deleteCollectionByName("casenoteaccesscontrolmatrices");
		
		RavenDbAPI.deleteCollectionByName("casenoteorganizationunitdefinitions");
	}
	
	
	@Test
	public void testCreateCaseNote() throws Exception {
		
		initOU();
		initRoleSet();
		initStaff();
		initIndividual();
		testAddCaseNotes();

	}

	public void initOU() throws Exception {
		try {

			HashMap<String, String[]> attributes = new HashMap<String, String[]>();
			attributes.put("CaseNoteOrganization1", new String[] { "System Administration", "Assessment" });
			attributes.put("CaseNoteOrganization2", new String[] { "B2I Provider", "Service Provider, Independent" });
			attributes.put("CaseNoteOrganization3", new String[] { "ICF/IID" });
			attributes.put("CaseNoteOrganization4", new String[] { "Case Management Agency" });
			attributes.put("CaseNoteOrganization5", new String[] { "Nursing Facility" });

			selenium.open(selenium.baseUrl
					+ "OrganizationUnits/OrganizationUnit/List/");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnLogin");

			clean();
			
			String orgName = null;
			String[] attrs = null;
			for (int i = 1; i < 6; i++) {

				selenium.click("id=_contentlink_OrganizationUnits_OrganizationUnit_Create");
				selenium.waitForPageToLoad("30000");
				orgName = "CaseNoteOrganization" + i;
				selenium.type("id=OrganizationUnit_BusinessName", orgName);
				selenium.type("id=OrganizationUnit_ShortName", "org" + i);
				selenium.type("id=OrganizationUnit_DbaName", "dba2");
				selenium.type("id=OrganizationUnit_WebsiteUrl", "www.fei.com");
				selenium.type("id=OrganizationUnit_TaxIdentifier_Value",
						"827439091");
				selenium.type("id=OrganizationUnit_ContactInfo_ContactName",
						"org6");
				selenium.type("id=OrganizationUnit_ContactInfo_Email",
						"ou1@fei.com");
				selenium.type("id=OrganizationUnitPhone", "1082743909");
				selenium.type("id=OrganizationUnitPhone_ext", "010");
				selenium.type(
						"id=OrganizationUnit_PrimaryAddress_AddressLine1",
						"ShangDi");
				selenium.type(
						"id=OrganizationUnit_PrimaryAddress_AddressLine2",
						"Haidian");
				selenium.type("id=OrganizationUnit_PrimaryAddress_City",
						"Beijing");
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
				selenium.type(
						"id=OrganizationUnit_AlternativeAddress_PostalCode",
						"10085");
				selenium.click("id=btnCreateOrganizationUnit");
				selenium.waitForPageToLoad("30000");

				selenium.click("id=cbAll");
				selenium.click("id=btnSaveAndContinue");
				selenium.waitForPageToLoad("30000");

				attrs = attributes.get(orgName);
				if (attrs != null) {
					for (int j = 0; j < attrs.length; j++) {
						selenium.clickCheckBoxByText(attrs[j]);
					}
				}

				// selenium.click("id=cbAll");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=btnCreateOrganizationUnitAttribute");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=OrganizationUnitPhone_PhoneType_Id",
						"label=Home");
				selenium.type("id=OrganizationUnitPhone", "1082743909");
				selenium.type("id=OrganizationUnitPhone_ext", "010");
				selenium.click("id=OrganizationUnitPhone_IsPrimary");
				selenium.click("id=btnSaveAndContinue");
				selenium.waitForPageToLoad("30000");
				selenium.open(selenium.baseUrl
						+ "OrganizationUnits/OrganizationUnit/List/");
			}

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public void initRoleSet() throws Exception {
		try {

			for (int i = 1; i < 6; i++) {
				selenium.open(selenium.baseUrl
						+ "OrganizationUnits/OrganizationUnit/List/");
				selenium.type(
						"id=OrganizationUnitSearchCriteria_OrganizationUnitName",
						"CaseNoteOrganization" + i);
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
				selenium.sleepSeconds(1);
				selenium.waitForPageToLoad("30000");
				selenium.click("id=cbAll");
				selenium.click("id=btnCreateOrganizationUnitRoleSetJurisdiction");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=cbFunctionRoles");
				selenium.click("id=cbSystemRoles");
				selenium.clickElementsByName("AvailableOrganizationUnitRoleSetRoles.Value");
				selenium.click("id=btnCreateOrganizationUnitRoleSetRoles");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=cbAll");
				selenium.click("id=btnCreateOrganizationUnitRoleSetProgramType");
				selenium.waitForPageToLoad("30000");

				selenium.open(selenium.baseUrl + "OrganizationUnits/OrganizationUnit/List/");
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	public void initStaff() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "OrganizationUnits/Staff/List/");
			//selenium.click("btnLogin");
			HashMap<String, String[]> names = new HashMap<String, String[]>();
			names.put("CaseNoteOrganization1",
					new String[] { "xin", "zhou", "xinzhou" });
			names.put("CaseNoteOrganization2", new String[] { "huihui", "wang", "hui" });
			names.put("CaseNoteOrganization3", new String[] { "xiaoming", "li",
					"xiaoming" });
			names.put("CaseNoteOrganization4", new String[] { "jianyang", "zhang",
					"jianyang" });
			names.put("CaseNoteOrganization5", new String[] { "haili", "li", "haili" });

			String orgName = null;
			String[] name = null;

			for (int i = 1; i < 6; i++) {

				selenium.click("id=_contentlink_OrganizationUnits_Staff_Create");
				selenium.waitForPageToLoad("30000");
				selenium.type("id=Staff_Prefix", "h");

				orgName = "CaseNoteOrganization" + i;
				name = names.get(orgName);

				selenium.type("id=Staff_StaffName_FirstName", name[0]);
				selenium.type("id=Staff_StaffName_LastName", name[1]);
				selenium.type("id=Staff_StaffName_Suffix", "l");
				selenium.type("id=Staff_BusinessTitle", "Admin");
				selenium.type("id=Staff_BusinessCredential", "RN");
				selenium.type("id=Staff_EmailAddress", "haili.li@fei.com");
				selenium.click("id=btnOrganizationUnitSearch");
				selenium.waitForElementDisplay("OrganizationUnitSearchCriteria.OrganizationUnitName");
				selenium.type(
						"OrganizationUnitSearchCriteria.OrganizationUnitName",
						orgName);
				selenium.sleepSeconds(2);
				selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
				selenium.waitForElementDisplay("//tbody/tr[1]/td[1]/a");
				selenium.click("//tbody/tr[1]/td[1]/a");
				selenium.sleepSeconds(2);
				selenium.click("id=IsReceivingEmailAlertYes");
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

				selenium.click("id=_leftnavlink_Users_UserRoleSet_Manage");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=_contentlink_Users_UserAccount_Create");
				selenium.waitForPageToLoad("30000");
				selenium.type("id=SsoId", name[2]);
				selenium.type("id=SsoEmail", "haili.li@fei.com");
				selenium.click("id=btnCreateUserAccount");
				selenium.waitForPageToLoad("30000");
				int timeout = 0;
				while (timeout < 10) {
					try {
						try {
							if (selenium
									.isVisible("_contentlink_Users_UserRoleSet_Create")) {
								selenium.waitForElementDisplay("_contentlink_Users_UserRoleSet_Create");
								selenium.click("id=_contentlink_Users_UserRoleSet_Create");// click
																							// the
																							// "Add User Role"link
								break;
							}

						} catch (Exception ex) {
							logger.error(ex);
						}

						try {
							if (selenium
									.isVisible("//div[@class='error-msg-container active']")) {
								selenium.type("id=SsoId",
										name[2] + new Date().getTime());
								selenium.type("id=SsoEmail", "xin.zhou@fei.com");
								selenium.click("id=btnCreateUserAccount");
								selenium.waitForElementDisplay("_contentlink_Users_UserRoleSet_Create");
								selenium.click("id=_contentlink_Users_UserRoleSet_Create");
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
				selenium.sleepSeconds(3);
				selenium.click("id=cbAll");
				
				selenium.click("id=btnCreateUserRoleSet");
				selenium.waitForPageToLoad("30000");

				selenium.waitForElementClickable("id=_topnavlink_OrganizationUnits_Staff_List");

			}

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public void initIndividual() throws Exception {

		try {
			selenium.open(selenium.baseUrl
					+ "Systems/Deployment/DeploymentList/");
			// selenium.click("btnLogin");
			selenium.waitForElementDisplay("CacheRegionsToBeCleared_Lookups");
			selenium.check("CacheRegionsToBeCleared_Lookups");
			selenium.check("CacheRegionsToBeCleared_Security");
			selenium.check("CacheRegionsToBeCleared_AppResource");
			selenium.click("btnRefreshCache");
			selenium.waitForPageToLoad("30000");
			selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
			selenium.waitForPageToLoad("30000");

			int i = 1;
			while (i < 3) {
				selenium.click("id=_topnavlink_Clients_Client_ClientSearch");
				selenium.click("id=_contentlinkCreateClient");
				selenium.type("id=ClientProfile_PersonName_FirstName",
						"Individual" + i);
				selenium.type("id=ClientProfile_PersonName_LastName", "test");
				selenium.type("id=ClientProfile_DateOfBirth",
						TestUtils.getRandomDate());
				selenium.select("id=ClientProfile_Gender_Id", "label=Female");
				selenium.select("id=ClientProfile_Race_Id", "label=Asian");
				selenium.select("id=ClientJurisdiction_Id", "label=Alcorn");
				selenium.click("id=chkSelectSSN");
				selenium.click("id=btnCreateClient");
				selenium.sleepSeconds(3);
				int timeout = 0;
				while (timeout < 10) {
					try {
						if (selenium.isVisible("btnCreateAsNew")) {
							selenium.click("btnCreateAsNew");
							selenium.waitForElementDisplay("id=ClientMANumber_MedicaidNumber");
							break;
						} else if (selenium
								.isVisible("ClientMANumber_MedicaidNumber")) {
							break;
						}
						Thread.sleep(1000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					timeout++;
				}

				selenium.waitForPageToLoad("30000");
				selenium.select("id=ClientPhone_ClientPhoneType_Id",
						"label=Home");
				selenium.type("id=ClientPhone_Phone_Number", "111 444 7890");
				selenium.click("id=IsPrimaryPhone");
				selenium.click("id=btnCreatePhoneNumber");

				selenium.click("id=_leftnavlink_Clients_Client_Details");
				selenium.click("id=_contentlink_Clients_ClientMANumber_List");
				selenium.click("id=CreateLink");
				selenium.type("id=ClientMANumber_MedicaidNumber",
						TestUtils.getRandomNumber(9));
				selenium.select("id=ClientMANumber_EligibilityType_Id",
						"label=Community");
				selenium.select("id=ClientMANumber_CoverageGroup_Id",
						"label=002 SSI Retroactive");
				selenium.type("id=ClientMANumber_EffectiveDateRange_StartDate",
						TestUtils.getNowString());
				selenium.type("id=ClientMANumber_EffectiveDateRange_EndDate",
						TestUtils.getEndDayString());

				selenium.click("id=IsCurrentMANumber");
				selenium.click("xpath=(//div[@class='ui-dialog-buttonset']/button[@type='button'])[1]");

				selenium.waitForElementHidden("id=ClientMANumberDialog");
				selenium.click("id=_contentlink_Clients_Client_Details");

				i++;
			}

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public void testAddCaseNotes() throws Exception {
		HashMap<String, String> names = new HashMap<String, String>();
		names.put("hui", "individual1");
		names.put("xiaoming", "individual1");
		names.put("haili", "individual2");

		for (java.util.Map.Entry<String, String> name : names.entrySet()) {
			selenium.click("id=_topmenulink_Account");
			selenium.click("id=_topmenulink_Account_Authorize");
			selenium.type("id=IdentityName", "!" + name.getKey());
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnLogin");
			selenium.waitForPageToLoad("30000");
			selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=ClientSearchCriteria_ClientFirstName", name.getValue());
			selenium.click("id=searchProfiles");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.click("//div[@id='lftNav']/div/ul/li[2]/span");
			selenium.click("id=_Cases_CaseNote_List");
			selenium.select("id=programType", "label=TBI/SCI");
			selenium.type("id=caseNoteEntry",
					"add first case note by " + name.getKey());
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnAddCaseNote");
			selenium.sleepSeconds(2);
			selenium.waitForPageToLoad("30000");
			selenium.select("id=caseNoteCategory", "label=Monthly Review");
			selenium.select("id=programType", "label=IL");
			selenium.type("id=caseNoteEntry",
					"add 2nd case note by " + name.getKey());
			selenium.click("id=btnAddCaseNote");
			selenium.sleepSeconds(2);
			selenium.waitForPageToLoad("30000");
			selenium.select("id=caseNoteCategory", "label=Quarterly Review");
			selenium.select("id=programType", "label=ID/DD");
			selenium.type("id=caseNoteEntry", "add 3rd case note by " + name.getKey());
			selenium.click("id=btnAddCaseNote");
			selenium.sleepSeconds(1);
			selenium.click("xpath=(//input[@value='Close'])[2]");
			selenium.waitForElementHidden("xpath=(//input[@value='Close'])[2]");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
		}
	}

	@Test
	public void testCreateEditCaseNoteAccessControl() throws Exception {
		selenium.open(selenium.baseUrl);
		//selenium.click("btnLogin");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_topmenulink_Menu");
		selenium.click("id=_topmenulink_Menu_Tools");
		selenium.click("id=_topmenulink_Cases_CaseNoteAccessControlMatrix_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_leftnavlink_Cases_CaseNoteOrganizationUnitDefinition_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteOrganizationUnitDefinition_Create");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=searchOrganization");
		selenium.waitForPageToLoad("30000");

		selenium.type("OrganizationUnitSearchCriteria.OrganizationUnitName",
				"CaseNoteOrganization1");
		selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
		selenium.click("xpath=(//a[contains(text(),'Select')])[1]");
		selenium.type("id=Name", "Organization 1_create");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteOrganizationUnitDefinition_Create");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=searchOrganization");
		selenium.waitForPageToLoad("30000");
		selenium.type("OrganizationUnitSearchCriteria.OrganizationUnitName",
				"CaseNoteOrganization2");
		selenium.click("css=#searchWindow0 > div.js-workarea-panel > form[name=\"searchForm\"] > fieldset.fieldset-container-one > div.searchspace-footer-bar > div.float-left > #btnWindowSearch");
		selenium.click("xpath=(//a[contains(text(),'Select')])[1]");
		selenium.type("id=Name", "Organization 2_create");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteOrganizationUnitDefinition_Create");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=TypeId", "label=Organization unit attribute");
		selenium.select("id=valueForOUAttribute", "label=ICF/IID");
		selenium.type("id=Name", "ICF/IID_create");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteOrganizationUnitDefinition_Create");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=TypeId", "label=Organization unit attribute");
		selenium.select("id=valueForOUAttribute",
				"label=Case Management Agency");
		selenium.type("id=Name", "Case Management Agency_create");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteOrganizationUnitDefinition_Create");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=TypeId", "label=Organization unit attribute");
		selenium.select("id=valueForOUAttribute", "label=Nursing Facility");
		selenium.type("id=Name", "Nursing Facility_create");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");

		// Edit the "Definition Name" of each 5 items:
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=CaseNoteOrganizationUnitDefinition_Name",
				"Organization 1_edit");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=CaseNoteOrganizationUnitDefinition_Name",
				"Organization 2_edit");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=CaseNoteOrganizationUnitDefinition_Name",
				"ICF/IID_edit");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=CaseNoteOrganizationUnitDefinition_Name",
				"Case Management Agency_edit");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=CaseNoteOrganizationUnitDefinition_Name",
				"Nursing Facility_edit");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");

		// View the 5th item:
		selenium.click("xpath=(//a[contains(text(),'View')])[5]");
		selenium.waitForPageToLoad("30000");

		// Navigate to "Case Note Access Control" to create Matrix:
		selenium.click("id=_leftnavlink_Cases_CaseNoteAccessControlMatrix_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteAccessControlMatrix_Create");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=AuthorizedCaseNoteOrganizationUnitDefinitionId",
				"label=Organization 1_edit");
		selenium.check("//form[@id='create']/div/fieldset[2]/div/div/div/div[2]/input[@type='checkbox']");
		selenium.check("//form[@id='create']/div/fieldset[2]/div/div/div/div[3]/input[@type='checkbox']");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.sleepSeconds(3);
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");

		selenium.click("xpath=(//a[contains(text(),'View')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteAccessControlMatrix_List");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_contentlink_Cases_CaseNoteAccessControlMatrix_Create");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=AuthorizedCaseNoteOrganizationUnitDefinitionId",
				"label=Case Management Agency_edit");
		selenium.check("//form[@id='create']/div/fieldset[2]/div/div/div/div[2]/input[@type='checkbox']");
		selenium.check("//form[@id='create']/div/fieldset[2]/div/div/div/div[5]/input[@type='checkbox']");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_Cases_CaseNoteAccessControlMatrix_Matrix");
		
		selenium.stop();
	}

}
