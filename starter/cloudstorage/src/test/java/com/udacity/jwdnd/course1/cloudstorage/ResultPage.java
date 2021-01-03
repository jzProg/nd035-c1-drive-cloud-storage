package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "goToHomeBtn")
    private WebElement goToHomeBtn;

    @FindBy(className = "alert-success")
    private WebElement successText;

    private JavascriptExecutor javascriptExecutor;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void goToHome() {
        javascriptExecutor.executeScript("arguments[0].click();", goToHomeBtn);
    }

    public boolean isSuccess() {
        return successText.isDisplayed();
    }
}
