package api.test;

import java.util.List;

import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;


public class UserTests {
	Faker faker;
	User userpayload;
	int tempId,messageId;
	String username;
	public Logger logger;

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 8));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		///logs
		
		logger = LogManager.getLogger(this.getClass());

	}

	@org.testng.annotations.Test(priority = 1)
	public void testPostuser() {
	
		logger.info("===============Create User test is start===========");
		Response response = UserEndPoint.createUser(userpayload);
		response.then().log().all()
		.extract()
		.response();
		
		JsonPath testid = new JsonPath(response.asString());

		messageId = testid.getInt("message");
		logger.info("messageId==========="+messageId);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("===============Create User test is Ended===========");
	}

	@org.testng.annotations.Test(priority = 2)
	public void testGetuserByName() {
		logger.info("===============Get User Info is start===========");
		Response response = UserEndPoint.readUser(this.userpayload.getUsername());
		response.then()
		.log().all()
		.extract()
		.response();
		
		JsonPath testid = new JsonPath(response.asString());

		tempId = testid.getInt("id");
		System.out.println("tempId==========="+tempId);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(messageId, tempId);

		logger.info("=============== Get User Info is End===========");

	}
	
	@org.testng.annotations.Test(priority = 3)
	public void testUpdateuserByName() {
		logger.info("=============== Updating User info is start===========");
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		
		Response response = UserEndPoint.updateUser(this.userpayload.getUsername(),userpayload);
		response.then()
		.log().all();
	
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseupda = UserEndPoint.readUser(this.userpayload.getUsername());
		
		responseupda.then()
		.log().all()
		.extract()
			.response();
		
		JsonPath resstring = new JsonPath(responseupda.asString());

		int uid = resstring.getInt("id");
		username =resstring.getString("username");
	
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(this.userpayload.getUsername() , username);
		Assert.assertEquals(tempId, uid);
		
		logger.info("=============== Updating  User info End===========");
	}
	
	@org.testng.annotations.Test(priority = 4)
	public void testDeleteuserByName() {
		logger.info("=============== Delete User Started===========");
		Response response = UserEndPoint.deleteUser(username);
		response.then()
		.log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("=============== Delete User Ended===========");
	}

}
