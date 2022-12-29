import Backend.Data.Variables;
import Backend.Models.ResponseModel.ErrorResRecord;
import Backend.Models.ResponseModel.UserResRecord;
import Backend.Models.ResponseModel.TokenResRecord;
import Backend.Steps.Request;
import Frontend.Steps.BookPageSteps;
import Frontend.Steps.BookStorePageSteps;
import Frontend.Steps.LoginPageSteps;
import Frontend.Steps.ProfilePageSteps;
import Frontend.Utils.AllureConfig;
import Frontend.Utils.Configurations;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(AllureConfig.class)
@Epic("User authorization check and Operations After Authorization")
@Feature("User authorization check with API. Operations After These!")
public class Login extends Configurations {
    //Rest Assured
    Response response;
    Request request = new Request();
    UserResRecord userResRecord;
    TokenResRecord tokenResRecord;
    ErrorResRecord errorResRecord;
    Variables variables = new Variables();

    //Selenide
    LoginPageSteps loginSteps = new LoginPageSteps(options);
    ProfilePageSteps profileSteps = new ProfilePageSteps(options);
    BookStorePageSteps bookStorePageSteps = new BookStorePageSteps(options);
    BookPageSteps bookPageSteps = new BookPageSteps(options);

    //-----------------------------------Rest Assured----------------------------------------------
    @Story("User auth with invalid password check")
    @Test(description = "User auth with valid username and invalid password", priority = 1)
    public void failLoginWithPassword() throws IOException {
        //registration of new user
        response = request.sendRequest(variables.baseURIUser, request.getJsonData(variables.name, variables.validpassword));
        userResRecord = response.as(UserResRecord.class);

        //generate token and authorize
        response = request.sendRequest(variables.baseURIToken, request.getJsonData(variables.name, variables.validpassword));
        tokenResRecord = response.as(TokenResRecord.class);
        Assert.assertEquals(tokenResRecord.result(), variables.result);

        //check if user is authorized
        response = request.sendRequest(variables.baseURIAuth, request.getJsonData(variables.name, variables.invalidPassword));
        errorResRecord = response.as(ErrorResRecord.class);
        Assert.assertEquals(errorResRecord.code(), variables.code);
    }

    @Story("User auth with invalid username check")
    @Test(description = "User auth with invalid username and valid password", priority = 2)
    public void failLoginWithUsername() throws IOException {
        //check if user is authorized
        response = request.sendRequest(variables.baseURIAuth, request.getJsonData(variables.invalidName, variables.validpassword));
        errorResRecord = response.as(ErrorResRecord.class);
        Assert.assertEquals(errorResRecord.message(), variables.message);
    }



    @Story("User auth with invalid credentials check")
    @Test(description = "User auth with invalid username and invalid password", priority = 3)
    public void failLoginWithUsernameAndPassword() throws IOException {
        //check if user is authorized
        response = request.sendRequest(variables.baseURIAuth, request.getJsonData(variables.invalidName, variables.invalidPassword));
        errorResRecord = response.as(ErrorResRecord.class);
        Assert.assertEquals(errorResRecord.code(), variables.code);
        Assert.assertEquals(errorResRecord.message(), variables.message);
    }

    @Story("User auth with valid credentials check")
    @Test(description = "User auth with valid username and password", priority = 4)
    public void successLogin() throws IOException {
        //registration of new user
        response = request.sendRequest(variables.baseURIUser, request.getJsonData(variables.validName, variables.validpassword));
        userResRecord = response.as(UserResRecord.class);

        //generate token and authorize
        response = request.sendRequest(variables.baseURIToken, request.getJsonData(variables.validName, variables.validpassword));
        tokenResRecord = response.as(TokenResRecord.class);
        Assert.assertEquals(tokenResRecord.result(), variables.result);

        //check if user is authorized
        response = request.sendRequest(variables.baseURIAuth, request.getJsonData(variables.validName, variables.validpassword));
        Assert.assertTrue(Boolean.parseBoolean(response.getBody().asPrettyString()));
    }

    @Story("Existing user registration check")
    @Test(description = "Register existing username and password", dependsOnMethods = {"successLogin"})
    public void failLoginWithExistingUsername() throws IOException {
        //registration of existing user
        response = request.sendRequest(variables.baseURIUser, request.getJsonData(variables.validName, variables.validpassword));
        errorResRecord = response.as(ErrorResRecord.class);
        Assert.assertEquals(errorResRecord.code(), variables.existCode);
        Assert.assertEquals(errorResRecord.message(), variables.existMessage);
    }

    //--------------------------------------Selenide--------------------------------------------
    @Story("Successful Operations With Registered User!")
    @Test(description = "Testing Performance Of User. Adding, Deleting And Validating Books!", priority = 6)
    public void login_and_operations()
    {
        //Login
        loginSteps.setUserName() //Beginning Of Shalvas Part
                .setPassword()
                .login();

        //Navigate to bookStore
        profileSteps.navigateToBookstore();

        //Navigate to first book
        bookStorePageSteps.navigateToBook(0);

        //Add first book to collection, validate popup and return back
        bookPageSteps.addBookToCollection()
                .validatePopup()
                .navigateToBookStore();

        //Navigate to second book
        bookStorePageSteps.navigateToBook(1);

        //Add second book to collection, validate popup and navigate to profile
        bookPageSteps.addBookToCollection()
                .validatePopup()    //Ending Of Shalvas Part
                .goBackToProfile();  //Beginning Of Ninos Part

        profileSteps.validateBookTitle(0, bookStorePageSteps.bookTitles.get(0))
                .validateBookTitle(1, bookStorePageSteps.bookTitles.get(1))
                .deleteBook()
                .bookDeletionFinalization()
                .bookDeletionPopup()
                .validateCollectionSize(1)
                .deleteBook()
                .bookDeletionFinalization()
                .bookDeletionPopup()
                .validateCollectionSize(0); //Ending Of Ninos Part
        //Ending Of Rest Assured And Selenide
    }
}
