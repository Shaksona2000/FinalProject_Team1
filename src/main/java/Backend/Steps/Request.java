package Backend.Steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import Backend.Data.Variables;
import Backend.Models.RequestModel.AuthReqModel;
import io.qameta.allure.Step;

public class Request {
    @Step("Send request to baseuri {0} with body {1}")
    public Response sendRequest(String baseuri, String param){
        return  new RestAssured().given()
                .filter(new AllureRestAssured())
                .baseUri(baseuri)
                .contentType(ContentType.JSON)
                .when()
                .body(param)
                .post();
    }

    @Step("Get json data")
    public String getJsonData(String name, String password) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AuthReqModel user = new AuthReqModel();
        user.setUserName(name);
        user.setPassword(password);

        return mapper.writeValueAsString(user);
    }
}
