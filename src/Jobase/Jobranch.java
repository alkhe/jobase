package Jobase;

import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public Jobranch setName(String name) {
		for (Jobase j: _parent) {
			((Jobranch)(j)).leaves.put(name, ((Jobranch)(j)).leaves.remove(_name));
		}
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
		Collections.reverse(j);
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

	public Joleaf addLeaf(String name, boolean b) {
		Joleaf j = new Joleaf(name, b).addParent(this);
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
		Collections.reverse(j);
		return j.toArray(new Joleaf[j.size()]);
	}

	public boolean hasLeaf(String name) {
		return leaves.containsKey(name);
	}

	public boolean hasLeaf(Joleaf j) {
		return leaves.containsKey(j._name);
	}

	public boolean hasLeafExplicit(Joleaf j) {
		return leaves.containsValue(j);
	}

	public String toString() {
		String s = "";
		for (Joleaf j: leaves.values()) {
			s += "\n" + j + ",";
		}
		for (Jobranch j: branches.values()) {
			s += "\n" + j + ",";
		}
		if (leaves.values().size() + branches.values().size() > 0)
			s = s.substring(0, s.length() - 1).replaceAll("\n", "\n\t") + "\n";
		return "\"" + _name + "\": {" + s + "}";
	}

	public Jobase[] getParents() {
		return _parent.toArray(new Jobase[_parent.size()]);
	}

	/*
	public Jobranch[] sortByLeaf(Jobranch[] j, String name) {
		ArrayList<Jobranch> leaf = new ArrayList<Jobranch>();
		ArrayList<Jobranch> less = new ArrayList<Jobranch>();
		for (Jobranch jb: j) {
			if (jb.hasLeaf(name))
				leaf.add(jb);
			else
				less.add(jb);
		}
		Joleaf.Type type = leaf.get(0).getLeaf(name)._type;
		leaf.sort();
		HashMap<>
	}
	*/

	protected Jobranch addParent(Jobase j) {
		if (!_parent.contains(j))
			_parent.add(j);
		return this;
	}

	protected Jobranch removeParent(Jobase j) {
		if (_parent.contains(j))
			_parent.remove(j);
		return this;
	}
}
