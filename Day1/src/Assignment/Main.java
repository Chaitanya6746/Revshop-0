package Assignment;

public class Main {
	public static void main(String[] args) {
		Animal dog = new Dog();
		Animal cow = new Cow();
		dog.sound();
		dog.eat();
		cow.eat();
		cow.sound();
	}
}
