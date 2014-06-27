package Jobase;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Jobase {
	protected HashMap<String, Jobranch> branches;
	protected String _name;

	// TODO Implement saving and reading from files
	//protected File diskFile;

	public Jobase() {
		branches = new HashMap<String, Jobranch>();
		_name = "";
	}

	public Jobase(String name) {
		branches = new HashMap<String, Jobranch>();
		_name = name;
	}

	/*public Jobase(String filename) {
		branches = new HashMap<String, Jobranch>();
		diskFile = new File(filename);
	}
	*/

	public String getName() {
		return _name;
	}

	public Jobranch getBranch(String name) {
		return branches.get(name);
	}

	public Jobranch getBranch(Jobranch j) {
		return branches.get(j._name);
	}

	public Jobranch[] getBranches() {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>(branches.values());
		Collections.reverse(j);
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch[] getBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names)
			if (branches.containsKey(name))
				j.add(branches.get(name));
		Collections.reverse(j);
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch addBranch(String name) {
		if (!branches.containsKey(name)) {
			Jobranch j = new Jobranch(name).addParent(this);
			branches.put(name, j);
			return j;

			// TODO Copy Constructor problems?
			//return branches.put(name, new Jobranch(name).addParent(this));
		}
		else
			return branches.replace(name, new Jobranch(name).addParent(this));
	}

	public Jobranch addBranch(Jobranch j) {
		branches.put(j._name, j.addParent(this));
		return j;
		//return branches.put(j._name, j.addParent(this));
	}

	public Jobranch addBranch(Jobranch j, String name) {
		branches.put(name, j.setName(name).addParent(this));
		return j;
		//return branches.put(name, j.setName(name));
	}

	public Jobranch[] addBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names) {
			if (!branches.containsKey(name))
				j.add(branches.put(name, new Jobranch(name).addParent(this)));
			else
				j.add(branches.replace(name, new Jobranch(name).addParent(this)));
		}
		Collections.reverse(j);
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch[] addBranches(Jobranch[] j) {
		for (Jobranch jb: j) {
			if (!branches.containsKey(jb._name))
				branches.put(jb._name, jb.addParent(this));
			else
				branches.replace(jb._name, jb.addParent(this));
		}
		return j;
	}

	public Jobranch[] addBranches(Jobranch[] j, String[] names) {
		for (int i = 0; i < j.length; i++) {
			j[i]._name = names[i];
			if (!branches.containsKey(names[i]))
				branches.put(names[i], j[i].addParent(this));
			else
				branches.replace(names[i], j[i].addParent(this));
		}
		return j;
	}

	public Jobranch removeBranch(String name) {
		if (branches.containsKey(name))
			return branches.remove(name).removeParent(this);
		return new Jobranch();
	}

	public Jobranch removeBranch(Jobranch j) {
		if (branches.containsKey(j._name))
			return branches.remove(j._name).removeParent(this);
		return j;
	}

	public Jobranch[] removeBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names)
			if (branches.containsKey(name))
				j.add(branches.remove(name).removeParent(this));
		Collections.reverse(j);
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch[] removeBranches(Jobranch[] j) {
		for (Jobranch jb: j)
			if (branches.containsKey(jb._name))
				branches.remove(jb._name).removeParent(this);
		return j;
	}

	public boolean hasBranch(String name) {
		return branches.containsKey(name);
	}

	public boolean hasBranch(Jobranch j) {
		return branches.containsKey(j._name);
	}

	public boolean hasBranchExplicit(Jobranch j) {
		return branches.containsValue(j);
	}

	public String toString() {
		//return branches.values().toString();
		String s = "";
		for (Jobranch j: branches.values()) {
			s += "\n" + j + ",";
		}
		if (branches.values().size() > 0)
			s = s.substring(0, s.length() - 1).replaceAll("\n", "\n\t") + "\n";
		return "\"" + _name + "\": {" + s + "}";
	}

	public String toJSON() {
		return "{\n\t" + toString().replaceAll("\n", "\n\t") + "\n}";
	}

	public static String toJSON(Jobase j) {
		return j.toJSON();
	}

	// Do not delete, this method useful to programmers using Autocomplete.
	public static String toJSON(Jobranch j) {
		return j.toJSON();
	}

	public static String toJSON(Joleaf j) {
		return j.toJSON();
	}

	public static String toJSON(String s) {
		return "{\n\t" + s.replaceAll("\n", "\n\t") + "\n}";
	}

	/*

	public File saveFile() {
		return diskFile;
	}

	public File getLastBackup() {
		return diskFile;
	}

	public static Jobase readFile(String filename) {
		try {
			Jobase j = new Jobase();
			FileInputStream input = new FileInputStream(new File(filename));
			byte[] baseData = new byte[] {};
			input.read(baseData);
			return j;
		}
		catch (Exception e) {
			return new Jobase();
		}
	}

	*/

}
