package Assignment;

public class BookStore {
	static {
		System.out.println("Book Order Details for Customer: ");
	}
	public static void main(String[] args) {
		BookOrder Info = new BookOrder("Deepak Jha","TYP00145",452.35);
		System.out.println("Customer Name: "+Info.getCustomerName());
		System.out.println("Customer ID: "+Info.getCustomerID());
		System.out.println("Order Amount: "+Info.getOrderamount());
	}
}
