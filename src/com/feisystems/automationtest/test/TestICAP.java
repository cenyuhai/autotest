package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.feisystems.automationtest.libary.AlertType;
import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

import org.junit.After;


public class TestICAP {
	
	static Logger logger = TestUtils.getLogger(TestPSS_Sandboxdev_NewUI.class);
	static SeleniumWrapper selenium = new SeleniumWrapper();
	final String clientName = "mike";

	public void clean() {
		RavenDbAPI.deleteCollectionByName("icapassessments");
		//RavenDbAPI.deleteDocumentByAttriute("clients", "ClientProfile.PersonName.FirstName", clientName);
	}
	
	
	public void create() throws Exception {
		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");		
		selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
		selenium.click("id=searchProfiles");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.click("//div[@id='lftNav']/div/ul/li[3]/span");
		selenium.click("id=_IcapAssessments_IcapAssessment_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=createIcapAssessment");
		selenium.waitForPageToLoad("30000");
		
		selenium.type("id=IcapAssessment_IcapClientInformation_ResidentialFacility", "residential facility");//Individual Information
		selenium.type("id=IcapAssessment_IcapClientInformation_SchoolDayProgram", "school program");
		selenium.select("id=IcapAssessment_IcapClientInformation_CountyDistrictResponsible", "label=Connecticut");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManager", "case manager");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManagerPhone", "1234567890");
		selenium.type("id=IcapAssessment_IcapClientInformation_Respondent", "respondent");
		selenium.type("id=IcapAssessment_IcapClientInformation_RespondentPhone", "9876543210");
		selenium.type("id=IcapAssessment_IcapClientInformation_Relationship", "relationship");
		selenium.type("id=IcapAssessment_IcapClientInformation_EvaluationReason", "reason");
		selenium.type("id=IcapAssessment_IcapClientInformation_ResidenceId", "residence id");
		selenium.type("id=IcapAssessment_IcapClientInformation_DayProgramId", "day program id");
		selenium.type("id=IcapAssessment_IcapClientInformation_DistrictId", "co id");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManagerId", "case manager id");
		selenium.type("id=IcapAssessment_IcapClientInformation_OtherId", "other id");
		selenium.type("id=IcapAssessment_IcapClientInformation_EvaluationDate", TestUtils.getNowString());
		selenium.click("//button[@onclick='SaveAndNext();']");
		
		selenium.verifyNotification(AlertType.RecordCreatedSuccessfully);////verify notification
		
		selenium.type("id=IcapAssessment_DescriptiveInformationSection_HeightFt", "5");//Descriptive information
		selenium.type("id=IcapAssessment_DescriptiveInformationSection_HeightIn", "6");
		selenium.type("id=IcapAssessment_DescriptiveInformationSection_WeightLbs", "80");
		selenium.select("id=IcapAssessment_DescriptiveInformationSection_PrimaryMeansOfExpression", "label=Speaks");
		selenium.select("id=IcapAssessment_DescriptiveInformationSection_LegalStatus", "label=Parent or relative is guardian or conservator");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.select("id=IcapAssessment_DiagnosticStatusSection_PrimaryDiagnosisId", "label=Autism");//Diagnostic status
		selenium.click("id=IcapAssessment.DiagnosticStatusSection.AdditionalDiagnosisIds_diagnosisconditions/-3");
		selenium.type("id=IcapAssessment_DiagnosticStatusSection_Comments", "comments");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div/fieldset/div/div[2]/label");//Functional limitations and needed assistance
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_MentalRetardationLevel_levelofmentalretardations_-2");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[2]/fieldset/div/div[2]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Vision_visions_-2");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[3]/fieldset/div/div[3]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Hearing_hearings_-3");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[4]/fieldset/div/div[2]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_SeizuresFrequency_frequencyofseizures_-2");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[5]/fieldset/div/div[2]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Health_healths_-2");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[6]/fieldset/div/div[5]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_RequiredCare_requiredcarebynurseorphysicians_-5");
		selenium.click("id=IcapAssessment.FunctionalLimitationsAndNeededAssistanceSection.CurrentMedications_currentmedications/-3");
		selenium.type("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_BehaviorExplanation", "mood explanation");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[8]/fieldset/div/div/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_ArmHand_armhands_-1");
		selenium.click("//div[@id='FunctionalLimitationsAndNeededAssistanceSection']/div/div/fieldset/div[9]/fieldset/div/div[4]/label");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Mobility_mobilitys_-4");
		selenium.click("id=IcapAssessment.FunctionalLimitationsAndNeededAssistanceSection.MobilityAssistance_mobilityassistanceneededs/-2");
		selenium.type("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_AssistiveDevicesExplanation", "assistive devices");
		selenium.type("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Comments", "comments");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_MobilityInBed_helpneededinbedtype_-2");//CSA Supplemental Questions section
		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_Transferring_helpneededbetweensurfacestype_-2");
		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_Bathing_helpneededtobathetype_-2");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ1_0");//Adaptive Behavior section
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ2_1");
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ3_2");
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ4_3");
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ5_0");
		selenium.click("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ6_1");
		
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ7_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ8_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ9_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ10_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ11_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ12_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ13_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ14_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ15_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ16_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ17_3");;
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ18_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ1_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ2_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ3_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ4_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ5_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ6_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ7_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ8_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ9_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ10_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ11_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ12_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ13_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ14_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ15_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ16_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ17_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ18_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ19_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ1_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ2_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ3_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ4_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ5_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ6_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ7_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ8_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ9_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ10_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ11_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ12_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ13_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ14_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ15_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ16_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ17_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ18_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ19_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ20_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ21_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ1_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ2_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ3_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ4_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ5_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ6_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ7_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ8_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ9_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ10_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ11_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ12_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ13_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ14_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ15_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ16_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ17_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ18_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ19_2");
		
		
		selenium.click("//button[@onclick='SaveAndNext();']");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfNo");//Problem Behavior 
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfProblem_Comments", "comments 1");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersProblem_PrimaryProblemDesc", "describe 1");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[5]/fieldset/div/div[2]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersProblem_Frequency_behaviorfrequencytypes_-2");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[6]/fieldset/div/div/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersProblem_Severity_problemseveritytypes_-1");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersProblem_Comments", "comments 2");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyProblem_PrimaryProblemDesc", "describe 2");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[10]/fieldset/div/div[3]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyProblem_Frequency_behaviorfrequencytypes_-3");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[11]/fieldset/div/div[3]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyProblem_Severity_problemseveritytypes_-3");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyProblem_Comments", "comments 3");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorNo");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorProblem_Comments", "comments 4");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_PrimaryProblemDesc", "describe 3");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[20]/fieldset/div/div[3]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Frequency_behaviorfrequencytypes_-3");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[21]/fieldset/div/div[4]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Severity_problemseveritytypes_-4");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Comments", "comments 5");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorNo");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorProblem_Comments", "comments 6");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_WithdrawalBehaviorNo");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_WithdrawalBehaviorProblem_Comments", "comments 7");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorProblem_PrimaryProblemDesc", "describe 4");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[35]/fieldset/div/div[5]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorProblem_Frequency_behaviorfrequencytypes_-5");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[36]/fieldset/div/div[5]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorProblem_Severity_problemseveritytypes_-5");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorProblem_Comments", "comments 8");
		selenium.click("//div[@id='ProblemBehaviorSection']/div[2]/div/fieldset/div[38]/fieldset/div[5]/label");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_ProblemBehaviorsResponse_responsetoproblembehaviors/-5");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_ProblemBehaviorsComments", "comments 9");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment_ResidentialPlacementSection_CurrentResidence_residentialplacements/-6");//Residential Placement
		selenium.click("id=IcapAssessment_ResidentialPlacementSection_RecommendedResidence_residentialplacements/-8");
		selenium.type("id=IcapAssessment_ResidentialPlacementSection_RecommendedResidenceNumberOfResidence", "5");
		selenium.type("id=IcapAssessment_ResidentialPlacementSection_ResidenceComments", "comdent");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment_DaytimeProgramSection_CurrentDaytimeProgram_daytimeprograms/-3");//Daytime Program
		selenium.click("id=IcapAssessment_DaytimeProgramSection_RecommendedDaytimeProgram_daytimeprograms/-7");
		selenium.type("id=IcapAssessment_DaytimeProgramSection_DaytimeProgramComments", "comment");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment.SupportServiceSection.CurrentSupportService.SupportServiceCodes_supportservices/-6");//Support Services		
		selenium.type("id=IcapAssessment_SupportServiceSection_CurrentSupportService_NursingCareComments", "nursing care");
		selenium.click("id=IcapAssessment.SupportServiceSection.NeededSupportService.SupportServiceCodes_supportservices/-7");		
		selenium.type("id=IcapAssessment_SupportServiceSection_NeededSupportService_MentalHealthServicesComments", "mental health service");
		selenium.type("id=IcapAssessment_SupportServiceSection_SupportServiceComments", "comment");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=IcapAssessment.SocialLeisureActivitiesSection.Present_socialandleisureactivities/-5");//Social and leisure activities
		selenium.click("id=IcapAssessment.SocialLeisureActivitiesSection.FL_socialandleisureactivities/-6");
		selenium.type("id=IcapAssessment_SocialLeisureActivitiesSection_ActivitiesComments", "comments");
		selenium.click("id=IcapAssessment_SocialLeisureActivitiesSection_AccurateRepresentationNo");
		selenium.type("id=IcapAssessment_SocialLeisureActivitiesSection_NotRepresentationReason", "reason for questioning result");
		selenium.click("//button[@onclick='SaveAndNext();']");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ProgramDecisionInformation", "functional limitation");//General information and recommendation
		selenium.click("id=btnAddOtherSource");
		selenium.type("id=unique1", "test1");
		selenium.type("id=unique2", TestUtils.getNowString());
		selenium.type("id=unique3", "8");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_AdditionalProgramDecisionInformation", "additional infromation");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_MotorSkills", "motor skills");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SocialSkills", "social and communication skill");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_PersonalLivingSkills", "personal living skill");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_CommunityLivingSkills", "community living skill");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ProblemBehaviorDesc", "problem behavior");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_PhysicalCare", "physical medical therapeutic care");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ResidentialServices", "residential");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_DaytimeSocialActivities", "daytime");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_EducationalServices", "educational");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SupportServices", "support");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SocialActivities", "social");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_OtherRecommendations", "other");
		selenium.click("css=div.float-right > button");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);//verify notification
		
		selenium.click("id=_IcapAssessments_IcapAssessment_I_Details");//click previous button ordinally
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_H_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_G_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_F_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_E_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_D_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_CsaSupplementalQuestions_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_C_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_B_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_A_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_ClientInfo_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_A_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_B_Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_C_Details");
		
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_CsaSupplementalQuestions_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_D_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_E_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_F_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_G_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_H_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_I_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_J_Details");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("css=div.float-left > #_IcapAssessments_IcapAssessment_Summary");
		selenium.waitForPageToLoad("30000");
		
		selenium.printVerfifyInfo();
		
	}

	
	public void offline_edit() throws Exception {
		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");		
		selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
		selenium.click("id=searchProfiles");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.click("//div[@id='lftNav']/div/ul/li[3]/span");
		selenium.click("id=_IcapAssessments_IcapAssessment_List");
		selenium.click("id=downIcapAssessmentLink");//click download button
		
		selenium.verifyNotification("Success: ICAP Assessment successfully downloaded");//verify notification
		
		selenium.click("css=a[title=\"IcapAssessmentId Summary\"]");//click summary button
		
		TestUtils.showMessage("Please stop IIS");
		
		selenium.click("link=Edit");//edit individual information
		selenium.waitForPageToLoad("30000");
		selenium.type("id=IcapAssessment_IcapClientInformation_ResidentialFacility", "residential facility edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_SchoolDayProgram", "school program edit");
		selenium.select("id=IcapAssessment_IcapClientInformation_CountyDistrictResponsible", "label=Alabama");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManager", "case manager edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManagerPhone", "1234567891");
		selenium.type("id=IcapAssessment_IcapClientInformation_Respondent", "respondent edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_RespondentPhone", "9876543211");
		selenium.type("id=IcapAssessment_IcapClientInformation_Relationship", "relationship edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_EvaluationReason", "reason edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_ResidenceId", "residence id edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_DayProgramId", "day program id edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_DistrictId", "co id edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_CaseManagerId", "case manager id edit");
		selenium.type("id=IcapAssessment_IcapClientInformation_OtherId", "other id edit");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.type("id=IcapAssessment_DescriptiveInformationSection_HeightFt", "6");//edit descriptive information
		selenium.type("id=IcapAssessment_DescriptiveInformationSection_HeightIn", "5");
		selenium.type("id=IcapAssessment_DescriptiveInformationSection_WeightLbs", "75");
		selenium.select("id=IcapAssessment_DescriptiveInformationSection_PrimaryMeansOfExpression", "label=Sign Language or finger spelling");
		selenium.select("id=IcapAssessment_DescriptiveInformationSection_LegalStatus", "label=Non-relative is guardian or conservator");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment.DiagnosticStatusSection.AdditionalDiagnosisIds_diagnosisconditions/-4");//edit diagnostic status
		selenium.type("id=IcapAssessment_DiagnosticStatusSection_Comments", "comments eidt");
		selenium.select("id=IcapAssessment_DiagnosticStatusSection_PrimaryDiagnosisId", "label=Cerebral palsy");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");
		
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_MentalRetardationLevel_levelofmentalretardations_-3");//edit functional limitations and needed assistance
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Vision_visions_-3");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Hearing_hearings_-2");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_SeizuresFrequency_frequencyofseizures_-3");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_Health_healths_-3");
		selenium.click("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_RequiredCare_requiredcarebynurseorphysicians_-3");
		selenium.clickCheckBoxByText("For health problem");
		selenium.type("id=IcapAssessment_FunctionalLimitationsAndNeededAssistanceSection_HealthExplanation", "health problem");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_MobilityInBed_helpneededinbedtype_-3");//edit CSA supplemental questions
		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_Transferring_helpneededbetweensurfacestype_-3");
		selenium.click("id=IcapAssessment_CsaSupplementalQuestionsSection_Bathing_helpneededtobathetype_-3");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");
		
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ7_3");//edit adaptive behavior
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ8_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_MotorSkillsQ9_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ1_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ2_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_SocialSkillsQ3_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ1_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ2_1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_PersonalSkillsQ3_0");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ1_2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ2_3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_AdaptiveBehaviorSection_CommunitySkillsQ3_2");		
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfYes");//edit problem behavior
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfProblem_PrimaryProblemDesc", "discribe new 1");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfProblem_Frequency_behaviorfrequencytypes_-3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfProblem_Severity_problemseveritytypes_-2");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToSelfProblem_Comments", "comments 1 edit");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_HurtfulToOthersNo");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DestructiveToPropertyNo");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorProblem_PrimaryProblemDesc", "discribe new 2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorProblem_Frequency_behaviorfrequencytypes_-3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_DisruptiveBehaviorProblem_Severity_problemseveritytypes_-4");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Frequency_behaviorfrequencytypes_-5");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Severity_problemseveritytypes_-2");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_UnusualHabitsProblem_Comments", "comments 5 edit");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorYes");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorProblem_PrimaryProblemDesc", "discribe new 3");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorProblem_Frequency_behaviorfrequencytypes_-2");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorProblem_Severity_problemseveritytypes_-4");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_SociallyOffensiveBehaviorProblem_Comments", "comments 6 edit");
		selenium.click("id=IcapAssessment_ProblemBehaviorsSection_UncooperativeBehaviorNo");
		selenium.clickKnockOutCheckbox("id=IcapAssessment_ProblemBehaviorsSection_ProblemBehaviorsResponse_responsetoproblembehaviors/-8");
		selenium.type("id=IcapAssessment_ProblemBehaviorsSection_ProblemBehaviorsComments", "comments 9 edit");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment_ResidentialPlacementSection_CurrentResidence_residentialplacements/-7");//edit residential placement
		selenium.click("id=IcapAssessment_ResidentialPlacementSection_RecommendedResidence_residentialplacements/-5");
		selenium.type("id=IcapAssessment_ResidentialPlacementSection_ResidenceComments", "comdent edit");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment_DaytimeProgramSection_CurrentDaytimeProgram_daytimeprograms/-8");//edit daytime program
		selenium.click("id=IcapAssessment_DaytimeProgramSection_RecommendedDaytimeProgram_daytimeprograms/-6");
		selenium.type("id=IcapAssessment_DaytimeProgramSection_DaytimeProgramComments", "comment edit");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment.SupportServiceSection.CurrentSupportService.SupportServiceCodes_supportservices/-3");//edit support services
		selenium.type("id=IcapAssessment_SupportServiceSection_CurrentSupportService_HomeBasedSupportServiceComments", "support service");
		selenium.click("id=IcapAssessment.SupportServiceSection.NeededSupportService.SupportServiceCodes_supportservices/-6");
		selenium.type("id=IcapAssessment_SupportServiceSection_NeededSupportService_NursingCareComments", "nursing care 2");
		selenium.type("id=IcapAssessment_SupportServiceSection_SupportServiceComments", "comment edit");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.click("id=IcapAssessment.SocialLeisureActivitiesSection.Present_socialandleisureactivities/-8");//edit social and leisure activities
		selenium.type("id=IcapAssessment_SocialLeisureActivitiesSection_ActivitiesComments", "comments edit");
		selenium.click("id=IcapAssessment.SocialLeisureActivitiesSection.FL_socialandleisureactivities/-4");
		selenium.click("id=IcapAssessment_SocialLeisureActivitiesSection_AccurateRepresentationYes");
		selenium.click("//button[@onclick='SaveToLocalStorageAndNext();']");

		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ProgramDecisionInformation", "functional limitation edit");//edit general information and recommendations
		selenium.click("link=Delete");
		selenium.click("id=btnAddOtherSource");
		selenium.type("id=unique4", "test new");
		selenium.type("id=unique5", TestUtils.getNowString());
		selenium.type("id=unique6", "20");
		selenium.selectWindow("null");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_AdditionalProgramDecisionInformation", "additional infromation edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_MotorSkills", "motor skills edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SocialSkills", "social and communication skill edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_PersonalLivingSkills", "personal living skill edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_CommunityLivingSkills", "community living skill edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ProblemBehaviorDesc", "problem behavior edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_PhysicalCare", "physical medical therapeutic care edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_ResidentialServices", "residential edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_DaytimeSocialActivities", "daytime edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_EducationalServices", "educational edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SupportServices", "support edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_SocialActivities", "social edit");
		selenium.type("id=IcapAssessment_GeneralInfoAndRecommendationsSection_OtherRecommendations", "other edit");
		selenium.click("css=div.float-right > button");

		selenium.click("id=_IcapAssessments_IcapAssessment_I_DetailsOffline");//click previous button ordinally
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_H_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_G_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_F_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_E_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_D_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_CsaSupplementalQuestions_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_C_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_B_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_A_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_IcapAssessments_IcapAssessment_ClientInfo_DetailsOffline");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.float-left > #_IcapAssessments_IcapAssessment_OfflineSummary");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Backtolink");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=chkSelectAll");
		
		TestUtils.showMessage("Please start IIS");//Start IIS
		
		selenium.click("id=btnUpload");//upload icap
		selenium.verifyNotification("Success: Assessment uploaded for mike test");//verify notification
		selenium.click("css=span.leftNavHeader");
		
		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");		
		selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
		selenium.click("id=searchProfiles");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.click("//div[@id='lftNav']/div/ul/li[3]/span");
		selenium.click("id=_IcapAssessments_IcapAssessment_List");
		
		selenium.click("id=_IcapAssessments_IcapAssessment_Summary");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Hold");//hold the icap
		selenium.type("id=reason", "hold reason");
		selenium.type("id=timestamp", TestUtils.getNowString());
		selenium.click("css=#formOnHold > div.fancybox-dialog-controls > #btnYes");
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification("Success: ICAP Assessment has been on hold");//verify notification
		
		selenium.click("id=Resume");//resume
		selenium.waitForPageToLoad("30000");
		
		selenium.verifyNotification(AlertType.RecordResumed);//verify notification
		
		selenium.click("id=Submit");//submit the icap		
		selenium.click("css=div.float-right > li > form > input[type=\"submit\"]");
		
		selenium.verifyNotification("Success: ICAP Assessment has been submitted");//verify notification
		
		selenium.type("id=comment", "discard");//discard
		selenium.click("css=#formWorkflowAction > div.fancybox-dialog-controls > #btnYes");
		
		selenium.verifyNotification(AlertType.RecordDiscarded);//verify notification
		
		selenium.printVerfifyInfo();
		
	}
	@Test
	public void testICAP() throws Exception {
		selenium.open(selenium.baseUrl);
		selenium.click("btnLogin");
		//clean();
		//No09_TestIndividual.createIndividual(selenium, clientName);
		//No09_TestIndividual.addRepresentativeForIndividual(selenium); 
		
		create();
		offline_edit();		
		
		
		
	}

	
	
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
