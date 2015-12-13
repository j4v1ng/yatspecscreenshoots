import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screenshootssupport.GuiSeleniumAndYatspecTest;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class InspiringTest extends GuiSeleniumAndYatspecTest {

    public static final String URL = "http://www.eduro.com/";

    @Test
    public void shouldScrapTheQuoteOfTheDay() throws Exception {
        givenWeAreAtTheQuotesWebsite();
        whenWeScrapTheQuote();
        thenTheQuoteIsNotEmpty();
    }

    private void givenWeAreAtTheQuotesWebsite() throws Exception {
        interestingGivens.add(URL);
        driver.get(URL);
        takeScreenshoot();
    }

    private void whenWeScrapTheQuote() {
        WebElement dailyquote = driver.findElement(By.tagName("dailyquote"));
        capturedInputAndOutputs.add("Captured daily quote", dailyquote.getText());
    }

    private void thenTheQuoteIsNotEmpty() {
        String quote = capturedInputAndOutputs.getType("Captured daily quote", String.class);
        assertThat(quote, not(isEmptyOrNullString()));
    }
}
