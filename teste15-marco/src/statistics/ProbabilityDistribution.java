package statistics;

import java.math.*;
import java.util.ArrayList;

public class ProbabilityDistribution {
	private NormalDistribution d;
	static protected ArrayList<NormalDistribution> nd = new ArrayList<NormalDistribution>();
	
	
	public ProbabilityDistribution() {
	};
	
	public ProbabilityDistribution(double Mean, double Stddev)
	{
		this.d = new NormalDistribution(Mean, Stddev);
	}
	
	public double getMean()
	{
		return this.d.getMean();
	}
	
	public double getStddev()
	{
		return this.d.getStddev();
	}
	
	public String getName()
	{
		return this.d.getName();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public double probabilityDensityFunction(double x) {	
		double res = (1/(getStddev() * Math.sqrt(2 * Math.PI)) * Math.exp(-((x - getMean()) * (x - getMean())) / (2 * getStddev() * getStddev())));
		return res;
	}
	public double calcRangeProbability(int a, int b) {
		  double res = (b - a)/2;
		  double prob2sigma = 0.477250; 
		  return res * prob2sigma;
	}
	public double calcLeftProbability(double x) {
		double res = (x - 1.0)/2;
	    double prob2sigma = 0.477250;
		return 0.5 + res * prob2sigma;
		
	}

	public static NormalDistribution find(String string) {
		
		for(int i =0; i <nd.size(); i++)
		{
			System.out.print(nd.get(i).getName());
			if(nd.get(i).getName().equals(string))
				return nd.get(i);
		}
		return null;		
	}
	
}
