import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class webOrder {
    public static void main(String[] args) throws InterruptedException, IOException {

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\omina\\Downloads\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
            WebElement login = driver.findElement(By.name("ctl00$MainContent$username"));
            WebElement password = driver.findElement(By.name("ctl00$MainContent$password"));
            login.sendKeys("Tester");
            password.sendKeys("test");
            driver.findElement(By.name("ctl00$MainContent$login_button")).click();
            driver.findElement(By.xpath("//a[@href='Process.aspx']")).click();
            WebElement quantity =  driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity"));
            Random rand = new Random();
            int myRand=rand.nextInt(100)+1;
           driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.BACK_SPACE);
           quantity.sendKeys(String.valueOf(myRand));
            driver.findElement(By.xpath("//input[@value='Calculate']")).click();
           int total;
            if (myRand<10){
                total=myRand*100;
            }else {
                    total=(myRand*100)-(myRand*8);
            }
            Thread.sleep(2000);
            String totalDiscount = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtTotal")).getAttribute("value");
            Assert.assertEquals(totalDiscount,String.valueOf(total));
            String read;
        BufferedReader dora = new BufferedReader(new FileReader("src/RandomData.csv"));
        List<String[]> result = new ArrayList<>();
        while ((read=dora.readLine())!=null){
            String[] each = read.split(",");
            result.add(each);
        }
        int randNum = (int)(Math.random() * 1001);
        String[] name = result.get(randNum);
        WebElement fullName = driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName"));
         WebElement street = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2"));
         WebElement city = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3"));
         WebElement state = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4"));
         WebElement zip_code = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5"));
         fullName.sendKeys(name[0]);
         street.sendKeys(name[1]);
         city.sendKeys(name[2]);
         state.sendKeys(name[3]);
         zip_code.sendKeys(name[4]);
         int sum = (int) (1+Math.random()*3);
         if (sum==1){
          String visaCardRandom = "4041594016710928";
          driver.findElement(By.xpath("//input[@value='Visa']")).click();
           WebElement visaCard = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6"));
           WebElement visaExpireDate = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));
           visaCard.sendKeys(visaCardRandom);
           visaExpireDate.sendKeys("08/24");
         }if (sum==2){
           String masterCardRandom = "5108758810618846";
           driver.findElement(By.xpath("//input[@value='MasterCard']")).click();
            WebElement masterCard = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6"));
            WebElement masterCardExpireDate = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));
            masterCard.sendKeys(masterCardRandom);
            masterCardExpireDate.sendKeys("09/23");
        }if (sum==3){
          String americanExpressRandom = "347458094330984";
          driver.findElement(By.xpath("//input[@value='American Express']")).click();
            WebElement americanExpress= driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6"));
            WebElement americanExpressExpireDate = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));
           americanExpress.sendKeys(americanExpressRandom);
           americanExpressExpireDate.sendKeys("11/24");
        }
       driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
       Thread.sleep(1000);
       String expected = "New order has been successfully added.";
       Assert.assertTrue(driver.getPageSource().contains(expected));
       driver.findElement(By.id("ctl00_logout")).click();
       driver.quit();



    }
}
