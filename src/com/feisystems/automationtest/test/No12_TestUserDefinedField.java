package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 

public class No12_TestUserDefinedField {
	static Logger logger = TestUtils.getLogger(No12_TestUserDefinedField.class);
	static SeleniumWrapper selenium  = new SeleniumWrapper();
	
	@Test
	public void N1_testClientUserDefinedField() throws Exception {
		try {
			
			selenium.open(selenium.baseUrl + "Systems/ModelMetadataDefinition/ModelMetadataDefinitionSearch/");
			selenium.click("btnLogin");
			selenium.waitForPageToLoad("30000");
			
			selenium.select("id=DomainAttribute_Guid", "label=Client");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select(
					"id=ModelMetadataDefinition_AttachedModelDescriptor_Guid",
					"label=Client");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_aaa");
			selenium.select("id=inputFieldTypeId", "label=TextBox");
			selenium.type("id=title", "First Name");
			selenium.type("id=subtitle", "first name");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_bbb");
			selenium.select("id=inputFieldTypeId", "label=TextArea");
			selenium.type("id=title", "Description");
			selenium.type("id=subtitle", "Description");
			selenium.click("id=isRequiredNo");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ccc");
			selenium.select("id=inputFieldTypeId", "label=DatePicker");
			selenium.type("id=title", "Date Of Birth");
			selenium.type("id=subtitle", "DOB");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ddd");
			selenium.select("id=inputFieldTypeId", "label=RadioButton");
			selenium.type("id=title", "Relationship");
			selenium.type("id=subtitle", "Relationship");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Child/Step Child");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=unique4");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Parent/Step Parent");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_eee");
			selenium.select("id=inputFieldTypeId", "label=CheckBox");
			selenium.type("id=title", "CheckBox");
			selenium.type("id=subtitle", "CheckBox");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Text1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Text2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "Text3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique12");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_fff");
			selenium.select("id=inputFieldTypeId", "label=DropDownList");
			selenium.type("id=title", "DropDownList");
			selenium.type("id=subtitle", "DropDownList");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "DropDownList1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "DropDownList2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "DropDownList3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique5");
			selenium.click("id=unique4");
			selenium.click("xpath=(//a[contains(text(),'Delete')])[3]"); //Delete the 3rd "Data Source Item"
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnPublish");  //publish the defined template
			selenium.sleepSeconds(3);
			selenium.click("id=btnCopy");  //copy an existed template
			selenium.waitForPageToLoad("30000");
			
			selenium.sleepSeconds(3);
			//selenium.waitForElementDisplay("id=_Systems_ModelMetadataDefinition_DeleteUserDefinedField");
			selenium.click("//tbody/tr[1]/td[7]/a[3]");//Delete the first row
			selenium.comfirmAlert();
			selenium.refresh();
			
			selenium.click("id=btnDelete"); // Delete the copied template
			selenium.comfirmAlert();
			selenium.sleepSeconds(3);
			selenium.refresh();
			
			selenium.waitForElementDisplay("DomainAttribute_Guid");
			selenium.select("id=DomainAttribute_Guid", "label=Client");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	@Test
	public void N2_testOUUserDefinedField() throws Exception {
		try {
			selenium.waitForPageToLoad("30000");
			selenium.open(selenium.baseUrl + "Systems/ModelMetadataDefinition/ModelMetadataDefinitionSearch/");
			selenium.waitForPageToLoad("30000");
			
			selenium.select("id=DomainAttribute_Guid", "label=Organization Unit");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select(
					"id=ModelMetadataDefinition_AttachedModelDescriptor_Guid",
					"label=Organization Unit");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_aaa");
			selenium.select("id=inputFieldTypeId", "label=TextBox");
			selenium.type("id=title", "First Name");
			selenium.type("id=subtitle", "first name");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_bbb");
			selenium.select("id=inputFieldTypeId", "label=TextArea");
			selenium.type("id=title", "Description");
			selenium.type("id=subtitle", "Description");
			selenium.click("id=isRequiredNo");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ccc");
			selenium.select("id=inputFieldTypeId", "label=DatePicker");
			selenium.type("id=title", "Date Of Birth");
			selenium.type("id=subtitle", "DOB");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ddd");
			selenium.select("id=inputFieldTypeId", "label=RadioButton");
			selenium.type("id=title", "Relationship");
			selenium.type("id=subtitle", "Relationship");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Child/Step Child");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=unique4");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Parent/Step Parent");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_eee");
			selenium.select("id=inputFieldTypeId", "label=CheckBox");
			selenium.type("id=title", "CheckBox");
			selenium.type("id=subtitle", "CheckBox");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Text1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Text2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "Text3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique12");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_fff");
			selenium.select("id=inputFieldTypeId", "label=DropDownList");
			selenium.type("id=title", "DropDownList");
			selenium.type("id=subtitle", "DropDownList");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "DropDownList1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "DropDownList2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "DropDownList3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique5");
			selenium.click("id=unique4");
			selenium.click("xpath=(//a[contains(text(),'Delete')])[3]"); //Delete the 3rd "Data Source Item"
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnPublish");  //publish the defined template
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("id=btnCopy"); 
			selenium.click("id=btnCopy");  //copy an existed template
			selenium.waitForPageToLoad("30000");
			
			selenium.waitForElementDisplay("id=_Systems_ModelMetadataDefinition_DeleteUserDefinedField"); 
			selenium.click("//tbody/tr[1]/td[7]/a[3]");//Delete the first row
			selenium.comfirmAlert();
			selenium.refresh();
			
			selenium.click("id=btnDelete"); // Delete the copied template
			selenium.comfirmAlert();
			selenium.sleepSeconds(3);
			selenium.refresh();
			
			selenium.waitForElementDisplay("DomainAttribute_Guid");
			selenium.select("id=DomainAttribute_Guid", "label=Organization Unit");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}
	
	@Test
	public void N3_testStaffUserDefinedField() throws Exception {
		try {
			selenium.waitForPageToLoad("30000");
			selenium.open(selenium.baseUrl + "Systems/ModelMetadataDefinition/ModelMetadataDefinitionSearch/");
			selenium.waitForPageToLoad("30000");
			selenium.select("id=DomainAttribute_Guid", "label=Staff");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_Create");
			selenium.waitForPageToLoad("30000");
			selenium.select(
					"id=ModelMetadataDefinition_AttachedModelDescriptor_Guid",
					"label=Staff");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_aaa");
			selenium.select("id=inputFieldTypeId", "label=TextBox");
			selenium.type("id=title", "First Name");
			selenium.type("id=subtitle", "first name");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_bbb");
			selenium.select("id=inputFieldTypeId", "label=TextArea");
			selenium.type("id=title", "Description");
			selenium.type("id=subtitle", "Description");
			selenium.click("id=isRequiredNo");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ccc");
			selenium.select("id=inputFieldTypeId", "label=DatePicker");
			selenium.type("id=title", "Date Of Birth");
			selenium.type("id=subtitle", "DOB");
			selenium.click("id=isRequiredYes");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_ddd");
			selenium.select("id=inputFieldTypeId", "label=RadioButton");
			selenium.type("id=title", "Relationship");
			selenium.type("id=subtitle", "Relationship");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Child/Step Child");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=unique4");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Parent/Step Parent");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_eee");
			selenium.select("id=inputFieldTypeId", "label=CheckBox");
			selenium.type("id=title", "CheckBox");
			selenium.type("id=subtitle", "CheckBox");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "Text1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "Text2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "Text3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique12");
			selenium.click("id=unique5");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=_Systems_ModelMetadataDefinition_CreateUserDefinedField");
			selenium.waitForPageToLoad("30000");
			selenium.type("id=propertyName", "Property Name_fff");
			selenium.select("id=inputFieldTypeId", "label=DropDownList");
			selenium.type("id=title", "DropDownList");
			selenium.type("id=subtitle", "DropDownList");
			selenium.click("id=isRequiredYes");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique2", "DropDownList1");
			selenium.type("id=unique3", "Value1");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique6", "DropDownList2");
			selenium.type("id=unique7", "Value2");
			selenium.click("id=ButtonAddDataSource");
			selenium.type("id=unique10", "DropDownList3");
			selenium.type("id=unique11", "Value3");
			selenium.click("id=unique5");
			selenium.click("id=unique4");
			selenium.click("xpath=(//a[contains(text(),'Delete')])[3]"); //Delete the 3rd "Data Source Item"
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			selenium.click("id=btnPublish");  //publish the defined template
			selenium.waitForPageToLoad("30000");
			selenium.waitForElementDisplay("id=btnCopy"); 
			selenium.click("id=btnCopy");  //copy an existed template
			selenium.waitForPageToLoad("30000");
			
			selenium.sleepSeconds(3);
			//selenium.waitForElementDisplay("id=_Systems_ModelMetadataDefinition_DeleteUserDefinedField"); 
			selenium.click("//tbody/tr[1]/td[7]/a[3]");//Delete the first row
			selenium.comfirmAlert();
			selenium.refresh();
			
			selenium.click("id=btnDelete"); // Delete the copied template
			selenium.comfirmAlert();
			selenium.sleepSeconds(3);
			selenium.refresh();
			
			selenium.waitForElementDisplay("DomainAttribute_Guid");
			selenium.select("id=DomainAttribute_Guid", "label=Organization Unit");
			selenium.click("css=button[type=\"submit\"]");
			selenium.waitForPageToLoad("30000");
			
			selenium.stop();
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}

	}

	/*@After
	public void tearDown() throws Exception {
		selenium.stop();
	}*/
}
