package model;

public class Seller {
	private int SellerId;
	private String business_name;
	private String bus_adress;

    // Getters and Setters
    public int getSellerId() {
        return SellerId;
    }
    public void setSellerId(int sellerId) {
		SellerId = sellerId;
	}

	public void setBusinessName(String business_name) {
		// TODO Auto-generated method stub
		this.business_name = business_name;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusinessAdress(String bus_adress) {
		// TODO Auto-generated method stub
		this.bus_adress = bus_adress;
	}
	public String getBus_adress() {
		return bus_adress;
	}
}
