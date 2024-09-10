package Assignment;

public class Cow implements Animal {

	@Override
	public void sound() {
		System.out.println("Cow moo");
		
	}

	@Override
	public void eat() {
		System.out.println("Cow eats grass.");
		
	}

}
