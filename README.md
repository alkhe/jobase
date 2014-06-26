#Jobase

A scalable and robust Java database centered around the Object Relational Mapping paradigm with jQuery style object chaining and elements of SQL and JavaScript.

##Using Jobase

The Jobase database is composed of three classes, Jobase, Jobranch, and Joleaf. Throughout this guide, they will sometimes be referred to as nodes. Each of the three classes and their functions will be explained in detail.

###Initialization
A Jobase database is a tree that contains branches and leaves. The Jobase class can only carry branches, similar to a traditional SQL database.

	Jobase db = new Jobase("server");

###Adding Branches
The Jobranch class is an extension to the concept of database tables. Jobranches can behave as tables, but can also be entries in another Jobranch. This way, values belonging to a similar group can exist in their own branches.

	Jobranch users = db.addBranch("users");
	Jobranch root = users.addBranch("root");

###Adding Leaves
The Joleaf class are entries of Jobranches that store values. Currently supported types are strings, integers, floating point values, and characters. Joleaf objects are similar to Javascript variables, as they support implicit and dynamic typing. This implicit typing allows for effective representation as a JSON object.

	root.addLeaf("id", 0);
	root.addLeaf("password", "toor");
	root.addLeaf("access_level", "admin");

###Exporting to JSON
A Jobase, Jobranch, or Joleaf may be exported as a JSON variable with the toString method. These must be surrounded by brackets in order to be treated as JSON objects. To directly export as a JSON object, use toJSON instead. The static Jobase.toJSON method can also be used, where it has been extended to work for strings.

	System.out.println(db);   
>
	"server": {
    	"users": {
    		"root": {
    			"access_level": "admin",
    			"password": "toor",
    			"id": 0
    		}
    	}
    }

	System.out.println(users);
>
	"users": {
    	"root": {
    		"access_level": "admin",
    		"password": "toor",
    		"id": 0
    	}
    }

	System.out.println(root.toJSON());
>
	{
    	"root": {
    		"access_level": "admin",
    		"password": "toor",
    		"id": 0
    	}
    }

	System.out.println(root.getLeaf("id"));
>
	"id": 0

	System.out.println(root.getLeaf("id").toJSON());
>
	{
    	"id": 0
    }

##Jobase Techniques

###Method Chaining
Operations on a single node may be chained, much the way jQuery elements can be chained, with the most recently returned node as the operand. A function will return a new node when it pertains to another Jobase, Jobranch, or Joleaf object. Examples are addBranch and removeLeaf.

	Jobranch foo = db.addBranch("foo");
    foo.addLeaf("bar", 4).set("57").setNumeric(true).set("baz");
    System.out.println(foo);
>
	"foo": {
    	"bar": "baz"
    }

###Cross-Referencing
A single branch or leaf can be accessed from multiple places in a database. This allows for automatic cross-referencing within an application, and is a useful solution for the disorganization of related values within an SQL database that is often cited.

	Jobranch users = db.addBranch("users");
	Jobranch superusers = db.addBranch("superusers");
	Jobranch foo = users.addBranch("foo");
	foo.addLeaf("id", 100);
	superusers.addBranch(foo);
	System.out.println(superusers);
>
	"superusers": {
    	"foo": {
    		"id": 100
    	}
    }

###Moving Branches
Branches and leaves can be moved by using a similar technique, where the node must also be removed from the source. The order of addition and removal does not matter, although adding a removed branch is more intuitive and therefore recommended.

	Jobranch users = db.addBranch("users");
	Jobranch superusers = db.addBranch("superusers");
	Jobranch root = users.addBranch("root");
	root.addLeaf("id", 100);
	superusers.addBranch(users.removeBranch("root"));
	System.out.println(db);
>
	"server": {
    	"superusers": {
    		"root": {
    			"id": 100
    		}
    	},
    	"users": {}
    }

###Multiplexing Nodes
Because the default toString method for Jobase, Jobranch, and Joleaf objects does not contain an outer JSON bracket pair, nodes can be multiplexed by concatenating them and then using the Jobase.toJSON method to produce a JSON object containing the arbitrarily specified Jobase, Jobranch, and Joleaf objects.

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
>
	{
		"root": {
			"id": 0
		}
		"su": {
			"id": 1
		}
		"toor": {
			"id": 2
		}
		"foo": {
			"id": 3
		}
		"bar": {
			"id": 4
		}
	}

###Accessing Parent Nodes
Jobranch and Joleaf nodes are given the addresses of their parents when assigned to them, and they can be accessed by the getParents method, which returns an array of Jobase. This technique can be used to quickly clean up branches or add a sibling node.



##Development
*	Writing to and reading from files, automatic interval write to disk
*	SOAP Query language/Web API
*	Extending ArrayList to account for internal java.util bugs
*	Further optimization
*	GUI Viewing Software
* 	Arrays and Lists as leaves
*	Initialize database from JSON
*	Port to other languages
*	Sort branches by leaf