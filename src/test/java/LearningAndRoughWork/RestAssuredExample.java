package LearningAndRoughWork;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.Base64;

public class RestAssuredExample {
	
	
	public static void detailedHeaderExample()
	{
		try {
			String fileName=RestAssuredExample.class.getClassLoader().getResource("Number.xml").getFile();
			 File requestBody = new File(fileName);
			
			Response response= given()
			.baseUri("http://www.dneonline.com")
			.basePath("/calculator.asmx")
			.header("Content-Type", "text/xml; charset=utf-8")
			.header("Accept", "application/xml")
			.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("username:password".getBytes()))
            // User-Agent: Identifies the client software initiating the request
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            // Cache-Control: Directives for caching mechanisms in both requests and responses
            .header("Cache-Control", "no-cache")
            // Cookie: Sends cookies from the client to the server
            .header("Cookie", "name=value; name2=value2")
            // Accept-Encoding: Informs the server which content-encoding the client can handle
            .header("Accept-Encoding", "gzip, deflate")
            // Connection: Controls whether the network connection stays open after the current transaction finishes
            .header("Connection", "keep-alive")
            // Host: Specifies the domain name of the server and (optionally) the TCP port number on which the server is listening
            .header("Host", "www.dneonline.com")
            // Referrer: The address of the previous web page from which a link to the currently requested page was followed
            .header("Referer", "http://www.example.com")
            // X-Requested-With: Mainly used to identify Ajax requests. Most JavaScript frameworks send this field with value of XMLHttpRequest
            .header("X-Requested-With", "XMLHttpRequest")
            // If-Modified-Since: Makes the request conditional; the server sends back the requested resource only if it has been last modified after the given date
            .header("If-Modified-Since", "Wed, 21 Oct 2015 07:28:00 GMT")
            .body(requestBody)
            .post();
			
	        System.out.println(response.getStatusCode());
	        System.out.println(response.getBody().asString());
			
		} catch (Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
		detailedHeaderExample();
		
	}

}
