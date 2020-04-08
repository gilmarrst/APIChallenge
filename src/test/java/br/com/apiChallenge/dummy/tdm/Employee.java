package br.com.apiChallenge.dummy.tdm;

public class Employee {

	private static String name;
	private static String age;
	private static String salary;
	private static String id;
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Employee.name = name;
	}
	public static String getAge() {
		return age;
	}
	public static void setAge(String age) {
		Employee.age = age;
	}
	public static String getSalary() {
		return salary;
	}
	public static void setSalary(String salary) {
		Employee.salary = salary;
	}
	public static String getId() {
		return id;
	}
	public static void setId(String id) {
		Employee.id = id;
	}
	
}
