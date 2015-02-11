package com.feisystems.automationtest.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestStandardPSS {
	static Logger logger = TestUtils.getLogger(TestStandardPSS.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	public void clean() {
		RavenDbAPI.deleteCollectionByName("planofservices");
		RavenDbAPI.deleteCollectionByName("serviceplans");
		//RavenDbAPI.deleteCollectionByName("costneutralitythresholds");
		// RavenDbAPI.deleteCollectionByName("lxservices");
		//RavenDbAPI.deleteCollectionByName("clients");
		// RavenDbAPI.deleteCollectionByName("mississippistandardizedassessments");
		// RavenDbAPI.deleteCollectionByName("providerprofiles");

	}

	public void createFormMetaData(SeleniumWrapper selenium) {
		selenium.open(selenium.baseUrl + "Systems/FormMetadataDefinition/List/");

		selenium.click("id=_Systems_FormMetadataDefinition_Create");
		selenium.select("id=FormMetadataDefinition_FormDescriptor_Guid",
				"Plan of Service");
		selenium.select("id=FormMetadataDefinition_FormType", "PSS");
		selenium.clickElementsByName("FormMetadataDefinitionPrograms.Value");
		selenium.click("id=btnSaveCreatedFormMetadataDefinition");
		selenium.click("id=_contentlink_Systems_FormMetadataDefinition_Publish");

	}

	public void createCostNeutrality() {
		try {
			String[][] costNeutrality = selenium.getExcelData(excelName,
					"CostNeutrality");
			selenium.click("id=_topmenulink_Menu");
			selenium.click("id=_topmenulink_Menu_Tools");
			selenium.click("id=_topmenulink_Systems_CostNeutralityThreshold_List");
			for (int i = 1; i < costNeutrality.length; i++) {
				selenium.click("id=_contentlink_Systems_CostNeutralityThreshold_Create");
				selenium.autoInputByData(costNeutrality[0], costNeutrality[i]);
				selenium.type("id=StartDateTime", TestUtils.getNowString());
				// selenium.type("id=EndDateTime", TestUtils.getEndDayString());
				selenium.click("//button[@type=\"submit\"]");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			logger.equals(ex);
		}

	}

	public static void createServiceDefinition(SeleniumWrapper selenium) {
		try {
			String[][] alData = selenium.getExcelData(excelName, "AL");
			String[][] frequencyData = selenium.getExcelData(excelName,
					"FrequencyData");

			for (int i = 1; i < alData.length; i++) {
				selenium.click("id=_contentlink_LxServices_LxService_Create");
				selenium.waitForPageToLoad("30000");
				// auto input data from excel
				selenium.autoInputByData(alData[0], alData[i]);

				selenium.type("id=LxService_AgeRangeStart", "10");
				selenium.type("id=LxService_AgeRangeEnd", "50");
				selenium.type("id=LxService_Description", "description");
				selenium.click("id=btnSaveCreatedLxService");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=lookupitems/providertypes/-1");
				selenium.click("id=lookupitems/providertypes/-2");
				selenium.click("id=btnSave");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=lookupitems/lxserviceplaceofservicecodes/-1");
				selenium.click("id=lookupitems/lxserviceplaceofservicecodes/-2");
				selenium.click("id=btnSave");
				selenium.waitForPageToLoad("30000");

				for (int j = 0; j < frequencyData.length; j++) {
					if (frequencyData[j][0].equals(alData[i][0])) {
						selenium.click("id=_contentlink_LxServices_FrequencyData_Create");
						selenium.autoInputByData(frequencyData[0],
								frequencyData[j], 1);
						selenium.click("id=btnSaveCreatedLxServiceFrequencyData");
						selenium.waitForPageToLoad("30000");

					}
				}

				selenium.click("css=button[type=\"button\"]");
				selenium.waitForPageToLoad("30000");
				selenium.check("id=lookupitems/lxservicetags/-2");
				selenium.click("id=btnSave");
				selenium.waitForPageToLoad("30000");
				selenium.click("//input[@value='Publish']");
				selenium.click("id=btnYes");
				selenium.waitForPageToLoad("30000");
				selenium.click("id=_contentlink_LxServices_LxService_List");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	final static String excelName = "TestStandardPSS.xls";

	@Test
	public void testStandardPSS() throws Exception {
		selenium.open(selenium.baseUrl);
		// selenium.type("id=IdentityName", "!bella1");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnLogin");
		clean();
		// No02_TestProvider.createProvider(selenium, "pss provider 1");

		//No09_TestIndividual.createIndividual(selenium);
		//No09_TestIndividual.createClientAddress(selenium);
		//createCostNeutrality();
		// createFormMetaData(selenium);
		// selenium.open(selenium.baseUrl + "LxServices/LxService/List/");
		// createServiceDefinition(selenium);
		//TestInterRAI.createInterRAI(selenium);

		selenium.waitForPageToLoad("30000");
		selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		//selenium.runScript("$('span:contains(\"PSS\")').click()");
		selenium.click("id=alwaivermanagement");
		selenium.click("id=_leftnavlink_Pss_PlanOfService_List");
		selenium.waitForPageToLoad("30000");

		String[][] programTypes = selenium.getExcelData(excelName,
				"ProgramType");
		for (int i = 1; i < programTypes.length; i++) {
			selenium.click("id=_Pss_PlanOfService_Create");
			selenium.autoInputByData(programTypes[0], programTypes[i]);
			// selenium.select("id=programTypeForCreateSelect", "label=" +
			// programTypes[i][0]);
			selenium.click("css=div.fancybox-dialog-controls > input[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.type("id=unique1",
					"Comments:This field is required to submit.**\na. Comments:");
			selenium.click("id=btnAddPreference");
			selenium.type("id=unique3",
					"Comments:This field is required to submit.**\na. Comments: \nAdd 2nd comments");
			selenium.type("id=unique2", "add 1st comments");
			selenium.click("id=btnAddNeed");
			selenium.type("id=unique4", "add 2ndcomments");
			selenium.type("id=EffectiveDateRangeStart",
					TestUtils.getNowString());
			selenium.click("id=Centeredness.GivenChoiceOfLivingSetting.Id_pssyesnooptions/-1");
			selenium.click("id=Centeredness.GivenOpportunityToSeekWork.Id_pssyesnooptions/-3");
			selenium.click("id=Centeredness.GivenOpportunityToParticipateInCommunityActivities.Id_pssyesnooptions/-2");
			selenium.runScript("$('#Centeredness_NoCommunityActivitiesReasons_0__Selected').click()");
			selenium.check("id=Centeredness_NoCommunityActivitiesReasons_0__Selected");
			selenium.runScript("$('#Centeredness_NoCommunityActivitiesReasons_0__Explain').val('Service Goals: copy all services goals from the PSS that is being revised. ')");
			selenium.check("id=Centeredness_NoCommunityActivitiesReasons_1__Selected");
			selenium.runScript("$('#Centeredness_NoCommunityActivitiesReasons_1__Explain').val('Service Goals: copy all services goals from the PSS that is being revised. 	')");
			selenium.click("id=Centeredness.HaveControlOfYourPersonalResources.Id_pssyesnooptions/-2");
			selenium.runScript("$('#Centeredness_HaveControlOfYourPersonalResourcesExplain').val('Service Goals: copy all services goals from the PSS that is being revised.  ')");
			selenium.click("id=btnClientOverviewCreate");
			selenium.waitForPageToLoad("30000");

			selenium.click("id=_Pss_PlanOfServiceStrength_Manage");
			selenium.waitForPageToLoad("30000");

			selenium.type("id=PlanOfServiceStrength_Strength",
					"Strength:This field is required to save.*");
			selenium.click("link=Add Strength");
			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.select("id=PlanOfServiceGoal_GoalCategory_Id",
					"label=Other");
			selenium.type("id=PlanOfServiceGoal_GoalCategoryOtherDescription",
					"Explain:This field is required to save.*");
			selenium.type("id=PlanOfServiceGoal_DesiredGoalDescription",
					"Explain:This field is required to save.*");
			selenium.type("id=PlanOfServiceGoal_StepsActions",
					"Explain:This field is required to save.*");
			selenium.select("id=PlanOfServiceGoal_AnnualProgress_Id",
					"label=Not Begun");
			selenium.click("link=Add Personal Goal");
			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.type("id=Risk_Description",
					"RiskThis field is required to save.*");
			selenium.type("id=Risk_MeasuresToMinimizeRisk",
					"Measures to Minimize Risk:This field is required to submit.**");
			selenium.click("link=Add Risk");
			selenium.click("Risk_Description");
			selenium.type(
					"id=Risk_Description",
					"When submitting a PSS in a status of In Progress that was revised from a previously Approved PSS, that PSS can automatically be set to a status of Approved upon submission if it meets the following conditions");
			selenium.type(
					"id=Risk_MeasuresToMinimizeRisk",
					"When submitting a PSS in a status of In Progress that was revised from a previously Approved PSS, that PSS can automatically be set to a status of Approved upon submission if it meets the following conditions");
			selenium.click("link=Add Risk");
			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			if (programTypes[i][0].equals("IL")
					|| programTypes[i][0].equals("TBI/SCI")) {
				selenium.click("id=btnNextSection");
			}

			selenium.click("id=EmergencyBackupPlan_IsPrimary_Yes");
			selenium.click("id=EBP_Enrolled_Yes");
			selenium.click("id=openProviderSearchButton");
			selenium.click("id=btnSearchProvider");
			selenium.click("xpath=(//a[contains(text(),'Select')])[1]");
			selenium.type("id=EBP_Availability",
					"Availability:This field is required to save.*");
			selenium.select("id=unique1", "label=Home");
			selenium.type("id=unique2", "12345678901");
			selenium.type("id=unique3", "888");
			selenium.click("id=btnAddPhone");
			selenium.select("id=unique4", "label=Work");
			selenium.type("id=unique5", "2223334445");
			selenium.type("id=unique6", "777");
			selenium.click("link=Add Back-up Provider");
			selenium.click("id=EBP_Enrolled_No");
			selenium.type("id=EBP_Name", "Jessica Wang");
			selenium.click("id=RelationshipType_option_0");
			selenium.click("id=RelationshipType_option_1");
			selenium.click("id=RelationshipType_option_2");
			selenium.click("id=RelationshipType_option_3");
			selenium.click("id=RelationshipType_option_4");
			selenium.click("id=RelationshipType_option_5");
			selenium.type("id=EBP_AddressLine1",
					"xi erqi street haidian distric");
			selenium.type("id=EBP_AddressLine2",
					"xi erqi street haidian distric");
			selenium.type("id=EBP_City", "Beijing");
			selenium.select("id=EBP_State", "label=Alaska");
			selenium.type("id=EBP_ZipCode", "10085");
			selenium.type("id=EBP_Availability",
					"Availability:This field is required to save.*");
			selenium.select("id=unique7", "label=Toll Free Phone");
			selenium.type("id=unique8", "8888889990");
			selenium.type("id=unique9", "444");
			selenium.click("id=btnAddPhone");
			selenium.select("id=unique10", "label=Other");
			selenium.type("id=unique11", "3333444456");
			selenium.type("id=unique12", "000");
			selenium.click("link=Add Back-up Provider");
			selenium.click("css=button[type=\"button\"]");

			// service manage
			String[][] serviceData = selenium.getExcelData(excelName,
					"services");
			for (int j = 1; j < serviceData.length; j++) {
				if (programTypes[i][0].equals(serviceData[j][0])) {
					selenium.click("id=Service_ServiceId");
					selenium.autoInputByData(serviceData[0], serviceData[j]);
					if (selenium
							.isElementPresent("id=xxxopenProviderSearchButton")) {
						selenium.click("id=xxxopenProviderSearchButton");
						selenium.click("id=btnSearchProvider");
						selenium.click("link=Select");
					}
					selenium.type("id=ServiceDetailsReason",
							"Explain:This field is required to submit.**");
					selenium.click("link=Add Service");
				}
			}

			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.click("xpath=(//button[@type='button'])[2]");
			selenium.click("//body[@id='body']/div[5]/div/ul/li/a/span[2]");
			selenium.type("id=ServiceGoal_Goal",
					"Service Goal:This field is required to submit.**");
			selenium.type("id=ServiceGoal_DesiredOutcome",
					"Desired Outcome:This field is required to submit.**");
			selenium.click("id=addServiceGoalLink");
			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.click("link=Sign");
			selenium.type("id=SignatureToSign_Signature_SignedName",
					"system administrator1");
			selenium.type("id=SignatureToSign_Signature_SignedDate",
					TestUtils.getNowString());
			selenium.click("id=EditSignatureBtn");
			selenium.waitForPageToLoad("30000");

			selenium.click("link=Sign");
			selenium.type("id=SignatureToSign_Signature_SignedName",
					"system administrator2");
			selenium.type("id=SignatureToSign_Signature_SignedDate",
					TestUtils.getNowString());
			selenium.click("id=EditSignatureBtn");
			selenium.waitForPageToLoad("30000");

			selenium.click("css=button[type=\"button\"]");
			selenium.waitForPageToLoad("30000");

			selenium.click("id=Review_MeetParticipantsHealthAndSafetyNeeds_true");
			selenium.type(
					"id=Review_AdditionalReviewInformation",
					"Does the PSS meet the person health and safety needs?\nThis field is required to submit.**");
			selenium.click("id=reviewSave");
			selenium.waitForPageToLoad("30000");

			selenium.click("css=div.float-right > form > input[type=\"submit\"]");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");

			selenium.click("css=div.float-right > form > input[type=\"submit\"]");
			selenium.click("id=reason");
			selenium.type("id=reason", "request clarification for this PSS");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");

			selenium.click("//div[@id='workspace-maincontent']/div/div[12]/div/h4");
			selenium.click("//div[@id='workspace-maincontent']/div/div[11]/div/h4");

			selenium.click("//div[@id='workspace-maincontent']/div/div[12]/div/h4");
			selenium.click("//div[@id='workspace-maincontent']/div/div[11]/div/h4");
			selenium.click("id=_Pss_PlanOfService_List");
			selenium.waitForPageToLoad("30000");

			selenium.runScript("window.location = $('#PlanOfServiceGrid tbody tr:eq(" + (i-1) +") a:contains(\"View\")').attr(\"href\")");
			
			selenium.waitForPageToLoad("30000");

			selenium.click("css=div.float-right > form > input[type=\"submit\"]");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");

			selenium.click("//div[@id='workspace-maincontent']/div/div[12]/div/h4");

			selenium.click("//div[@id='workspace-maincontent']/div/div[12]/div/h4");
			selenium.click("//div[@id='workspace-maincontent']/div/div[11]/div/h4");
			selenium.click("//input[@value='Approve']");
			selenium.click("id=comment");
			selenium.type("id=comment", "APPROVE THIS PSS");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");

			selenium.click("//div[@id='workspace-maincontent']/div/div[11]/div/h4");
			selenium.click("id=_Pss_PlanOfService_List");
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
