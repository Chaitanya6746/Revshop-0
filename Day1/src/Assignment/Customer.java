package Assignment;

public class Customer {
	private String CustomerName;
	private String CustomerID;
	public Customer(String CN,String CID) {
		this.CustomerID = CID;
		this.CustomerName = CN;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public String getCustomerID() {
		return CustomerID;
	}
}


