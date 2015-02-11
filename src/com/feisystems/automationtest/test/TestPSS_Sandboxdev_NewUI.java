package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.feisystems.automationtest.libary.AlertType;
import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestPSS_Sandboxdev_NewUI {
	static Logger logger = TestUtils.getLogger(TestPSS_Sandboxdev_NewUI.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();

	public void clean() {

		RavenDbAPI.deleteCollectionByName("idddplanofservices");
		RavenDbAPI.deleteCollectionByName("idddpssserviceplans");
		/*No02_TestProvider.clean();
		No09_TestIndividual.clean();*/
		//No15_TestLxService.clean();

	}

	public void addRepresentativeForIndividual() {
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

	public void createServiceDefinition() throws Exception {
		selenium.open(selenium.baseUrl + "LxServices/LxService/List/");
		No15_TestServiceDefinition.createServiceDefinition(selenium, "TestPSS.xls");
	}

	@Test
	public void testPSS_create() throws Exception {
		selenium.open(selenium.baseUrl);
		selenium.click("btnLogin");
		//clean();
		
		String provider1 = "provider _1";
		String provider2 = "provider _2";
		
		/*No09_TestIndividual.createIndividual(selenium);
		addRepresentativeForIndividual(); 
		No02_TestProvider.createProvider(selenium, provider1);
		No02_TestProvider.createProvider(selenium, provider2);*/
		//createServiceDefinition();
		 

		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		selenium.runScript("window.location = $('#_leftnavlink_Pss_PlanOfService_List').attr('href')");
		selenium.sleepSeconds(1);
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_Pss_PlanOfService_Create");
		selenium.select("id=programTypeForCreateSelect", "label=ID/DD");
		selenium.click("xpath=(//button[@type='button'])[7]");
		selenium.waitForPageToLoad("30000");

		selenium.select("id=Overview_Type_Id", "label=Initial");
		selenium.type("id=overview_StartDate", TestUtils.getNowString());		
		selenium.type("id=overview_EndDate", TestUtils.getEndDayString());
		selenium.click("id=Overview_ServiceType_1915c");
		selenium.type("id=Overview_Narrative", "Comments");
		selenium.click("id=btnClientOverviewCreate");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordCreatedSuccessfully);////verify notification
		selenium.verifyEquals("css=h3 > span", "Status: In Progress");//verify status in title	

		selenium.click("//div[@id='workspace-maincontent']/div/div[2]/div/h4");//click Part I section
		
		selenium.click("id=_IdddPlanOfService_ContactInformation_Manage");// add family contact
		selenium.type("id=FamilyContact_PersonName_FirstName", "first name");
		selenium.type("id=FamilyContact_PersonName_LastName", "last name");
		selenium.type("id=FamilyContact_PersonName_MiddleName", "middle name");
		selenium.select("id=FamilyContact.ContactType.Id", "label=Family");
		selenium.select("id=FamilyContact.Relationship.Id", "label=Friend");
		selenium.type("id=FamilyContact_Phone", "1234567890");
		selenium.type("id=FamilyContact_Fax", "1234567890");
		selenium.type("id=FamilyContact_Email", "xin@fei.com");
		selenium.type("id=FamilyContact_Address_AddressLine1", "shangdi");
		selenium.type("id=FamilyContact_Address_AddressLine2", "donglu");
		selenium.type("id=FamilyContact_Address_City", "beijing");
		selenium.select("id=FamilyContact.Address.StateProvince.Id", "label=Alaska");
		selenium.type("id=FamilyContact_Address_PostalCode", "12345");		
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");			
				
		String[][] addServices = selenium.getExcelData("TestPSS.xls",
				"AddServices");
		for (int i = 1; i < addServices.length; i++) {
			selenium.click("id=Service_ServiceId");
			selenium.autoInputByData(addServices[0], addServices[i]);

			try {
				if (selenium.isElementPresent("id=xxxopenProviderSearchButton")) {
					selenium.click("id=xxxopenProviderSearchButton");
					selenium.click("id=btnSearchProvider");
					selenium.click("link=Select");

					String address = null;
					int timeout = 0;
					boolean flag = true;
					while (flag) {
						address = selenium.getAttribute("ProviderAdress@value");
						if (address != null && !address.equals("")) {
							flag = false;
						} else {
							if (timeout / 10 == selenium.getTimeout())
								break;
							Thread.sleep(100);
							timeout++;
						}
					}
				}
			} catch (Exception ex) {
				logger.error(ex);
				System.out.println(ex.getMessage());
			}

			selenium.click("link=Add Service");
			
			selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
			selenium.verifyNotification("Info: Please change providers and services accordingly for outcome records in Shared Planning Section.");
			
			selenium.runScript("$.unblockUI()");
			selenium.refresh();

		}
				
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		
		selenium.type("id=Agency", "agency");//non waiver support
		selenium.type("id=ContactName", "contact name");
		selenium.type("id=PhoneNumber", "1234567890");
		selenium.type("id=HowAndWhenSupportProvided", "how");
		selenium.type("id=SupportOrProgram", "idd community");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.type("id=SupportPerson_SupportPerson", "support person");//natural supports section
		selenium.type("id=Relationship_Relationship", "relationship");
		selenium.type("id=SupportRole_SupportRole", "support role");
		selenium.type("id=PhoneNumber_PhoneNumber", "1234567890");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
						
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Medical_Physician", "physician");// Medical Information section
		selenium.type("id=Medical_Specialty", "specialty");
		selenium.type("id=Medical_Address", "Shangdi");
		selenium.type("id=Medical_Phone", "6665554444");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");		
		
		//selenium.click("id=btnAddMedication");
		selenium.type("id=Medication_Medication", "medication 1");// Medications section
		selenium.type("id=Medication_Physician", "physician 1");
		selenium.type("id=Medication_Dosage", "two pieces");
		selenium.type("id=Medication_Frequency", "once a day");
		selenium.type("id=Reason_prescribed", "reason");
		selenium.click("id=Medication_Psychotropic_Yes");
		selenium.click("link=Add");		
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button;	
		
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_ChronicHealthConditions_Id_lookupitems_idddpssyesnonaoptions_-1");//Recent Physical Complaints and/or Medical Conditions section
		selenium.type("id=DescriptionChronicHealthConditions_Yes", "description 1");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_HistoryOfHealthProblemsIssues_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionHistoryOfHealthProblemsIssues_Yes", "description 2");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_CurrentLimitationsRestrictionsOnPhysicalActivities_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionCurrentLimitationsRestrictionsOnPhysicalActivities_Yes", "description 3");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_Hospitalizations_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionHospitalizations_Yes", "description 4");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_AdmissionsToIcfIid_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionAdmissionsIcfIid_Yes", "description 5");
		selenium.type("id=MedicationInfo_Allergies", "allergies");
		selenium.type("id=MedicationInfo_Reactions", "reactions");
		selenium.type("id=MedicationInfo_DateOfPhysicalExam", TestUtils.getNowString());
		selenium.type("id=MedicationInfo_DateOfDentalExam", TestUtils.getEndDayString());
		selenium.click("id=btnNextSection");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
			
		selenium.type("id=MedicalSupportNeeds", "medical support needs");
		selenium.type("id=MentalHealthSupportNeeds", "medical health support needs");
		selenium.click("id=btnNextSection");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
				
		selenium.type("id=Communication_MethodOfCommunication", "method of communication");// Communication section
		selenium.type("id=Communication_SupportsNeededForCommunication", "support needed");
		selenium.type("id=Communication_AdaptiveEquipment",	"adaptive equipment");
		selenium.type("id=Communication_HowIsEquipmentMaintained", "once a week");
		selenium.type("id=Communication_BackupPlanForPowerOutage", "back-up plan");
		selenium.type("id=Communication_EnvironmentalModifications", "environmental modification");
		selenium.click("id=Communication_AnyReferralsNeeded_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=ExplainTextArea", "explain");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=Risk_Description", "risk");// Risk section
		selenium.type("id=Risk_Resolution", "resolution");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");		
		
		selenium.type("id=PssBackupAndEmergencyPlan_ProviderNotShowUp",	"provider does not show up");// Back up and Emergency Plans section
		selenium.type("id=PssBackupAndEmergencyPlan_DayProgramOrWorkCanceled", "day program is canceled");
		selenium.type("id=PssBackupAndEmergencyPlan_NaturalDisasterOccurs", "natural disaster occurs");
		selenium.type("id=PssBackupAndEmergencyPlan_SomethingWereToHappenToThePrimaryCaregiver", "plan for future living arrangements");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=FamilyAndCurrentLivingArrangements", "family and current living arrangements");// Family section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=Education_DateBegan", TestUtils.getNowString());// Education section
		selenium.type("id=Education_DateEnded", TestUtils.getRandomDate());
		selenium.type("id=Education_Date", TestUtils.getEndDayString());
		selenium.type("id=Education_CurrentSchool", "current school");
		selenium.type("id=Education_LastSchoolAttended", "school attended");
		selenium.type("id=Education_TypeofDiplomaCertificate", "diploma");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		selenium.type("id=Employment_PlaceOfEmployment", "place1");// Employment section
		selenium.type("id=Employment_Duties", "duty1");
		selenium.type("id=Employment_BeginDate", TestUtils.getRandomDate());
		selenium.click("Medication_Psychotropic_No");
		selenium.type("id=Employment_EndDate", TestUtils.getNowString());
		selenium.type("id=Employment_Reasonforleaving", "reason 1");
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.type("id=Employment_HoursWorked", "20");		
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.runScript("$.unblockUI()");
		selenium.sleepSeconds(2);		
		
		selenium.type("id=Employment_PlaceOfEmployment", "place2");
		selenium.type("id=Employment_Duties", "duty2");
		selenium.type("id=Employment_BeginDate", TestUtils.getNowString());
		selenium.click("Employment_IsCurrent_Yes");
		selenium.click("id=wednesday");
		selenium.click("id=thursday");
		selenium.click("id=friday");
		selenium.type("id=Employment_HoursWorked", "30");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=VolunteerActivity_VolunteerActivity", "volunteer");// Volunteer Activity section
		selenium.type("id=VolunteerActivity_Duties", "duty");
		selenium.click("VolunteerActivity_IsCurrentlyDoingVolunteer_Yes");
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.click("id=tuesday");
		selenium.click("id=wednesday");
		selenium.type("id=VolunteerActivity_Hours", "35");
		selenium.click("link=Add");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");	
		

		selenium.type("id=PreviousAndCurrentBehaviorSupports",
				"Previous and Current Behavior Supports");// Previous and Current Behavior Supports section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		selenium.type("id=SeriousIncidentsDuringthePastYear",
				"Serious Incidents During the Past Year");// Serious Incidents During the Past Year section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=EvaluationInformation_Date", TestUtils.getNowString());// Evaluation Information section
		selenium.type("id=EvaluationInformation_ExaminerName", "name");
		selenium.type("id=EvaluationInformation_ExaminerAgency", "agency");
		selenium.type("id=EvaluationInformation_PrimaryDSMCode", "DSM code");
		selenium.type("id=EvaluationInformation_SecondaryDSMCode", "DSM code 2");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.waitForPageToLoad("30000");// Completed by section
		selenium.type("id=CompletedByDateReviewed",TestUtils.getNowString());
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		
	
		// Part II Personal Profile
		selenium.type("id=GreatThingsAbout", "great things");//great things about 
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=HopesAndDreams", "hopes and dreams");
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");

		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification		
		
		selenium.type("id=unique1", "important to 1");//Important TO/FOR section
		selenium.click("id=btnAddImportantTo");
		selenium.type("id=unique3", "important to 2");
		selenium.type("id=unique2", "important for 1");
		selenium.click("id=btnAddImportantFor");
		selenium.type("id=unique4", "important for 2");
		selenium.click("id=btnNextSection");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification		
		
		selenium.click("id=WorkingOrNot_Perspective_Person");//Working/NotWorking section
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_Yes");
		selenium.type("id=WorkingOrNot_Comments", "things that work for person");
		selenium.click("link=Add");
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
		
		selenium.click("id=WorkingOrNot_Perspective_Family");
		selenium.type("id=WorkingOrNot_FamilyName", "family name");
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_No");
		selenium.type("id=WorkingOrNot_Comments", "things that don't work for family");
		selenium.click("link=Add");
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
		
		selenium.click("id=WorkingOrNot_Perspective_Provider");
		selenium.type("id=WorkingOrNot_ProviderName", "provider name");
		selenium.select("id=WorkingOrNot_LxServiceId", "label=Hour");
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_Yes");
		selenium.type("id=WorkingOrNot_Comments", "things that work for provider");
		selenium.click("link=Add");
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
		
		selenium.click("id=WorkingOrNot_Perspective_Other");
		selenium.type("id=WorkingOrNot_OtherName", "other name");
		selenium.type("id=WorkingOrNot_OtherSupport", "other support");
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_No");
		selenium.type("id=WorkingOrNot_Comments", "things that don't work for other");
		selenium.click("link=Add");
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
		
		selenium.click("css=button[type=\"button\"]");
		
		selenium.type("id=ThingsPeopleNeedToKnow", "Things People Need to Know to");//need to know and strengths
		selenium.type("id=Strengths", "mike¡¯s Strengths");
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification		
		
		selenium.type("id=Question", "question 1");//Questions/Things to Figure Out section
		selenium.type("id=PersonResponsible", "responsible 1");
		selenium.click("link=Save");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		//Part III Person Centeredness
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfServices_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfProviders_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfProvidersTextArea", "description 1");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfLivingSettings_Id_lookupitems_idddpssyesnonaoptions_-1");		
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfRoommates_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfRoommatesTextArea", "description 2");
		selenium.click("id=ChoiceControlRestrictions_HaveControlOfPersonalResources_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfLivingSettingActivities_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfLivingSettingActivitiesTextArea", "description 4");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfDayProgramSettingActivities_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=ChoiceControlRestrictions_HaveRestrictionsOrLimitations_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		selenium.type("id=SupportPerson", "support person");//Names of Support People Who Contributed to the Plan But Did Not Attend the Meeting section
		selenium.type("id=Relationship", "family");
		selenium.type("id=Date", TestUtils.getNowString());
		selenium.click("link=Add Support People");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");			

		//Signatures		
		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");// Signatures section
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SignatureToSign_Signature_SignedName", "mike");
		selenium.type("id=SignatureToSign_Signature_SignedDate",TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "coordinator");
		selenium.type("id=SignatureToSign_Signature_SignedDate",TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "guardian");
		selenium.type("id=SignatureToSign_Signature_SignedDate",TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "provider1");
		selenium.type("id=SignatureToSign_Signature_SignedDate",TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification

		/*selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SignatureToSign_Signature_SignedName", "provider2");
		selenium.type("id=SignatureToSign_Signature_SignedDate",TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification	
*/		
		selenium.click("css=button[type=\"button\"]");
		
		//Part IV Shared Planning			
		selenium.type("id=SharedPlanning_DesiredOutcome", "desired outcomes");// Shared Planning section
		selenium.clickCheckBoxByText(provider1);
		//selenium.clickCheckBoxByText(provider2);
		selenium.clickCheckBoxByText("Hour");
		selenium.clickCheckBoxByText("One-Time Purchase");
		selenium.clickCheckBoxByText("agency");
		selenium.clickCheckBoxByText("support person");
		selenium.click("id=Weekly");
		//selenium.click("//form[@id='formManageSharedPlanning']/div/fieldset/div[6]/span/span/span/span");
		selenium.type("id=weeklyfrequence", "5");
		selenium.type("id=sharedPlanning_StartDate", TestUtils.getNowString());
		selenium.type("id=sharedPlanning_EndDate", TestUtils.getEndDayString());
		selenium.click("link=Add");
				
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");//Return to Summary page
		selenium.waitForPageToLoad("30000");

		selenium.click("id=expandCollapseAll");
		selenium.click("id=expandCollapseAll");
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");//submit
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordHasBeenSubmitted);//verify notification

		selenium.verifyEquals("css=h3 > span", "Status: Pending Director Review");//verify status in title	

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");//Request clarification
		selenium.type("id=reason", "director clarify");
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordRequestedClarification);//verify notification	
		
		selenium.verifyEquals("css=h3 > span", "Status: Clarification Requested");//verify status in title	
		
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");//submit
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordHasBeenSubmitted);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Pending Director Review");//verify status in title	

		selenium.click("//input[@value='Submit']");//submit
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");

		selenium.verifyNotification(AlertType.RecordHasBeenSubmitted);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Pending BIDD Review");//verify status in title	
		
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");//Request clarification
		selenium.type("id=reason", "reason");
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordRequestedClarification);//verify notification	
		
		selenium.verifyEquals("css=h3 > span", "Status: Clarification Requested");//verify status in title	

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");//submit
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordHasBeenSubmitted);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Pending Director Review");//verify status in title	

		selenium.click("//input[@value='Submit']");//submit
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordHasBeenSubmitted);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Pending BIDD Review");//verify status in title	
		
		selenium.click("//input[@value='Approve']");//approve
		selenium.type("css=#div_comment > #comment", "approve");
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordApproved);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Approved");//verify status in title	
		
		selenium.click("id=_Pss_PlanOfService_List");

		selenium.printVerfifyInfo();
	}

	@Test
	public void testPSS_edit() throws Exception {
		selenium.open(selenium.baseUrl);
		//selenium.click("btnLogin");		

		String provider1 = "provider _1";		

		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");// search a client
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		//selenium.runScript("$('span:contains(\"IDDDPSS\")').click()");
		selenium.runScript("window.location = $('#_leftnavlink_Pss_PlanOfService_List').attr('href')");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");// enter summary page of PSS
		selenium.click("link=Revise");//revise a new PSS
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification("Success: A revised PSS has been created");//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: In Progress");//verify status in title		
		selenium.click("css=h4.section-submit-required");
		selenium.verifyEquals("//div[@id='workspace-maincontent']/div/div/div[2]/div/fieldset/div[6]/span", "In Progress");//verify status in overview section
		
		selenium.click("//input[@value='Discard']");//Discard the new PSS
		selenium.type("id=reason", "discard");
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");	
		
		selenium.verifyNotification(AlertType.RecordDiscarded);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Discarded");//verify status in title		
		selenium.click("css=h4.section-submit-required");
		selenium.verifyEquals("//div[@id='workspace-maincontent']/div/div/div[2]/div/fieldset/div[6]/span", "Discarded");//verify status in overview section
		
		selenium.click("id=_Pss_PlanOfService_List");//Return to list page
		
		selenium.click("link=Revise");//revise a new PSS again
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification("Success: A revised PSS has been created");//verify notification			
		
		selenium.click("xpath=(//a[contains(text(),'View')])[3]");//navigate to summary page
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Approve']");//approve the revised pss straightly
		selenium.type("css=#div_comment > #comment", "approve revised PSS");
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordApproved);//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: Approved");//verify status in title		
		selenium.click("css=h4.section-submit-required");
		selenium.verifyEquals("//div[@id='workspace-maincontent']/div/div/div[2]/div/fieldset/div[6]/span", "Approved");//verify status in overview section
		
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Revise");//revise a new PSS again
		selenium.click("css=#formConfirm > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");		
		
		selenium.verifyNotification("Success: A revised PSS has been created");//verify notification
		
		selenium.verifyEquals("css=h3 > span", "Status: In Progress");//verify status in title		
			
		selenium.click("id=_IdddPlanOfService_Overview_Edit");// edit overview section
		selenium.click("id=Overview_ServiceType_1915c");		
		selenium.type("id=Overview_Narrative", "Comments edit");
		selenium.click("id=btnEditClientOverviewFormSave");
		selenium.waitForPageToLoad("30000");
		
				
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_ContactInformation_Manage");//enter family contact
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Quick View");//quick view family contact
		selenium.click("id=familyContactViewWindowClose");
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=FamilyContact_PersonName_FirstName", "first name new");//add new
		selenium.type("id=FamilyContact_PersonName_LastName", "last name new");
		selenium.type("id=FamilyContact_PersonName_MiddleName", "middle name new");
		selenium.select("id=FamilyContact.ContactType.Id", "label=Other");
		selenium.select("id=FamilyContact.Relationship.Id", "label=Other");
		selenium.type("id=FamilyContact_OtherRelationship", "other family");
		selenium.type("id=FamilyContact_Phone", "9876543210");
		selenium.type("id=FamilyContact_Fax", "9876543210");
		selenium.type("id=FamilyContact_Email", "xinnew@fei.com");
		selenium.type("id=FamilyContact_Address_AddressLine1", "anningzhuang");
		selenium.type("id=FamilyContact_Address_AddressLine2", "xilu");
		selenium.type("id=FamilyContact_Address_City", "bei");
		selenium.select("id=FamilyContact.Address.StateProvince.Id", "label=Florida");
		selenium.type("id=FamilyContact_Address_PostalCode", "54321");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit the new family contact
		selenium.type("id=FamilyContact_PersonName_FirstName", "first name new edit");
		selenium.type("id=FamilyContact_PersonName_LastName", "last name new edit");
		selenium.type("id=FamilyContact_PersonName_MiddleName", "middle name new edit");
		selenium.type("id=FamilyContact_OtherRelationship", "other family edit");
		selenium.type("id=FamilyContact_Phone", "9876543211");
		selenium.type("id=FamilyContact_Fax", "9876543211");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_SupportService_ManageServicePlan");//enter service
		selenium.waitForPageToLoad("30000");
		
		selenium.click("xpath=(//a[contains(text(),'Delete')])[2]");//delete the second service
		selenium.click("id=btnConfirmYes");
		
		
		String[][] addServices = selenium.getExcelData("TestPSS.xls",//add new service
				"AddServicesforEdit");
		for (int i = 1; i < addServices.length; i++) {
			selenium.click("id=Service_ServiceId");
			selenium.autoInputByData(addServices[0], addServices[i]);

			try {
				if (selenium.isElementPresent("id=xxxopenProviderSearchButton")) {
					selenium.click("id=xxxopenProviderSearchButton");
					selenium.click("id=btnSearchProvider");
					selenium.click("xpath=(//a[contains(text(),'Select')])[2]");

					String address = null;
					int timeout = 0;
					boolean flag = true;
					while (flag) {
						address = selenium.getAttribute("ProviderAdress@value");
						if (address != null && !address.equals("")) {
							flag = false;
						} else {
							if (timeout / 10 == selenium.getTimeout())
								break;
							Thread.sleep(100);
							timeout++;
						}
					}
				}
			} catch (Exception ex) {
				logger.error(ex);
				System.out.println(ex.getMessage());
			}

			selenium.click("link=Add Service");
			selenium.runScript("$.unblockUI()");
			selenium.refresh();

		}

		String[][] editServices = selenium.getExcelData("TestPSS.xls",//edit the new service
				"EditServices");
		for (int i = 1; i < editServices.length; i++) {
			selenium.click("id=Service_ServiceId");
			selenium.runScript("$('#servicesTable td:contains(\""
					+ editServices[i][0]
					+ "\")').siblings().find('a:contains(\"Edit\")').click()");
			selenium.click("id=Service_ServiceId");
			selenium.autoInputByData(editServices[0], editServices[i], 1);
			selenium.click("link=Save Changes");
		}
		
		
		selenium.click("id=_contentlink_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_NonWaiverSupport_Manage");//enter Non Waiver Agency Supports
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete 
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Agency", "agency new");//add new
		selenium.type("id=ContactName", "contact name new");
		selenium.type("id=PhoneNumber", "9876543210");
		selenium.type("id=HowAndWhenSupportProvided", "how new");
		selenium.type("id=SupportOrProgram", "support new");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=Agency", "agency new edit");
		selenium.type("id=ContactName", "contact name new edit");
		selenium.type("id=PhoneNumber", "9876543211");
		selenium.type("id=HowAndWhenSupportProvided", "how new edit");
		selenium.type("id=SupportOrProgram", "support new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_NaturalSupport_Manage");//enter natural supports
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=SupportPerson_SupportPerson", "person new");//add new
		selenium.type("id=Relationship_Relationship", "relationship new");
		selenium.type("id=SupportRole_SupportRole", "role new");
		selenium.type("id=PhoneNumber_PhoneNumber", "9876543210");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=SupportPerson_SupportPerson", "person new edit");
		selenium.type("id=Relationship_Relationship", "relationship new edit");
		selenium.type("id=SupportRole_SupportRole", "role new edit");
		selenium.type("id=PhoneNumber_PhoneNumber", "9876543211");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_MedicalInfo_Manage");//enter physician information
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Medical_Physician", "physician new");//add new
		selenium.type("id=Medical_Specialty", "specialty new");
		selenium.type("id=Medical_Address", "address new");
		selenium.type("id=Medical_Phone", "9876543210");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=Medical_Physician", "physician new edit");
		selenium.type("id=Medical_Specialty", "specialty new edit");
		selenium.type("id=Medical_Address", "address new edit");
		selenium.type("id=Medical_Phone", "9876543211");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_Medication_MedicationManage");//enter medications
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Medication_Medication", "medication new");//add new
		selenium.type("id=Medication_Physician", "physician new");
		selenium.type("id=Medication_Dosage", "dosage new");
		selenium.type("id=Medication_Frequency", "frequency new");
		selenium.type("id=Reason_prescribed", "prescribed new");
		selenium.click("id=Medication_Psychotropic_No");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit 
		selenium.type("id=Medication_Medication", "medication new edit");
		selenium.type("id=Medication_Physician", "physician new edit");
		selenium.type("id=Medication_Dosage", "dosage new edit");
		selenium.type("id=Medication_Frequency", "frequency new edit");
		selenium.type("id=Reason_prescribed", "prescribed new edit");
		selenium.click("id=Medication_Psychotropic_Yes");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_RecentPhysicalComplaintsMedicalConditions_Edit");//enter recent physical...
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_ChronicHealthConditions_Id_lookupitems_idddpssyesnonaoptions_-2");//edit
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_HistoryOfHealthProblemsIssues_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_CurrentLimitationsRestrictionsOnPhysicalActivities_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_Hospitalizations_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=RecentPhysicalComplaintsMedicalConditions_AdmissionsToIcfIid_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=MedicationInfo_Allergies", "allergies edit");
		selenium.type("id=MedicationInfo_Reactions", "reactions edit");
		selenium.click("id=recentPhysicalComplaintsMedicalConditionsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_MedicalSupportNeeds_Edit");//enter medical and mental health ...
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=MedicalSupportNeeds", "medical support needs edit");//edit
		selenium.type("id=MentalHealthSupportNeeds", "medical health support needs edit");
		selenium.click("id=btnMedicalSupportNeedsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_Communication_Edit");//enter communication...
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=Communication_MethodOfCommunication", "method of communication edit");//edit
		selenium.type("id=Communication_SupportsNeededForCommunication", "support needed edit");
		selenium.type("id=Communication_AdaptiveEquipment", "adaptive equipment edit");
		selenium.type("id=Communication_HowIsEquipmentMaintained", "once a week edit");
		selenium.type("id=Communication_BackupPlanForPowerOutage", "back-up plan edit");
		selenium.type("id=Communication_EnvironmentalModifications", "environmental modification edit");
		selenium.click("id=Communication_AnyReferralsNeeded_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=communicationSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required > span.section-submit-required-indicator");//click Part I section
		selenium.click("//div[@id='partOne']/div[2]/div[2]/div/div/h5");//click risk section
		selenium.click("id=_IdddPlanOfService_RiskAssessment_Manage");//enter risk section
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Risk_Description", "risk new");//add new
		selenium.type("id=Risk_Resolution", "resolution new");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=Risk_Description", "risk new edit");
		selenium.type("id=Risk_Resolution", "resolution new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_BackupAndEmergencyPlan_Edit");//enter back up ...
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=PssBackupAndEmergencyPlan_ProviderNotShowUp", "provider does not show up edit");//edit
		selenium.type("id=PssBackupAndEmergencyPlan_DayProgramOrWorkCanceled", "day program is canceled edit");
		
		selenium.type("id=PssBackupAndEmergencyPlan_NaturalDisasterOccurs", "natural disaster occurs edit");
		selenium.type("id=PssBackupAndEmergencyPlan_SomethingWereToHappenToThePrimaryCaregiver", "plan for future living arrangements edit");
		selenium.click("id=backupAndEmergencyPlanSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_LivingArrangements_Edit");//enter family & current living		
		selenium.type("id=FamilyAndCurrentLivingArrangements", "family and current living arrangements edit");//edit
		selenium.click("id=familyAndCurrentLivingArrangementsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_Education_Edit");//enter education
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=Education_CurrentSchool", "current school edit");//edit
		selenium.type("id=Education_LastSchoolAttended", "school attended edit");
		selenium.type("id=Education_TypeofDiplomaCertificate", "diploma edit");
		selenium.click("id=educationSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("//div[@id='partOne']/div[2]/div[6]/div/div/h5");//click employments
		selenium.click("css=#DataTables_Table_6 > tbody > tr.odd > td.. > a");//click quick view
		selenium.click("id=employmentViewWindowClose");
		selenium.click("id=_IdddPlanOfService_Employment_Manage");//enter employments
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Quick View");//click quick view
		selenium.click("id=employmentViewWindowClose");
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Employment_PlaceOfEmployment", "place new");//add new
		selenium.type("id=Employment_Duties", "duty new");
		selenium.type("id=Employment_BeginDate",TestUtils.getNowString());
		selenium.click("id=Employment_IsCurrent_Yes");
		selenium.click("id=sunday");
		selenium.type("id=Employment_HoursWorked", "30");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=Employment_PlaceOfEmployment", "place2 edit");
		selenium.type("id=Employment_Duties", "duty2 edit");
		selenium.click("id=Medication_Psychotropic_No");
		selenium.type("id=Employment_EndDate",TestUtils.getFutureDay(1));
		selenium.type("id=Employment_Reasonforleaving", "leaving edit");
		selenium.click("id=tuesday");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_VolunteerActivity_Manage");//enter volunteer activity
		selenium.waitForPageToLoad("30000");
		selenium.click("id=VolunteerActivity_VolunteerActivity");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=VolunteerActivity_VolunteerActivity", "volunteer new");//add new
		selenium.type("id=VolunteerActivity_Duties", "duty new");
		selenium.click("id=VolunteerActivity_IsCurrentlyDoingVolunteer_Yes");
		selenium.click("id=sunday");
		selenium.type("id=VolunteerActivity_Hours", "10");
		selenium.click("link=Add");
		selenium.click("link=Edit");//edit
		selenium.type("id=VolunteerActivity_VolunteerActivity", "volunteer new edit");
		selenium.type("id=VolunteerActivity_Duties", "duty new edit");
		selenium.click("id=VolunteerActivity_IsCurrentlyDoingVolunteer_No");
		selenium.click("id=monday");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_BehaviorSupports_Edit");//enter behavior supports		
		selenium.type("id=PreviousAndCurrentBehaviorSupports", "Previous and Current Behavior Supports edit");//edit
		selenium.click("id=previousAndCurrentBehaviorSupportsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_SeriousIncidents_Edit");//serious incidents		
		selenium.type("id=SeriousIncidentsDuringthePastYear", "Serious Incidents During the Past Year edit");
		selenium.click("id=seriousIncidentsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_EvaluationInformation_Edit");//enter evaluation		
		selenium.type("id=EvaluationInformation_ExaminerName", "name edit");//edit
		selenium.type("id=EvaluationInformation_ExaminerAgency", "agency edit");
		selenium.type("id=EvaluationInformation_PrimaryDSMCode", "DSM code edit");
		selenium.type("id=EvaluationInformation_SecondaryDSMCode", "DSM code 2 edit");
		selenium.click("id=evaluationInformationSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=#partOne > div.header.collapsed > h4.section-submit-required");//click Part I section
		selenium.click("id=_IdddPlanOfService_EssentialInfoCompletedBy_Edit");//enter essential information completed by
		selenium.type("id=CompletedByDateReviewed", TestUtils.getFutureDay(1));
		selenium.click("id=essentialInformationCompletedBySave");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_GreatThingsAbout_Edit");//enter great things about..
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=GreatThingsAbout", "great things edit");//edit
		selenium.click("id=greatThingsAboutSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_HopesAndDreams_Edit");//enter hopes and dreams
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=HopesAndDreams", "hopes and dreams edit");
		selenium.click("id=hopesAndDreamsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_Important_Edit");//enter important to/for
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=btnAddImportantTo");//add new important to
		selenium.type("id=unique5", "important to 3");
		selenium.click("id=btnAddImportantFor");//add new important for
		selenium.type("id=unique6", "important for 3");
		selenium.click("link=Delete");//delete
		selenium.click("xpath=(//a[contains(text(),'Delete')])[3]");
		selenium.click("id=importantSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_WorkingOrNot_Manage");//enter working/not working section
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");//delete
		selenium.click("id=btnConfirmYes");
		selenium.click("id=WorkingOrNot_Perspective_Person");//add new
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_No");
		selenium.type("id=WorkingOrNot_Comments", "person new perspective");
		selenium.click("link=Add");
		selenium.click("xpath=(//a[contains(text(),'Edit')])[2]");//edit
		selenium.click("id=WorkingOrNot_MakeSenseOrNot_Yes");
		selenium.type("id=WorkingOrNot_Comments", "things that work for family edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_ThingsPeopleNeedToKnow_Edit");//enter need to know & Strengths
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=ThingsPeopleNeedToKnow", "Things People Need to Know to edit");
		selenium.type("id=Strengths", "mike¡¯s Strengths edit");
		selenium.click("id=thingsPeopleNeedToKnowandStrengthsSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");//click Part II section
		selenium.click("id=_IdddPlanOfService_QuestionsThingsToFigureOut_Manage");//enter Questions/Things to Figure Out
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Question", "question new");
		selenium.type("id=PersonResponsible", "responsible new");
		selenium.click("link=Save");
		selenium.click("link=Edit");
		selenium.type("id=Question", "question new edit");
		selenium.type("id=PersonResponsible", "responsible new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[4]/div/h4");//click Part III section
		selenium.click("id=_IdddPlanOfService_ChoiceControlRestrictions_Edit");//enter choice, control...
		
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfServices_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfServicesTextArea", "description new 1");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfProviders_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfLivingSettings_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfLivingSettingsTextArea", "description new 2");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfRoommates_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=ChoiceControlRestrictions_HaveControlOfPersonalResources_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionHaveControlOfPersonalResourcesTextArea", "description new  3");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfLivingSettingActivities_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=ChoiceControlRestrictions_GivenAChoiceOfDayProgramSettingActivities_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=ChoiceControlRestrictions_HaveRestrictionsOrLimitations_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=personCenterednessSave");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[4]/div/h4");//click Part III section
		selenium.click("id=_IdddPlanOfService_SupportPeople_Manage");//enter contributors not at meeting
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");
		selenium.click("id=btnConfirmYes");
		selenium.type("id=SupportPerson", "support person new");
		selenium.type("id=Relationship", "relationship new");
		selenium.type("Date", TestUtils.getFutureDay(1));
		selenium.click("link=Add Support People");
		selenium.click("link=Edit");
		selenium.type("id=SupportPerson", "support person new edit");
		selenium.type("id=Relationship", "relationship new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[4]/div/h4");//click Part III section
		selenium.click("id=_IdddPlanOfService_Signatures_Manage");//enter signatures
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Edit");
		selenium.type("id=SignatureToSign_Signature_SignedName", "mike edit");
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("xpath=(//a[contains(text(),'Retract')])[5]");
		selenium.click("id=RetractSignatureBtn");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("//div[@id='workspace-maincontent']/div/div[5]/div/h4");//click Part IV section
		selenium.click("id=_IdddPlanOfService_SharedPlannings_Manage");//enter shared planning section
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=Delete");
		selenium.click("id=btnConfirmYes");
		selenium.type("id=SharedPlanning_DesiredOutcome", "outcomes new");
		selenium.clickCheckBoxByText(provider1);
		selenium.clickCheckBoxByText("One-Time Purchase");
		selenium.clickCheckBoxByText("agency new edit, contact name new edit");
		selenium.clickCheckBoxByText("person new edit");
		selenium.type("id=sharedPlanning_StartDate",TestUtils.getNowString());
		selenium.type("id=sharedPlanning_EndDate",TestUtils.getFutureDay(1));
		selenium.click("id=Daily");
		selenium.click("css=label.grouped-radiobuttons-label");
		selenium.click("link=Add");
		selenium.click("link=Edit");
		selenium.type("id=SharedPlanning_DesiredOutcome", "outcomes new edit");
		selenium.click("id=Monthly");
		selenium.type("id=MonthlyFrequency", "5");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
				
		selenium.printVerfifyInfo();
		
		selenium.stop();

	}

}
