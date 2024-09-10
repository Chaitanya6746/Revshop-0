package Assignment;

public class Dog implements Animal {

	@Override
	public void sound() {
		System.out.println("Dog barks.!");
	}

	@Override
	public void eat() {
		System.out.println("Dog Eats bone.");
	}
	
}
