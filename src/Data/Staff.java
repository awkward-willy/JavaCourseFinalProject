package Data;

public class Staff extends Member {

	public Staff(String name, String account, String identity) {
		super(name, account, identity);
		setMaxBookAmount(5);
		setLendDay(30);
	}

}