package org.iit.mmp.patientmodule.tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.RegistrationPage;
import org.iit.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;



public class RegistrationTests extends TestBase{
	
	HelperClass helper;
	
	@Parameters("url")
	 @Test(description="US_002 Validation of the Registration Func",groups={"US_002","regression","sanity"})
	 
	 public void validateRegistration(String url)throws InterruptedException {
		 helper=new HelperClass(driver);
		 helper.launchModule(url);
		 RegistrationPage regPage=new RegistrationPage(driver);
		 HashMap<String,String> hmap=regPage.fillData();
		 String actual=regPage.readSuccessMsg();
		 System.out.println("ActualMessage"+actual);
		 String expected="Thank you for registering with MMP. ";
		 AssertJUnit.assertEquals(actual, expected);
		 
		 
}
}
