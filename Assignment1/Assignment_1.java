
 /* 
    Submitted By:- Dheeraj Kumar
	Roll No:- 1801052
	Btech 2nd Year CSE 
 */


 /* 
	State Space:- Any arrangement of 8X8 matrix with n<=8 queens.
  	Initial State:- No queens on the board(i.e., all entries are null)
 				- - - - - - - -
 				- - - - - - - -
  				- - - - - - - -
  				- - - - - - - -
 				- - - - - - - -
 				- - - - - - - -
  				- - - - - - - -
  				- - - - - - - -
 	Transition Operator:- Adding 1 new queen in an empty row.
  	Goal state:- All 8 Queens placed on the board so that each queens are in non-attacking position.
  	One such scenerio is,
				_ _ _ _ _ _ _ Q 
				_ Q _ _ _ _ _ _ 
				_ _ _ _ Q _ _ _ 
				_ _ Q _ _ _ _ _ 
				Q _ _ _ _ _ _ _ 
				_ _ _ _ _ _ Q _ 
				_ _ _ Q _ _ _ _ 
				_ _ _ _ _ Q _ _
 	Cost:- One per move
 */
 

import java.util.*;
public class Assignment_1
{
	int SIZE = 8;
	
	//Creation of initial state
	String initialise(String arr)
	{
		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++)
				arr+="0";
		return arr;
	}
	
	//Finding row where the queen to be inserted.
	int find(String arr)
	{
	    int i;
		for(i=0;i<SIZE;i++)
		{
			int flag = 0;
			for(int j=0;j<SIZE;j++)
			{
				if(arr.charAt(SIZE*i+j) == '1')
				{
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				return i;
		}
		return i;
	}
	
	//Is current state valid?
	boolean isValid(String arr)
	{
		for(int i=0;i<SIZE;++i)
		{
			int row_sum = 0;
			int col_sum = 0;
			for(int j=0;j<SIZE;++j)
			{
				row_sum=row_sum + (int)arr.charAt(SIZE*i+j)-48;
				col_sum=col_sum + (int)arr.charAt(SIZE*j+i)-48;
			}
			if(row_sum > 1 || col_sum > 1)
				return false;
		}
		for(int i=0;i<SIZE;++i)
		{
			int left_sum = 0;
			int right_sum = 0;
			for(int j=0;j+i<SIZE;++j)
			{
				left_sum=left_sum + (int)arr.charAt(SIZE*j+j+i)-48;
				right_sum=right_sum + (int)arr.charAt(SIZE*(j+i)+j)-48;
			}
			if(left_sum > 1 || right_sum > 1)
				return false;
		}
		for(int i=0;i<2*SIZE-1;++i)
		{
			int left_sum = 0;
			int right_sum = 0;
			if(i<SIZE)
			{
			   for(int j=0;i-j>=0;++j)
				   left_sum = left_sum + (int)arr.charAt(SIZE*j+i-j)-48;
			}
			else
			{
			   for(int j=i-SIZE+1;j<SIZE;++j)
			       right_sum=right_sum + (int)arr.charAt(SIZE*j+i-j)-48;
			}
			if(left_sum > 1 || right_sum > 1)
				return false;
		}
		return true;
	}
	
	//print valid state
	void printing(String arr)
	{
		for(int i=0;i<SIZE;i++)
		{
			for(int j=0;j<SIZE;j++)
			{
				if(arr.charAt(SIZE*i+j)=='1')
					System.out.print("Q ");
				else 
					System.out.print("_ ");
			}
		System.out.println();
		}
	}
	
	//main function
	public static void main(String args[])
	{
		Eight_Q obj = new Eight_Q();
		String sol = "";
		sol = obj.initialise(sol);
		
		LinkedList<String> ucs = new LinkedList<String>();
		ucs.add(sol);
		
		int count = 1;
		
		while(!(ucs.isEmpty()))
		{
			//dequeue head of the queue
			String answer = ucs.poll();
			
			//check if the current state is valid
			if(!(obj.isValid(answer)))continue;
			
			//find where to insert the queen
			int i = obj.find(answer);
			
			//if the state is valid and solution is complete
			if(i==obj.SIZE)
			{
				System.out.println("Solution:"+count);
				obj.printing(answer);
				System.out.println("\n \n");
				++count;
			}
			else
			{
				//explore the current node and enqueue it
				for(int j=0;j<obj.SIZE;++j)
				{
					answer = answer.substring(0, obj.SIZE*i+j)+"1"+answer.substring(obj.SIZE*i+j+1);
					ucs.offer(answer);
					answer = answer.substring(0, obj.SIZE*i+j)+"0"+answer.substring(obj.SIZE*i+j+1);
				}
			}
			
		}
	}
}