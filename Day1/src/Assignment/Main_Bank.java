package Assignment;

public class Main_Bank {
	static {
		System.out.println("initialization process has begun Banking Process has started,");
		
	}
	public static void main(String[] args) {
		System.out.println("Bank Details are: ");
		BankingSystemInitializer bs = new BankingSystemInitializer(448742310873423262L, "Fedral Bank of India.", 98385354265735737L);
		System.out.println("BANK ID: "+ bs.nonStaticBankid);
		System.out.println("BANK NAME: "+ bs.nonStaticBankName);
		System.out.println("BANK BALANCE: "+ bs.nonStaticBankBalance);
		System.out.println("Thank You for using Fedral Bank of India");
	}
}
