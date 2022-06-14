package Data;

public class Student extends Member {

	public Student(String name, String account, String identity) {
		super(name, account, identity);
		setMaxBookAmount(3);
		setLendDay(14);
	}

}