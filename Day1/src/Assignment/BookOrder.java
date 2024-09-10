package Assignment;

public class BookOrder extends Customer {
	private double Orderamount;
	public BookOrder(String CN, String CID,double OrderAmt) {
		super(CN,CID);
		this.Orderamount = OrderAmt;
	}
	public double getOrderamount() {
		return Orderamount;
	}
}
