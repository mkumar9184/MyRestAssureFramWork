package api.test;

import java.util.List;

import org.testng.Assert;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utility.DataProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DDTests {



	@org.testng.annotations.Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProvider.class)
	public void testPostuser(String UserID,String UserName, String FirstNmae,String LastName,String Email, String Passowrd, String Phone) {
		
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
	
	
	@org.testng.annotations.Test(priority = 2,dataProvider = "UserData",dataProviderClass = DataProvider.class)
	public void testDeleteUserByName(String UserName) {
				
		Response response = UserEndPoint.deleteUser(UserName);
		response.then().log().all();

		String bodyAsString = response.asString();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(bodyAsString.contains(UserName), true, "Response body contains - 'India'");
		
		

	}
}
