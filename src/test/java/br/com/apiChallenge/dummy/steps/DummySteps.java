package br.com.apiChallenge.dummy.steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import br.com.apiChallenge.dummy.routes.Routes;
import br.com.apiChallenge.dummy.tdm.Employee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DummySteps implements En{
	Map<String, String> map;
	JsonPath  json;
	Response response;
	public DummySteps() {
		
		When("realizo o cadastro de um novo empregado",(DataTable data)->{
			map = data.asMap(String.class, String.class);
			String payload = "{\n" +
			            "  \"name\": \""+map.get("nome")+"\",\n" +
			            "  \"salary\": \""+map.get("salario")+"\",\n" +
			            "  \"age\": \""+map.get("idade")+"\"\n" +
			            "}";
			RestAssured.baseURI = Routes.urlBase;
			response = given().urlEncodingEnabled(true).body(payload)
	            .header("Accept", ContentType.JSON.getAcceptHeader())
	            .post(Routes.routeCreate);
			response.then().statusCode(200);
			json = response.andReturn().jsonPath();
			assertTrue(json.get("status").equals("success"));
			Employee.setName(json.get("data.name"));
			Employee.setSalary(json.get("data.salary"));
			Employee.setAge(json.get("data.age"));
		});
		
		Then("valido os dados retornados no cadastrado",()->{
			assertTrue(map.get("nome").equals(Employee.getName()));
			assertTrue(map.get("salario").equals(Employee.getSalary()));
			assertTrue(map.get("idade").equals(Employee.getAge()));
			Employee.setId(json.get("data.id").toString());

		});
		
		When("consulto o empregado cadastrado",()->{
			System.out.println(Employee.getId());
			RestAssured.baseURI = Routes.urlBase;
			response = given()
					.urlEncodingEnabled(true)
					.header("Accept", ContentType.JSON.getAcceptHeader())
					.param("",Employee.getId())
					.get(Routes.routeEmployee);
			response.then().statusCode(200);
			json = response.andReturn().jsonPath();
			assertTrue(json.get("status").equals("success"));
			
		});
		
		Then("valido os dados do empregado",()->{
			assertTrue(Employee.getId().equals(json.get("data.id")));
			assertTrue(Employee.getName().equals(json.get("data.employee_name")));
			assertTrue(Employee.getSalary().equals(json.get("data.employee_salary")));
			assertTrue(Employee.getAge().equals(json.get("data.employee_age")));
		});
		
		Then("excluo o empregado cadastrado",()->{
			RestAssured.baseURI = Routes.urlBase;
			response = given()
					.urlEncodingEnabled(true)
					.header("Accept", ContentType.JSON.getAcceptHeader())
					.param("",Employee.getId())
					.delete(Routes.routeDlete);
			response.then().statusCode(200);
			json = response.andReturn().jsonPath();
			assertTrue(json.get("status").equals("success"));
			assertTrue(json.get("message").equals("successfully! deleted Records"));
		});
		
	}
	

}
