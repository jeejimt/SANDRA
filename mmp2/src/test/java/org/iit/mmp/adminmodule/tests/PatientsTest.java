package org.iit.mmp.adminmodule.tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.PatientsPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportHistoryPage;
import org.iit.util.TestBase;


public class PatientsTest extends TestBase{
	HelperClass helper;
	PatientsPage rpage;
	ViewReportHistoryPage vrp;
	
	 public HashMap<String,String> hmap1,hmap2,hmap3;
	 public int i;
	@Parameters({"url","ssnnoex","user","pass","DrName"})
	@Test
	public  void  viewReport(String url,String ssnnoex,String user,String pass,String DrName) throws InterruptedException {
		helper=new HelperClass(driver);
		rpage=new PatientsPage(driver);
		helper.launchModule(url);
		helper.login(user,pass);
		helper.navigateToModule("Patients ");
		rpage.searchBySSN(ssnnoex);	
		
		helper.clickButtons("Create Visit");
		hmap1=new HashMap<String,String>();
		 hmap1=rpage.createVisit(DrName);
		rpage.searchBySSN(ssnnoex);	
		
		helper.clickButtons("Add Precription");
		 hmap2=rpage.prescriptonDetails();
		Thread.sleep(1000);
		helper.navigateToModule("Patients ");
		rpage.searchBySSN(ssnnoex);	
		
		helper.clickButtons("Create Fee");	
		rpage.feeDetails();
			
		helper.clickButtons("Reports");
		Thread.sleep(5000);
	     hmap3= rpage.reportDetails();
	     
	     
	      
		
			}
	public HashMap<String,String> appointment() {
	return hmap1;	
	}
	
	public HashMap<String,String> prescription() {
		return hmap2;	
		}

	public HashMap<String,String> reports() {
		return hmap3;	
		}
	
}
	
	
	

	
	


