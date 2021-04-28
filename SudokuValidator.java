import java.util.Scanner;


class ThreadCol implements Runnable
{
	public static boolean valid=true;
	String name;
	Thread t;
	
	ThreadCol(String tname)
	{
		name = tname;
		t = new Thread(this,name);
		
		t.start();
	}
	
	public void run()
	{
		 for(int i=1;i<=9 && valid==true;i++)
		 { int sum=0;
			for(int j=1;j<=9;j++)
		 	{
				sum+=SudokuValidator.a[j][i];
			}
		
			if(sum!=45)
			{
				valid=false;
			}
			else
			{
				valid=valid & true;
			}	
		 }	
	}
}

class ThreadRow implements Runnable
{
	public static boolean valid=true;
	String name;
	Thread t;

	ThreadRow(String tname)
	{
		name = tname;
		t = new Thread(this,name);
		t.start();
	}
	
	public void run()
	{
		for(int i=1;i<=9 && valid==true;i++)
		 { int sum=0;
			for(int j=1;j<=9;j++)
		 	{
				sum+=SudokuValidator.a[i][j];
			}
		
			if(sum!=45)
			{
				valid=false;
			}
			else
			{
				valid=valid & true;
			}
		 }
	}
}

class ThreadGrid implements Runnable
{
	public static boolean valid=true;
	String name;
	Thread t;
	
	ThreadGrid(String tname)
	{
		name = tname;
		t = new Thread(this,name);
	
		t.start();
	}
	
	
	public void run()
	{
			for(int i=1;i<=9 && valid==true;i=i+3)
		 { int sum=0;
			
		     for(int j=i;j<i+3;j++)
		     {
		    	 sum=0;
		    	 for(int k=i;k<i+3;k++)
		    	 {
		    		 sum+=SudokuValidator.a[j][k];
		    	 }
		    	
		    	 if(sum!=45)
		    	 {
		    		 valid=false;
		    	 }
		    	 else
		    		valid=valid & true; 
		     }
                 }
        }
}	


public class SudokuValidator {
	public static int a[][] = new int [11][11];
        
	public static void main(String args[])
	{
//            int min = 1;  
//            int max = 9;  
		boolean valid=true;
		 Scanner sc = new Scanner(System.in);
		int count[]=new int [10],i,j;
                System.out.println("Enter the numbers to fill Sudoku boxes (9 x 9):");
		for(i=1;i<=9;i++)
			for(j=1;j<=9;j++)
			{
				a[i][j]= sc.nextInt(); // (int)(Math.random()*(max-min+1)+min);
				count[a[i][j]]++;
                                System.out.println(a[i][j]); 
			}	
		for(i=1;i<=9;i++)
			if(count[i]!=9)
				valid=false;
		if(valid)
		{
               
                new ThreadGrid("Grid");
		new ThreadRow("Row");
		new ThreadCol("Column");
		}	 
		valid=ThreadGrid.valid & ThreadRow.valid & ThreadCol.valid;	
	  if(valid)
		  System.out.println("Valid Sudoku!");
	  else
		  System.out.println("Invalid Sudoku!");
	  sc.close();
	}
}
