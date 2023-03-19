package TestCases;

import PageObject.FirstPage;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TC02 extends BaseClass {

    @Parameters({"Environment"})
    @Test(groups = {"Smoke", "all"})
    public void TC02(String env) throws IOException {

        if (env.equalsIgnoreCase("Production")) {
            driver.get(baseURL);
            logger.info("Navigated to Production Site");
        } else if (env.equalsIgnoreCase("Staging1")) {
            driver.get(StagingURL);
            logger.info("Navigated to Staging Site");

        }
        driver.manage().window().maximize();

        FirstPage fp = new FirstPage(driver);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        fp.ClickPortfolioButton();
        logger.info("Clicked the Portfolio button");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String actualtitle = fp.GetPageTitle();
        if (actualtitle.equalsIgnoreCase("Free Crypto Portfolio Tracker - CoinGecko")) {
            captureScreen(driver, "TC01-CorrectTitle");
            Assert.assertTrue(true);
        } else {
            captureScreen(driver, "TC01-ErrorTitle");
            Assert.assertTrue(false);
        }
    }
}
