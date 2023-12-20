package Token;

import Service.service;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import java.io.IOException;

public class generateToken {
    public String generate_token() throws IOException {
       String access_token= given()
               .config(config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL))).
                config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("client_id",service.get_client_id())
                .formParam("client_secret",service.get_client_secret())
                .formParam("grant_type",service.get_grant_type())
                .formParam("refresh_token",service.get_refresh_token())
                .when()
                .post(service.get_refresh_token_url()).then().statusCode(200)
                .body("token_type", Matchers.equalTo("Bearer"))
                .extract().response().jsonPath().getString("access_token");
      return access_token;
    }

}
