package StepDefinitions;

import HelperLibrary.DataReader;
import com.fasterxml.jackson.databind.JsonNode;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.pmw.tinylog.Logger;

public class BaseStepDefinitions {

    public RequestSpecification requestSpecification;
    public JsonNode envData;
    private String accessToken;

    @Before
    public void beforeScenario() throws Exception{
        Logger.info("Hook for Setup invoked");
        DataReader dataReader = new DataReader();
        envData = dataReader.getEnvironmentData().get("dev");

        Response response = RestAssured.given()
                .baseUri(envData.get("auth-base-uri").asText())
                .filter(new RequestLoggingFilter())
                .relaxedHTTPSValidation()
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParams("grant_type", "password",
                        "client_id", envData.get("auth-client-id").asText(),
                                                "username", envData.get("auth-username").asText(),
                                                "password", envData.get("auth-password").asText())
                .post(envData.get("auth-endpoint").asText());

        accessToken = "Bearer " + response.getBody().jsonPath().get("access_token");

        requestSpecification = RestAssured.with().given()
                .relaxedHTTPSValidation()
                .header("Authorization", accessToken)
//                .filter(new ResponseLoggingFilter())
                .filter(new RequestLoggingFilter());
    }

    @After
    public void afterScenario() throws Exception{
        Logger.info("Hook for Teardown invoked");
        requestSpecification = null;
        envData = null;
    }

}
