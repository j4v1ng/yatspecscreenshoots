package screenshootssupport;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

@RunWith(SpecRunner.class)
public class GuiSeleniumAndYatspecTest extends TestState implements WithCustomResultListeners {

    private static final String START_URL = "http://www.google.com";
    protected WebDriver driver;
    protected static WebDriverBackedSelenium selenium;

    @Before
    public void setupSelenium() throws InterruptedException {
        driver = new FirefoxDriver();
        selenium = new WebDriverBackedSelenium(driver, START_URL);
        selenium.open(START_URL);
        selenium.waitForPageToLoad("2000");
    }

    @After
    public void closeSelenium() throws Exception {
        selenium.stop();
    }

    public void takeScreenshoot() throws Exception {
        String imageData = selenium.captureScreenshotToString();
        this.capturedInputAndOutputs.add(ScreenshootHolder.INTERESTING_GIVENS_KEY, new ScreenshootHolder(imageData));
        System.out.println("Captured screen shoot");
    }


    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        String testName = this.getClass().getSimpleName();
        return Arrays.asList((SpecResultListener) new HtmlWithScreenshootResultListener(testName));
    }
}
