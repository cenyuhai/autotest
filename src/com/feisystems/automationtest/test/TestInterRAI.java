package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

import com.feisystems.automationtest.libary.RavenDbAPI;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestInterRAI {

	static Logger logger = null;
	static SeleniumWrapper selenium = null;
	final static String clientName = "mike";

	public TestInterRAI() {
		logger = TestUtils.getLogger(TestInterRAI.class);
		selenium = new SeleniumWrapper();
	}
	
	public void clean() {
		RavenDbAPI.deleteCollectionByName("clients");
		RavenDbAPI.deleteCollectionByName("mississippistandardizedassessments");
	}
	
	public static void createInterRAI(SeleniumWrapper selenium) {
		selenium.waitForPageToLoad("30000");
		selenium.open(selenium.baseUrl + "Clients/client/ClientSearch/");
		//selenium.click("id=_topnavlink_Clients_Client_ClientSearch");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", clientName);
		selenium.click("id=searchProfiles");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[1]");
		selenium.waitForPageToLoad("30000");
		selenium.click("//span[contains(text(),'Screening and Assessment')]");
		selenium.click("id=_Assessments_Assessment_List");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=_Assessments_Assessment_Create");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
		//part A
		selenium.click("link=Start");
		selenium.waitForPageToLoad("30000");

		selenium.click("id=MaritalStatus_2");
		selenium.click("id=paymentSourceNo0");
		selenium.click("id=paymentSourceNo1");
		selenium.click("id=paymentSourceYes2");
		selenium.click("id=paymentSourceYes3");
		selenium.click("id=paymentSourceNo4");
		selenium.click("id=paymentSourceYes5");
		selenium.clickCheckBoxByText("1 - First assessment");
		selenium.type("id=FullTranscript", "Full Transcript:");
		selenium.clickCheckBoxByText("3 - Board and care");
		selenium.click("id=LivingArrangement_4");
		selenium.click("id=livesWithSomeoneNewYes");
		selenium.click("id=LivingArrangementLiveElsewhere_1");
		selenium.clickCheckBoxByText("0 - No hospitalization within 90 days");
		selenium.click("id=btnNextSection");
		
		selenium.click("id=Ethinicity_1");
		selenium.click("id=Race_b_0");
		selenium.click("id=Race_c_0");
		selenium.click("id=Race_d_1");
		selenium.click("id=Race_e_1");
		selenium.click("id=Race_f_1");
		selenium.click("id=PrimaryLanguage_1");
		selenium.click("id=ResidentialHistory_a_0");
		selenium.click("id=ResidentialHistory_b_0");
		selenium.click("id=ResidentialHistory_c_1");
		selenium.click("id=ResidentialHistory_d_1");
		selenium.click("id=ResidentialHistory_e_1");
		selenium.click("id=ResidentialHistory_f_1");
		selenium.click("id=Employment_a_0");
		selenium.click("id=Employment_b_0");
		selenium.click("id=Employment_c_1");
		selenium.click("id=EmploymentPart2_a_0");
		selenium.click("id=EmploymentPart2_b_0");
		selenium.click("id=EmploymentPart2_c_1");
		selenium.click("id=IB6Education_2");
		selenium.click("id=IB6Education_4");
		selenium.click("id=btnNextSection");
		selenium.click("id=Q1_2");
		selenium.click("id=RepetitionThreeWords_1");
		selenium.clickKnockOutCheckbox("AbleReportCorrectYear_1");
		selenium.click("id=AbleReportCorrectMonth_1");
		selenium.click("id=AbleReportCorrectWeek_1");
		selenium.click("id=AbleRecallSock_1");
		selenium.click("id=AbleRecallBlue_1");
		selenium.click("id=AbleRecallBed_1");
		selenium.click("id=Q2_a_0");
		selenium.click("id=Q2_b_1");
		selenium.click("id=Q2_c_0");
		selenium.click("id=Q3_0_1");
		selenium.click("id=Q3_1_1");
		selenium.click("id=Q3_2_1");
		selenium.clickKnockOutCheckbox("Q4_1");
		selenium.clickKnockOutCheckbox("Q5_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=MakingSelfUnderstood_2");
		selenium.click("id=AbilityToUnderstandOthers_1");
		selenium.click("id=Hearing_1");
		selenium.clickKnockOutCheckbox("Vision_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=IndicatorsMood_a_1");
		selenium.click("id=IndicatorsMood_b_1");
		selenium.click("id=IndicatorsMood_c_1");
		selenium.click("id=IndicatorsMood_d_1");
		selenium.click("id=IndicatorsMood_e_1");
		selenium.click("id=IndicatorsMood_f_1");
		selenium.click("id=IndicatorsMood_g_2");
		selenium.click("id=IndicatorsMood_h_1");
		selenium.click("id=IndicatorsMood_i_1");
		selenium.click("id=IndicatorsMood_j_1");
		selenium.click("id=IndicatorsMood_k_1");
		selenium.click("id=SelfReprotedMood_a_0");
		selenium.click("id=SelfReprotedMood_b_2");
		selenium.click("id=SelfReprotedMood_c_1");
		selenium.click("id=BehaviorSymptoms_a_1");
		selenium.click("id=IE3aIntensityIntervention_1");
		selenium.click("id=BehaviorSymptoms_b_2");
		selenium.click("id=IE3bIntensityIntervention_1");
		selenium.click("id=BehaviorSymptoms_c_1");
		selenium.click("id=IE3cIntensityIntervention_0");
		selenium.click("id=BehaviorSymptoms_d_0");
		selenium.click("id=BehaviorSymptoms_e_0");
		selenium.click("id=BehaviorSymptoms_f_0");
		selenium.click("id=BehaviorSymptoms_g_0");
		selenium.click("id=btnNextSection");
		selenium.click("id=SocialRelationships_a_0");
		selenium.click("id=SocialRelationships_b_1");
		selenium.click("id=SocialRelationships_c_1");
		selenium.clickKnockOutCheckbox("SocialRelationships_d_1");
		selenium.click("id=SocialRelationships_e_1");
		selenium.click("id=SocialRelationships_f_1");
		selenium.clickKnockOutCheckbox("Lonely_1");
		selenium.clickKnockOutCheckbox("ChangeInSocialActivities_1");
		selenium.clickKnockOutCheckbox("LengthOfTimeAlone_1");
		selenium.clickKnockOutCheckbox("MajorLifeStressing_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=IadlSelfPerformance_a_Performance_2");
		selenium.click("id=IadlSelfPerformance_a_Capacity_2");
		selenium.click("id=IadlSelfPerformance_b_Performance_1");
		selenium.click("id=IadlSelfPerformance_b_Capacity_1");
		selenium.click("id=IadlSelfPerformance_c_Performance_2");
		selenium.click("id=IadlSelfPerformance_c_Capacity_1");
		selenium.click("id=IadlSelfPerformance_d_Performance_2");
		selenium.click("id=IadlSelfPerformance_d_Capacity_2");
		selenium.click("id=IadlSelfPerformance_e_Performance_1");
		selenium.click("id=IadlSelfPerformance_e_Capacity_1");
		selenium.click("id=IadlSelfPerformance_f_Performance_3");
		selenium.click("id=IadlSelfPerformance_f_Capacity_3");
		selenium.click("id=IadlSelfPerformance_g_Performance_2");
		selenium.click("id=IadlSelfPerformance_g_Capacity_2");
		selenium.click("id=IadlSelfPerformance_h_Performance_5");
		selenium.click("id=IadlSelfPerformance_h_Capacity_4");
		selenium.click("id=AdlSelfPerformance_a_3");
		selenium.click("id=AdlSelfPerformance_b_2");
		selenium.click("id=AdlSelfPerformance_c_2");
		selenium.click("id=AdlSelfPerformance_d_1");
		selenium.click("id=AdlSelfPerformance_e_0");
		selenium.click("id=AdlSelfPerformance_f_1");
		selenium.click("id=AdlSelfPerformance_g_0");
		selenium.click("id=AdlSelfPerformance_h_0");
		selenium.click("id=AdlSelfPerformance_i_0");
		selenium.click("id=AdlSelfPerformance_j_1");
		selenium.click("id=Q3_a_0");
		selenium.click("id=Q3_b_0");
		selenium.click("id=Q3_b_30");
		selenium.click("id=Q3_c_1");
		selenium.click("id=Q3_d_1");
		selenium.click("id=Q4_a_2");
		selenium.click("id=Q4_b_1");
		selenium.click("id=Q5_a_1");
		selenium.click("id=Q5_b_1");
		selenium.clickKnockOutCheckbox("Q6_1");
		selenium.clickKnockOutCheckbox("Q7_b_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Choking_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Amputation_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_DecEndurance_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_MotorImpair_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Fractures_0");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_LackAssistiveDevices_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_LimitedMotion_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_MuscleTone_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_NeuroImpairment_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Obesity_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_PhysioDefect_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Weakness_1");
		selenium.click("id=Assessment.MarylandAssessment.IGUnderlyingAdlIadlCauses_Apathy_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=BladderContinence_2");
		selenium.click("id=UrinaryCollectionDevice_1");
		selenium.click("id=BowelContinence_0");
		selenium.clickKnockOutCheckbox("PadsOrBriefsWorn_0");
		selenium.click("id=btnNextSection");
		selenium.click("id=Musculoskeletal_a_0");
		selenium.click("id=Musculoskeletal_b_0");
		selenium.click("id=xxc_0");
		selenium.click("id=xxd_1");
		selenium.click("id=xxe_1");
		selenium.click("id=xxf_2");
		selenium.click("id=xxg_2");
		selenium.click("id=xxh_2");
		selenium.click("id=xxi_2");
		selenium.click("id=xxj_2");
		selenium.click("id=CardiacOrPulmonary_k_1");
		selenium.click("id=CardiacOrPulmonary_l_1");
		selenium.click("id=CardiacOrPulmonary_m_1");
		selenium.click("id=Psychiatric_n_2");
		selenium.click("id=Psychiatric_p_1");
		selenium.click("id=Psychiatric_q_2");
		selenium.click("id=Infections_r_1");
		selenium.click("id=Infections_s_1");
		selenium.click("id=Other_t_1");
		selenium.click("id=Other_u_1");
		selenium.click("id=Other_v_2");
		selenium.click("id=Other_w_2");
		selenium.click("id=Other_x_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=SectionJ_Falls_1");
		selenium.click("id=SectionJ_RecentFalls_1");
		selenium.click("id=SectionJ_Balance_Q3_A_1");
		selenium.click("id=SectionJ_Balance_Q3_B_1");
		selenium.click("id=SectionJ_Balance_Q3_C_0");
		selenium.click("id=SectionJ_Balance_Q3_D_0");
		selenium.click("id=SectionJ_CardiacOrPulmonary_Q3_E_0");
		selenium.click("id=SectionJ_CardiacOrPulmonary_Q3_F_0");
		selenium.click("id=SectionJ_Psychiatric_Q3_G_1");
		selenium.click("id=SectionJ_Psychiatric_Q3_H_1");
		selenium.click("id=SectionJ_Psychiatric_Q3_I_1");
		selenium.click("id=SectionJ_Neurological_Q3_J_1");
		selenium.click("id=SectionJ_GIStatus_Q3_K_0");
		selenium.click("id=SectionJ_GIStatus_Q3_L_1");
		selenium.click("id=SectionJ_GIStatus_Q3_M_1");
		selenium.click("id=SectionJ_GIStatus_Q3_N_4");
		selenium.click("id=SectionJ_SleepProblems_Q3_O_1");
		selenium.click("id=SectionJ_SleepProblems_Q3_P_1");
		selenium.click("id=SectionJ_Other_Q3_Q_0");
		selenium.click("id=SectionJ_Other_Q3_R_0");
		selenium.click("id=SectionJ_Other_Q3_S_1");
		selenium.click("id=SectionJ_Other_Q3_T_1");
		selenium.click("id=SectionJ_Other_Q3_U_1");
		selenium.clickKnockOutCheckbox("SectionJ_Dyspena_1");
		selenium.clickKnockOutCheckbox("SectionJ_Fatigue_1");
		selenium.click("id=SectionJ_PainSymptoms_Q6_A_1");
		selenium.click("id=SectionJ_PainSymptoms_Q6_B_1");
		selenium.click("id=SectionJ_PainSymptoms_Q6_C_1");
		selenium.click("id=BreakthrouthPain_1");
		selenium.click("id=SectionJ_PainSymptoms_Q6_E_1");
		selenium.click("id=SectionJ_Q7_A_1");
		selenium.click("id=SectionJ_Q7_B_1");
		selenium.click("id=SectionJ_Q7_C_1");
		selenium.clickKnockOutCheckbox("SectionJ_SelfReprotedHealth_1");
		selenium.click("id=SectionJ_Q9_A_1");
		selenium.click("id=SectionJ_Q9_B_1");
		selenium.click("id=substanceAbuseNo");
		selenium.click("id=btnNextSection");
		selenium.type("id=heightFeet", "20");
		selenium.type("id=Weight", "80");
		selenium.click("id=NutritionalIssues_a_1");
		selenium.click("id=NutritionalIssues_b_1");
		selenium.click("id=NutritionalIssues_c_1");
		selenium.click("id=NutritionalIssues_d_1");
		selenium.clickKnockOutCheckbox("ModeOfNutritionalIntake_0");
		selenium.click("id=DentalOral_a_0");
		selenium.click("id=DentalOral_b_0");
		selenium.click("id=DentalOral_c_1");
		selenium.click("id=DentalOral_d_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=MostSeverePressureUlcer_1");
		selenium.click("id=PriorPressureUlcer_1");
		selenium.click("id=PresenceOfSkinUlcer_1");
		selenium.click("id=MajorSkinProblems_0");
		selenium.clickKnockOutCheckbox("SkinTearsOrCuts_1");
		selenium.clickKnockOutCheckbox("OtherSkinConditions_1");
		selenium.clickKnockOutCheckbox("FootProblems_0");
		selenium.click("id=btnNextSection");
		selenium.click("id=Q2_1");
		selenium.click("id=Q3_1");
		selenium.click("id=SectionM_Q6_Select_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=Prevention_Q1_A_0");
		selenium.click("id=Prevention_Q1_B_0");
		selenium.click("id=Prevention_Q1_C_0");
		selenium.click("id=Prevention_Q1_D_0");
		selenium.click("id=Prevention_Q1_E_1");
		selenium.click("id=Prevention_Q1_F_1");
		selenium.click("id=Prevention_Q1_G_1");
		selenium.click("id=Prevention_Q1_H_1");
		selenium.click("id=Treatment_Q2_A_1");
		selenium.click("id=Treatment_Q2_B_1");
		selenium.click("id=Treatment_Q2_C_1");
		selenium.click("id=Treatment_Q2_D_1");
		selenium.click("id=Treatment_Q2_E_1");
		selenium.click("id=Treatment_Q2_F_1");
		selenium.click("id=Treatment_Q2_G_1");
		selenium.click("id=Treatment_Q2_H_1");
		selenium.click("id=Treatment_Q2_I_1");
		selenium.click("id=Treatment_Q2_J_1");
		selenium.click("id=Treatment_Q2_K_1");
		selenium.click("id=Programs_Q2_L_1");
		selenium.click("id=Programs_Q2_M_1");
		selenium.click("id=Programs_Q2_N_1");
		selenium.type("id=FormalCare_Q3_A_Days", "1");
		selenium.type("id=FormalCare_Q3_A_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_B_Days", "1");
		selenium.type("id=FormalCare_Q3_B_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_C_Days", "1");
		selenium.type("id=FormalCare_Q3_D_Days", "1");
		selenium.type("id=FormalCare_Q3_D_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_E_Days", "1");
		selenium.type("id=FormalCare_Q3_E_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_F_Days", "1");
		selenium.type("id=FormalCare_Q3_F_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_G_Days", "1");
		selenium.type("id=FormalCare_Q3_G_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_H_Days", "1");
		selenium.type("id=FormalCare_Q3_H_TotalMins", "1");
		selenium.type("id=FormalCare_Q3_i_Days", "1");
		selenium.type("id=FormalCare_Q3_i_TotalMins", "1");
		selenium.type("id=SectionN_Q4_A", "2");
		selenium.type("id=SectionN_Q4_B", "2");
		selenium.type("id=SectionN_Q4_C", "2");
		selenium.clickKnockOutCheckbox("SectionN_Q5_1");
		selenium.clickKnockOutCheckbox("SectionN_Q6_Select_1");
		selenium.type("id=SectionN_Q6YesComment", "If yes, please explain:This field is required to submit.");
		selenium.type("id=FormalCare_Q3_C_TotalMins", "1");
		selenium.click("id=btnNextSection");
		selenium.click("id=btnNextSection");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=RelationshipToPerson_Helper1_3");
		selenium.click("id=RelationshipToPerson_Helper2_3");
		selenium.click("id=LivesWithPerson_Helper1_1");
		selenium.click("id=LivesWithPerson_Helper2_1");
		selenium.click("id=IADLHelp_Helper1_1");
		selenium.click("id=IADLHelp_Helper2_1");
		selenium.click("id=ADLHelp_Helper1_1");
		selenium.click("id=ADLHelp_Helper2_8");
		selenium.click("id=InformalHelperStatus_a_0");
		selenium.click("id=InformalHelperStatus_b_0");
		selenium.click("id=InformalHelperStatus_c_1");
		selenium.type("id=HoursOfInformalCare", "5");
		selenium.clickKnockOutCheckbox("StrongAndSupportiveRelationship_0");
		selenium.click("id=btnNextSection");
		selenium.click("id=HomeEnvironment_Q1_A_1");
		selenium.click("id=HomeEnvironment_Q1_B_0");
		selenium.click("id=HomeEnvironment_Q1_C_0");
		selenium.click("id=HomeEnvironment_Q1_D_0");
		selenium.click("id=HomeEnvironment_Q1_E_0");
		selenium.click("id=SectionQ_Q2_1");
		selenium.click("id=OutsideEnvironment_Q1_A_1");
		selenium.clickKnockOutCheckbox("OutsideEnvironment_Q1_B_1");
		selenium.click("id=SectionQ_Q4_Finance_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=SectionR_Q1_1");
		selenium.click("id=SectionR_SelfSufficiency_1");
		selenium.click("id=btnNextSection");
		selenium.click("id=btnAddSignature");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.click("id=lnkBackToSummary");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Check Errors");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Q7_a_0");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.click("id=lnkBackToSummary");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Check Errors");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Psychiatric_o_1");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.click("id=lnkBackToSummary");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Check Errors");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Unknown");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.click("id=lnkBackToSummary");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=Submit");
		selenium.click("id=btnYes");
		selenium.waitForPageToLoad("30000");
	}
	 
	@Test
	public void testInterRAI() throws Exception {
		selenium.open(selenium.baseUrl);
		//selenium.type("id=IdentityName", "!bella");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=btnLogin");
		clean();
		No09_TestIndividual.createIndividual(selenium, clientName);
		createInterRAI(selenium);
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}