package RestTestig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Ecommerce {
	
	public static String baseurl= "https://ecommerceservice.herokuapp.com/";
	public static String message;
	public static String accessToken;
	public static String Delid;
	@Test(priority = 0,enabled = false)
	
	public void Signup()
	{
		RestAssured.baseURI = baseurl;
		String Responsebody = "{\r\n"
				+ "	\"email\": \"Ab1223@gmail.com\",\r\n"
				+ "	\"password\": \"saddhu@47\"\r\n"
				+ "}\r\n"
				+ "";
		Response response = given()
				.header("Content-Type","application/json")
				.body(Responsebody)
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
		JsonPath js = new JsonPath(jsonresponse);
		//now i have to fetch the id
		message = js.get("message");
		System.out.println(message);
	}
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI = baseurl;
		String Responsebody = "{\r\n"
				+ "	\"email\": \"Ab123@gmail.com\",\r\n"
				+ "	\"password\": \"saddhu@47\"\r\n"
				+ "}\r\n"
				+ "";
		Response response = given()
				.header("Content-Type","application/json")
				.body(Responsebody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
		JsonPath js = new JsonPath(jsonresponse);
		//now i have to fetch the id
		accessToken = js.get("accessToken");
		System.out.println(accessToken);
   
    
    }
	
	@Test(priority = 2)
	public void GetAllUser()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given()
				.header("Authorization","Bearer "+accessToken)
				
				.header("Content-Type","application/json")
				
				
				.when()
				.get("/user")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		
		/*String jsonresponse = response.asString();
		//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
		JsonPath js = new JsonPath(jsonresponse);
		//now i have to fetch the id
		Delid = js.get("users/_id");
		System.out.println(Delid);*/
		
		
   
    
    }

	
	/*public void Delete()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given()
				.header("Authorization","Bearer "+accessToken)
				
				.header("Content-Type","application/json")
				
				
				.when()
				.delete("user/"+ Delid)
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		
		
}*/
	
}
