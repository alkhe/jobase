package Jobase;

import java.util.*;

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

	public static Jobranch[] sortByLeaf(Jobranch[] j, final String name) {
		ArrayList<Jobranch> leaf = new ArrayList<Jobranch>();
		ArrayList<Jobranch> less = new ArrayList<Jobranch>();
		for (Jobranch jb: j) {
			if (jb.hasLeaf(name))
				leaf.add(jb);
			else
				less.add(jb);
		}
		leaf.sort(new Comparator<Jobranch>() {
			public int compare(Jobranch o1, Jobranch o2) {
				Joleaf l1 = o1.getLeaf(name);
				Joleaf l2 = o2.getLeaf(name);
				switch (l1._type) {
					case INTEGER:
						return (l1.getInt() == l2.getInt() ? 0 : (l1.getInt() > l2.getInt() ? 1 : -1));
					case FLOAT:
						return (l1.getDouble() == l2.getDouble() ? 0 : (l1.getDouble() > l2.getDouble() ? 1 : -1));
					case BOOLEAN:
						return (l1.getBoolean() == l2.getBoolean() ? 0 : (l1.getBoolean() ? 1 : -1));
					case STRING:
					default:
						return l1.compareTo(l2);
				}
			}
		});
		leaf.addAll(less);
		Collections.reverse(leaf);
		return leaf.toArray(new Jobranch[j.length]);
	}

	public static Jobranch[] sortByLeaf(Jobranch[] j, final String name, final boolean reverse) {
		ArrayList<Jobranch> leaf = new ArrayList<Jobranch>();
		ArrayList<Jobranch> less = new ArrayList<Jobranch>();
		for (Jobranch jb: j) {
			if (jb.hasLeaf(name))
				leaf.add(jb);
			else
				less.add(jb);
		}
		leaf.sort(new Comparator<Jobranch>() {
			public int compare(Jobranch o1, Jobranch o2) {
				Joleaf l1 = o1.getLeaf(name);
				Joleaf l2 = o2.getLeaf(name);
				switch (l1._type) {
					case INTEGER:
						return (l1.getInt() == l2.getInt() ? 0 : (!reverse ? (l1.getInt() > l2.getInt() ? 1 : -1) : (l1.getInt() < l2.getInt() ? 1 : -1)));
					case FLOAT:
						return (l1.getDouble() == l2.getDouble() ? 0 : (!reverse ? (l1.getDouble() > l2.getDouble() ? 1 : -1) : (l1.getDouble() < l2.getDouble() ? 1 : -1)));
					case BOOLEAN:
						return (l1.getBoolean() == l2.getBoolean() ? 0 : (!reverse ? (l1.getBoolean() ? 1 : -1) : (!l1.getBoolean() ? 1 : -1)));
					case STRING:
					default:
						return (!reverse ? l1.compareTo(l2) : l2.compareTo(l1));
				}
			}
		});
		leaf.addAll(less);
		Collections.reverse(leaf);
		return leaf.toArray(new Jobranch[j.length]);
	}

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
