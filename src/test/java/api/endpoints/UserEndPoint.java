package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint {
	
	//get Url from properties file
	
	static  ResourceBundle  getUrl(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		
		return routes;
	}
	
	public static Response createUser(User payload){
		
		String post_UrlEndpoint =getUrl().getString("post_Url");
		
		System.out.println("post_UrlEndpoint == "+post_UrlEndpoint);
		 
		Response response=
				given()
				.contentType(ContentType.JSON) 
				.accept(ContentType.JSON)
				.body(payload)
		 .when()
		 
		 	.post(Routes.post_Url);// Getting URl through Class file
//		 	.post(post_UrlEndpoint);// Getting URL through Properties File
		
		return response;
	 }
	
	public static Response readUser(String UserName){
		 
		Response response=
				given()
					.pathParam("username", UserName)
				
				.when()
					.get(Routes.get_Url);
		
		return response;
	 }
	
	public static Response updateUser(String UserName, User payload){
		 
		Response response=
				given()
				.contentType(ContentType.JSON) 
				.accept(ContentType.JSON)
				.pathParam("username", UserName)
				.body(payload)
		 .when()
		 	.put(Routes.update_Url);
		
		return response;
	 }
	
	
	public static Response deleteUser(String UserName){
		 		 
		Response response=
				given()
					.pathParam("username", UserName)
				
				.when()
					.get(Routes.delete_Url);
		
		return response;
	 }
}
