package lfink;

public class NerualDriver 
{
	public static void main(String []args)
	{
		double [][][] scores = NeuralNet.andscores;
		double[] weights = NeuralNet.INITIAL_WEIGHTS;
		NerualDriver driver = new NerualDriver();
		NeuralNet net = new NeuralNet();
		boolean errorFlag = true;
		double error = 0;
		double[] adjustedWeights = null;
		int epochNumber =  0;
		while(errorFlag==true)
		{
			printHeading(epochNumber++);
			errorFlag = false;
			error = 0;
			for (int x = 0; x< scores.length; x++){
				double weightedSum = net.clcWeightSm(scores[x][0], weights);
				double result = net.applyActivation(weightedSum);
				error = scores [x][1][0] - result;
				//error = round(error,10);
				if(error !=0) errorFlag = true;
				adjustedWeights = net.adjustWeights(scores[x][0], weights, error);
				driver.printVector(scores[x], weights, result, error, weightedSum, adjustedWeights);
				weights = adjustedWeights;
			}
		}
	}

	private static void printHeading(int epochNumber) 
	{
		System.out.println("\n===================================Epoch # " + epochNumber + "================================");
		System.out.println(" w1 | w2 | e(t) | INT(e(t)) | d(e(t))/dt | u(t)| Result | Error | Weighted Sum | adjusted w1 | adjusted w2 ");
		System.out.println("------------------------------------------------------------------------------------------------");
	}
	
	public void printVector(double[][] scores, double[] weights, double result, double error, double weightedSum, double[] adjustedWeights)
	{
		System.out.println(" "+ String.format("%.2f", weights[0])+ " | " + 
				String.format("%.2f", weights[1]) +"  |  "+ scores[0][1]+ "     |" + scores[0][0] +
				"  |  " + scores[1][0]+"      |   " +result+"    | "+ error+
				"   | " +String.format("%.2f", weightedSum)+
				"       |  " + String.format("%.2f", adjustedWeights[0] )+
				"       |    " + String.format("%.2f",  adjustedWeights[1]) );
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
