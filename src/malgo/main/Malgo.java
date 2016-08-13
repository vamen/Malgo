package malgo.main;

import java.io.IOException;

import malgo.main.LinearRegression.LinearRegression;

public class Malgo {

	public static void main(String[] args) {
	//LinearRegression regression=new LinearRegression(2,"/home/vivek/vivek/ml/ex3x.dat","/home/vivek/vivek/ml/ex3y.dat","\t");
	    LinearRegression regression=new LinearRegression();
		regression.run(1);
		System.out.println("machine is trained enter the input to predict is output");
         try {
			System.out.println("predicted rent value : "+regression.predict()+" + or - upto 2%");
		} catch (NumberFormatException | IOException e) {
			System.out.println("enter proper values");
			e.printStackTrace();
		} 
	}

}
