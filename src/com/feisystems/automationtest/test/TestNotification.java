package com.feisystems.automationtest.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.util.Callback;
import org.xvolks.jnative.util.User32;

import com.feisystems.automationtest.libary.AlertType;
import com.feisystems.automationtest.libary.SeleniumWrapper;
import com.feisystems.automationtest.libary.TestUtils;

public class TestNotification {
	private SeleniumWrapper selenium;
	Logger logger = TestUtils.getLogger(this.getClass());
	
	@Before
	public void setUp() throws Exception {
		selenium = new SeleniumWrapper();
	}
	
	
	public class MyCallback implements Callback {

		@Override
		public int callback(long[] values) {
			for(int i=0;i<values.length;i++) {
				System.out.println(values[i]);
			}
			return 0;
		}

		@Override
		public int getCallbackAddress() throws NativeException {
			return 0;
		}
	
	
	}
	
	
	@Test
	public void testInitial() throws Exception {
		selenium.open(selenium.baseUrl);
		//String handler = selenium.getWindowHandle();
		
		HWND hWnd = User32.FindWindow("", "data:, - Google Chrome");
		MyCallback callback = new MyCallback();
		if(hWnd != null) {
			User32.EnumChildWindows(hWnd, callback, 0);
		}
		
		
		/*if(hWnd.getValue()>0) {
			HWND childWindow = User32.FindWindowEx(hWnd,null,"","Authentication Required");
			
		}*/
		
		selenium.click("id=btnLogin");
		
		selenium.open(selenium.baseUrl + "Clients/Client/ClientSearch/");
		selenium.type("id=ClientSearchCriteria_ClientFirstName", "mike");
		selenium.click("id=searchProfiles");
		selenium.click("//table[@id='ClientSummaryGrid']/tbody/tr[1]/td[10]/a[2]");
		selenium.select("id=ProgramTypeId", "AL");
		selenium.click("id=btnOk");
		
		//selenium.collectNotification();
		selenium.verifyNotification(AlertType.RecordSavedSuccessfully);
		
		selenium.open("http://localhost:50099/Pss/PlanOfService/Details/planofservices/d3e098bb-6e7a-428d-bf96-ead78cf3108f?clientId=clients%2Ff817d511-84e2-4245-b665-be2f84cc470c");
		selenium.verifyEquals("css=h3 > span", "In Progress");
		
		selenium.printVerfifyInfo();
		
	}
}
