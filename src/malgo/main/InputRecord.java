package malgo.main;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputRecord {
	double sd[],mean[];
	FileInputStream input,xInput,yInput;	
public static class Record{
    	public  double inputXi[];
    	public double y;
    	
    	public Record(int n)
    	{    
    		
    		inputXi=new double[n+1];
			inputXi[0]=1.0;	
    	}
  	}
    
	
	public List<Record> recordList=new ArrayList<Record>();
	
	public InputRecord(int n,String xFileName,String yFileName,String delimnator) throws FileNotFoundException {
	     
		xInput=new FileInputStream(xFileName);
		yInput=new FileInputStream(yFileName);
		BufferedReader readerX=new BufferedReader(new InputStreamReader(xInput));
		BufferedReader readerY=new BufferedReader(new InputStreamReader(yInput));
		String x=new String();
		String iy=new String();
	    
		try {
			while(((x=readerX.readLine())!=null)&&((iy=readerY.readLine()))!=null)
			{   
				Record record=new Record(n);
				String[] arry=new String[n];
				arry=x.split(delimnator);
				for(int i=0;i<arry.length;i++)
			    {
			    	record.inputXi[i+1]=Double.parseDouble(arry[i]);
			    	
			    }
			    
			    record.y=Double.parseDouble(iy);
			    recordList.add(record);
			    
			}
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
		
		sd=new double[n+1];
		mean=new double[n+1];
		for(int i=0;i<=n;i++)
		{
			mean[i]=0.0;
			sd[i]=0.0;
		}
		
	    
	    
	}
	
	public InputRecord(int n,String fileName,String delimnator) throws FileNotFoundException {
		
		input=new FileInputStream(fileName);
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(input));
		
		String s=new String();
		
	    
		try {
			while((s=reader.readLine())!=null)
			{   
				Record record=new Record(n);
				String[] arry=new String[n];
			    arry=s.split(delimnator);
				record.inputXi=new double[n+1];
				record.inputXi[0]=1.0;
			    for(int i=0;i<arry.length-1;i++)
			    {
			    	//System.out.print(arry[i]+" op ");
			    	record.inputXi[i+1]=Double.parseDouble(arry[i]);
			    	
			    }
			    
			    record.y=Double.parseDouble(arry[arry.length-1]);
			    recordList.add(record);
			    //System.out.println("="+record.y);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sd=new double[n+1];
		mean=new double[n+1];
		for(int i=0;i<=n;i++)
		{
			mean[i]=0.0;
			sd[i]=0.0;
		}
		
	    
	    
	}
	
	public   double scaleX(int i)  {
		
		for(Record in:recordList)
		       mean[i]+=in.inputXi[i];  	
		   mean[i]=mean[i]/recordList.size();
		   for(Record in:recordList)
			   sd[i]+=(in.inputXi[i]-mean[i])*(in.inputXi[i]-mean[i]);
		   sd[i]=java.lang.Math.sqrt(sd[i]/recordList.size());
		   
		 for(Record in:recordList)
	     	{   
			
			in.inputXi[i]=(in.inputXi[i]-mean[i])/sd[i];
			//System.out.println(in.inputXi[0]+"\t"+in.inputXi[1]+"\t"+in.inputXi[2]+"\t"+in.y);	
		    }
	return 0.0;
	}
	public Record scaleX(Record r,int n)
	{
		for(int i=1;i<=n;i++)
		{
			r.inputXi[i]=(r.inputXi[i]-mean[i])/sd[i];	
		}
	    return r;
	}
	
}
