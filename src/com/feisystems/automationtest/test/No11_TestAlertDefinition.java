package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;


import com.feisystems.automationtest.libary.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No11_TestAlertDefinition {
	static Logger logger = TestUtils.getLogger(No11_TestAlertDefinition.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();
	
	@Test
	public void N1_testCreateEditAlertDefinition_AddToWave() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.click("btnLogin");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "AddToWave");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : AddToWave')");
			
			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is added to wave"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/Wave/List/')");
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is added on wave again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	
	@Test
	public void N2_testCreateEditAlertDefinition_DeactivateOnRegistry() throws Exception {
		try {
			selenium.refresh();
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "DeactivatedOnRegistry");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : DeactivatedOnRegistry')");
			
			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is deactivated on registry"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/ClientRegistry/List/')");
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is deactivated on registry again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N3_testCreateEditAlertDefinition_DeactivateOnWave() throws Exception {
		try {
			selenium.refresh();
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "DeactivateOnWave");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : DeactivateOnWave')");

			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is deactivated on wave"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/Wave/List/')");
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is deactivated on wave again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N4_testCreateEditAlertDefinition_OnHold() throws Exception {
		try {
			selenium.refresh();
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "OnHold");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : OnHold')");

			
			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is onholded"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/ClientRegistry/List/')");
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is onholded again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N5_testCreateEditAlertDefinition_Resume() throws Exception {
		try {
			selenium.refresh();
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "Resume");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : Resume')");
			
			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is resumed"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/ClientRegistry/List/')");
			
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is resumed again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N6_testCreateEditAlertDefinition_ReturnToRegistry() throws Exception {
		try {
			selenium.refresh();
			selenium.open(selenium.baseUrl + "Notifications/AlertDefinition/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitElementClickableById("_contentlink_Notifications_AlertDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnSaveCreatedAlertDefinition");
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event");
			selenium.select("id=AlertDefinition_SourceModelDescriptor_Guid", "label=Client Registry");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=AlertDefinition_EventName", "label=WorkflowEventProcessed");
			selenium.type("id=AlertDefinition_EventNameModifier", "ReturnToRegistry");
			selenium.click("id=btnSaveCreatedAlertDefinition"); //Save the alert template
			selenium.waitForPageToLoad("30000");
			
			selenium.click("id=_contentlink_Notifications_AlertDefinition_Edit"); //click the "Edit" to enter edit page
			selenium.waitForPageToLoad("30000");
			
			selenium.type("id=AlertDefinitionCategory_input", "reportable alert event_edit");
			selenium.sleepSeconds(1);
			//select the "Type" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[6]/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select the "Role" in "To" section:
			selenium.click("//td[@role='presentation']/ul/li[8]/span/span/span/span");
			selenium.pressKey(Keys.DOWN);
			selenium.pressKey(Keys.ENTER);
			
			//select "program" in Subject section:
			selenium.runScript("$(window.frames[2].document).find('body').text('{clientName} : ReturnToRegistry')");
			
			//input comments in "Body" section:
			selenium.type("id=AlertDefinition_TemplateBody", "individual is returned to registry"); 
			
			//select "documentID"in Url section:
			selenium.runScript("$(window.frames[1].document).find('body').text('WaiverRegistry/ClientRegistry/List/')");
			
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_blank"); //select "blank" for "Target" field
			//check all the program types:
			selenium.click("id=unique1");
			selenium.click("id=unique3");
			selenium.click("id=unique5");
			selenium.click("id=unique2");
			selenium.click("id=unique4");
			selenium.click("id=unique6");
			
			selenium.type("id=AlertDefinition_TemplateBody", "individual is returned to registry again");
			selenium.select("id=AlertDefinition_TemplateTarget", "label=_self");
			selenium.click("id=btnSaveEditedAlertDefinition");
			
			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
}
