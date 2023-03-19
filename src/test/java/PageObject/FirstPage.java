package PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static Utilities.ReadConfig.logger;

public class FirstPage {

    WebDriver ldriver;

    public FirstPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(xpath = "//div[@class=\"dropdown\"]//a[@class=\"text-black tw-mr-4 tw-text-sm\"]")
    @CacheLookup
    WebElement Portfoliobutton;


    public void ClickPortfolioButton(){
        Portfoliobutton.click();
    }

    public String GetPageTitle(){
        return ldriver.getTitle();
    }





}
