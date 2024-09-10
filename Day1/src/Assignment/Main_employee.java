package Assignment;

public class Main_employee {
	public static void main(String[] args) {
		Employee ep = new Employee();
		ep.setId(101);
		ep.setName("Chaitanya");
		ep.setSalary(60000);
		Manager mn = new Manager();
		mn.setDept("IT Solutions");
		System.out.println("Display ID :"+ep.getId());
		System.out.println("Display Name :"+ep.getName());
		System.out.println("Display Salary :"+ep.getSalary());
		System.out.println("Display Dept :"+mn.getDept());
		
	}
}
