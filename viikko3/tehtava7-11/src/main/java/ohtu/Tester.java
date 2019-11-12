package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
        

        // LOGIN with invalid credentials
        driver.get("http://localhost:4567");
        sleep(1);
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());

        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sergei");
        element = driver.findElement(By.name("login"));
        //sleep(1);
        element.submit();
        if (!driver.getPageSource().contains("error")) {System.exit(1);}
        if (!driver.getPageSource().contains("invalid username or password")) {System.exit(1);}
        System.out.println(driver.getPageSource());


        // CREATING NEW USER

        driver.get("http://localhost:4567");
        element = driver.findElement(By.linkText("register new user"));
        element.click();

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("pakka"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("signup"));
        element.submit();
        //sleep(1);
        System.out.println(driver.getPageSource());

        //SIGNING OUT AFTER CREATING NEW USER

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());


        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
