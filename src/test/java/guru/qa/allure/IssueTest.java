package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String ISSUE_NAME = "e.sh";

    @Test
    public void cleanSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();

        $(By.linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    @Test
    public void lambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main github page", () -> {
            open("https://github.com/");
        });
        step("Search for repository " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Click on link with repo " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Click on Issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Check of existence issue with name " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }

    @Test
    public void annotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(ISSUE_NAME);
    }
}
