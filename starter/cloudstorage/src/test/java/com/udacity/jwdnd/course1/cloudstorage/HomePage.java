package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(css = "#logoutDiv button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-notes")
    private WebElement notesTab;

    @FindBy(id = "nav-files")
    private WebElement filesTab;

    @FindBy(id = "nav-credentials")
    private WebElement credsTab;

    @FindBy(id = "addNoteBtn")
    private WebElement addNoteBtn;

    @FindBy(id = "addCredBtn")
    private WebElement addCredBtn;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitBtn;

    @FindBy(id = "credential-url")
    private WebElement cred_url;

    @FindBy(id = "credential-username")
    private WebElement cred_username;

    @FindBy(id = "credential-password")
    private WebElement cred_password;

    @FindBy(id = "credentialSubmit")
    private WebElement credSubmitBtn;

    private JavascriptExecutor javascriptExecutor;
    private WebDriverWait waitDriver;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
        waitDriver = new WebDriverWait(driver, 500);
        this.driver = driver;
    }

    public void goToNotesTab() {
        clickButton(notesTab);
    }

    public void goToFilesTab() {
        clickButton(filesTab);
    }

    public void goToCredsTab() {
        clickButton(credsTab);
    }

    public void addNewNote(String title, String description) {
        clickButton(addNoteBtn);
        completeFields(title, description);
        clickButton(noteSubmitBtn);
    }

    public void editFirstNote(String title, String description) {
        WebElement editBtn = findBy("button", "Edit");
        clickButton(editBtn);
        completeFields(title, description);
        clickButton(noteSubmitBtn);
    }

    public void deleteFirstNote() {
        WebElement deleteBtn = findBy("a", "Delete");
        clickButton(deleteBtn);
    }

    public void addNewCredential(String url, String username, String password) {
        clickButton(addCredBtn);
        completeFields(url, username, password);
        clickButton(credSubmitBtn);
    }

    public void editFirstCredential(String url, String username, String password) {
        WebElement editBtn = findBy("button", "Edit");
        clickButton(editBtn);
        completeFields(url, username, password);
        clickButton(credSubmitBtn);
    }

    public void deleteFirstCredential() {
        WebElement deleteBtn = findBy("a", "Delete");
        clickButton(deleteBtn);
    }

    public boolean isCredVisible(String url, String username, String password) {
        return findBy("th", url) != null || findBy("td", username) != null || findBy("td", password) != null;
    }

    public boolean isNoteVisible(String title, String description) {
        return findBy("th", title) != null || findBy("tr", description) != null;
    }

    private void clickButton(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    private WebElement findBy(String element, String value) {
        return driver.findElement(By.xpath("//"+ element +"[text()='" + value + "']"));
    }

    private void completeFields(String title, String description) {
        javascriptExecutor.executeScript("arguments[0].value='" + title + "';", noteTitle);
        javascriptExecutor.executeScript("arguments[0].value='" + description + "';", noteDescription);
    }

    private void completeFields(String url, String username, String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + url + "';", cred_url);
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", cred_username);
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", cred_password);
    }

    public void logout() {
        clickButton(logoutBtn);
    }
}
