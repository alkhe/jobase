package Jobase;

import java.util.ArrayList;
import java.util.HashMap;

public class Jobranch extends Jobase {
	protected HashMap<String, Joleaf> leaves;
	protected String _name;
	protected ArrayList<Jobase> _parent;

	public Jobranch() {
		super();
		leaves = new HashMap<String, Joleaf>();
		_name = "";
		_parent = new ArrayList<Jobase>();
	}

	public Jobranch(String name) {
		super();
		leaves = new HashMap<String, Joleaf>();
		_name = name;
		_parent = new ArrayList<Jobase>();
	}

	public String getName() {
		return _name;
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

	public Joleaf addLeaf(String name) {
		return leaves.put(name, new Joleaf().setName(name).addParent(this));
	}

	public Joleaf addLeaf(String name, String s) {
		return leaves.put(name, new Joleaf(s).setName(name).addParent(this));
	}

	public Joleaf addLeaf(String name, int i) {
		return leaves.put(name, new Joleaf(i).setName(name).addParent(this));
	}

	public Joleaf addLeaf(String name, double d) {
		return leaves.put(name, new Joleaf(d).setName(name).addParent(this));
	}

	public Joleaf addLeaf(String name, char c) {
		return leaves.put(name, new Joleaf(c).setName(name).addParent(this));
	}

	public Joleaf addLeaf(String name, Joleaf j) {
		return leaves.put(name, j.setName(name).addParent(this));
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
		return "{\n\t" + _name + ":\n\t" + super.toString().replaceAll("\n", "\n\t") + "\n\t:\n\t" + leaves.values().toString().replaceAll("\n", "\n\t") + "\t\n}";
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

	protected Jobranch setName(String name) {
		_name = name;
		return this;
	}
}
