package Jobase;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Jobase {
	protected HashMap<String, Jobranch> branches;

	// TODO Implement saving and reading from files
	//protected File diskFile;

	public Jobase() {
		branches = new HashMap<String, Jobranch>();
		//diskFile = null;
	}

	/*public Jobase(String filename) {
		branches = new HashMap<String, Jobranch>();
		diskFile = new File(filename);
	}
	*/

	public Jobranch getBranch(String name) {
		return branches.get(name);
	}

	public Jobranch getBranch(Jobranch j) {
		return branches.get(j.getName());
	}

	public Jobranch[] getBranches() {
		return branches.values().toArray(new Jobranch[branches.size()]);
	}

	public Jobranch[] getBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names)
			if (branches.containsKey(name))
				j.add(branches.get(name));
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch addBranch(String name) {
		if (!branches.containsKey(name)) {
			Jobranch j = new Jobranch(name);
			branches.put(name, j);
			return j;

			// TODO Copy Constructor problems?
			//return branches.put(name, new Jobranch(name));
		}
		else
			return branches.replace(name, new Jobranch(name));
	}

	public Jobranch addBranch(Jobranch j) {
		return branches.put(j._name, j);
	}

	public Jobranch addBranch(Jobranch j, String name) {
		return branches.put(name, j.setName(name));
	}

	public Jobranch[] addBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names) {
			if (!branches.containsKey(name))
				j.add(branches.put(name, new Jobranch(name)));
			else
				j.add(branches.replace(name, new Jobranch(name)));
		}
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch[] addBranches(Jobranch[] j) {
		for (Jobranch jb: j) {
			if (!branches.containsKey(jb._name))
				branches.put(jb._name, jb);
			else
				branches.replace(jb._name, jb);
		}
		return j;
	}

	public Jobranch[] addBranches(Jobranch[] j, String[] names) {
		for (int i = 0; i < j.length; i++) {
			j[i]._name = names[i];
			if (!branches.containsKey(names[i]))
				branches.put(names[i], j[i]);
			else
				branches.replace(names[i], j[i]);
		}
		return j;
	}

	public Jobranch removeBranch(String name) {
		if (branches.containsKey(name))
			return branches.remove(name).removeParent(this);
		return new Jobranch();
	}

	public Jobranch removeBranch(Jobranch j) {
		if (branches.containsKey(j.getName()))
			return branches.remove(j.getName()).removeParent(this);
		return j;
	}

	public Jobranch[] removeBranches(String[] names) {
		ArrayList<Jobranch> j = new ArrayList<Jobranch>();
		for (String name: names)
			if (branches.containsKey(name))
				j.add(branches.remove(name).removeParent(this));
		return j.toArray(new Jobranch[j.size()]);
	}

	public Jobranch[] removeBranches(Jobranch[] j) {
		for (Jobranch jo: j)
			if (branches.containsKey(jo.getName()))
				branches.remove(jo.getName()).removeParent(this);
		return j;
	}

	public String toString() {
		return branches.values().toString();
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
