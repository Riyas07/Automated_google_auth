package stepDefinitions;

import Service.service;
import Token.generateToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.io.IOException;
import java.util.ArrayList;

public class Authorization_Grant_flow_Stepdef {
    generateToken gnerate_tkn=new generateToken();
    String accessToken="";
    @Given("generate Access and refresh token by user Access code")
    public void generate_access_and_refresh_token_by_user_access_code() throws IOException {
    this.accessToken=  gnerate_tkn.generate_token();
    }
    @When("get the user profile")
    public void get_the_user_profile() {
         sam userDetails=given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when().get(service.get_user_profile_url()).then().statusCode(HttpStatus.SC_OK)
                .body("emailAddress", Matchers.is(Matchers.equalTo("muhammedriyas6262@gmail.com")))
                .extract().response().as(sam.class);
        System.out.println(userDetails.getEmailAddress());

    }
    @Then("send the email to random person")
    public void send_the_email_to_random_person() {
//        String mail="RnJvbTogbXVoYW1tZWRyaXlhczYyNjJAZ21haWwuY29tClRvOiByaXlhc211aGFtbWVkMTgyQGdtYWlsLmNvbQpTdWJqZWN0OiBSZXN0IEFzc3VyZWQgVGVzdAoKaGkgaG93IGFyZSB5b3Ugc2VuZGluZyBmcm9tIEdtYWlsIEFQSQ==";
//        LinkedHashMap map=new LinkedHashMap();
//        map.put("raw",mail);
//      Response response=given()
//              .auth().oauth2(accessToken)
//              .contentType(ContentType.JSON)
//              .body(map)
//              .when().post(service.get_send_email_url());
        //System.out.println(response.getBody().prettyPrint());
    }
ArrayList list=new ArrayList();
    @Then("list out all the messages send bu user")
    public void listOutAllTheMessagesSendBuUser() {
        Response response=given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .pathParam("userId","muhammedriyas6262@gmail.com")
                .when()
                .get("https://gmail.googleapis.com/gmail/v1/users/{userId}/messages");
       int size= response.jsonPath().get("messages.size()");
        for (int i=0;i<size;i++)
        {
           String ids= response.jsonPath().getString("messages["+i+"].id");
            list.add(ids);
        }
    }
 ArrayList<Object> sorted=new ArrayList();
    @Then("sort the message send to riyasmuhammed{int} mail id message")
    public void sortTheMessageSendToRiyasmuhammedMailIdMessage(int arg0) {
      for (int i=0;i<30;i++)
       {
       try
       {
           String ids= given()
                   .pathParam("userId","muhammedriyas6262@gmail.com")
                   .pathParam("id",list.get(i))
                   .auth().oauth2(accessToken)
                   .when()
                   .get("https://gmail.googleapis.com/gmail/v1/users/{userId}/messages/{id}").then().
                   extract().response().jsonPath().getString("snippet");
           if(ids.equals("hi how are you sending from Gmail API"))
           {
               sorted.add(list.get(i));
           }
       }
       catch (Exception e)
       {
           continue;
       }
      }
    }
//"snippet": "hi how are you sending from Gmail API"
    @Then("delete the message")
    public void deleteTheMessage() {
       for (int i=0;i<sorted.size();i++)
       {
          Response response= given()
                   .pathParam("userId","muhammedriyas6262@gmail.com")
                   .pathParam("id",list.get(i))
                   .auth().oauth2(accessToken)
                   .when().delete("https://gmail.googleapis.com/gmail/v1/users/{userId}/messages/{id}");
           System.out.println(response.statusCode());
       }
    }
}
