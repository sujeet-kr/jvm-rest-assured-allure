package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;

import org.pmw.tinylog.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnvexApi {

    private BaseStepDefinitions baseStepDefinitions;
    private Response response;

    public ConnvexApi(BaseStepDefinitions baseStepDefinitions){
        this.baseStepDefinitions = baseStepDefinitions;
    }

    @Then("^I recieve a \"([^\"]*)\" status$")
    public void iRecieveAStatus(String statusCode) throws Throwable {
        assertThat(response.getContentType()).contains("application/json");
        assertThat(response.statusCode()).isEqualTo(Integer.parseInt(statusCode));
    }

    @Given("^that I am connected to the test environment$")
    public void thatIAmConnectedToTheTestEnvironment() throws Throwable {
        Logger.info("Given Condition");
        baseStepDefinitions.requestSpecification
                .baseUri(baseStepDefinitions.envData.get("base-uri").asText());
    }

    @When("^I perform a GET request to the \"([^\"]*)\" endpoint$")
    public void iPerformAGETRequestToTheEndpoint(String endPoint) throws Throwable {
        Logger.info("GET Request made to endpoint " + endPoint);
        response = baseStepDefinitions.requestSpecification.get(endPoint);
    }


    @And("^the response contains the id, geometry, coordinates and type fields$")
    public void theResponseContainsTheIdGeometryCoordinatesAndTypeFields() throws Throwable {
        assertThat(response.body().asString()).contains("\"geometry\":", "\"coordinates\":", "\"type\":", "\"_id\":");
    }

    @And("^the response contains the id, geometry, coordinates, type, description, name and markForDeletion fields$")
    public void theResponseContainsTheIdGeometryCoordinatesTypeDescriptionNameAndMarkForDeletionFields() throws Throwable {
        assertThat(response.body().asString()).contains("\"geometry\":", "\"coordinates\":", "\"type\":", "\"_id\":", "\"description\":", "\"name\":", "\"markForDeletion\":" );
    }
}
