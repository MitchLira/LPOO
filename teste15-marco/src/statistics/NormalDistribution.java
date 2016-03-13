package statistics;

import java.util.ArrayList;


public class NormalDistribution extends ProbabilityDistribution {


	private double Mean;
	private String name;
	private double Stddev;
	
	
	public NormalDistribution(){		
		this.Mean = 0.0;
		this.Stddev = 1.0;
		this.name = "";
		if(this.Stddev == 0)
			throw new IllegalArgumentException();
		nd.add(this);
		

		
	}
	public NormalDistribution(double Mean, double Stddev){
		this.Mean = Mean;
		this.Stddev = Stddev;
		this.name = "";
		
		if(this.Stddev == 0)
			throw new IllegalArgumentException();
		nd.add(this);
	
	}
	

	public NormalDistribution(String name, double Mean, double Stddev) {
		this.name = name;
		this.Mean = Mean;
		this.Stddev = Stddev;
		if(this.Stddev == 0)
			throw new IllegalArgumentException();
		nd.add(this);
		
	}
	public double getMean()
	{
		return this.Mean;
	}
	public double getStddev()
	{
		return this.Stddev;
	}
	public String getName()
	{
		return this.name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this.getClass() != obj.getClass())
			return false;
		
		NormalDistribution d = (NormalDistribution) obj;
		
		return (this.Mean == d.Mean && this.Stddev == d.Stddev & this.name == d.name);		
	}
	
	@Override
	public String toString() {
		
		String res = "N(" + this.Mean + ", " + this.Stddev +")";
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
