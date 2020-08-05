package org.iit.mmp.patientmodule.pages;
import java.util.HashMap;
import java.util.List;

import org.iit.mmp.adminmodule.pages.PatientsPage;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ViewReportHistoryPage {
	
	
	By viewReportBT=By.xpath("//button[contains(text(),'View Reports')]");
	By viewHistoryBT=By.xpath("//button[contains(text(),'View History')]");
	By dateTB=By.xpath("//table/tbody/tr[1]/td[1]");
	By timeLB=By.xpath("//table/tbody/tr[1]/td[2]");
	By symTB=By.xpath("//table/tbody/tr[1]/td[3]");
	By DrName=By.xpath("//table/tbody/tr[1]/td[4]");
	By prescTB=By.xpath("//div[@class='col-md-6']/div/div[2]/h2");
	By prescDescTB=By.xpath("//div[@class='col-md-6']/div/div[2]/p");		
	By tableselector=By.xpath("//table[@class='table']");
	
	String selectDate="07/14/2020";
	HashMap<String,String> hmapApp;
	HashMap<String,String> hmapPresc;
	HashMap<String,String> hmapReport;
	WebDriver driver;
	HelperClass helperObj;
	ScheduleAppointmentPage sap;
	PatientsPage pPage;
	
	
	
	public ViewReportHistoryPage(WebDriver driver){
	this.driver=driver;	
	 helperObj=new  HelperClass(driver);
	}
	
	
	
		public void viewReport() throws InterruptedException{
			hmapReport=new HashMap<String,String>();
		driver.findElement(viewReportBT).click();
		
		int[] myArray=findTableSize();
		int rowindex=myArray[0];
		int colindex=myArray[1];
		Thread.sleep(5000);
		
		 String nameR=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/h4")).getText();
		 System.out.println(nameR);
		 String descR=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/div/p")).getText();
		 System.out.println(descR);
		driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/h4")).click();
		 hmapReport.put("nameRepo", nameR);
		 hmapReport.put("descRepo", descR);
		 Thread.sleep(2000);
		}
		
		public void viewHistory() throws InterruptedException {
			driver.findElement(viewHistoryBT).click();	
			pastAppointment();
			driver.navigate().back();
			pastPrescription();
			 	 
		}
		public  void pastAppointment() throws InterruptedException {
			hmapApp=new HashMap<String,String>();
			Thread.sleep(2000);
			helperObj.clickButtons("Past Appointments");
			 String date=driver.findElement(dateTB).getText().trim();
			 String time=driver.findElement(timeLB).getText().trim();
			 String symptoms=driver.findElement(symTB).getText().trim();
			 String docName=driver.findElement(DrName).getText().trim();
			 hmapApp.put("dateApp", date);
			 hmapApp.put("timeApp", time);
			 hmapApp.put("symp", symptoms);
			 hmapApp.put("DrName",docName );
			 Thread.sleep(2000);
			
			
		}
		public void pastPrescription() throws InterruptedException {
			Thread.sleep(2000);
			helperObj.clickButtons("Past Prescription");
			hmapPresc=new HashMap<String,String>();
			int[] myArray=findTableSize();
				int rowindex=myArray[0];
				int colindex=myArray[1];
				Thread.sleep(2000);
				String date=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]/a/ul/li/div/p")).getText();
				String[] dateArray=date.split(":");
				String datePresc=dateArray[1];
				 driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rowindex)+"]/td["+(colindex)+"]")).click();
				String prescName=driver.findElement(prescTB).getText();
				 String prescDesc=driver.findElement(prescDescTB).getText();	
				 hmapPresc.put("DateP", datePresc);
				 hmapPresc.put("NameP",prescName );
				 hmapPresc.put("DescP",prescDesc );
		}
		
		public int[] findTableSize() {
			WebElement tabledriver=driver.findElement(tableselector);
			 List<WebElement>  rowList=tabledriver.findElements(By.tagName("tr"));
			 int rows=rowList.size();
			 int[] myArray=new int[2];
			
			 
			 //number of columns
 
			 WebElement rowdriver=driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+(rows)+"]"));
			 List<WebElement> colList=rowdriver.findElements(By.tagName("td"));
			 int cols=colList.size();
			 if (cols==0) {
				 rows=rows-1;
				 cols=4;
			 }
			 myArray[0]=rows;
			 myArray[1]=cols;
			 System.out.println(rows+"   "+cols);
			 return myArray;

			
		}
		
		
		//validation
		public boolean validateAppointment(HashMap<String, String> hmap1)  {
			
			boolean result=false;
			
			
			if ((hmap1.get("dateofAppointment").contains(hmapApp.get("dateApp")))&& (hmap1.get("timeOfAppointment").contains(hmapApp.get("timeApp"))) &&
					(hmap1.get("symptoms").contains(hmapApp.get("symp"))) &&  (hmap1.get("doctorName").contains(hmapApp.get("DrName"))))
					result=true;
					
			return result;
		}
		public boolean validatePrescription(HashMap<String, String> hmap2) {
			boolean result=false;
			
			
			if ((hmap2.get("prescDate").contains(hmapPresc.get("DateP"))) && (hmap2.get("medName").contains(hmapPresc.get("NameP"))) && 
					(hmap2.get("medDesc").contains(hmapPresc.get("DescP")))){
				result=true;
			}
			return result;
		}
		public boolean validateReport(HashMap<String, String> hmap3) {
			boolean result=false;
			
			 if ((hmapReport.get("nameRepo").contains(hmap3.get("reportName"))) && ((hmapReport.get("descRepo")).contains(hmap3.get("reportDesc")))){
			 result=true;
			 }
			return result;
			
		}



		



		
		
}

	


