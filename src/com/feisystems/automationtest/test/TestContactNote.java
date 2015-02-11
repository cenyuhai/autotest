package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestContactNote {
	Logger logger = TestUtils.getLogger(this.getClass());
	private SeleniumWrapper selenium = new SeleniumWrapper();
	

	@Test
	public void testContactNote() throws Exception {
		selenium.open(selenium.baseUrl + "ContactNotes/ContactNote/List/");
		selenium.click("btnLogin");
		selenium.click("id=_ContactNotes_ContactNote_Create");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ContactReasonIdList_lookupitems/contactnotecontactreasons/-1");
		selenium.click("id=ContactReasonIdList_lookupitems/contactnotecontactreasons/-5");
		selenium.click("id=ContactReasonIdList_lookupitems/contactnotecontactreasons/-2");
		selenium.click("id=ContactNote_IsAnonymous");
		selenium.type("id=ContactNote_DateOfBirth", TestUtils.getNowString());
		selenium.type("id=phone_0", "1234567890");
		selenium.type("id=phone_0_ext", "123");
		selenium.type("id=ContactNote_PersonFaxNumber", "1234567890");
		selenium.type("id=ContactNote_PersonEmailAddress", "xin.zhou@fei.com");
		selenium.select("id=ContactNote_PersonLocation_Id", "label=Nursing Facility");
		selenium.type("id=ContactNote_PersonAddress_AddressLine1", "ShangDi");
		selenium.type("id=ContactNote_PersonAddress_AddressLine2", "Donglu");
		selenium.type("id=ContactNote_PersonAddress_City", "Beijing");
		selenium.type("id=ContactNote_PersonAddress_PostalCode", "12345");
		selenium.select("id=ContactNote_ContactInitiatorType_Id", "label=Family/Friend");
		selenium.click("id=ContactNote_ContactInitiatorIsAnonymous");
		selenium.type("name=ContactNote.ContactInitiatorPhoneNumber.Number", "9876543210");
		selenium.type("id=ContactNote_ContactInitiatorFaxNumber", "9876543210");		
		selenium.type("id=ContactNote_ContactInitiatorEmailAddress", "aa@aa.com");
		selenium.type("id=ContactNote_ContactTime", "10:00 AM");
		selenium.type("id=ContactNote_Duration", "10");		
		selenium.type("id=ContactNote_Comments", "contact note");
		selenium.type("id=ContactNote_InitialFollowUpDate", TestUtils.getNowString());
		selenium.click("css=button[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.workspace-header-bar > div.float-right > input[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnAddFollowUp");
		selenium.click("css=input[type=\"checkbox\"]");		
		selenium.type("id=unique10", TestUtils.getNowString());
		selenium.type("id=unique11", "2:00 PM");
		selenium.type("id=unique12", "20");		
		selenium.type("id=unique13", "follow up 1");
		selenium.type("id=unique14", TestUtils.getNowString());
		selenium.click("css=button[type=\"button\"]");
		selenium.click("css=legend.legend-header-two.collapsed");
		selenium.click("css=div.workspace-header-bar > div.float-right > input[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=legend.legend-header-one.collapsed");
		selenium.click("css=legend.legend-header-one.expanded");
		selenium.click("id=btnAddFollowUp");
		selenium.type("id=unique19", "9998887777");
		selenium.type("id=unique20", "9998887777");
		selenium.type("id=unique21", "aa@aa.com");
		selenium.type("id=unique24", TestUtils.getNowString());
		selenium.type("id=unique25", "4:00 PM");		
		selenium.type("id=unique26", "30");
		selenium.type("id=unique27", "follow up 2");
		selenium.click("css=button[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Back to List");
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=ContactNoteSearchCriteria_PersonLastName", "anony");
		selenium.click("id=btnSearchContactNotes");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_ContactNotes_ContactNote_Edit");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=legend.legend-header-one.collapsed");
		selenium.click("id=ContactReasonIdList_lookupitems/contactnotecontactreasons/-3");
		selenium.click("id=ContactReasonIdList_lookupitems/contactnotecontactreasons/-6");
		selenium.type("id=ContactNote_ContactReasonSpecify", "other reason");
		selenium.click("id=ContactNote_IsAnonymous");
		selenium.type("id=ContactNote_PersonName_FirstName", "nonanonymous");
		selenium.type("id=ContactNote_PersonName_LastName", "nonanonymous");
		selenium.select("id=ContactNote_PersonLocation_Id", "label=Other");
		selenium.type("id=ContactNote_PersonLocationSpecify", "other address");
		selenium.select("id=ContactNote_County_Id", "label=Other");
		selenium.type("id=ContactNote_CountySpecify", "other county");
		selenium.select("id=ContactNote_ContactInitiatorType_Id", "label=Self");
		selenium.select("id=ContactNote_ContactType_Id", "label=Other");
		selenium.type("id=ContactNote_ContactTypeSpecify", "other contact taye");
		selenium.click("css=button[type=\"button\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.workspace-header-bar > div.float-right > input[type=\"button\"]");
		selenium.click("css=legend.legend-header-two.collapsed");
		selenium.select("id=unique1", "label=Family/Friend");
		selenium.type("id=unique3", "family");
		selenium.type("id=unique4", "family");
		selenium.type("id=unique13", "follow up 1 edit");
		selenium.click("css=button[type=\"button\"]");
		selenium.click("link=Back to List");
		selenium.waitForPageToLoad("30000");
		selenium.stop();
	}


}
