package com.feisystems.automationtest.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;


public class TestPSSDataDriven {
	static Logger logger = TestUtils.getLogger(TestPSSDataDriven.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	public void clean() {
		RavenDbAPI.deleteCollectionByName("planofservices");
		RavenDbAPI.deleteCollectionByName("serviceplans");
		RavenDbAPI.deleteCollectionByName("lxservices");
	}

	public void createFormMetaData(SeleniumWrapper selenium) {
		selenium.open(selenium.baseUrl + "Systems/FormMetadataDefinition/List/");

		selenium.click("id=_Systems_FormMetadataDefinition_Create");
		selenium.select("id=FormMetadataDefinition_FormDescriptor_Guid","Plan of Service");
		selenium.select("id=FormMetadataDefinition_FormType", "PSS");
		selenium.clickElementsByName("FormMetadataDefinitionPrograms.Value");
		selenium.click("id=btnSaveCreatedFormMetadataDefinition");
		selenium.click("id=_contentlink_Systems_FormMetadataDefinition_Publish");

	}
	
	
	public static void createServiceDefinition(SeleniumWrapper selenium)  {
		try {
			String[][] alData = selenium.getExcelData("TestPSSDataDriven.xls", "AL");
			String[][] frequencyData = selenium.getExcelData("TestPSSDataDriven.xls", "FrequencyData");
			
			for (int i = 1; i < alData.length; i++) {
				selenium.click("id=_contentlink_LxServices_LxService_Create");
				selenium.waitForPageToLoad("30000");
				//auto input data from excel
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
				
				for(int j=0;j<frequencyData.length;j++) {
					if(frequencyData[j][0].equals(alData[i][0])) {
						selenium.click("id=_contentlink_LxServices_FrequencyData_Create");
						selenium.autoInputByData(frequencyData[0], frequencyData[j], 1);
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
	
	@Test
	public void testStandardPSS() throws Exception {
		selenium.open(selenium.baseUrl);
		//selenium.type("id=IdentityName", "!bella1");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnLogin");
		clean();
		//createFormMetaData(selenium);
		
		selenium.open(selenium.baseUrl + "LxServices/LxService/List/");		 
		createServiceDefinition(selenium);

		selenium.waitForPageToLoad("30000");
		selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		selenium.runScript("$('span:contains(\"PSS\")').click()");
		selenium.click("id=_leftnavlink_Pss_PlanOfService_List");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_Pss_PlanOfService_Create");
		selenium.select("id=programTypeForCreateSelect", "label=AL");
		selenium.click("css=div.fancybox-dialog-controls > input[type=\"button\"]");
		selenium.waitForPageToLoad("30000");

		selenium.type("id=unique1", "Comments:This field is required to submit.**\na. Comments:");
		selenium.click("id=btnAddPreference");
	    selenium.type("id=unique3", "Comments:This field is required to submit.**\na. Comments: \nAdd 2nd comments");
		selenium.type("id=unique2", "add 1st comments");
		selenium.click("id=btnAddNeed");
		selenium.type("id=unique4", "add 2ndcomments");
		selenium.type("id=EffectiveDateRange_StartDate",TestUtils.getNowString());
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

		selenium.click("id=_Pss_PlanOfServiceServicePlan_ManageServicePlan");
	
		//service manage	
		String[][] serviceData = selenium.getExcelData("TestPSSDataDriven.xls", "services");
		for (int i = 1; i < serviceData.length; i++) {
			selenium.click("id=Service_ServiceId");
			selenium.autoInputByData(serviceData[0], serviceData[i]);
			if(selenium.isElementPresent("id=xxxopenProviderSearchButton")) {
				selenium.click("id=xxxopenProviderSearchButton");
				selenium.click("id=btnSearchProvider");
				selenium.click("link=Select");
			}
			selenium.type("id=ServiceDetailsReason", "Explain:This field is required to submit.**");
			selenium.click("link=Add Service");
		}

		
		selenium.click("css=button[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
	    selenium.click("id=_Pss_PlanOfService_Details");


	}

}
