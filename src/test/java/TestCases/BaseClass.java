package TestCases;

import Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class BaseClass {

    ReadConfig rc = new ReadConfig();
    Dotenv dotenv = Dotenv.load();

    public String baseURL = rc.getApplicationURL();

    public String StagingURL = rc.getStagingURl();

    public String username = dotenv.get("USERNAME_CORRECT");

    public String password = dotenv.get("PASSWORD_CORRECT");

    public String IncorrectPassword = rc.getWrongPassword();

    public WebDriver driver;

    public static Logger logger;


    @Parameters({"browser"})   // to choose which browser and environment to start testing
    @BeforeMethod(alwaysRun = true)
    public void Setup(String br) throws MalformedURLException {

        logger = Logger.getLogger("CoingeckoWeb");
        PropertyConfigurator.configure("Log4j.properties");
        DesiredCapabilities cap = new DesiredCapabilities();

        if (br.equalsIgnoreCase("chrome")){

            //ChromeOptions options = new ChromeOptions();
            cap.setBrowserName("chrome");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/"), cap);

            //WebDriverManager.chromedriver().setup();
            //ChromeOptions options = new ChromeOptions();
            //options.setHeadless(true);
            //options.addArguments("--incognito");
            //options.addArguments("--no-sandbox");
            //options.addArguments("--start-maximized");
            //options.addArguments("--window-size=1920,1080");
            //options.addArguments("--disable-notifications");
            //options.addArguments("--disable-dev-shm-usage");

            //driver = new ChromeDriver();

        }else if (br.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            //options.setHeadless(true);
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-dev-shm-usage");

            driver = new FirefoxDriver(options);

        } else if (br.equalsIgnoreCase("Edge") ){
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            //options.addArguments("--no-sandbox");
            options.setHeadless(true);
            //options.UseChromium = true;
            //options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--window-size=1920,1080");
            //options.addArguments("--disable-notifications");
            //options.addArguments("--disable-dev-shm-usage");
            //ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.setBinary("/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge");
            //EdgeOptions edgeOptions = new EdgeOptions().merge(chromeOptions);

            driver = new EdgeDriver(options);

        }



    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver,String tname)throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/test-output/Screenshots/"+tname+".png");
        FileUtils.copyFile(source,target);
        logger.info("Screenshot Taken");

    }







}
