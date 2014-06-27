package Jobase;

import java.util.ArrayList;
import java.util.Objects;

public class Joleaf {

	public static enum Type {
		STRING,
		INTEGER,
		FLOAT,
		BOOLEAN
	}

	protected String _value;
	protected String _name;
	protected Type _type;
	protected ArrayList<Jobase> _parent;

	public Joleaf() {
		_name = "";
		_value = null;
		_type = Type.STRING;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String s) {
		_name = "";
		_value = s;
		_type = Type.STRING;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(int i) {
		_name = "";
		_value = i + "";
		_type = Type.INTEGER;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(double d) {
		_name = "";
		_value = d + "";
		_type = Type.FLOAT;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(boolean b) {
		_name = "";
		_value = b + "";
		_type = Type.BOOLEAN;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String s, Type t) {
		_name = "";
		_value = s;
		_type = t;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, String s) {
		_name = name;
		_value = s;
		_type = Type.STRING;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, int i) {
		_name = name;
		_value = i + "";
		_type = Type.INTEGER;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, double d) {
		_name = name;
		_value = d + "";
		_type = Type.FLOAT;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, boolean b) {
		_name = name;
		_value = b + "";
		_type = Type.BOOLEAN;
		_parent = new ArrayList<Jobase>();
	}

	public Joleaf(String name, String s, Type t) {
		_name = name;
		_value = s;
		_type = t;
		_parent = new ArrayList<Jobase>();
	}

	public String getName() {
		return _name;
	}

	public Joleaf setName(String name) {
		for (Jobase j: _parent) {
			((Jobranch)(j)).leaves.put(name, ((Jobranch)(j)).leaves.remove(_name));
		}
		_name = name;
		return this;
	}

	public Joleaf setType(Type t) {
		_type = t;
		return this;
	}

	public Type getType() {
		return _type;
	}

	public Joleaf set(String s) {
		_value = s;
		_type = Type.STRING;
		return this;
	}

	public Joleaf set(int i) {
		_value = i + "";
		_type = Type.INTEGER;
		return this;
	}

	public Joleaf set(double d) {
		_value = d + "";
		_type = Type.FLOAT;
		return this;
	}

	public Joleaf set(boolean b) {
		_value = b + "";
		_type = Type.BOOLEAN;
		return this;
	}

	public String getString() {
		return _value;
	}

	public int getInt() {
		return Integer.parseInt(_value);
	}

	public double getDouble() {
		return Double.parseDouble(_value);
	}

	public boolean getBoolean() {
		return Boolean.parseBoolean(_value);
	}

	public String toString() {
		return "\"" + _name + "\": " + (_type != Type.STRING ? _value : "\"" + _value + "\"");
	}

	public String toJSON() {
		return "{\n\t" + toString() + "\n}";
	}

	public Jobase[] getParents() {
		return _parent.toArray(new Jobase[_parent.size()]);
	}

	public int compareTo(Joleaf j) {
		return _value.compareTo(j._value);
	}

	protected Joleaf addParent(Jobase j) {
		if (!_parent.contains(j))
			_parent.add(j);
		return this;
	}

	protected Joleaf removeParent(Jobase j) {
		if (_parent.contains(j))
			_parent.remove(j);
		return this;
	}
}


