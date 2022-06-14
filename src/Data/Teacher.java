package Data;

public class Teacher extends Member {

	public Teacher(String name, String account, String identity) {
		super(name, account, identity);
		setMaxBookAmount(7);
		setLendDay(30);
	}

}