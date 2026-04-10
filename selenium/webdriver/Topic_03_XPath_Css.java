package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {
//    1 - Setup: OS/ Browser/ Web/ Page/ Data/ Variable/ Object/ ...
    WebDriver driver;
    @BeforeClass
    public void  initalBrowser(){
        driver = new FirefoxDriver();
//        driver.get("https://live.techpanda.org/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }
//    2 - Action/ Execute: tuong tac len element nao/ input / verify/ ...
    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
//        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        driver.findElement(By.xpath("//li[@class='success-msg'//span]"));
    }

    @Test
    public void Register_02_Invalid_Email (){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Tam Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("tam02t3");
        driver.findElement(By.id("txtCEmail")).sendKeys("tam02t3");
        driver.findElement(By.id("txtPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtCPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtPhone")).sendKeys("0912567890");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_03_Incorrect_Confirm_Email (){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Tam Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("tam02t3");
        driver.findElement(By.id("txtPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtCPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtPhone")).sendKeys("0912567890");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }

    @Test
    public void Register_04_Invalid_Password (){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Tam Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0912567890");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_05_Incorrect_Confirm_Password (){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Tam Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtCPassword")).sendKeys("TamNguyen");
        driver.findElement(By.id("txtPhone")).sendKeys("0912567890");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_06_Invalid_Phone_Number (){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Tam Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("tam02t3@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtCPassword")).sendKeys("Tam@Nguyen");
        driver.findElement(By.id("txtPhone")).sendKeys("1912567890");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
//    3 - Clean: Delete data set/ account/ close browser/...
    @AfterClass
    public void clearBrowser (){
        driver.quit();
    }
}
