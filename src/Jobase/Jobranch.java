package Jobase;

import java.util.ArrayList;
import java.util.HashMap;

public class Jobranch extends Jobase {
	protected HashMap<String, Joleaf> leaves;
	protected ArrayList<Jobase> _parent;

	public Jobranch() {
		super();
		leaves = new HashMap<String, Joleaf>();
		_parent = new ArrayList<Jobase>();
	}

	public Jobranch(String name) {
		super(name);
		leaves = new HashMap<String, Joleaf>();
		_parent = new ArrayList<Jobase>();
	}

	protected Jobranch setName(String name) {
		_name = name;
		return this;
	}

	public Joleaf getLeaf(String name) {
		return leaves.get(name);
	}

	public Joleaf[] getLeaves() {
		return leaves.values().toArray(new Joleaf[leaves.size()]);
	}

	public Joleaf[] getLeaves(String[] names) {
		ArrayList<Joleaf> j = new ArrayList<Joleaf>();
		for (String name: names)
			if (leaves.containsKey(name))
				j.add(leaves.get(name));
		return j.toArray(new Joleaf[j.size()]);
	}

	// TODO Also fix null pointer exceptions here

	public Joleaf addLeaf(String name) {
		Joleaf j = new Joleaf(name).addParent(this);
		leaves.put(name, j);
		return j;
	}

	public Joleaf addLeaf(String name, String s) {
		Joleaf j = new Joleaf(name, s).addParent(this);
		leaves.put(name, j);
		return j;
	}

	public Joleaf addLeaf(String name, int i) {
		Joleaf j = new Joleaf(name, i).addParent(this);
		leaves.put(name, j);
		return j;
	}

	public Joleaf addLeaf(String name, double d) {
		Joleaf j = new Joleaf(name, d).addParent(this);
		leaves.put(name, j);
		return j;
	}

	public Joleaf addLeaf(String name, char c) {
		Joleaf j = new Joleaf(name, c).addParent(this);
		leaves.put(name, j);
		return j;
	}

	public Joleaf addLeaf(String name, Joleaf j) {
		leaves.put(name, j.setName(name).addParent(this));
		return j;
	}

	public Joleaf removeLeaf(String name) {
		if (leaves.containsKey(name))
			return leaves.remove(name).removeParent(this);
		return new Joleaf();
	}

	public Joleaf[] removeLeaves(String[] names) {
		ArrayList<Joleaf> j = new ArrayList<Joleaf>();
		for (String name: names)
			if (leaves.containsKey(name))
				j.add(leaves.remove(name).removeParent(this));
		return j.toArray(new Joleaf[j.size()]);
	}

	public String toString() {
		//return "{\n\t" + _name + ":\n\t" + super.toString().replaceAll("\n", "\n\t") + "\n\t:\n\t" + leaves.values().toString().replaceAll("\n", "\n\t") + "\t\n}";
		String s = "\n\"branches\": {";
		String t = "";
		for (Jobranch j: branches.values()) {
			t += j + ",";
		}
		if (branches.values().size() > 0)
			t = t.substring(0, t.length() - 1).replaceAll("\n", "\n\t") + "\n";
		s += t + "},\n\"leaves\": {";
		t = "";
		for (Joleaf j: leaves.values()) {
			t += "\n" + j + ",";
		}
		if (leaves.values().size() > 0)
			t = t.substring(0, t.length() - 1).replaceAll("\n", "\n\t") + "\n";
		return "\n\"" + _name + "\": {" + (s + t).replaceAll("\n", "\n\t") + "}\n}";
	}

	public String toJSON() {
		return "{" + toString().replaceAll("\n", "\n\t") + "\n}";
	}

	public Jobase[] getParents() {
		return _parent.toArray(new Jobase[_parent.size()]);
	}

	protected Jobranch addParent(Jobase j) {
		_parent.add(j);
		return this;
	}

	protected Jobranch removeParent(Jobase j) {
		_parent.remove(j);
		return this;
	}
}
