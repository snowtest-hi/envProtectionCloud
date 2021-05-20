package cases;
import dataRead.RangeDataByPOI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Login {
    private static Logger logger=Logger.getLogger(Test.class);
    WebDriver dr;
    @DataProvider(name = "dataPro")
    public  Object[][] getData() throws IOException {
        String filepath="D:/dataPOI/account.xls";
        Object[][] data= RangeDataByPOI.poiRangeData(filepath);
        return  data;
    }

    @Parameters({"browser","url"})
    @BeforeTest
    public void beforeTest(String browser,String url){
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","D:/driver/chromedriver.exe");
            dr=new ChromeDriver();
        }else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","D:/driver/firefox.exe");
        }else {
            System.out.println("浏览器错误");
        }
        dr.manage();
        dr.get(url);
    }

    @Test(dataProvider = "dataPro")
    public void Login(String userAccount,String password) throws InterruptedException{
        dr.findElement(By.name("userAccount")).sendKeys(userAccount);
        dr.findElement(By.name("passWord")).sendKeys(password);
        dr.findElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
        Thread.sleep(2000);
        dr.quit();
    }
}
