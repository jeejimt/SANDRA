package org.iit.mmp.patientmodule.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.util.TestBase;

import org.testng.Assert;


public class ScheduleAppointmentTest extends TestBase{
	HelperClass helper;
	
	@Parameters({"url","uname","pword","drName"})
	@Test(description="US_004, Validate Schedule appointment",groups={"sanity","regression","UI","patientmodule","US_004"})
	public  void validateScheduleAppointment(String url,String uname,String pword,String drName) throws InterruptedException
	 {
		
		helper=new HelperClass(driver);
		helper.launchModule(url);
		helper.login(uname,pword);
		
		
		helper.navigateToModule("Schedule Appointment");
		ScheduleAppointmentPage sap=new ScheduleAppointmentPage(driver);
		helper.clickButtons("Create new appointment");
		
		HashMap<String,String> hmap=sap.bookAppointment(drName);
		helper.navigateToModule("Schedule Appointment");
		boolean result=sap.validateAppointmentDetailsinSchedulePage(hmap);
		Assert.assertTrue(result);
		
	}
	
	
	
	
	
	
	
	
}
	
	
	
