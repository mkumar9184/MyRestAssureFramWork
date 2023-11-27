package api.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utility.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DDTests {
	
	public Logger logger = LogManager.getLogger(this.getClass());


	@org.testng.annotations.Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProvider.class)
	public void testPostuser(String UserID,String UserName, String FirstNmae,String LastName,String Email, String Passowrd, String Phone) {
		
		//Get setter call
		User userpayload= new User();
		
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstNmae);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Passowrd);
		userpayload.setPhone(Phone);
		
		Response response = UserEndPoint.createUser(userpayload);
		response.then().log().all();
	
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	@org.testng.annotations.Test(priority = 2,dataProvider = "Data",dataProviderClass = DataProvider.class)
	public void testGetuserByName(String UserID,String UserName, String FirstNmae,String LastName,String Email, String Passowrd, String Phone) {
		
		SoftAssert sassert= new SoftAssert();
		logger.info("=============== User Info is start===========");

		User userpayload= new User();
		
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstNmae);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Passowrd);
		userpayload.setPhone(Phone);
		
		Response response = UserEndPoint.readUser(userpayload.getUsername());
		response.then()
		.log().all()
		.extract()
		.response();
		
		JsonPath testid = new JsonPath(response.asString());
		int tempId = testid.getInt("id");
		String  username = testid.getString("username");
		String  firstName = testid.getString("firstName");
		String  lastName = testid.getString("lastName");
		String  email = testid.getString("email");
		String  password = testid.getString("password");
		String  phone = testid.getString("phone");
		
		
		sassert.assertEquals(response.getStatusCode(), 200);
		sassert.assertEquals(userpayload.getId(), tempId);
		sassert.assertEquals(userpayload.getUsername(), username);
		sassert.assertEquals(userpayload.getFirstName(), firstName);
		sassert.assertEquals(userpayload.getLastName(), lastName);
		sassert.assertEquals(userpayload.getEmail(), email);
		sassert.assertEquals(userpayload.getPassword(), password);
		sassert.assertEquals(userpayload.getPhone(), phone);
		
		sassert.assertAll();

		logger.info("=============== User Info is End===========");

	}
	
	@org.testng.annotations.Test(priority = 3,dataProvider = "UserData",dataProviderClass = DataProvider.class)
	public void testDeleteUserByName(String UserName) {
				
		Response response = UserEndPoint.deleteUser(UserName);
		response.then().log().all();

		String bodyAsString = response.getBody().asString();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(bodyAsString.contains(UserName), true, "Response body contains - "+ UserName);
		
		

	}
}
