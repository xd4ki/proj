package proj1;

public class node {
	node lc,rc;
	String name;
	String meaning;
	int h;
	public node(String name,String meaning)   
	{
		this.name=name.toLowerCase();
		this.meaning=meaning; 
		lc=rc=null;
		h=1;
	}
}
