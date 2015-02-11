package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class No10_TestWaiveRegistry {
	static Logger logger = TestUtils.getLogger(No10_TestWaiveRegistry.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();
	
	public void clean() {

		RavenDbAPI.deleteCollectionByName("clients");
		RavenDbAPI.deleteCollectionByName("waves");
		RavenDbAPI.deleteCollectionByName("clientregistries");
		RavenDbAPI.deleteCollectionByName("clientregistryhistories");
		
	}
	
	
	@Test
	public void N0_initIndividual() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
			selenium.waitForPageToLoad("30000");
			selenium.click("btnLogin");
			
			clean();
			
			int i = 0;
			while(i < 3) {
				selenium.click("id=_topnavlink_Clients_Client_ClientSearch");
				selenium.click("id=_contentlinkCreateClient");
				selenium.type("id=ClientProfile_PersonName_FirstName", "jessica " + TestUtils.getRandomNumber(4));
				selenium.type("id=ClientProfile_PersonName_LastName", "test");
				selenium.type("id=ClientProfile_DateOfBirth", TestUtils.getRandomDate());
				selenium.select("id=ClientProfile_Gender_Id", "label=Female");
				selenium.select("id=ClientProfile_Race_Id", "label=Asian");
				selenium.select("id=ClientJurisdiction_Id", "label=Alcorn");
				selenium.click("id=chkSelectSSN");
				selenium.click("id=btnCreateClient");
				selenium.sleepSeconds(3);
				int timeout = 0;
				while(timeout < 10) {
					try {
						if(selenium.isVisible("btnCreateAsNew")) {
							selenium.click("btnCreateAsNew");
							selenium.waitForElementDisplay("id=ClientMANumber_MedicaidNumber");
							break;
						}else if(selenium.isVisible("ClientMANumber_MedicaidNumber")) {
							break;
						}
						Thread.sleep(1000);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					timeout ++;
				}
	
				selenium.waitForPageToLoad("30000");
				/*selenium.select("id=ClientPhone_ClientPhoneType_Id", "label=Home");
				selenium.type("id=ClientPhone_Phone_Number", "111 444 7890");
				selenium.click("id=IsPrimaryPhone");
				selenium.click("id=btnCreatePhoneNumber");*/
				
				selenium.click("id=_leftnavlink_Clients_Client_Details");
				selenium.click("id=_contentlink_Clients_ClientMANumber_List");
				selenium.click("id=CreateLink");
				selenium.type("id=ClientMANumber_MedicaidNumber", TestUtils.getRandomNumber(9));
				selenium.select("id=ClientMANumber_EligibilityType_Id", "label=Community");
				selenium.click("css=option[value=\"lookupitems/eligibilitytypes/-1\"]");
				selenium.select("id=ClientMANumber_CoverageGroup_Id", "label=002 SSI Retroactive");
				selenium.click("css=option[value=\"lookupitems/coveragegroups/-2\"]");
				selenium.type("id=ClientMANumber_EffectiveDateRange_StartDate", TestUtils.getNowString());
				selenium.type("id=ClientMANumber_EffectiveDateRange_EndDate", TestUtils.getEndDayString());
				
				selenium.click("id=IsCurrentMANumber");
				selenium.click("xpath=(//div[@class='ui-dialog-buttonset']/button[@type='button'])[1]");
				
				selenium.waitForElementHidden("id=ClientMANumberDialog");
				
				selenium.click("id=_leftnavlink_WaiverRegistry_ClientRegistry_HistoryList");
				selenium.click("id=_contentlink_WaiverRegistry_ClientRegistry_Create");
				selenium.waitForElementDisplay("ProgramTypeId");
				selenium.select("ProgramTypeId", "label=ID/DD");
				selenium.click("btnOk");
				selenium.sleepSeconds(3);
				
				i++;
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N1_testContacts() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
			//selenium.click("btnLogin");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StatusName", "label=Active");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_listlink_WaiverRegistry_ClientRegistryContact_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_WaiverRegistry_ClientRegistryContact_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ClientRegistryContactDto_ContactType_Id",
					"label=Incoming Call");
			selenium.select("id=ClientRegistryContactDto_ContactStatus_Id",
					"label=Successful");
			selenium.type("id=ClientRegistryContactDto_Comments",
					"add new contact for individual");
			selenium.click("id=btnSaveCreatedClientRegistryContact");
			selenium.waitForPageToLoad("30000");
			selenium.click("//table[@id='DataTables_Table_0']/tbody/tr[1]/td[5]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ClientRegistryContactDto_ContactType_Id",
					"label=Outgoing Call");
			selenium.select("id=ClientRegistryContactDto_ContactStatus_Id",
					"label=Unsuccessful");
			selenium.type("id=ClientRegistryContactDto_Comments",
					"add new contact for individual \ntesting");
			selenium.click("id=btnSaveEditedClientRegistryContact");
			selenium.refresh();
			selenium.runScript("$('#DataTables_Table_0 tbody tr:eq(0) a:eq(1)').click()");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	@Test
	public void N2_testUpdatePriority() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StatusName", "label=Active");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[2]/td[11]/a[1]");
			selenium.select(
					"css=fieldset.fieldset-container-one > div.row > #Priority",
					"label=3");
			selenium.type("id=Comments", "update the priority");
			selenium.click("id=btnOk");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N3_testOnHoldAndResume() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StatusName", "label=Active");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[2]/td[11]/a[5]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StatusName", "label=On Hold");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[2]/td[11]/a[1]");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
	@Test
	public void N4_testCreateNewWaveAndAddToWave() throws Exception {
		selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=StatusName", "label=Active");
		selenium.click("id=submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='clientRegistrySearchResultTable_wrapper']/div/div/div/table/thead/tr/th[7]");
		selenium.click("id=ClientRegistryDtos_0__IsSelected");
		selenium.click("id=ClientRegistryDtos_1__IsSelected");
		selenium.click("id=btnCreateWaveWithMultipleClients");
		selenium.waitForElementDisplay("divCreateWave");
		selenium.click("id=btnOk");
		selenium.click("id=submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("_topnavlink_WaiverRegistry_Wave_List");
		selenium.select("WaiverProgramId", "label=ID/DD");
		selenium.click("submit");
		selenium.click("//tbody/tr[1]/td[9]/a[contains(text(),'Details')]");
		selenium.waitForElementDisplay("txtMaxCapacity");
		selenium.type("txtMaxCapacity", "10");
		selenium.click("btnUpdate");
		selenium.sleepSeconds(3);
		selenium.click("_topnavlink_WaiverRegistry_ClientRegistry_List");
		selenium.select("id=StatusName", "label=Active");
		selenium.click("id=submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=ClientRegistryDtos_0__IsSelected");
		selenium.click("//tbody/tr[2]/td[11]/a[2]");
		selenium.waitForElementDisplay("WaveNumber");
		selenium.type("id=WaveNumber", "1");
		selenium.click("id=btnOk");
		
		selenium.click("id=submit");
		selenium.waitForPageToLoad("30000");
		
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_topnavlink_WaiverRegistry_Wave_List");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=WaiverProgramId", "label=ID/DD");
		selenium.click("id=submit");
		selenium.waitForPageToLoad("30000");
	}
	
	@Test
	public void N5_testWaveDetailAndSendAlert() throws Exception {
		try {
			selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_topnavlink_WaiverRegistry_Wave_List");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=WaveNumberFrom", "1");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[9]/a[2]");
			selenium.waitForElementDisplay("txtMaxCapacity");
			selenium.type("id=txtMaxCapacity", "11");
			selenium.click("id=btnUpdate");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=WaiverProgramId", "label=ID/DD");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_listlink_WaiverRegistry_Wave_SendToRegionalCenter");
			selenium.select(
					"css=fieldset.fieldset-container-one > div.row > #RegionalProgramTypeId",
					"label=RP2");
			selenium.type("id=Comments", "send wave to regional center");
			selenium.click("id=btnOk");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	@Test
	public void N6_testWaveManage() throws Exception {
		try {
			selenium.open(selenium.baseUrl+ "WaiverRegistry/Wave/List/");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("id=WaveNumberFrom");
			selenium.type("id=WaveNumberFrom", "1");
			selenium.click("id=submit");//Click "Search" button
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[9]/a[1]");//Click "Manage" link
			selenium.waitForPageToLoad("30000");			
			/*selenium.click("id=chkSelectAll");//GenerateLetter function now is invalid
			selenium.click("id=btnGenerateLetters");
			selenium.waitForPageToLoad("30000");

			selenium.type("createDate", TestUtils.getNowString());
			selenium.click("id=btnSubmit");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=ClientRegistries_0__IsSelected");
			selenium.click("id=_listlink_WaiverRegistry_ClientRegistryLetter_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[3]/a[1]");
			selenium.refresh();
			selenium.runScript("$('#DataTables_Table_0 tbody tr:eq(0) a:eq(1)').click()");
			selenium.click("id=btnNo");
			selenium.click("id=_contentlink_WaiverRegistry_Wave_Manage");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=chkSelectAll");
			selenium.click("id=btnPrintLetters");*/

			selenium.click("id=_listlink_WaiverRegistry_ClientRegistryContact_List");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_WaiverRegistry_ClientRegistryContact_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ClientRegistryContactDto_ContactType_Id",
					"label=Incoming Call");
			selenium.select("id=ClientRegistryContactDto_ContactStatus_Id",
					"label=Successful");
			selenium.type("id=ClientRegistryContactDto_Comments",
					"add new contact");
			selenium.click("id=btnSaveCreatedClientRegistryContact");
			selenium.waitForPageToLoad("30000");
			selenium.click("//tbody/tr[1]/td[5]/a[1]");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=ClientRegistryContactDto_ContactType_Id",
					"label=Email");
			selenium.select("id=ClientRegistryContactDto_ContactStatus_Id",
					"label=Unsuccessful");
			selenium.type("id=ClientRegistryContactDto_Comments",
					"add new contact\ntesting");
			selenium.click("id=btnSaveEditedClientRegistryContact");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.runScript("$('#DataTables_Table_0 tbody tr:eq(0) a:eq(1)').click()");
			selenium.click("id=btnYes");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_contentlink_WaiverRegistry_Wave_Manage");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=chkSelectAll");
			selenium.click("id=btnMailMerge");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.dataTables_scrollBody");
			selenium.click("id=chkSelectAll");
			selenium.click("id=ClientRegistries_0__IsSelected");
			selenium.click("id=btnReturnToRegistry");
			selenium.waitForPageToLoad("30000");
			selenium.refresh();
			selenium.runScript("$('#clientRegistrySearchResultTable tbody tr:eq(0) input[type=\"checkbox\"]').click()");
			selenium.click("id=btnDeactivate");
			selenium.sleepSeconds(2);
			selenium.waitForElementDisplay("id=DeactivateReasonId");
			selenium.select("id=DeactivateReasonId", "label=Client moved out of state Applicant/Representative declined services");
			selenium.type("id=Comments", "deactivate individual");
			selenium.click("id=btnOk");
			selenium.waitForPageToLoad("30000");

		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	@Test
	public void N7_testViewAndDeactivate() throws Exception {
		try {
			//selenium.refresh();
			selenium.open(selenium.baseUrl + "WaiverRegistry/ClientRegistry/List/");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=StatusName", "label=Active");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("id=_listlink_WaiverRegistry_ClientRegistry_HistoryList");
			selenium.click("id=_listlink_WaiverRegistry_ClientRegistry_HistoryList");
			selenium.click("//tbody/tr[2]/td[11]/a[3]");
			selenium.waitForElementDisplay("DeactivateReasonId");
			selenium.select("id=DeactivateReasonId", "label=Already on another waiver");
			selenium.type("id=Comments", "deactivate an individual");
			selenium.click("id=btnOk");
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementHidden("id=btnOk");
			selenium.click("id=submit");
			selenium.waitForPageToLoad("30000");

			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}
	
}
