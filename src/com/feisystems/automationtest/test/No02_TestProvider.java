package com.feisystems.automationtest.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class No02_TestProvider {
	static Logger logger = null;
	static SeleniumWrapper selenium  = null;

	public No02_TestProvider() {
		logger = TestUtils.getLogger(No02_TestProvider.class);
		selenium  = new SeleniumWrapper();
	}
	
	public static void clean() {
		RavenDbAPI.deleteCollectionByName("providerprofiles");
	}
	
	public static void createProvider(SeleniumWrapper selenium, String providerName) {
		
		selenium.open(selenium.baseUrl + "OrganizationUnits/ProviderProfile/List/");

		selenium.click("_contentlink_OrganizationUnits_ProviderProfile_Create");
		selenium.type("ProviderProfile_ProviderNumber", "12345678");
		selenium.type("ProviderProfile_ProviderName", providerName);
		selenium.type("ProviderProfile_TaxIdentifier_Value", "123456789");
		selenium.type(
				"ProviderProfile_FederalEmployerIdentificationNumber_Value",
				"1234567890");
		selenium.type(
				"ProviderProfile_NationalProviderIdentification_Value",
				"1234567890");
		selenium.type("ProviderProfile_MedicareNumber", "123456789");
		selenium.type("ProviderProfile_ContactInfo_ContactName", "pro1");
		selenium.type("ProviderProfile_ContactInfo_Email",
				"provider1@fei.com");
		selenium.type("ProviderProfile_ServiceAddress_AddressLine1",
				"ShangDi Dong Road");
		selenium.type("ProviderProfile_ServiceAddress_AddressLine2",
				"Haidian");
		selenium.type("ProviderProfile_ServiceAddress_City", "Beijing");
		selenium.type("ProviderProfile_ServiceAddress_PostalCode", "10085");
		selenium.type("ServicePhone", "1082743909");
		selenium.type("ServicePhone_ext", "010");
		selenium.type("ProviderProfile_PayToAddress_AddressLine1",
				"ShangDi Dong Road");
		selenium.type("ProviderProfile_PayToAddress_AddressLine2",
				"Haidian");
		selenium.type("ProviderProfile_PayToAddress_City", "Beijing");
		selenium.type("ProviderProfile_PayToAddress_PostalCode", "10085");
		selenium.type("PayToPhone", "1082743909");
		selenium.type("PayToPhone_ext", "010");
		selenium.type("ProviderProfile_CorrespondenceAddress_AddressLine1",
				"ShangDi Dong Road");
		selenium.type("ProviderProfile_CorrespondenceAddress_AddressLine2",
				"Haidian");
		selenium.type("ProviderProfile_CorrespondenceAddress_City",
				"Beijing");
		selenium.type("ProviderProfile_CorrespondenceAddress_PostalCode",
				"10085");
		selenium.click("btnCreateProviderProfile");

		
	}
	
	@Test
	public void testProviderCreate() throws Exception {
		try {
			selenium.open(selenium.baseUrl);
			selenium.click("id=btnLogin");
			clean();
			selenium.waitForPageToLoad("30000");
			createProvider(selenium, "provider1");
			
			selenium.click("_contentlink_OrganizationUnits_ServiceProviderType_Create");
			selenium.select("ServiceProviderType_ProviderType_Id", "G00 ICF-Nonprofit Unclassified");

			selenium.type("ServiceProviderType_EffectiveRange_StartDate",
					TestUtils.getNowString());
			selenium.type("ServiceProviderType_EffectiveRange_EndDate",
					TestUtils.getEndDayString());
			selenium.click("btnCreateServiceProviderType");
			selenium.stop();
			
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	@Test
	public void testProviderEdit() throws Exception {
		selenium.open(selenium.baseUrl + "OrganizationUnits/ProviderProfile/List/");
		selenium.click("id=btnLogin");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ProviderProfileSearchCriteria_ProviderName", "provider1");
		selenium.click("id=btnSearchProviderProfile");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='OrganizationUnitSummaryGrid']/tbody/tr[1]/td[8]/a");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_OrganizationUnits_ProviderProfile_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ProviderProfile_ContactInfo_ContactName", "proedit");
		selenium.click("id=btnEditProviderProfile");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("_contentlink_OrganizationUnits_ServiceProviderType_Create");
	    selenium.select("ServiceProviderType_ProviderType_Id", "G01 ICF-Nonprofit General");
		
	    SimpleDateFormat  dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(new Date()); 
	    calendar.add(Calendar.YEAR,1);

	    		
	    selenium.type("ServiceProviderType_EffectiveRange_StartDate",  TestUtils.getNowString());
	    selenium.type("ServiceProviderType_EffectiveRange_EndDate", TestUtils.getEndDayString());
	    selenium.click("btnCreateServiceProviderType");
	    
	    selenium.waitForPageToLoad("30000");
		
		selenium.sleepSeconds(2);
		selenium.click("//a[@title='Remove Service Provider Type']");
		selenium.waitForPageToLoad("30000");
		selenium.sleepSeconds(1);
		selenium.click("id=btnYes");
		
		selenium.sleepSeconds(2);
		selenium.click("//tbody/tr[1]/td[5]/a[@title='Edit Service Provider Type']");
		selenium.waitForPageToLoad("30000");
		
	    calendar.set(GregorianCalendar.DAY_OF_MONTH, 28);
		selenium.type("ServiceProviderType_EffectiveRange_EndDate",dateFormat.format(calendar.getTime()));//date input
		selenium.click("id=btnCreateServiceProviderType");
		
		selenium.waitForPageToLoad("30000");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=expandCollapseAll");
		selenium.click("id=expandCollapseAll");
		selenium.click("id=btnEnableOrDisableProviderProfile");
		selenium.click("id=btnNo");
		selenium.click("id=btnEnableOrDisableProviderProfile");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnEnableOrDisableProviderProfile");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_OrganizationUnits_ProviderProfile_List");
		selenium.waitForPageToLoad("30000");
		
		selenium.stop();
	}

}
