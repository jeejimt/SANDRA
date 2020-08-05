package org.iit.mmp.adminmodule.pages;
import java.util.HashMap;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PatientsPage {
	
	
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
	String selectDate="07/14/2020";
	
	WebDriver driver;
	HelperClass helperObj;
	ScheduleAppointmentPage sap;
	
	HashMap<String, String> hmap_visit;
	HashMap<String,String> hmap_prescription;
	HashMap<String, String> hmap_report;
	
	public PatientsPage(WebDriver driver){
	this.driver=driver;	
	 helperObj=new  HelperClass(driver);
	}
	public void searchBySSN(String ssnno) throws InterruptedException {
		driver.findElement(searchTB).sendKeys(ssnno);
		driver.findElement(searchButton).click();
		Thread.sleep(5000);
		driver.findElement(nameLink).click();
		Thread.sleep(2000);
	}
	public HashMap<String,String> createVisit(String DrName) throws InterruptedException {
		ScheduleAppointmentPage sap=new ScheduleAppointmentPage(driver);
		HashMap<String,String> hmap_visit=sap.bookAppointment(DrName);
		return hmap_visit;
	}
		
	public HashMap<String,String> prescriptonDetails() throws InterruptedException {
		 hmap_prescription=new HashMap<String,String>();
		String medicine="ibuprofen";
		String medicinedesc="take every 6 hrs";
		helperObj.giveDate(selectDate);
		driver.findElement(prescNameTB).sendKeys(medicine);
		driver.findElement(prescDescTA).sendKeys(medicinedesc);
		Thread.sleep(3000);
		driver.findElement(submitButton2).click();
		hmap_prescription.put("prescDate", selectDate);
		hmap_prescription.put("medName", medicine);
		hmap_prescription.put("medDesc", medicinedesc);
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		return hmap_prescription;
		
	}
	public void feeDetails() throws InterruptedException {
		helperObj.giveDate(selectDate);
		Select ser=new Select(driver.findElement(By.id("service")));
		ser.selectByIndex(1);
		Thread.sleep(2000);
		// str=driver.findElement(By.xpath("//div[@id='show']//input[@type='text']")).getText();
		
		Thread.sleep(1000);
		driver.findElement(submitButton2).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		
	}
	public HashMap<String,String> reportDetails() throws InterruptedException {
		String reportname="X-Ray Report";
		String reportdesc="chest x-ray";
		HashMap<String, String> hmap_report=new HashMap<String,String>();
		helperObj.giveDate(selectDate);
		driver.findElement(By.name("report_name")).sendKeys(reportname);
		WebElement uploadElement=driver.findElement(By.id("file"));
		String path = System.getProperty("user.dir")+"/report.docx";
		uploadElement.sendKeys(path);
		System.out.println(path);
		driver.findElement(By.name("report_desc")).sendKeys(reportdesc);
		Thread.sleep(2000);
		driver.findElement(submitButton2).click();
		hmap_report.put("reportName",reportname);
				
		hmap_report.put("reportDesc",reportdesc);
		return hmap_report;
	}
	
			
}
