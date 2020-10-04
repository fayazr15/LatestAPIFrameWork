package com.rmgyantra.api.projecttest;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rmgyantra.api.pojoClassLib.Project;
import com.rmgyantra.genericlib.BaseClass;
import com.rmgyantra.genericlib.DataBaseUtilities;
import com.rmgyantra.genericlib.IEndPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateProject_dataProviderTest extends BaseClass{
	
	

	@Test(dataProvider = "getData")
	public void createProjectTest(String actAPIProjectNAme, String projectStatus) throws Throwable {
	
        Project pObj = new Project(actAPIProjectNAme, "aug", "deepak", projectStatus, 12);
		
   	 
      Response resp =  given()
                        .contentType(ContentType.JSON)
                        .body(pObj)
                      .when()
                      	.post(IEndPoints.addSinglePRoject);
			   resp.then()
				       .assertThat().statusCode(201)
				       .and()
				       .assertThat().contentType(ContentType.JSON)
				       .and()
				         .log().all();
       String scuMg = resp.jsonPath().get("msg");
       Assert.assertEquals(scuMg, "Successfully Added");
       
              //Connect to dataDase
       String dbProjectNAme = DataBaseUtilities.executeQueryAndGetData("select *from project", 4, actAPIProjectNAme);
       Assert.assertEquals(dbProjectNAme, actAPIProjectNAme);
       
	
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[4][2];
		objArr[0][0] = "IDBS-0";
		objArr[0][1] = "Completed";
		
		objArr[1][0] = "IDBS-1";
		objArr[1][1] = "Completed";
		
		objArr[2][0] = "IDBS-2";
		objArr[2][1] = "Completed";
		
		objArr[3][0] = "IDBS-3";
		objArr[3][1] = "Completed";
		
		return objArr;
		
	}

}













