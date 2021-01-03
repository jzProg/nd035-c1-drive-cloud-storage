package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement submitBtn;

    @FindBy(id = "logoutText")
    private WebElement logoutText;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setUserInfo(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public void login() {
        submitBtn.click();
    }

    public boolean isLogout() {
        return logoutText.isDisplayed();
    }
}
