package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestPSS {
	static Logger logger = TestUtils.getLogger(TestPSS.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();
	String clientName = "mike";
	
	public void clean() {

		RavenDbAPI.deleteCollectionByName("idddplanofservices");
		RavenDbAPI.deleteCollectionByName("idddpssserviceplans");
		No02_TestProvider.clean();
		//No09_TestIndividual.clean();
		RavenDbAPI.deleteDocumentByAttriute("clients", "ClientProfile.PersonName.FirstName", clientName);
		//No15_TestLxService.clean();

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
		No09_TestIndividual.addRepresentativeForIndividual(selenium); 
		No02_TestProvider.createProvider(selenium, provider1);
		No02_TestProvider.createProvider(selenium, provider2");
		createServiceDefinition();*/
		 

		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		selenium.runScript("$('span:contains(\"IDDDPSS\")').click()");
		selenium.sleepSeconds(1);
		selenium.runScript("window.location = $('#_leftnavlink_Pss_PlanOfService_List').attr('href')");
		selenium.sleepSeconds(1);
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_Pss_PlanOfService_Create");
		selenium.select("id=programTypeForCreateSelect", "label=ID/DD");
		// selenium.click("id=btnOk");
		selenium.click("css=div.fancybox-dialog-controls > input[type='button']");
		selenium.waitForPageToLoad("30000");

		selenium.select("id=Overview_Type_Id", "label=Initial");
		selenium.type("id=overview_StartDate", TestUtils.getNowString());
		selenium.type("id=overview_EndDate", TestUtils.getEndDayString());
		selenium.type("id=Overview_Narrative", "Comments");
		selenium.click("id=btnClientOverviewCreate");
		selenium.waitForPageToLoad("30000");

		selenium.click("//div[@id='workspace-maincontent']/div/div[2]/div/h4");
		selenium.click("id=_IdddPlanOfService_FamilyContact_Manage");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_contentlink_IdddPlanOfService_FamilyContact_Create");// add
																					// family
																					// contact
		selenium.waitForPageToLoad("30000");

		selenium.type("id=FamilyContact_PersonName_FirstName", "Mary");
		selenium.type("id=FamilyContact_PersonName_LastName", "Test");
		selenium.type("id=FamilyContact_PersonName_MiddleName", "m");
		selenium.select("id=FamilyContact_ContactType_Id", "label=Family");
		selenium.select("id=RelationshipId", "label=Spouse");
		selenium.type("id=FamilyContact_Phone", "1234567890");
		selenium.type("id=FamilyContact_Fax", "1234567890");
		selenium.type("id=FamilyContact_Email", "xin.zhou@feisystems.com");
		selenium.type("id=FamilyContact_Address_AddressLine1", "Shangdi dong");
		selenium.type("id=FamilyContact_Address_AddressLine2", "Haidian");
		selenium.type("id=FamilyContact_Address_City", "Beijing");
		selenium.type("id=FamilyContact_Address_PostalCode", "12345");
		selenium.click("id=btnSaveCreatedClientContact");
		selenium.waitForPageToLoad("30000");

		selenium.click("css=button[type=\"button\"]");// Click "Next" button
		selenium.waitForPageToLoad("30000");// Completed by section
		selenium.type("id=EssentialInformationCompletedBy_DateReviewed",
				TestUtils.getNowString());
		// selenium.click("id=essentialInformationCompletedBySave");
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=Service_ServiceId");// Add first service
		selenium.select("id=Service_ServiceId", "label=Hour");

		selenium.type("id=Service_HoursPerYear", "2000");
		selenium.type("id=HowAndWhenServiceUsed", "every day");
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

		selenium.click("link=Add Service");
		//selenium.runScript("$.unblockUI()");
		//selenium.refresh();
		selenium.select("id=Service_ServiceId", "label=One-Time Purchase");// add
																			// second
																			// service
		selenium.type("id=Service_ItemDescription", "one time purchase");
		selenium.type("id=Service_Units", "200");
		selenium.type("id=HowAndWhenServiceUsed", "one time fee service");
		selenium.click("id=xxxopenProviderSearchButton");
		selenium.click("id=btnSearchProvider");
		selenium.click("xpath=(//a[contains(text(),'Select')])[2]");
		

		flag = true;
		timeout = 0;
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
		selenium.click("link=Add Service");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.type("id=NonWaiverAgencySupports", "waiver agency supports");// "Other Supports"
																				// section
		selenium.type("id=Non1915IAgencySupports", "1915i agency supports");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=SupportPerson_SupportPerson", "Anna");// Natural
																// Support
																// section
		selenium.type("id=Relationship_Relationship", "friend");
		selenium.type("id=SupportRole_SupportRole", "deliver");
		selenium.type("id=PhoneNumber_PhoneNumber", "9998887777");
		selenium.click("link=Add");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		// selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_IdddPlanOfService_SharedPlannings_Edit");// Shared
																		// Planning
																		// section
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SharedPlanning_DesiredOutcome", "desired outcome");
		selenium.clickCheckBoxByText(provider1);
		selenium.clickCheckBoxByText(provider2);
		selenium.clickCheckBoxByText("Hour");
		selenium.clickCheckBoxByText("One-Time Purchase");
		selenium.type("id=SharedPlanning_HowOftenOrByWhen", "day");
		selenium.type("id=sharedPlanning_StartDate", TestUtils.getNowString());
		selenium.type("id=sharedPlanning_EndDate", TestUtils.getEndDayString());
		selenium.click("id=btnEditSharedPlanningFormSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button[type=\"button\"]");// click "Next" button

		selenium.type("id=Medical_Physician", "physician");// Medical
															// Information
															// section
		selenium.type("id=Medical_Specialty", "specialty");
		selenium.type("id=Medical_Address", "Shangdi");
		selenium.type("id=Medical_Phone", "6665554444");
		selenium.click("link=Add");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.click("_IdddPlanOfService_Medication_MedicationManage");// Medications
																			// sction
		selenium.type("id=Medication_Medication", "medication1");
		selenium.type("id=Medication_Physician", "physician1");
		selenium.type("id=Medication_Dosage", "two pieces");
		selenium.type("id=Medication_Frequency", "once a day");
		selenium.type("id=Reason_prescribed", "reason");
		selenium.click("link=Add");
		selenium.click("id=_IdddPlanOfService_Medication_Manage");
		selenium.waitForPageToLoad("30000");

		selenium.type("id=MedicationInfo_Allergies", "allergies");
		selenium.type("id=MedicationInfo_Reactions", "reactions");
		selenium.type("id=MedicationInfo_DateOfPhysicalExam",
				TestUtils.getNowString());
		selenium.type("id=MedicationInfo_DateOfDentalExam",
				TestUtils.getEndDayString());
		selenium.click("id=MedicationInfo_MentalHealthSupportNeeds_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionMentalHealthSupportNeeds_Yes",
				"description 1");
		selenium.click("id=MedicationInfo_ChronicHealthConditions_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionChronicHealthConditions_Yes",
				"description 2");
		selenium.click("id=MedicationInfo_HistoryOfHealthProblemsIssues_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionHistoryOfHealthProblemsIssues_Yes",
				"description 3");
		selenium.click("id=MedicationInfo_CurrentLimitationsRestrictionsOnPhysicalActivities_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type(
				"id=DescriptionCurrentLimitationsRestrictionsOnPhysicalActivities_Yes",
				"description 4");
		selenium.click("id=MedicationInfo_Hospitalizations_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionHospitalizations_Yes", "description 5");
		selenium.click("id=MedicationInfo_AdmissionsToIcfIid_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionAdmissionsIcfIid_Yes", "description 6");
		selenium.type("id=MedicationInfo_MedicalSupportNeeds",
				"medical support needs");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=Communication_MethodOfCommunication",
				"method of communication");// Communication section
		selenium.type("id=Communication_SupportsNeededForCommunication",
				"support needed");
		selenium.type("id=Communication_AdaptiveEquipment",
				"adaptive equipment");
		selenium.type("id=Communication_HowIsEquipmentMaintaied", "once a week");
		selenium.type("id=Communication_BackupPlanForPowerOutage",
				"back-up plan");
		selenium.type("id=Communication_EnvironmentalModifications",
				"environmental modification");
		selenium.click("id=Communication_AnyReferrelsNeeded_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=ExplainTextArea", "explain");
		selenium.click("id=btnNextSection");// "Save & Next" button
		// selenium.click("id=communicationSave");
		selenium.waitForPageToLoad("30000");

		selenium.type("id=Risk_Description", "risk");// Risk section
		selenium.type("id=Risk_Resolution", "resolution");
		selenium.click("link=Add");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=PssBackupAndEmergencyPlan_ProviderNotShowUp",
				"provider does not show up");// Back up and Emergency Plans
												// section
		selenium.type("id=PssBackupAndEmergencyPlan_DayProgramOrWorkCanceled",
				"day program is canceled");
		selenium.type("id=PssBackupAndEmergencyPlan_NaturalDisasterOoccurs",
				"natural disaster occurs");
		selenium.type(
				"id=PssBackupAndEmergencyPlan_SomethingWereToHappenToThePrimaryCaregiver",
				"plan for future living arrangements");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=FamilyAndCurrentLivingArrangements",
				"family and current living arrangements");// Family section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=Education_DateBegan", TestUtils.getNowString());// Education
																			// section
		selenium.type("id=Education_DateEnded", TestUtils.getRandomDate());
		selenium.type("id=Education_Date", TestUtils.getEndDayString());

		selenium.type("id=Education_CurrentSchool", "current school");
		selenium.type("id=Education_LastSchoolAttended", "school attended");
		selenium.type("id=Education_TypeofDiplomaCertificate", "diploma");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=Employment_PlaceOfEmployment", "place1");// Employment
																	// section
		selenium.type("id=Employment_Duties", "duty1");
		selenium.type("id=Employment_BeginDate", TestUtils.getRandomDate());
		selenium.type("id=Employment_EndDate", TestUtils.getNowString());
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.type("id=Employment_HoursWorked", "20");
		selenium.type("id=Employment_Reasonforleaving", "reason 1");
		selenium.click("link=Add");
		selenium.runScript("$.unblockUI()");
		selenium.sleepSeconds(2);

		selenium.type("id=Employment_PlaceOfEmployment", "place2");
		selenium.type("id=Employment_Duties", "duty2");
		selenium.click("id=Employment_IsCurrent_Yes");
		selenium.type("id=Employment_BeginDate", TestUtils.getNowString());
		selenium.click("id=wednesday");
		selenium.click("id=thursday");
		selenium.click("id=friday");
		selenium.type("id=Employment_HoursWorked", "30");
		// selenium.type("id=Employment_Reasonforleaving", "reason 2");
		selenium.click("link=Add");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=VolunteerActivity_VolunteerActivity", "volunteer");// Volunteer
																				// Activity
																				// section
		selenium.type("id=VolunteerActivity_Duties", "duty");
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.click("id=tuesday");
		selenium.click("id=wednesday");
		selenium.type("id=VolunteerActivity_Hours", "35");
		selenium.click("link=Add");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=PreviousAndCurrentBehaviorSupports",
				"Previous and Current Behavior Supports");// Previous and
															// Current Behavior
															// Supports section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=SeriousIncidentsDuringthePastYear",
				"Serious Incidents During the Past Year");// Serious Incidents
															// During the Past
															// Year section
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=EvaluationInformation_Date", TestUtils.getNowString());// Evaluation
																					// Information
																					// section
		selenium.type("id=EvaluationInformation_ExaminerName", "name");
		selenium.type("id=EvaluationInformation_ExaminerAgency", "agency");
		selenium.type("id=EvaluationInformation_PrimaryDSMCode", "DSM code");
		selenium.type("id=EvaluationInformation_SecondaryDSMCode", "DSM code 2");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=PersonalProfile_IntroductionGreatThingsAbout",
				"great things");// Personal Profile section
		selenium.type("id=PersonalProfile_HopesAndDreams", "hopes and dreams");
		selenium.type("id=unique1", "important to 1");
		selenium.click("id=btnAddImportantTo");
		selenium.type("id=unique3", "important to 2");
		selenium.type("id=unique2", "important for 1");
		selenium.click("id=btnAddImportantFor");
		selenium.type("id=unique4", "important for 2");
		selenium.click("id=PersonalProfile_WorkingPersonsPerspective");
		selenium.type(
				"id=PersonalProfile_WorkingPersonsPerspectiveDescription",
				"description 1");
		selenium.click("id=PersonalProfile_WorkingFamilysPerspective");
		selenium.type(
				"id=PersonalProfile_WorkingFamilysPerspectiveDescription",
				"description 2");
		selenium.click("id=PersonalProfile_WorkingProvidersPerspective");
		selenium.type(
				"id=PersonalProfile_WorkingProvidersPerspectiveDescription",
				"description 3");
		selenium.click("id=PersonalProfile_WorkingOthersPerspective");
		selenium.type("id=PersonalProfile_WorkingOthersPerspectiveDescription",
				"description 4");
		selenium.click("id=PersonalProfile_NotWorkingPersonsPerspective");
		selenium.type(
				"id=PersonalProfile_NotWorkingPersonsPerspectiveDescription",
				"description 5");
		selenium.click("id=PersonalProfile_NotWorkingFamilysPerspective");
		selenium.type(
				"id=PersonalProfile_NotWorkingFamilysPerspectiveDescription",
				"description 6");
		selenium.click("id=PersonalProfile_NotWorkingProvidersPerspective");
		selenium.type(
				"id=PersonalProfile_NotWorkingProvidersPerspectiveDescription",
				"description 7");
		selenium.click("id=PersonalProfile_NotWorkingOthersPerspective");
		selenium.type(
				"id=PersonalProfile_NotWorkingOthersPerspectiveDescription",
				"description 8");
		selenium.type("id=PersonalProfile_ThingsPeopleNeedtoKnow",
				"keep healthy");
		selenium.type("id=PersonalProfile_Strengths", "strength");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=Question", "question");// Questions section
		selenium.type("id=PersonResponsible", "person responsible");
		selenium.click("link=Save");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");



		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfServices_Id_lookupitems_idddpssyesnonaoptions_-2");// Person
																														// Centeredness
																														// section
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfProviders_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfLivingSettings_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfRoommates_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.click("id=IdddPssPersonCenteredness_HaveControlOfPersonalResources_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfLivingSettingActivitis_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfDayProgramSettingActivitis_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionGivenAChoiceOfServicesTextArea",
				"description 1");
		selenium.type("id=DescriptionGivenAChoiceOfProvidersTextArea",
				"description 2");
		selenium.type("id=DescriptionGivenAChoiceOfLivingSettingsTextArea",
				"description 3");
		selenium.type("id=DescriptionGivenAChoiceOfRoommatesTextArea",
				"description 4");
		selenium.type(
				"id=DescriptionGivenAChoiceOfDayProgramSettingActivitisTextArea",
				"description 5");
		selenium.click("id=IdddPssPersonCenteredness_HaveRestrictionsOrLimitations_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=btnNextSection");// "Save & Next" button
		selenium.waitForPageToLoad("30000");

		selenium.type("id=SupportPerson", "support person");// Absent
															// Contributors
															// section
		selenium.type("id=Relationship", "family");
		selenium.type("id=Date", TestUtils.getNowString());
		selenium.click("link=Add Support People");
		selenium.click("css=button[type=\"button\"]");// click "Next" button
		selenium.waitForPageToLoad("30000");

		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");// Signatures
																			// section
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SignatureToSign_Signature_SignedName", "mike");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "coordinator");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.runScript("$('td a:contains(\"Sign\"):eq(0)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "guardian");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.runScript("$('td a:contains(\"Sign\"):eq(1)').click()");
		selenium.type("id=SignatureToSign_Signature_SignedName", "provider1");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.runScript("$('td a:contains(\"Sign\"):eq(1)').click()");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SignatureToSign_Signature_SignedName", "provider2");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=expandCollapseAll");
		selenium.click("id=expandCollapseAll");
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.type("id=reason", "director clarify");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("//input[@value='Submit']");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.type("id=reason", "BIDD clarify");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("//input[@value='Submit']");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("//input[@value='Approve']");
		selenium.type("id=comment", "approve");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_Pss_PlanOfService_List");

	}

	@Test
	public void testPSS_edit() throws Exception {
		selenium.open(selenium.baseUrl);
		selenium.click("btnLogin");
		
		String provider1 = "provider _1";
		String provider2 = "provider _2";

		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");// search a client
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		selenium.runScript("$('span:contains(\"IDDDPSS\")').click()");
		selenium.runScript("window.location = $('#_leftnavlink_Pss_PlanOfService_List').attr('href')");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");// enter summary page of PSS
		selenium.click("link=Revise");//revise a new PSS
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Discard']");//Discard the new PSS
		selenium.click("id=reason");
		selenium.type("id=reason", "discard");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");		
		selenium.click("id=_Pss_PlanOfService_List");//Return to list page
		
		selenium.click("link=Revise");//revise a new PSS again
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'View')])[3]");//navigate to summary page
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Approve']");//approve the revised pss straightly
		selenium.click("id=comment");
		selenium.type("id=comment", "approve revised PSS");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Revise");//revise a new PSS again
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");		
		
		selenium.click("id=_IdddPlanOfService_Overview_Edit");// edit overview section
		selenium.waitForPageToLoad("30000");
		selenium.select("id=Overview_Type_Id", "label=Recertification");
		selenium.type("id=Overview_Narrative", "Comments edit");
		selenium.click("id=btnEditClientOverviewFormSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[2]/div/h4");
		selenium.click("id=_IdddPlanOfService_FamilyContact_Manage");
		selenium.runScript("$('#_listlink_IdddPlanOfService_FamilyContact_Delete').trigger('click')");// delete family contact
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnYes");

		selenium.click("id=_contentlink_IdddPlanOfService_FamilyContact_Create");// add family contact
		selenium.waitForPageToLoad("30000");
		selenium.type("id=FamilyContact_PersonName_FirstName", "Mary");
		selenium.type("id=FamilyContact_PersonName_LastName", "Test");
		selenium.type("id=FamilyContact_PersonName_MiddleName", "m");
		selenium.select("id=FamilyContact_ContactType_Id", "label=Family");
		selenium.select("id=RelationshipId", "label=Spouse");
		selenium.type("id=FamilyContact_Phone", "1234567890");
		selenium.type("id=FamilyContact_Fax", "1234567890");
		selenium.type("id=FamilyContact_Email", "xin.zhou@feisystems.com");
		selenium.type("id=FamilyContact_Address_AddressLine1", "Shangdi dong");
		selenium.type("id=FamilyContact_Address_AddressLine2", "Haidian");
		selenium.type("id=FamilyContact_Address_City", "Beijing");
		selenium.type("id=FamilyContact_Address_PostalCode", "12345");
		selenium.click("id=btnSaveCreatedClientContact");
		selenium.waitForPageToLoad("30000");

		selenium.clickByFuzzyLinkText("Edit*");// edit family contact
		selenium.type("id=FamilyContact_PersonName_LastName", "Test edit");
		selenium.select("id=FamilyContact_ContactType_Id", "label=Other");
		selenium.select("id=RelationshipId", "label=Daughter");
		selenium.type("id=FamilyContact_Fax", "0987654321");
		selenium.click("id=SaveEditClientContact");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");

		selenium.click("//div[@id='workspace-maincontent']/div/div[2]/div/h4");
		selenium.click("id=_IdddPlanOfService_EssentialInfoCompletedBy_Edit");// edit completed by section
		selenium.click("id=essentialInformationCompletedBySave");
		selenium.waitForPageToLoad("30000");

		selenium.click("//div[@id='workspace-maincontent']/div/div[3]/div/h4");// open ID/DD Support section
		selenium.click("link=Quick View");// click quick view
		selenium.click("id=serviceViewWindowClose");
		selenium.click("id=_IdddPlanOfService_SupportService_ManageServicePlan");// enter service list
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Quick View");// click quick view
		selenium.click("id=serviceViewWindowClose");
		selenium.click("link=Delete");// delete service "Hour"
		selenium.click("id=btnConfirmYes");

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
			selenium.runScript("$.unblockUI()");
			selenium.refresh();

		}

		String[][] editServices = selenium.getExcelData("TestPSS.xls",
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
		selenium.click("//div[@id='workspace-maincontent']/div/div[4]/div/h4");
		selenium.click("id=_IdddPlanOfService_SharedPlannings_List");
		selenium.click("id=_IdddPlanOfService_SharedPlannings_Delete");// delete current shared planning
		selenium.click("id=btnConfirmYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_SharedPlannings_Edit");// add shared planning
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SharedPlanning_DesiredOutcome", "desired outcome");
		selenium.clickCheckBoxByText(provider1);
		selenium.clickCheckBoxByText(provider2);

		String[][] sharePlanning = selenium.getExcelData("TestPSS.xls",
				"Services_SharePlanning");
		for (int i = 1; i < sharePlanning.length; i++) {
			selenium.autoInputByData(sharePlanning[0], sharePlanning[i]);
		}

		selenium.type("id=SharedPlanning_HowOftenOrByWhen", "day");
		selenium.type("id=sharedPlanning_StartDate", TestUtils.getNowString());
		selenium.type("id=sharedPlanning_EndDate", TestUtils.getEndDayString());
		selenium.click("id=btnEditSharedPlanningFormSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Edit");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=SharedPlanning_DesiredOutcome",
				"desired outcome edit");
		selenium.clickCheckBoxByText(provider1);
		selenium.type("id=SharedPlanning_HowOftenOrByWhen", "day edit");
		selenium.click("id=btnEditSharedPlanningFormSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[5]/div/h4/span[3]");
		selenium.click("id=_IdddPlanOfService_MedicalInfo_Manage");// manage
																	// medical
																	// infromation
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Quick View");
		selenium.runScript("$('a[role=\"button\"] span[class=\"k-icon k-i-close\"]').click()");
		selenium.click("link=Delete");// delete medical information
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Medical_Physician", "physician new");// add new
																// medical
																// information
		selenium.type("id=Medical_Specialty", "specialty new");
		selenium.type("id=Medical_Address", "address new");
		selenium.type("id=Medical_Phone", "9876543210");
		selenium.click("link=Add");
		selenium.click("link=Edit");// edit new medical information
		selenium.type("id=Medical_Physician", "physician new edit");
		selenium.type("id=Medical_Specialty", "specialty new edit");
		selenium.type("id=Medical_Address", "address new edit");
		selenium.type("id=Medical_Phone", "9876543211");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");// return
																		// summary
																		// page
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[5]/div/h4");
		selenium.click("id=_IdddPlanOfService_Medication_Manage");// manage
																	// medications
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_Medication_MedicationManage");// manage
																			// medication
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Quick View");
		selenium.runScript("$('a[role=\"button\"] span[class=\"k-icon k-i-close\"]').click()");
		selenium.click("link=Delete");// delete medication
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Medication_Medication", "medication new");// add new
																	// medication
		selenium.type("id=Medication_Physician", "physician new");
		selenium.type("id=Medication_Dosage", "dosage new");
		selenium.type("id=Medication_Frequency", "frequency new");
		selenium.type("id=Reason_prescribed", "reason new");
		selenium.click("link=Add");
		selenium.click("link=Edit");// edit new medication
		selenium.type("id=Medication_Medication", "medication new edit");
		selenium.type("id=Medication_Physician", "physician new  edit");
		selenium.type("id=Medication_Dosage", "dosage new edit");
		selenium.type("id=Medication_Frequency", "frequency new edit");
		selenium.type("id=Reason_prescribed", "reason new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_Medication_Manage");// return
																	// medications
		selenium.waitForPageToLoad("30000");
		selenium.type("id=MedicationInfo_Allergies", "allergies edit");// edit
																		// medications
																		// information
		selenium.type("id=MedicationInfo_Reactions", "reactions edit");
		selenium.click("id=MedicationInfo_MentalHealthSupportNeeds_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=MedicationInfo_ChronicHealthConditions_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=MedicationInfo_HistoryOfHealthProblemsIssues_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=MedicationInfo_CurrentLimitationsRestrictionsOnPhysicalActivities_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=MedicationInfo_Hospitalizations_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=MedicationInfo_AdmissionsToIcfIid_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=MedicationInfo_MedicalSupportNeeds",
				"medical support needs edit");
		selenium.click("id=medicationInfoSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_Communication_Edit");// edit
																	// communication
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Communication_MethodOfCommunication",
				"method of communication edit");
		selenium.type("id=Communication_SupportsNeededForCommunication",
				"support needed edit");
		selenium.type("id=Communication_AdaptiveEquipment",
				"adaptive equipment edit");
		selenium.type("id=Communication_HowIsEquipmentMaintaied",
				"once a week edit");
		selenium.type("id=Communication_BackupPlanForPowerOutage",
				"back-up plan edit");
		selenium.type("id=Communication_EnvironmentalModifications",
				"environmental modification edit");
		selenium.click("id=Communication_AnyReferrelsNeeded_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.click("id=communicationSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_RiskAssessment_Manage");// manage
																		// risk
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Delete");// delete risk
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Risk_Description", "risk new");// add a new resk
		selenium.type("id=Risk_Resolution", "resolution new");
		selenium.click("link=Add");
		selenium.click("link=Edit");
		selenium.type("id=Risk_Description", "risk new edit");
		selenium.type("id=Risk_Resolution", "resolution new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_BackupAndEmergencyPlan_Edit");// edit
																			// Back
																			// up
																			// and
																			// Emergency
																			// Plans
		selenium.type("id=PssBackupAndEmergencyPlan_ProviderNotShowUp",
				"provider does not show up edit");
		selenium.type("id=PssBackupAndEmergencyPlan_DayProgramOrWorkCanceled",
				"day program is canceled edit");
		selenium.type("id=PssBackupAndEmergencyPlan_NaturalDisasterOoccurs",
				"natural disaster occurs edit");
		selenium.type(
				"id=PssBackupAndEmergencyPlan_SomethingWereToHappenToThePrimaryCaregiver",
				"plan for future living arrangements edit");
		selenium.click("id=backupAndEmergencyPlanSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_LivingArrangements_Edit");// edit
																		// family
																		// and
																		// living
																		// arrangements
		selenium.waitForPageToLoad("30000");
		selenium.type("id=FamilyAndCurrentLivingArrangements",
				"family and current living arrangements edit");
		selenium.click("id=familyAndCurrentLivingArrangementsSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[10]/div/h4");// employment
																				// section
		selenium.click("id=_IdddPlanOfService_Education_Edit");// edit education
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Education_CurrentSchool", "current school edit");
		selenium.type("id=Education_LastSchoolAttended", "school attended edit");
		selenium.type("id=Education_TypeofDiplomaCertificate", "diploma edit");
		selenium.click("id=educationSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[10]/div/h4");
		selenium.click("css=#DataTables_Table_7 > tbody > tr.odd > td.. > a");// click
																				// quick
																				// view
																				// of
																				// empolyments
		selenium.runScript("$('a[role=\"button\"] span[class=\"k-icon k-i-close\"]').click()");
		selenium.click("id=_IdddPlanOfService_Employment_Manage");// manage
																	// employment
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Quick View");
		selenium.click("id=employmentViewWindowClose");
		//selenium.click("link=Close");
		selenium.click("link=Delete");// delete employment
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Employment_PlaceOfEmployment", "place new");// add new
																		// employment
		selenium.type("id=Employment_Duties", "duty new");
		selenium.type("id=Employment_BeginDate", TestUtils.getNowString());
		selenium.type("id=Employment_EndDate", TestUtils.getEndDayString());
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.click("id=tuesday");
		selenium.click("id=wednesday");
		selenium.click("id=thursday");
		selenium.click("id=friday");
		selenium.click("id=saturday");
		selenium.type("id=Employment_HoursWorked", "20");
		selenium.type("id=Employment_Reasonforleaving", "reason new");
		selenium.click("link=Add");
		selenium.click("link=Edit");// edit old employment
		selenium.type("id=Employment_PlaceOfEmployment", "place2 edit");
		selenium.type("id=Employment_Duties", "duty2 edit");
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[10]/div/h4");
		selenium.click("id=_IdddPlanOfService_VolunteerActivity_Manage");// manage
																			// volunteer
																			// activities
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Delete");// delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=VolunteerActivity_VolunteerActivity",
				"volunteer activity new");// add new
		selenium.type("id=VolunteerActivity_Duties", "duty new");
		selenium.click("id=sunday");
		selenium.click("id=monday");
		selenium.click("id=tuesday");
		selenium.click("id=wednesday");
		selenium.click("id=thursday");
		selenium.click("id=friday");
		selenium.click("id=saturday");
		selenium.type("id=VolunteerActivity_Hours", "30");
		selenium.click("link=Add");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_BehaviorSupports_Edit");// edit
																		// previous
																		// and
																		// current
																		// behavior
																		// support
		selenium.waitForPageToLoad("30000");
		selenium.type("id=PreviousAndCurrentBehaviorSupports",
				"Previous and Current Behavior Supports edit");
		selenium.click("id=previousAndCurrentBehaviorSupportsSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_SeriousIncidents_Edit");// edit
																		// serious
																		// incidents
																		// during
																		// the
																		// past
																		// year
		selenium.type("id=SeriousIncidentsDuringthePastYear",
				"Serious Incidents During the Past Year edit");
		selenium.click("id=seriousIncidentsSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_EvaluationInformation_Edit");// edit
																			// evaluation
		selenium.type("id=EvaluationInformation_ExaminerName", "name edit");
		selenium.type("id=EvaluationInformation_ExaminerAgency", "agency edit");
		selenium.type("id=EvaluationInformation_PrimaryDSMCode",
				"DSM code edit");
		selenium.type("id=EvaluationInformation_SecondaryDSMCode",
				"DSM code 2 edit");
		selenium.click("id=evaluationInformationSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[14]/div/h4");
		selenium.click("id=_IdddPlanOfService_PersonalProfile_Edit");// edit
																		// personal
																		// profile
		selenium.waitForPageToLoad("30000");
		selenium.type("id=PersonalProfile_IntroductionGreatThingsAbout",
				"great things edit");
		selenium.type("id=PersonalProfile_HopesAndDreams",
				"hopes and dreams edit");
		selenium.click("link=Delete");
		selenium.click("id=btnAddImportantTo");
		selenium.type("id=unique5", "important to new");
		selenium.click("xpath=(//a[contains(text(),'Delete')])[3]");
		selenium.click("id=btnAddImportantFor");
		selenium.type("id=unique6", "important for new");
		selenium.click("id=PersonalProfile_WorkingPersonsPerspective");
		selenium.click("id=PersonalProfile_WorkingFamilysPerspective");
		selenium.type(
				"id=PersonalProfile_WorkingProvidersPerspectiveDescription",
				"description 3 edit");
		selenium.type("id=PersonalProfile_WorkingOthersPerspectiveDescription",
				"description 4 edit");
		selenium.type(
				"id=PersonalProfile_NotWorkingPersonsPerspectiveDescription",
				"description 5 edit");
		selenium.type(
				"id=PersonalProfile_NotWorkingFamilysPerspectiveDescription",
				"description 6 edit");
		selenium.click("id=PersonalProfile_NotWorkingProvidersPerspective");
		selenium.click("id=PersonalProfile_NotWorkingOthersPerspective");
		selenium.type("id=PersonalProfile_ThingsPeopleNeedtoKnow",
				"keep healthy edit");
		selenium.type("id=PersonalProfile_Strengths", "strength edit");
		selenium.click("id=btnEditPersonalProfileFormSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='workspace-maincontent']/div/div[14]/div/h4");
		selenium.click("id=_IdddPlanOfService_PersonalProfile_QuestionManage");// manage
																				// question
																				// and
																				// thing
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Delete");// delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=Question", "question new");// add new
		selenium.type("id=PersonResponsible", "person responsible new");
		selenium.click("link=Save");
		selenium.click("link=Edit");// edit
		selenium.type("id=Question", "question new edit");
		selenium.type("id=PersonResponsible", "person responsible new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_PersonCenteredness_Edit");// edit
																		// person
																		// centeredness
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfServices_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionGivenAChoiceOfServicesTextArea",
				"description 1 edit");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfProviders_Id_lookupitems_idddpssyesnonaoptions_-1");
		selenium.type("id=DescriptionGivenAChoiceOfProvidersTextArea",
				"description 2  edit");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfLivingSettings_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfRoommates_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=IdddPssPersonCenteredness_HaveControlOfPersonalResources_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionHaveControlOfPersonalResourcesTextArea",
				"edit");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfLivingSettingActivitis_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type(
				"id=DescriptionGivenAChoiceOfLivingSettingActivitisTextArea",
				"edit");
		selenium.click("id=IdddPssPersonCenteredness_GivenAChoiceOfDayProgramSettingActivitis_Id_lookupitems_idddpssyesnonaoptions_-3");
		selenium.click("id=IdddPssPersonCenteredness_HaveRestrictionsOrLimitations_Id_lookupitems_idddpssyesnonaoptions_-2");
		selenium.type("id=DescriptionHaveRestrictionsOrLimitationsTextArea",
				"edit");
		selenium.click("id=personCenterednessSave");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_SupportPeople_Manage");// manage
																		// absent
																		// contributors
		selenium.click("link=Delete");// delete
		selenium.click("id=btnConfirmYes");
		selenium.type("id=SupportPerson", "support person new");// add new
		selenium.type("id=Relationship", "relationship new");
		selenium.type("Date", TestUtils.getNowString());
		selenium.click("link=Add Support People");
		selenium.click("link=Edit");// edit
		selenium.type("id=SupportPerson", "support person new edit");
		selenium.type("id=Relationship", "relationship new edit");
		selenium.click("link=Save Changes");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_Signatures_Manage");// manage
																	// signature
		selenium.click("link=Edit");// edit
		selenium.type("id=SignatureToSign_Signature_SignedName", "mike edit");
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'Retract')])[3]");// retract
		selenium.click("id=RetractSignatureBtn");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Sign");// sign
		selenium.type("id=SignatureToSign_Signature_SignedName",
				"coordinator new");
		selenium.type("id=SignatureToSign_Signature_SignedDate",
				TestUtils.getNowString());
		selenium.click("id=EditSignatureBtn");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IdddPlanOfService_PlanOfService_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=div.float-right > form > input[type=\"submit\"]");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Submit']");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Approve']");		
		selenium.click("id=comment");
		selenium.type("id=comment", "approve the revised PSS again");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_Pss_PlanOfService_List");
		
		selenium.stop();

	}

}
