package proj1;
import java.util.Scanner;
public class AVL {
	Scanner sc = new Scanner (System.in);
	private node root;   
	public AVL()
	{
		root=null;
	}
	
	int height(node N)   
	{
		int lh, rh;			
		if(N == null)
		return 0;
		if(N.lc == null)	
		lh = 0;					
		else				
		lh = 1 + N.lc.h;		
		if(N.rc == null)
		rh = 0;
		else
		rh = 1 + N.rc.h;
		if(lh > rh)
		return lh;
		else
		return rh;
	}
	
	int balanceFactor(node root)   
	{int bf, lh, rh;
	if(root == null)
		return 0;
		if(root.lc == null)
		lh = 0;						
		else
		lh = 1 + height(root.lc);
		if(root.rc == null)		
		rh = 0;
		else
		rh = 1 + height(root.rc);
		bf = lh - rh;
		return bf;
		
	}
	
	node LL(node ptr)   
	{				
		//Right Rotation
		node tmp = ptr.lc;
		ptr.lc = tmp.rc;		
		tmp.rc = ptr;
		tmp.h = height(tmp);
		ptr.h = height(ptr);
		return tmp;
	}
	
	node RR(node ptr)    
	{
		//Left Rotation
		node tmp = ptr.rc;
		ptr.rc = tmp.lc;
		tmp.lc = ptr;			
		tmp.h = height(tmp);
		ptr.h = height(ptr);
		return tmp;	
	}
	
	node LR(node root)
	{	
		//left right rotation
		root.lc=RR(root.lc);
		root=LL(root);				
		return root;
	}
	
	node RL(node root)    
	{
		//right left rotation	
		root.rc=LL(root.rc);		
		root=RR(root);
		
		return root;
	}
	
	
	node insert(node root, node temp){
		int bf;
		if(root == null){
		root = new node(temp.name, temp.meaning);
		return root;
		}
		if(temp.name.compareTo(root.name) < 0){
		root.lc = insert(root.lc, temp);
		bf = balanceFactor(root);
		if(bf == 2){
		if(temp.name.compareToIgnoreCase(root.lc.name) < 0)
		root = LL(root);
		else
		root = LR(root);
		}
		}
		else{ //cn.compareToIgnoreCase(root.Name) > 0
		root.rc = insert(root.rc, temp);
		bf = balanceFactor(root);
		if(bf == -2){
		if(temp.name.compareToIgnoreCase(root.rc.name) > 0)
		root = RR(root);
		else
		root = RL(root);
		}
		}
		root.h = height(root);
		return root;
	}
	
	void create(String Name,String mean)    
	{
		
		node temp=new node(Name,mean);
		root=insert(root,temp);
		
		
	}
	void display(node localRoot)       
	{
			if(localRoot != null){
			display(localRoot.lc);
			System.out.println(localRoot.name.toUpperCase()+" -"+localRoot.meaning);
			display(localRoot.rc);
			}
			
	}
	node getRoot() {
		return root;		
	}
	void findWord() {
		System.out.print("\nEnter word : ");
		String target=sc.nextLine().toLowerCase();
		node current=root;
		while(current!=null) {
			int comparison=target.compareTo(current.name);
			if(comparison==0) {
				System.out.println("\nWord : "+current.name.toUpperCase()+"\t\t-\t\tMeaning : "+current.meaning);
				return;
			}
			else if(comparison<0) {
				current=current.lc;
			}
			else {
				current=current.rc;
			}
		}
		System.out.println("\nWord not found! Please be more specific.");
	}
	
	       
}	   

	
	

