package org.iit.mmp.adminmodule.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.adminmodule.pages.CreateReportVisitPrescFeesAdmPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportHistoryPatientPage;
import org.iit.util.TestBase;

   public class ReportHistoryTest extends TestBase{
	ViewReportHistoryPatientPage viewReportPage;
	HelperClass helper;
	CreateReportVisitPrescFeesAdmPage admPapage;
	public static HashMap<String,String> haVisit,haPresc,haReport;
	public static HashMap<String,String> hpApp,hpPresc,hpReport;
	
	//admin module 
	@Parameters({"adminUrl","ssnNo","adminUname","adminPword","DrName"})
	@Test
	public  void  createVisit(String adminUrl,String ssnNo,String adminUname,String adminPword,String DrName) throws InterruptedException {
		admPapage=new CreateReportVisitPrescFeesAdmPage(driver);
		helper=new HelperClass(driver);
		helper.launchModule(adminUrl);
		helper.adminLogin(adminUname,adminPword);
		helper.navigateToAModule("Patients ");
		admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Create Visit");
		 haVisit=admPapage.visitDetails(DrName);
	}
	
	@Parameters({"ssnNo"})
	@Test(dependsOnMethods= {"createVisit"})
		public void addPrescription(String ssnNo) throws InterruptedException {
		 admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Add Precription");
		 haPresc=admPapage.prescriptonDetails();	
	}
	
	@Parameters({"ssnNo"})
	@Test(dependsOnMethods= {"addPrescription"})
	public void createFee(String ssnNo) throws InterruptedException{
		helper.navigateToAModule("Patients ");
		admPapage.searchBySSN(ssnNo);
		helper.clickButtons("Create Fee");	
		admPapage.feeDetails();
	}
	
	@Test(dependsOnMethods= {"createFee"})
	public void creatReport() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
		helper.clickButtons("Reports");
	     haReport= admPapage.reportDetails();
	} 
	
	// patient module
	
	     @Parameters({"patientUrl","patientUname","patientPword"})
	     @Test(dependsOnMethods={"creatReport"})
	     public void viewReport(String patientUrl,String patientUname,String patientPword) throws InterruptedException {
	    	 viewReportPage=new ViewReportHistoryPatientPage(driver);
	    	 helper=new HelperClass(driver);
			 helper.launchModule(patientUrl);
			 helper.login(patientUname,patientPword);
			 helper.navigateToAModule(" Profile ");
			 hpReport=viewReportPage.viewReport();
	     }
	     
	     @Test(dependsOnMethods={"viewReport"})
			 public void viewHistory() throws InterruptedException {
	    	 
	    	// viewReportPage=new ViewReportHistoryPatientPage(driver);
	    	 viewReportPage.viewHistory();
	     }
	     
	     @Test(dependsOnMethods={"viewHistory"})
	     public void viewPastAppointment() throws InterruptedException{
	     
	    	// viewReportPage=new ViewReportHistoryPatientPage(driver); 
	    	 hpApp=viewReportPage.pastAppointment();
	     }
	     
	     	@Test(dependsOnMethods={"viewPastAppointment"})
	     	public void viewPastPrescription() throws InterruptedException {
	    	 
	    	// viewReportPage=new ViewReportHistoryPatientPage(driver);
			 hpPresc=viewReportPage.pastPrescription();
	     }
	     
			//validation 
	     	@Test(dependsOnMethods= {"viewPastPrescription"})
			public void validateAppointment() {	
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(haVisit.get("dateofAppointment").contains(hpApp.get("dateApp")));
				softAssert.assertTrue(haVisit.get("timeOfAppointment").contains(hpApp.get("timeApp"))); 		 
				softAssert.assertTrue(haVisit.get("symptoms").contains(hpApp.get("symp"))); 
				softAssert.assertTrue(haVisit.get("doctorName").contains(hpApp.get("DrName")));
				softAssert.assertAll();		
						}
	     	@Test(dependsOnMethods= {"validateAppointment"})
			public void validatePrescription(){	
				SoftAssert softAssert = new SoftAssert();	
				softAssert.assertTrue(haPresc.get("prescDate").contains(hpPresc.get("DateP"))); 
				softAssert.assertTrue(haPresc.get("medName").contains(hpPresc.get("NameP")));  
				softAssert.assertTrue(haPresc.get("medDesc").contains(hpPresc.get("DescP")));
				softAssert.assertAll();
					}
	     	@Test(dependsOnMethods= {"validatePrescription"})
			public void validateReports(){
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(hpReport.get("nameRepo").contains(haReport.get("reportName")));
				softAssert.assertTrue(hpReport.get("descRepo").contains(haReport.get("reportDesc")));
				softAssert.assertAll();	
		
			}
				
	}


	
	


	
		

	
	
	

	
	


