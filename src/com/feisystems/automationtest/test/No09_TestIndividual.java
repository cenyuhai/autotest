package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bsh.This;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No09_TestIndividual {
	static Logger logger = null;
	static SeleniumWrapper selenium = null;
	String clientName = "mike";

	public No09_TestIndividual() {
		logger = TestUtils.getLogger(No09_TestIndividual.class);
		selenium = new SeleniumWrapper();
	}

	public void clean() {
		RavenDbAPI.deleteDocumentByAttriute("clients",
				"ClientProfile.PersonName.FirstName", clientName);
	}

	public static void createIndividual(SeleniumWrapper selenium, String name) {
		selenium.open(selenium.baseUrl + "Systems/Deployment/DeploymentList/");
		selenium.waitForElementDisplay("CacheRegionsToBeCleared_Lookups");
		selenium.check("CacheRegionsToBeCleared_Lookups");
		selenium.check("CacheRegionsToBeCleared_Security");
		selenium.check("CacheRegionsToBeCleared_AppResource");
		selenium.click("btnRefreshCache");
		selenium.waitForPageToLoad("30000");
		selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_topnavlink_Clients_Client_ClientSearch");
		selenium.click("id=_contentlinkCreateClient");
		selenium.type("id=ClientProfile_PersonName_FirstName", name);
		selenium.type("id=ClientProfile_PersonName_LastName", "test");
		selenium.type("id=ClientProfile_PersonName_MiddleName", "middle");
		selenium.type("id=ClientProfile_PreferredName", "prefer");
		selenium.type("id=ClientProfile_PersonName_Suffix", "suffix");
		selenium.type("id=ClientProfile_DateOfBirth", TestUtils.getRandomDate());
		selenium.select("id=ClientProfile_Gender_Id", "label=Female");
		selenium.click("id=HispanicLatinoYes");
		selenium.select("id=ClientProfile_Race_Id", "label=Asian");
		selenium.select("id=ClientJurisdiction_Id", "label=Alcorn");
		selenium.click("id=chkSelectSSN");
		selenium.select("id=ClientProfile_MaritalStatusType_Id",
				"Married and Living with Spouse");
		selenium.select("id=ClientProfile_PrimaryLanguage_Id", "English");
		selenium.click("id=btnCreateClient");
		selenium.sleepSeconds(3);
		int timeout = 0;
		while (timeout < 10) {
			try {
				if (selenium.isVisible("btnCreateAsNew")) {
					selenium.click("btnCreateAsNew");
					selenium.waitForElementDisplay("id=ClientPhone_ClientPhoneType_Id");
					break;
				} else if (selenium
						.isVisible("id=ClientPhone_ClientPhoneType_Id")) {
					break;
				}
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			timeout++;
		}

		selenium.waitForPageToLoad("30000");
		selenium.select("id=ClientPhone_ClientPhoneType_Id", "label=Mobile");
		selenium.type("id=ClientPhone_Phone_Number", "111 444 7890");
		selenium.click("id=IsPrimaryPhone");
		selenium.click("id=btnCreatePhoneNumber");
		selenium.type("id=ClientEmail_EmailAdress_Address",
				"test@feisystems.com");
		selenium.check("id=IsPrimaryEmail");
		selenium.click("id=btnCreateEmailAddress");

		selenium.click("id=IsCurrentAddress");// add address
		selenium.click("id=IsMailingAddress");
		selenium.click("id=IsPhysicalAddress");
		selenium.select("id=ClientAddress_ClientAddressType_Id",
				"label=Community");
		selenium.select("id=ClientAddress_ClientHomeType_Id",
				"label=Congregate");
		selenium.click("css=option[value=\"lookupitems/clienthometypes/-1\"]");
		selenium.select("id=ClientAddress_ClientHomeSetting_Id",
				"label=Assisted Living");
		selenium.select("id=ClientAddress_NumberOfPeopleInHomeSetting_Id",
				"label=1-3");
		selenium.select("id=ClientAddress_LivingStatus_Id", "label=Family");
		selenium.type("id=ClientAddress_Address_AddressLine1", "Jintaifudi");
		selenium.type("id=ClientAddress_Address_AddressLine2", "Shangdi");
		selenium.type("id=ClientAddress_Address_City", "Beijing");
		selenium.type("id=ClientAddress_Address_PostalCode", "12345");
		selenium.click("id=btnCreateAddress");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ClientContact_PersonName_FirstName", "guardian");// add
																			// representative
		selenium.type("id=ClientContact_PersonName_LastName", "person");
		selenium.type("id=ClientContact_PersonName_MiddleName", "m");
		selenium.type("id=ClientContact_PersonName_Suffix", "s");
		selenium.select("id=ClientContact_RelationshipType_Id", "label=Spouse");
		selenium.select("id=ClientContact_ContactPhone_ContactPhoneType_Id",
				"label=Home");
		selenium.type("id=ClientContact_ContactPhone_Phone_Number",
				"1234567890");
		selenium.click("id=IsSetAsCurrentGuardianOfPerson");
		selenium.click("id=btnCreateContact");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnCreateAdvancedDirectives");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_leftnavlink_Clients_Client_Details");
		selenium.click("id=_contentlink_Clients_ClientMANumber_List");
		selenium.click("id=CreateLink");
		selenium.type("id=ClientMANumber_MedicaidNumber",
				TestUtils.getRandomNumber(9));
		// selenium.select("id=ClientMANumber_EligibilityType_Id",
		// "label=Community");
		selenium.select("id=ClientMANumber_CoverageGroup_Id",
				"label=002 SSI Retroactive");
		selenium.type("id=ClientMANumber_EffectiveDateRange_StartDate",
				TestUtils.getNowString());
		selenium.type("id=ClientMANumber_EffectiveDateRange_EndDate",
				TestUtils.getEndDayString());

		selenium.click("id=IsCurrentMANumber");
		selenium.click("xpath=(//div[@class='ui-dialog-buttonset']/button[@type='button'])[1]");

		selenium.waitForElementHidden("id=ClientMANumberDialog");
		selenium.click("id=_contentlink_Clients_Client_Details"); // "Back to Profile"

		selenium.click("xpath=(//a[contains(text(),'Manage')])[3]"); // Add
																		// Strengths
		selenium.type("id=PlanOfServiceStrength_Strength",
				"Strength:This field is required to save.*");
		selenium.click("link=Add Strength");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[1]");
		selenium.type("id=PlanOfServiceStrength_Strength",
				"Strength:This field is required to save.*_EDIT");
		selenium.click("link=Save Changes");
		selenium.click("xpath=(//a[contains(text(),'Delete')])[1]");
		selenium.click("id=btnConfirmNo");

		selenium.click("id=_Clients_Client_Details");// "Back to Profile"

	}

	// Add Goals
	public static void createGoals(SeleniumWrapper selenium) throws Exception {

		try {
			final String excelName = "Individual.xls";
			selenium.click("id=_Clients_ClientGoal_Manage");
			String[][] GoalData = selenium.getExcelData(excelName, "Goals");

			for (int i = 1; i < GoalData.length; i++) // create goals
			{
				selenium.autoInputByData(GoalData[0], GoalData[i], 0);

				if (GoalData[i][0].equals("Other")) {
					selenium.type(
							"id=PlanOfServiceGoal_GoalCategoryOtherDescription",
							"Specify Other:This field is required to save.*");
				}
				selenium.click("link=Add Goal");
				selenium.waitForPageToLoad("30000");
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public static void addRepresentativeForIndividual(SeleniumWrapper selenium) {
		selenium.click("id=_contentlink_Clients_ClientContact_Create");
		selenium.type("id=ClientContact_PersonName_FirstName", "test");
		selenium.type("id=ClientContact_PersonName_LastName", "mike");
		selenium.type("id=ClientContact_PersonName_MiddleName", "middle");
		selenium.type("id=ClientContact_PersonName_Suffix", "suffix");
		selenium.select("id=ClientContact_RelationshipType_Id", "Spouse");
		selenium.select("id=ClientContact_ContactPhone_ContactPhoneType_Id",
				"Mobile");
		selenium.type("id=ClientContact_ContactPhone_Phone_Number",
				TestUtils.getRandomNumber(10));
		selenium.runScript("$('input[type=\"checkbox\"]').attr('checked',true)");
		selenium.click("id=btnSaveCreatedClientContact");

	}

	/*
	 * public void addToWaiveRegistry() {
	 * selenium.click("id=_leftnavlink_WaiverRegistry_ClientRegistry_HistoryList"
	 * );
	 * selenium.click("id=_contentlink_WaiverRegistry_ClientRegistry_Create");
	 * selenium.waitForElementDisplay("ProgramTypeId");
	 * selenium.select("ProgramTypeId", "label=ID/DD"); selenium.click("btnOk");
	 * selenium.sleepSeconds(3);
	 * 
	 * }
	 */

	@Test
	public void testCreateIndividual() throws Exception {
		try {
			clean();
			selenium.open(selenium.baseUrl);
			selenium.click("btnLogin");
			// clean();
			createIndividual(selenium, clientName);
			// addToWaiveRegistry();

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void testEditIndividual() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
			selenium.click("id=searchProfiles");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_Client_EditProfile");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=Client_ClientProfile_Race_Id", "label=Asian");
			selenium.select("id=Client_ClientMovingToJurisdiction_Id",
					"label=Alcorn");
			selenium.select("id=Client_ClientProfile_MaritalStatusType_Id",
					"label=Married and Living with Spouse");
			selenium.select("id=Client_ClientProfile_PrimaryLanguage_Id",
					"label=Chinese");
			selenium.click("id=btnEditClientProfile");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientMANumber_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=CreateLink");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementPresentById("ClientMANumber_MedicaidNumber");
			selenium.type("id=ClientMANumber_MedicaidNumber",
					TestUtils.getRandomNumber(9));
			// selenium.select("id=ClientMANumber_EligibilityType_Id","label=Community");
			// selenium.click("css=option[value=\"lookupitems/eligibilitytypes/-1\"]");
			selenium.select("id=ClientMANumber_CoverageGroup_Id",
					"label=001 SSI via SDX");
			selenium.click("css=option[value=\"lookupitems/coveragegroups/-1\"]");
			selenium.type("id=ClientMANumber_EffectiveDateRange_StartDate",
					TestUtils.getNowString());
			selenium.type("id=ClientMANumber_EffectiveDateRange_EndDate",
					TestUtils.getEndDayString());
			selenium.click("id=IsCurrentMANumber");
			selenium.type("id=ClientMANumber_Comment", "test");
			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.waitForElementHidden("ClientMANumberDialog");
			selenium.click("//table[@id='ClientMANumbersGrid']/tbody/tr[1]/td[6]/a[1]");//
			selenium.waitElementPresentById("ClientMANumber_CoverageGroup_Id");
			selenium.select("id=ClientMANumber_CoverageGroup_Id",
					"label=010 Nursing Home, < 300%");
			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.waitForElementHidden("ClientMANumberDialog");
			selenium.refresh();
			selenium.runScript("$('#ClientMANumbersGrid tbody tr:eq(0) td a:eq(1)').click()");
			selenium.waitForElementDisplay("id=btnYes");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_Client_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientPhone_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=CreateLink");
			selenium.waitForElementDisplay("ClientPhoneDialog");
			selenium.select("id=ClientPhone_ClientPhoneType_Id", "label=Home");
			selenium.type("id=ClientPhone_Phone_Number",
					TestUtils.getRandomNumber(10));
			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.waitForElementHidden("ClientPhoneDialog");
			selenium.click("//table[@id='ClientPhonesGrid']/tbody/tr[1]/td[5]/a[1]");
			selenium.waitForElementDisplay("ClientPhoneDialog");
			selenium.type("id=ClientPhone_ClientPhoneDescription",
					"phone number test");
			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.waitForElementHidden("ClientPhoneDialog");
			selenium.refresh();
			selenium.runScript("$('#ClientPhonesGrid tbody tr:eq(0) a:eq(1)').click()");
			selenium.waitForElementDisplay("id=btnYes");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_Client_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientEmail_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=CreateLink");
			selenium.waitForElementDisplay("ClientEmailDialog");
			selenium.type("id=ClientEmail_EmailAdress_Address",
					"huihui.wang@feisystems.com");
			selenium.type("id=ClientEmail_EmailNote", "email testing");
			selenium.click("id=IsPrimaryEmail");
			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.waitForElementHidden("ClientEmailDialog");
			selenium.click("id=_contentlink_Clients_Client_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientAddress_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientAddress_Create");
			selenium.waitForPageToLoad("30000");
			selenium.selectByIdAndIndex("ClientAddress_ClientAddressType_Id", 1);
			selenium.select("id=ClientAddress_ClientHomeType_Id",
					"label=Congregate");
			selenium.select("id=ClientAddress_ClientHomeSetting_Id",
					"label=Supervised Living");

			selenium.type("id=ClientAddress_Address_AddressLine1", "xier qi");
			selenium.type("id=ClientAddress_Address_City", "beijing");
			selenium.click("id=IsCurrentAddress");
			selenium.click("id=IsMailingAddress");
			selenium.click("id=IsPhysicalAddress");
			selenium.type("id=ClientAddress_Address_PostalCode", "10008");
			selenium.click("id=btnSaveClientAddress");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_Client_Details");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_ClientContact_Create");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=ClientContact_PersonName_FirstName", "dingding");
			selenium.type("id=ClientContact_PersonName_LastName", "wang");
			selenium.select("id=ClientContact_RelationshipType_Id",
					"label=Other Relative");
			selenium.type("id=ClientContact_OtherRelationshipSpecify",
					"other relative");

			// new field
			selenium.selectByIdAndIndex(
					"ClientContact_ContactPhone_ContactPhoneType_Id", 1);
			selenium.type("id=ClientContact_ContactPhone_Phone_Number",
					TestUtils.getRandomNumber(10));

			selenium.click("id=btnSaveCreatedClientContact");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_Clients_Client_EditAdvancedDirectives");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=LivingWillYes");
			selenium.click("id=MedicalPoaDurableYes");
			selenium.click("id=btnEditAdvancedDirectives");
			selenium.waitForPageToLoad("30000");
			// selenium.click("id=_leftnavlink_WaiverRegistry_ClientRegistry_HistoryList");

			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

}
