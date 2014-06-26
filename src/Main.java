import Jobase.*;

public class Main {

	static Jobase jobase;

	public static void main(String[] args) {

		jobase = new Jobase();

		Jobranch users = jobase.addBranch("users");
		Jobranch superusers = jobase.addBranch("superusers");

		Jobranch root = users.addBranch("0");
		root.addLeaf("username", "root");
		root.addLeaf("password", "rootpassword");

		Jobranch toor = users.addBranch("1");
		toor.addLeaf("username", "toor");
		toor.addLeaf("password", "toorpassword");

		superusers.addBranch(users.removeBranch("0"));


		System.out.println(jobase);
	}
}
