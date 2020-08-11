package org.iit.mmp.adminmodule.pages;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateReportVisitPrescFeesAdmPage {
	
	By continueButton = By.id("ChangeHeatName");
	By symptxtbox = By.name("sym");
	By submitButton =By.xpath("//input[@value='Submit']");
	By submitButton2 =By.xpath("//input[@value='submit']");
	By searchTB=By.xpath("//input[@id='search']");
	By searchButton=By.xpath("//input[@value='search']");
	By viewHistoryBT=By.xpath("//button[contains(text(),'View History')]");		
	By nameLink=By.xpath("//div[@id='show']//table//tbody//tr//td//a");
	By prescNameTB=By.id("exampleInputcardnumber1");
	By prescDescTA=By.xpath("//textarea[@class='form-control']");
	WebDriver driver;
	HelperClass helperObj;
	WebDriverWait w;
	ScheduleAppointmentPage sap;
	HashMap<String, String> hmap_visit,hmap_prescription,hmap_report;
	
	public CreateReportVisitPrescFeesAdmPage(WebDriver driver){
	  this.driver=driver;	
	  helperObj=new  HelperClass(driver);
	 
	}
	 
	public void searchBySSN(String ssnno) throws InterruptedException {
		
		driver.findElement(searchTB).sendKeys(ssnno);
		driver.findElement(searchButton).click();		
		driver.findElement(nameLink).click();
		Thread.sleep(1000);
	}
	
	public HashMap<String, String> visitDetails(String DrName) throws InterruptedException {
		ScheduleAppointmentPage sap=new ScheduleAppointmentPage(driver);
		HashMap<String,String> hmap_visit=sap.bookAnAppointment(DrName);
		return hmap_visit;
	}
		
	public HashMap<String,String> prescriptonDetails() throws InterruptedException {
		hmap_prescription=new HashMap<String,String>();
		String medicine="ibuprofen";
		String medicinedesc="take every 6 hrs";
		String selectDate=helperObj.giveDate();
		driver.findElement(prescNameTB).sendKeys(medicine);
		driver.findElement(prescDescTA).sendKeys(medicinedesc);
		driver.findElement(submitButton2).click();
		hmap_prescription.put("prescDate", selectDate);
		hmap_prescription.put("medName", medicine);
		hmap_prescription.put("medDesc", medicinedesc);
		driver.switchTo().alert().accept();
		return hmap_prescription;
		
	}
	public void feeDetails() throws InterruptedException {
		String selectDate=helperObj.giveDate();
		Select ser=new Select(driver.findElement(By.id("service")));
		ser.selectByIndex(1);		
		driver.findElement(submitButton2).click();
		driver.switchTo().alert().accept();
		
	}
	public HashMap<String,String> reportDetails() throws InterruptedException {
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
		String reportname="X-Ray Report";
		String reportdesc="chest x-ray";
		HashMap<String, String> hmap_report=new HashMap<String,String>();
		String selectDate=helperObj.giveDate();
		driver.findElement(By.name("report_name")).sendKeys(reportname);
		WebElement uploadElement=driver.findElement(By.id("file"));
		String path = System.getProperty("user.dir")+"/report.docx";
		uploadElement.sendKeys(path);
		driver.findElement(By.name("report_desc")).sendKeys(reportdesc);
		driver.findElement(submitButton2).click();
		hmap_report.put("AppDate",selectDate);
		hmap_report.put("reportName",reportname);		
		hmap_report.put("reportDesc",reportdesc);
		return hmap_report;
	}
			
}
