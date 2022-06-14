package Data;

import java.util.ArrayList;

public class User {
	public static ArrayList<Admin> admin = new ArrayList<Admin>();
	public static ArrayList<Member> member = new ArrayList<Member>();

	public static void addMember(Member m) {
		member.add(m);
	}

	public static void addAdmin(Admin a) {
		admin.add(a);
	}

	public Member getMember(int i) {
		return member.get(i);
	}

	public Admin getAdmin(int i) {
		return admin.get(i);
	}

	public ArrayList<Admin> getAllAdmins() {
		return admin;
	}

	public ArrayList<Member> getAllMembers() {
		return member;
	}

}
