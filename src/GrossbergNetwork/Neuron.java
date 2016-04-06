package GrossbergNetwork;

public class Neuron {

    private double[] kohonenOutput;
    private double[] weights;

    public Neuron(double[] i) {
        this.kohonenOutput = i;
        weights = new double[i.length];
        setRandRange(0, 1);
    }

    public void setRandRange(double randMin, double randMax) {
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = randMin + (randMax - randMin) * Math.random();
        }
    }

    public double summingBlock() {
        double sum = 0.0;
        for (int i = 0; i < kohonenOutput.length; ++i) {
            sum += kohonenOutput[i] * weights[i];
        }
        return sum;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public void setInput(double[] input) {
        this.kohonenOutput = input;
    }

    public double[] getInput() {
        return kohonenOutput;
    }

    public double[] getWeights() {
        return weights;
    }
}
