package org.example.automation.stepsdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.automation.utils.ApiHelper;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiSteps {

    private ApiHelper apiHelper;
    private static Map<String, Object> context = new HashMap<>();
    private Response response;

    @Given("the API base URL is {string}")
    public void setApiBaseUrl(String baseUrl) {
        apiHelper = new ApiHelper(baseUrl);
    }

    @When("I send a POST request to {string} with body:")
    public void sendPostRequest(String endpoint, String body) {
        response = apiHelper.post(endpoint, body);
    }

    @Then("the response status code should be {int}")
    public void validateHttpStatusCode(int statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode);
    }

    @Then("I store the value of {string} as {string}")
    public void storeToken(String from, String to) {
        String value = response.jsonPath().getString(from);
        context.put(to, value);
    }

    @Given("I use the stored token {string}")
    public void iUseTheStoredToken(String tokenKey) {
        apiHelper.setToken(context.getOrDefault(tokenKey, "").toString());
    }

    @When("I send a GET request to {string}")
    public void sendPostRequest(String endpoint) {
        response = apiHelper.get(endpoint);
    }

    @And("the response should contain {string} in the {string} field")
    public void theResponseShouldContainInTheField(String value, String key) {
        String userName = response.jsonPath().getString(key);
        Assert.assertEquals(userName, value);
    }

    @Then("the response should match the schema {string}")
    public void validateJsonSchema(String schemaFile) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/" + schemaFile));
        System.out.println("✅ JSON schema validado correctamente: " + schemaFile);
    }
}
