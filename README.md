#Jobase

A scalable and robust Java database centered around the Object Relational Mapping paradigm with jQuery style object chaining and elements of SQL and JavaScript.

##Using Jobase

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
A Jobase, Jobranch, or Joleaf may be exported as a JSON variable with the toString method. These must be surrounded by brackets in order to be treated as JSON objects. To directly export as a JSON object, use toJSON instead.

	System.out.println(db);   
>
	"server": {
		"users": {
			"branches": {
				"root": {
					"branches": {},
					"leaves": {
						"access_level": "admin",
						"password": "toor",
						"id": 0
					}
				}
			},
			"leaves": {}
		}
	}

	System.out.println(users);
>
	"users": {
		"branches": {
			"root": {
				"branches": {},
				"leaves": {
					"access_level": "admin",
					"password": "toor",
					"id": 0
				}
			}
		},
		"leaves": {}
	}

	System.out.println(root.toJSON());
>
	{
		"root": {
			"branches": {},
			"leaves": {
				"access_level": "admin",
				"password": "toor",
				"id": 0
			}
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
	Joleaf foobar = foo.addLeaf("bar", 4).set("57").setNumeric(true).set("baz");
	System.out.println(foo);
>
	"foo": {
		"branches": {},
		"leaves": {
			"bar": "baz"
		}
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
		"branches": {
			"foo": {
				"branches": {},
				"leaves": {
					"id": 100
				}
			}
		},
		"leaves": {}
	}

###Moving Branches
Branches and leaves can be moved by using a similar technique, where the node must also be removed from the source. The order of addition and removal does not matter, although adding a removed branch is more intuitive and therefore recommended.

	Jobase db = new Jobase("server");
	Jobranch users = db.addBranch("users");
	Jobranch superusers = db.addBranch("superusers");
	Jobranch root = users.addBranch("root");
	root.addLeaf("id", 100);
	superusers.addBranch(users.removeBranch("root"));
	System.out.println(db);
>
	"server": {
		"superusers": {
			"branches": {
				"root": {
					"branches": {},
					"leaves": {
						"id": 100
					}
				}
			},
			"leaves": {}
		},
		"users": {
			"branches": {},
			"leaves": {}
		}
	}

##Development
*	Writing to and reading from files, automatic interval write to disk
*	SOAP Query language/Web API
*	Extending ArrayList to account for internal java.util bugs
*	Further optimization
*	GUI Viewing Software
* 	Arrays and Lists as leaves
*	Initialize database from JSON
