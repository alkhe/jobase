import Jobase.*;

public class Main {

	//static Jobase jobase;

	public static void main(String[] args) {

		/*
		jobase = new Jobase("server");

		Jobranch users = jobase.addBranch("users");
		Jobranch superusers = jobase.addBranch("superusers");

		Jobranch root = users.addBranch("0");
		root.addLeaf("username", "root");
		root.addLeaf("password", "rootpassword");
		root.addLeaf("id", 0);

		Jobranch toor = users.addBranch("1");
		toor.addLeaf("username", "toor");
		toor.addLeaf("password", "toorpassword");
		toor.addLeaf("id", 1);

		superusers.addBranch(users.removeBranch("0"));

		Jobranch foo = users.addBranch("2");
		foo.addLeaf("username", "foo");
		foo.addLeaf("password", "bar");
		foo.addLeaf("id", 2);
		foo.addLeaf("note", "user may attempt to login as root");

		Jobranch foostrings = foo.addBranch("strings");
		foostrings.addLeaf("favorite_number", 7);
		foostrings.addLeaf("favorite_word", "fortune");
		foostrings.addLeaf("age", 14);

		System.out.println(foo);

		//System.out.println(superusers.getBranch("0").getLeaf("username"));
		*/

		/*
		Jobase db = new Jobase("server");
		Jobranch users = db.addBranch("users");
		Jobranch superusers = db.addBranch("superusers");
		Jobranch root = users.addBranch("root");
		root.addLeaf("id", 100);
		superusers.addBranch(users.removeBranch("root"));
		System.out.println(db);
		*/

		/*
		Jobase db = new Jobase("server");

		Jobranch users = db.addBranch("users");
		Jobranch superusers = db.addBranch("superusers");
		Jobranch root = users.addBranch("root");
		root.addLeaf("id", 100);
		superusers.addBranch(users.removeBranch("root"));
		System.out.println(db);
		*/

		/*
		Jobase db = new Jobase("server");

		Jobranch users = db.addBranch("users");
		Jobranch superusers = db.addBranch("superusers");
		superusers.addBranch("root").addLeaf("id", 0);
		superusers.addBranch("su").addLeaf("id", 1);
		users.addBranch("toor").addLeaf("id", 2);
		users.addBranch("foo").addLeaf("id", 3);
		users.addBranch("bar").addLeaf("id", 4);
		String mux = "";
		for (Jobranch jobranch: superusers.getBranches())
			mux += jobranch + "\n";
		for (Jobranch jobranch: users.getBranches())
			mux += jobranch + "\n";
		System.out.println(Jobase.toJSON(mux.substring(0, mux.length() - 1)));
		*/

		// TODO Complete parent node portion


		Jobase db = new Jobase("server");

		Jobranch root = db.addBranch("users").addBranch("root");
		root.addLeaf("id", 100);
		Jobranch toor = root.getParents()[0].addBranch("toor");
		toor.addLeaf("id", 200);
		System.out.println(db);


	}
}
