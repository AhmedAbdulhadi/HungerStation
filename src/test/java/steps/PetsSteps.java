package steps;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.Category;
import entities.Pet;
import entities.Tags;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import joptsimple.internal.Strings;
import resources.EEndpoints;
import resources.HttpMethod;
import resources.TestData;
import utils.Helper;
import utils.RequestBuilder;


public class PetsSteps extends RequestBuilder{
	/*
	 * identify all global data members
	 */
	private RequestSpecification objRequest;
	private Response response;
	private TestData objData = new TestData();
	private JsonPath objResponse;
	private String strResponse;
	private Helper helper = new Helper();
	private HashMap<Object, Object> body = new HashMap<Object, Object>();
	private static String placeId;
	private Pet objPet = new Pet();
	

	
	@Given("^Pet Request payload$")
    public void add_pet_payload() throws Throwable {
				
		/**
		 * When we are extracting the spec builder it will return an object the request builder
		 * So now we have spec builder and body information.	
		 */
		
		//This will return an object of spec builder including the base url and params
		//define the body
	
		 objRequest = given().spec(setUpBuilder())

		.body(GenerateStringFromResource("src//test//java//resources//addPet.json")).contentType(ContentType.JSON);

    }

	
	@Given("^Pet Request Param ID is (.*)$")
    public void pet_request_param(String id)throws Throwable {
				
		 objRequest = given().spec(setUpBuilder()).pathParam("id", id);


    }
	
	@Given("^Pet status is (.*)$")
    public void get_pet_by_status(String status) throws Throwable {
				
		 objRequest = given().spec(setUpBuilder()).queryParam("status", status);

    }
	
    @When("^User wants to call \"(.*)\" with http method \"(.*)\"$")
    public void user_wants_to_call_request_with_httpMethod(String endpoint, String method) throws Throwable {
    	//Preparing the endpoint
    	EEndpoints eResources = EEndpoints.valueOf(endpoint);
    	HttpMethod httpMethod = HttpMethod.valueOf(method);
    	
    	System.out.println(eResources.getResource());
    	
    	switch (httpMethod) {
		case POST:
			System.out.println(httpMethod);
			response = objRequest.when().
        	post(eResources.getResource());
			break;

		case GET:
			System.out.println(httpMethod);
			response = objRequest.when().
        	get(eResources.getResource());
			break;
			
		case PUT:
			System.out.println(httpMethod);
			response = objRequest.when().
        	put(eResources.getResource());
			break;
		
		case DELETE:
			System.out.println(httpMethod);
			response = objRequest.when().
        	delete(eResources.getResource());
			break;
			
		default:
			System.out.println("ERROR , RAHAF IS THE BEST");
			break;
		}
    	
    }
     
    @Then("^Status code should be (.*)$")
    public void status_code_should_be_200(int code) throws Throwable {
    	
    	strResponse = response.then().assertThat().statusCode(code).
		extract().response().asString();
    	
    	//This will contains the response body as JsonPath
		objResponse = new JsonPath(strResponse);
		
		//Extracr placeId to be used in delete scenario
		placeId = objResponse.getString("place_id");
    }

    @And("(.*) is response body should equal (.*)")
    public void something_is_response_body_should_equal_something(String key, String expectedValue) throws Throwable {
    
    	assertEquals(helper.getJsonPathValue(response,key), expectedValue);
    
    }
    
    @And("verify created name is (.*) using (.*)")
    public void verify_create_name_is(String expectedName, String endpoint) throws Throwable {
		 objRequest = given().spec(setUpBuilder().queryParam("place_id", helper.getJsonPathValue(response, "place_id")));
		 
		 user_wants_to_call_request_with_httpMethod(endpoint,"GET");
		 assertEquals(expectedName, helper.getJsonPathValue(response, "name"));
    	
    }
    
//    @And("^Request Query Param key is \"(.*)\" and value is \"(.*)\"$")
//    public void add_request_query_param(String key, String value) throws Throwable {
//		 objRequest = given().spec(setUpBuilder().queryParam("place_id", helper.getJsonPathValue(response, "place_id")));
//		 
//		 user_wants_to_call_request_with_httpMethod(endpoint,"GET");
//		 assertEquals(expectedName, helper.getJsonPathValue(response, "name"));
//    	
//    }
    
    @Given("^Delete created place payload$")
    public void delete_created_place() throws IOException {
    	body.put("place_id", placeId);
		 objRequest = given().spec(setUpBuilder().body(body));
    	
    }
    
    public static String GenerateStringFromResource(String path) throws IOException {
        //Read json file method.
    	// Return json file as string
    	
    	return new String(Files.readAllBytes(Paths.get(path)));
    }

}
