package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No15_TestServiceDefinition {

	static Logger logger = null;
	static SeleniumWrapper selenium = null;

	public static void clean() {
		RavenDbAPI.deleteCollectionByName("lxservices");
	}

	public No15_TestServiceDefinition() {
		logger = TestUtils.getLogger(No15_TestServiceDefinition.class);
		selenium = new SeleniumWrapper();
	}

	public static void createServiceDefinition(SeleniumWrapper selenium,
			String excelname) throws Exception {

		// selenium.open(selenium.baseUrl + "LxServices/LxService/List/");
		// selenium.click("id=btnLogin");

		String[][] IDDDData = selenium.getExcelData(excelname, "IDDD");
		String[][] FrequencyData = selenium.getExcelData(excelname,
				"FrequencyData");

		for (int i = 1; i < IDDDData.length; i++) // create services
		{
			selenium.click("id=_contentlink_LxServices_LxService_Create");
			selenium.autoInputByData(IDDDData[0], IDDDData[i], 0);
			if (IDDDData[i][0].equals("Composite")) {
				selenium.click("id=lookupitems_lxservicebasicunittypes_-1");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-2");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-3");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-4");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-5");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-6");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-7");
				selenium.click("id=lookupitems_lxservicebasicunittypes_-8");
			}
			if (IDDDData[i][0].equals("User Defined")) {
				selenium.type("id=LxService_UserDefinedUnitQuantity", "1");
				selenium.select("id=LxService_UserDefinedUnitType_Id",
						"label=Month(s)");
			}

			selenium.type("id=LxService_Description", "description");
			selenium.click("id=btnSaveCreatedLxService");
			selenium.waitForPageToLoad("30000");

			for (int j = 1; j < FrequencyData.length; j++) // create frequency
															// data for each
															// services
			{
				if (IDDDData[i][0].equals(FrequencyData[j][0])) {
					selenium.click("id=_contentlink_LxServices_FrequencyData_Create");
					selenium.waitForPageToLoad("30000");
					selenium.click("id=FrequencyDataDto_IsDefault");
					selenium.click("id=FrequencyDataDto_IsDaysPerWeekChecked");
					selenium.click("id=FrequencyDataDto_IsHoursPerDayChecked");
					selenium.click("id=FrequencyDataDto_IsUnitsPerDayChecked");
					selenium.autoInputByData(FrequencyData[0],
							FrequencyData[j], 1);
					// selenium.type("id=FrequencyDataDto_UnitDefault", "0");
					// selenium.type("id=FrequencyDataDto_FrequencyDefault",
					// "0");
					selenium.type("id=FrequencyDataDto_Comments", "comments");
					selenium.click("id=btnSaveCreatedLxServiceFrequencyData");
				}
			}

			selenium.click("css=button[type='button']");
			selenium.click("id=lookupitems/lxservicetags/-2");
			selenium.click("id=btnSave");

			selenium.click("id=lookupitems/providertypes/-1");
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=lookupitems/lxserviceplaceofservicecodes/-1");
			selenium.click("id=btnSave");
			selenium.waitForPageToLoad("30000");

			/*
			 * selenium.click("css=button[type=\"button\"]");
			 * selenium.waitForPageToLoad("30000");
			 * selenium.click("id=lookupitems/lxservicetags/-2");
			 * selenium.click("id=btnSave");
			 */
			selenium.waitForPageToLoad("30000");
			selenium.click("//input[@value='Publish']");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_LxServices_LxService_List");
			selenium.waitForPageToLoad("30000");

		}

	}

	@Test
	public void testCreate_LxService() throws Exception {
		selenium.open(selenium.baseUrl + "LxServices/LxService/List/");
		selenium.click("id=btnLogin");
		// clean();
		createServiceDefinition(selenium, "ServiceDefinition.xls");

	}

	@Test
	public void testEdit_LxService() throws Exception {
		selenium.open(selenium.baseUrl + "LxServices/LxService/List/");
		selenium.click("id=btnLogin");
		selenium.type("id=Name", "Day");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.clickByFuzzyLinkText("Detail*");
		// selenium.click("id=_listlink_LxServices_LxService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_LxServices_LxService_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=LxService_MinimumRate", "5");
		selenium.type("LxService_Description", "description edit");
		selenium.click("id=btnSaveCreatedLxService");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_LxServices_LxService_LxServiceProviderTypesChooser");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=lookupitems/providertypes/-3");
		selenium.click("id=lookupitems/providertypes/-5");
		selenium.click("id=btnSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_LxServices_LxService_LxServicePlacesOfServiceChooser");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=lookupitems/lxserviceplaceofservicecodes/-3");
		selenium.click("id=lookupitems/lxserviceplaceofservicecodes/-5");
		selenium.click("id=btnSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_LxServices_FrequencyData_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_listlink_LxServices_FrequencyData_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=FrequencyDataDto_IsDefault");
		selenium.type("id=FrequencyDataDto_Comments", "commentsedit");
		selenium.click("id=btnSaveCreatedLxServiceFrequencyData");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=lookupitems/lxservicetags/-2");
		selenium.click("id=btnSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.workspace-header-bar > div.float-right > input[type=\"button\"]");
		selenium.click("css=div.row > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Name", "Day");
		selenium.click("css=button[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.clickByFuzzyLinkText("Clone*");
		selenium.type("cloneRate", "10");
		selenium.click("css=div.row > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
