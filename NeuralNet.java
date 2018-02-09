package lfink;

public class NeuralNet 

{
	public static final double THRESH =.5;
	public double x;
	public static final double [ ] [ ][ ]andscores= {{{0,0},{0}},
													{{.5,0},{0}},
													{{0,.5},{0}},
													{{.5,.5},{1}}};
	
	public static final double LEARNING_RATE = .25;
	public static final double[ ] INITIAL_WEIGHTS ={Math.random(), Math.random()};
	public double clcWeightSm (double[] score, double[] weights)
	{
		double weightedsum = 0;
		for(double x=0; x < score.length; x++) weightedsum += score[(int) x]* weights [(int) x];
		return weightedsum;
	}
	public double applyActivation(double weightedsum)
	{
		double sigmoid = Math.tanh(weightedsum);
		double result = 0;
		if(sigmoid>THRESH) result = round(weightedsum, 6);

		return result;
		
	}
	public double[] adjustWeights(double[] scores, double[] weights, double error)
	{
		double[] adjustedWeights = new double[weights.length];
		for(int x=0; x < weights.length; x++) adjustedWeights[x] = LEARNING_RATE * error * scores[x] + weights [x];
		return adjustedWeights;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}

