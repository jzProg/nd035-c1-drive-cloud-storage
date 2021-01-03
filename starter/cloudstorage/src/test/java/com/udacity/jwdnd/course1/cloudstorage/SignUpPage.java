package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement submitBtn;

    @FindBy(id = "goToLoginBtn")
    private WebElement goToLoginBtn;

    @FindBy(id = "signupText")
    private WebElement signupText;

    private JavascriptExecutor javascriptExecutor;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void setUserInfo(String username, String password, String firstName, String lastName) {
       this.username.sendKeys(username);
       this.password.sendKeys(password);
       this.firstName.sendKeys(firstName);
       this.lastName.sendKeys(lastName);
    }

    public void signup() {
        submitBtn.click();
    }

    public void goToLogin() {
        javascriptExecutor.executeScript("arguments[0].click();", goToLoginBtn);
    }

    public boolean successSignUp() {
        return signupText.isDisplayed();
    }
}
