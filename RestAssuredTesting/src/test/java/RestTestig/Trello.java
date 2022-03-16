package RestTestig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trello {
	public static String baseurl = "https://api.trello.com";
	public static String id;
	 
	@Test(priority =0)
	
	public void createBoard() 
	{
	RestAssured.baseURI = baseurl;
	
	
	Response response =  given()
	.queryParam("name", "Eclipse Board")
	.queryParam("key", "2dd2083919e808744e8fa76b0d8c0021")
	.queryParam("token","14d2b2fc7a6afd363bd21e7883e943911d516bf589078e2ec451e3a20af18673")
	.header("Content-Type","application/json")
	
	
	.when()
	.post("/1/boards/")
	
	.then()
	.assertThat().statusCode(200).contentType(ContentType.JSON)
	.extract().response();
	//this is to fetch the response as string
	String jsonresponse = response.asString();
	//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
	JsonPath js = new JsonPath(jsonresponse);
	//now i have to fetch the id
	id = js.get("id");
	System.out.println(id);
	
	
	
	}
	@Test(priority = 1)

	public void getBoard()
	
	{
		RestAssured.baseURI = baseurl;
		
		given()
		
		.queryParam("key", "2dd2083919e808744e8fa76b0d8c0021")
		.queryParam("token","14d2b2fc7a6afd363bd21e7883e943911d516bf589078e2ec451e3a20af18673")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	}
	
	@Test(priority = 2)

	public void DeleteBoard()
	
	{
		RestAssured.baseURI = baseurl;
		
		given()
		
		.queryParam("key", "2dd2083919e808744e8fa76b0d8c0021")
		.queryParam("token","14d2b2fc7a6afd363bd21e7883e943911d516bf589078e2ec451e3a20af18673")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	
}
}
