package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	protected int port;

	private WebDriver driver;
	private WebDriverWait waitDriver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.waitDriver = new WebDriverWait(driver, 500);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	@Test
	public void testUnauthorized() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void testSignUpAndLoginAndLogout() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		String username = "jzProg" + UUID.randomUUID().toString(), firstName = "John", lastName = "Zaga", password = "1234";
        signUpPage.setUserInfo(username, password, firstName, lastName);
        signUpPage.signup();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assertions.assertTrue(signUpPage.successSignUp());
		signUpPage.goToLogin();
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserInfo(username, password);
		loginPage.login();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assertions.assertEquals("Home", driver.getTitle());

		HomePage homePage = new HomePage(driver);
		homePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertTrue(new LoginPage(driver).isLogout());
	}

	@Nested
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class NoteTests {

		@BeforeEach
		public void init() {
			System.out.println("SignUp (if needed) note test user...");
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/signup");
			SignUpPage signUpPage = new SignUpPage(driver);
			String username = "noteUser", firstName = "John", lastName = "Zaga", password = "1234";
			signUpPage.setUserInfo(username, password, firstName, lastName);
			signUpPage.signup();
		}

		@Test
		@Order(1)
		public void testNoteAdd() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("noteUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
            homePage.goToNotesTab();
            homePage.addNewNote("title1", "description1");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
            resultPage.goToHome();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			homePage.goToNotesTab();
			Assertions.assertTrue(homePage.isNoteVisible("title1", "description1"));
			homePage.logout();
		}

		@Test
		@Order(2)
		public void testNoteEdit() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("noteUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
			homePage.goToNotesTab();
			homePage.editFirstNote("title1_new", "description1_new");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
			resultPage.goToHome();

			homePage.goToNotesTab();
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isNoteVisible("title1", "description1"));
			Assertions.assertTrue(homePage.isNoteVisible("title1_new", "description1_new"));
			homePage.logout();
		}

		@Test
		@Order(3)
		public void testNoteDelete() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("noteUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
			homePage.goToNotesTab();
			homePage.deleteFirstNote();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
			resultPage.goToHome();

			homePage.goToNotesTab();
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isNoteVisible("title1", "description1"));
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isNoteVisible("title1_new", "description1_new"));
			homePage.logout();
		}
	}

	@Nested
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class CredentialTests {

		@BeforeEach
		public void init() {
			System.out.println("SignUp (if needed) credential test user...");
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/signup");
			SignUpPage signUpPage = new SignUpPage(driver);
			String username = "credUser", firstName = "John", lastName = "Zaga", password = "1234";
			signUpPage.setUserInfo(username, password, firstName, lastName);
			signUpPage.signup();
		}

		@Test
		@Order(1)
		public void testCredentialsAdd() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("credUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
			homePage.goToCredsTab();
			homePage.addNewCredential("google.com", "user1", "123");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
			resultPage.goToHome();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			homePage.goToCredsTab();
			Assertions.assertTrue(homePage.isCredVisible("google.com", "user1", "123"));
			homePage.logout();
		}

		@Test
		@Order(2)
		public void testCredentialsEdit() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("credUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
			homePage.goToCredsTab();
			homePage.editFirstCredential("www.google.com", "user1_new", "1234");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
			resultPage.goToHome();

			homePage.goToCredsTab();
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isCredVisible("google.com", "user1", "123"));
			Assertions.assertTrue(homePage.isCredVisible("www.google.com", "user1_new", "1234"));
			homePage.logout();
		}

		@Test
		@Order(3)
		public void testCredentialsDelete() {
			driver.get("http://localhost:" + CloudStorageApplicationTests.this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setUserInfo("credUser", "1234");
			loginPage.login();
			waitDriver.until(ExpectedConditions.titleIs("Home"));

			HomePage homePage = new HomePage(driver);
			homePage.goToCredsTab();
			homePage.deleteFirstCredential();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assertions.assertEquals("Result", driver.getTitle());

			ResultPage resultPage = new ResultPage(driver);
			Assertions.assertTrue(resultPage.isSuccess());
			resultPage.goToHome();

			homePage.goToCredsTab();
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isCredVisible("google.com", "user1", "123"));
			Assertions.assertThrows(NoSuchElementException.class, () -> homePage.isCredVisible("www.google.com", "user1_new", "1234"));
			homePage.logout();
		}
	}

}
