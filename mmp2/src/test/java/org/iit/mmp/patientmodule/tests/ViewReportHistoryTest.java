package org.iit.mmp.patientmodule.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportHistoryPage;
import org.iit.util.TestBase;
import org.iit.mmp.adminmodule.tests.PatientsTest;
public class ViewReportHistoryTest extends TestBase{
	HelperClass helper;
	ViewReportHistoryPage vrhpage=new ViewReportHistoryPage(driver);
	PatientsTest ptest=new PatientsTest();
	
	@Parameters({"url1","user1","pass1"})
	@Test
	public void viewReport(String url1,String user1,String pass1) throws InterruptedException {
		helper=new HelperClass(driver);
		vrhpage=new ViewReportHistoryPage(driver);
		helper.launchModule(url1);
		helper.login(user1,pass1);
		helper.navigateToModule(" Profile ");
		vrhpage.viewReport();
		driver.navigate().back();
		vrhpage.viewHistory();
		
		//validation modules
		
		
		HashMap<String,String> hmap1=ptest.appointment();
		HashMap<String,String> hmap2=ptest.prescription();
		HashMap<String,String> hmap3=ptest.reports();
		boolean result1=vrhpage.validateAppointment(hmap1);
		boolean result2=vrhpage.validatePrescription(hmap2);
		boolean result3=vrhpage.validateReport(hmap3);
		
		boolean result = false;
		if((result1==true) && (result2==true )&& (result3==true) )
			result=true;
		
		Assert.assertTrue(result);
			}
	
	
}
	
	
	

	
	


