package Assignment;

public class BankingSystemInitializer {
	long nonStaticBankid;
	String nonStaticBankName;
	long nonStaticBankBalance;
	
	BankingSystemInitializer(long id,String name,long bal) {
		this.nonStaticBankid = id;
		this.nonStaticBankName = name;
		this.nonStaticBankBalance = bal;
	}
}
