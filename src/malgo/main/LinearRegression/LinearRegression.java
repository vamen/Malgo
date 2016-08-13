package malgo.main.LinearRegression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import malgo.main.InputRecord;

public class LinearRegression {
    
	int n,choice,m;
	double[] theta;
	double[] prevTheta;
	int inputType;
	String xFileName=new String();
	String yFileName=new String();
	String fileName=new String();
	InputRecord input=null;
	String delimiter=new String();
	BufferedReader bReader=null; 
	
	public LinearRegression()
	{
	
	bReader=new BufferedReader(new InputStreamReader(System.in));
	try {
		
		System.out.println("enter the number of the input featuers");
		n=Integer.parseInt(bReader.readLine());
		
		System.out.println("do you have saparate files for input and output training samples ?\n\t0(no)/1(yes):");
		inputType=Integer.parseInt(bReader.readLine());
		
		if(inputType==1)
		{
			System.out.println("enter input variable file name");
			xFileName=bReader.readLine();
			
			System.out.println("enter output variable file name");
		    yFileName=bReader.readLine();
		}
		else
		{
			System.out.println("enter trining example file name");
			fileName=bReader.readLine();
			
		}
		
		System.out.print("inputFormat :\n1\tX1,X2,X3.....Xn,Y\n2\tX1\tX2\tX3.....\tXn\tY\n3\tOther : ");
		choice=Integer.parseInt(bReader.readLine());
		
		switch(choice)
		{
		case 1:
			delimiter=",";
			break;
		case 2:
			delimiter="\t";
			break;
		case 3:
			System.out.println("enter delimter of your input");
			delimiter=bReader.readLine();
			break;
			}
		
	} catch (NumberFormatException e) {
		System.out.println("enter integer value only");
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	theta=new double[n+1];	 
	prevTheta=new double[n+1];
	try {
		if(inputType==1)
		input=new InputRecord(n, xFileName, yFileName, delimiter);
		else
			input=new InputRecord(n,fileName,delimiter);
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		e.printStackTrace();
	}
	m=input.recordList.size();
	}
	public LinearRegression(int n,String file,String delim)
	{
		this.n=n;
	fileName=file;
	delimiter=delim;
	theta=new double[n+1];	 
	prevTheta=new double[n+1];
	try {
			input=new InputRecord(n,fileName,delimiter);
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		e.printStackTrace();
	}
	m=input.recordList.size();
	}
	public LinearRegression(int n,String xFile,String yFile,String delim)
	{
		this.n=n;
	xFileName=xFile;
	yFileName=yFile;
    delimiter=delim;
	
	theta=new double[n+1];	 
	prevTheta=new double[n+1];
	try {
			input=new InputRecord(n,xFileName,yFileName,delimiter);
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		e.printStackTrace();
	}
	m=input.recordList.size();
	}
    public void run(double alpha)
    {
    	gradiantDescentFunction(n, m, 100,0.5);
    }
	private  void gradiantDescentFunction(int n,int m,int num_ittr,double alpha)  {
		int j=0;
		
		for(int i=1;i<=n;i++)
		{   
			theta[i]=input.scaleX(i);
			
			prevTheta[i]=theta[i];
		}
		do
		{
			for(int i=0;i<=n;i++)
		      {	
				theta[i]=prevTheta[i]-(alpha*(partialDerivativeofCostFunction(prevTheta,i))/m);
		
	         }
		
		
		    
		for(int i=0;i<=n;i++)
			{
			prevTheta[i]=theta[i];
			System.out.print(theta[i]+"\t"); 
			}
		System.out.println("\n");
		 
	     	
	     j++;
		}while(j<num_ittr);
       		
		
		
		   
		   System.out.println((hypothisis(theta,input.recordList.get(1)))+"\nthank you");
		
	}
	private  double partialDerivativeofCostFunction(double[] theta,int j)  {
		double sum=0;
		for(InputRecord.Record in:input.recordList)
		{    
			   sum+=(hypothisis(theta,in)-in.y)*in.inputXi[j];
		}
		
		return sum;
	}
	private  double hypothisis(double[] theta, InputRecord.Record record)  {
	  	double sum=0.0;   
		for(int i=0;i<=n;i++)
		 sum+=theta[i]*record.inputXi[i];
	    return sum;
	}
	public double predict() throws NumberFormatException, IOException {
	    
		if(bReader==null)
	    	bReader=new BufferedReader(new InputStreamReader(System.in));
		InputRecord.Record record=new InputRecord.Record(n);
		
		    record.inputXi[0]=1.0;  
		for(int i=1;i<=n;i++)
		 {
			System.out.println("enter feature x"+(i));
		    record.inputXi[i]=Double.parseDouble(bReader.readLine());
		    
		 }
		 record=input.scaleX(record,n); 
		 
		 return hypothisis(theta, record);
		
	}
	
}
