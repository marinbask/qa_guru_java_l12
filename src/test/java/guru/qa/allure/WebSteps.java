package guru.qa.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Search for {repo}")
    public void searchForRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Click on repository link with {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Click on tab Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check of existence issue with name {issueName}")
    public void shouldSeeIssueWithName(String issueName) {
        $(withText(issueName)).should(Condition.exist);
    }
}
