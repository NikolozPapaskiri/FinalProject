package Steps;

import Data.TokenOutput;
import Data.UserOutput;
import Endpoints.Endpoints;
import io.ous.jtoml.impl.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserTestSteps {

    //გადაეცემა Map-ი რომელიც შემდგომში გადაიქცევა reqsest body-დ, მეთოდი აბრუნებს response-ის კლასის instance-ს რასაც ტესტ კლასსში ვადარებთ
    public static UserOutput createUser(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody<Response> response = httpRequest.contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(Endpoints.user);

        return response.as(UserOutput.class);
    }

    //გადაეცემა Map-ი რომელიც შემდგომში გადაიქცევა reqsest body-დ, მეთოდი აბრუნებს response-ის კლასის instance-ს რასაც ტესტ კლასსში ვადარებთ
    public static TokenOutput generateToken(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody<Response> response = httpRequest.contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(Endpoints.generateToken);

        return response.as(TokenOutput.class);
    }

    //გადაეცემა Map-ი რომელიც შემდგომში გადაიქცევა reqsest body-დ, მეთოდი აბრუნებს boolean-ის კლასის instance-ს რასაც ტესტ კლასსში ვადარებთ
    public static boolean checkAuthorized(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();

        return httpRequest.contentType(ContentType.JSON)
                    .body(data)
                    .when()
                    .post(Endpoints.authorized)
                    .then()
                    .extract()
                    .response()
                    .as(Boolean.class);
    }
}
