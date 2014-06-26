package Jobase;

import java.util.ArrayList;

public class Joleaf {
	protected String _string;
	protected boolean _numeric;
	protected String _name;
	protected ArrayList<Jobase> _parent;

	public Joleaf() {
		_name = "";
		_string = null;
		_numeric = true;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String s) {
		_name = "";
		_string = s;
		_numeric = false;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(int i) {
		_name = "";
		_string = i + "";
		_numeric = true;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(double d) {
		_name = "";
		_string = d + "";
		_numeric = true;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(char c) {
		_name = "";
		_string = c + "";
		_numeric = false;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String s, boolean numeric) {
		_name = "";
		_string = s;
		_numeric = numeric;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, String s) {
		_name = name;
		_string = s;
		_numeric = false;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, int i) {
		_name = name;
		_string = i + "";
		_numeric = true;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, double d) {
		_name = name;
		_string = d + "";
		_numeric = true;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, char c) {
		_name = name;
		_string = c + "";
		_numeric = false;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, String s, boolean numeric) {
		_name = name;
		_string = s;
		_numeric = numeric;
		_parent = new ArrayList<Jobase>();
	}

	public String getName() {
		return _name;
	}

	public void setNumeric(boolean n) {
		_numeric = n;
	}

	public Joleaf set(String s) {
		_string = s;
		_numeric = false;
		return this;
	}

	public Joleaf set(int i) {
		_string = i + "";
		_numeric = true;
		return this;
	}

	public Joleaf set(double d) {
		_string = d + "";
		_numeric = true;
		return this;
	}

	public Joleaf set(char c) {
		_string = c + "";
		_numeric = false;
		return this;
	}

	public String getString() {
		return _string;
	}

	public int getInt() {
		return Integer.parseInt(_string);
	}

	public double getDouble() {
		return Double.parseDouble(_string);
	}

	public char getChar() {
		return _string.charAt(0);
	}

	public String toString() {
		return "{\n\t" + _name + ":\n\t" + _string + "\n}";
	}

	public Jobase[] getParents() {
		return _parent.toArray(new Jobase[_parent.size()]);
	}

	protected Joleaf addParent(Jobase j) {
		_parent.add(j);
		return this;
	}

	protected Joleaf removeParent(Jobase j) {
		_parent.remove(j);
		return this;
	}

	protected Joleaf setName(String name) {
		_name = name;
		return this;
	}
}
