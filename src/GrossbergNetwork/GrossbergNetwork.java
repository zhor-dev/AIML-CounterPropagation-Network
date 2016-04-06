package GrossbergNetwork;

import java.io.*;

public class GrossbergNetwork {
    private double[] desiredOutput;
    private Layer layer;
    private double betta = 0.1;

    public GrossbergNetwork(int layerSize, double[] kohonenOutput) {
        layer = new Layer(layerSize, kohonenOutput);
    }

    public double[] output() {
        return layer.output();
    }

    public void learn() {
        double[] kOutput = layer.getNeurons()[0].getInput();
        int index = 0;
        for (int i = 0; i < kOutput.length; ++i) {
            if (kOutput[i] == 1) {
                index = i;
                break;
            }
        }
        for (int i = 0; i < layer.getNeurons().length; ++i) {
            layer.getNeurons()[i].getWeights()[index] +=
                    (desiredOutput[i] - layer.getNeurons()[i].getWeights()[index]) * betta;
        }
    }

    public void setDesiredOuput(double[] desiredOutput) {
        this.desiredOutput = desiredOutput;
    }

    public void saveWeights() {
        String res = "";
        BufferedWriter output = null;
        try {
            File file = new File("src/weights1.txt");
            output = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < layer.getNeurons().length; ++i) {
                for (int j = 0; j < layer.getNeurons()[i].getWeights().length; ++j) {
                    res += layer.getNeurons()[i].getWeights()[j] + "\n";
                }
            }
            output.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static double[][] loadWeights(double[][] weights) {
        BufferedReader buffReader = null;
        try {
            FileReader fileReader = new FileReader("src/weights1.txt");
            buffReader = new BufferedReader(fileReader);
            for (int i = 0; i < weights.length; ++i) {
                for (int j = 0; j < weights[0].length; ++j) {
                    weights[i][j] = Double.parseDouble(buffReader.readLine());
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                assert buffReader != null;
                buffReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return weights;
    }
}
