/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import Classes.CBook;
import Classes.MysqlConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author iiscancinos
 */
public class ANN {
    final boolean isTrained = true;
    DecimalFormat df;
    final Random rand = new Random();
    ArrayList<Neuron> inputLayer = new ArrayList<Neuron>();
    ArrayList<Neuron> hiddenLayer = new ArrayList<Neuron>();
    ArrayList<Neuron> outputLayer = new ArrayList<Neuron>();
    final Neuron bias = new Neuron();
    int[] layers;
    final int randomWeightMultiplier = 1;
 
    final double epsilon = 0.00000000001;
 
    final double learningRate = 0.1f;
    final double momentum = 0.07f;
    
    double inputs[][]; 
    final double expectedOutputs[][] = new double[1][1];
    double resultOutputs[][] = new double[1][1];
    double output[];
 
    // for weight update all
    final HashMap<String, Double> weightUpdate = new HashMap<>();
    String cats[] = {"Fiction", "History", "Biography", "Juvenile fiction", "Social life and customs"};
    MysqlConnection connection;
    
    public ANN() throws SQLException{
        connection = new MysqlConnection();
        connection.connect();
    }
 
    
    /**
     * Popular category: Fiction (1)
     * Popular category: History (2)
     * Popular category: Biography (3)
     * Popular category: Juvenile fiction (4)
     * Popular category: Social life and customs (5)
     * 
     * 
     * @param id
     * @return 
     */
    public LinkedList<String> getRecommendations(int id){
        
        
        LinkedList<String> recommendedIds = new LinkedList<>();
        //get user favorite categories
        
        inputs = new double[1][10];
        for (String category : cats) {
            setInputsUserByCat(category, connection.getCategoryPorcent(id, category));
        }
        //inputs[0][5] = 1; inputs[0][6] = 0; inputs[0][7] = 1; inputs[0][8] = 0; inputs[0][9] = 0;
        
        //get all the books on the database and iterate over it
        for (CBook book : connection.getBooks()) {
            String[] categories = book.getBook_genre().split(",");
            for (String category : categories) {
                setInputsBookByCat(category);
            }
            NeuralNetwork(10, 8, 1);
            run(1, 0.001);
            if (getOutput()[0] > 0.9) {
                //recommendedIds.add(book.getBookId());
            }
        }
        return recommendedIds;
    }
    
    public void setInputsUserByCat(String category, double threshold){
        double value = 0;
        if (threshold > 0.6) {
            value = 1;
        }
        switch(category){
            case("Fiction"):
                inputs[0][5] = value;
                break;
            case("History"):
                inputs[0][6] = value;
                break;
            case("Biography"):
                inputs[0][7] = value;
                break;
            case("Juvenile fiction"):
                inputs[0][8] = value;
                break;
            case("Social life and customs"):
                inputs[0][9] = value;
                break;
               
        }
    }
    
    public void setInputsBookByCat(String category)
    {
        switch(category){
            case("Fiction"):
                inputs[0][0] = 1;
                break;
            case("History"):
                inputs[0][1] = 1;
                break;
            case("Biography"):
                inputs[0][2] = 1;
                break;
            case("Juvenile fiction"):
                inputs[0][3] = 1;
                break;
            case("Social life and customs"):
                inputs[0][4] = 1;
                break;
               
        }
    }
   
    public void NeuralNetwork(int input, int hidden, int output){
        this.layers = new int[] { input, hidden, output };
        df = new DecimalFormat("#.0#");
        //getDataToTrain();
        /**
         * Create all neurons and connections Connections are created in the
         * neuron class
         */
        for (int i = 0; i < layers.length; i++) {
            if (i == 0) { // input layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    inputLayer.add(neuron);
                }
            } else if (i == 1) { // hidden layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addInConnectionsS(inputLayer);
                    neuron.addBiasConnection(bias);
                    hiddenLayer.add(neuron);
                }
            }
 
            else if (i == 2) { // output layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addInConnectionsS(hiddenLayer);
                    neuron.addBiasConnection(bias);
                    outputLayer.add(neuron);
                }
            } else {
                System.out.println("!Error NeuralNetwork init");
            }
        }
 
        // initialize random weights
        for (Neuron neuron : hiddenLayer) {
            ArrayList<Connection> connections = neuron.getAllInConnections();
            for (Connection conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }
        for (Neuron neuron : outputLayer) {
            ArrayList<Connection> connections = neuron.getAllInConnections();
            for (Connection conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }
 
        // reset id counters
        Neuron.counter = 0;
        Connection.counter = 0;
 
        if (isTrained) {
            trainedWeights();
            updateAllWeights();
        }
    }
 
    // random
    double getRandom() {
        return randomWeightMultiplier * (rand.nextDouble() * 2 - 1); // [-1;1[
    }
 
    /**
     * 
     * @param inputs
     *            There is equally many neurons in the input layer as there are
     *            in input variables
     */
    public void setInput(double inputs[]) {
        for (int i = 0; i < inputLayer.size(); i++) {
            inputLayer.get(i).setOutput(inputs[i]);
        }
    }
 
    public double[] getOutput() {
        double[] outputs = new double[outputLayer.size()];
        for (int i = 0; i < outputLayer.size(); i++)
            outputs[i] = outputLayer.get(i).getOutput();
        return outputs;
    }
 
    /**
     * Calculate the output of the neural network based on the input The forward
     * operation
     */
    public void activate() {
        for (Neuron n : hiddenLayer)
            n.calculateOutput();
        for (Neuron n : outputLayer)
            n.calculateOutput();
    }
 
    /**
     * all output propagate back
     * 
     * @param expectedOutput
     *            first calculate the partial derivative of the error with
     *            respect to each of the weight leading into the output neurons
     *            bias is also updated here
     */
    public void applyBackpropagation(double expectedOutput[]) {
 
        // error check, normalize value ]0;1[
        for (int i = 0; i < expectedOutput.length; i++) {
            double d = expectedOutput[i];
            if (d < 0 || d > 1) {
                if (d < 0)
                    expectedOutput[i] = 0 + epsilon;
                else
                    expectedOutput[i] = 1 - epsilon;
            }
        }
 
        int i = 0;
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double ak = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double desiredOutput = expectedOutput[i];
 
                double partialDerivative = -ak * (1 - ak) * ai
                        * (desiredOutput - ak);
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
            i++;
        }
 
        // update weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double aj = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Neuron out_neu : outputLayer) {
                    double wjk = out_neu.getConnection(n.id).getWeight();
                    double desiredOutput = (double) expectedOutput[j];
                    double ak = out_neu.getOutput();
                    j++;
                    sumKoutputs = sumKoutputs
                            + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                }
 
                double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
        }
    }
 
    public void run(int maxSteps, double minError) {
        int i;
        // Train neural network until minError reached or maxSteps exceeded
        double error = 1;
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.length; p++) {
                setInput(inputs[p]);
 
                activate();
 
                output = getOutput();
                resultOutputs[p] = output;
 
                for (int j = 0; j < expectedOutputs[p].length; j++) {
                    double err = Math.pow(output[j] - expectedOutputs[p][j], 2);
                    error += err;
                }
 
                applyBackpropagation(expectedOutputs[p]);
            }
        }
             printResult();
         
        System.out.println("Sum of squared errors = " + error);
        System.out.println("##### EPOCH " + i+"\n");
        if (i == maxSteps) {
            System.out.println("!Error training try again");
        } else {
            printAllWeights();
            printWeightUpdate();
        }
    }
 
    String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
 
    /**
     * Take from hash table and put into all weights
     */
    public void updateAllWeights() {
        // update weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
        // update weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
    }
 
    // trained data
    void trainedWeights() {
        weightUpdate.clear();
         
        weightUpdate.put(weightKey(11, 0), -4.55);
        weightUpdate.put(weightKey(11, 1), -13.24);
        weightUpdate.put(weightKey(11, 2), -2.19);
        weightUpdate.put(weightKey(11, 3), -2.81);
        weightUpdate.put(weightKey(11, 4), -1.69);
        weightUpdate.put(weightKey(11, 5), 6.11);
        weightUpdate.put(weightKey(11, 6), 12.33);
        weightUpdate.put(weightKey(11, 7), -10.35);
        weightUpdate.put(weightKey(11, 8), -7.05);
        weightUpdate.put(weightKey(11, 9), -8.32);
        weightUpdate.put(weightKey(11, 10), -.64);
        weightUpdate.put(weightKey(12, 11), -.65);
        weightUpdate.put(weightKey(12, 12), -.64);
        weightUpdate.put(weightKey(12, 13), -1.08);
        weightUpdate.put(weightKey(12, 14), -9.19);
        weightUpdate.put(weightKey(12, 15), -2.08);
        weightUpdate.put(weightKey(12, 16), -1.85);
        weightUpdate.put(weightKey(12, 17), -.08);
        weightUpdate.put(weightKey(12, 18), -1.49);
        weightUpdate.put(weightKey(12, 19), 4.93);
        weightUpdate.put(weightKey(12, 20), -1.01);
        weightUpdate.put(weightKey(12, 21), .43);
        weightUpdate.put(weightKey(13, 22), 8.42);
        weightUpdate.put(weightKey(13, 23), 2.13);
        weightUpdate.put(weightKey(13, 24), .85);
        weightUpdate.put(weightKey(13, 25), 1.07);
        weightUpdate.put(weightKey(13, 26), .45);
        weightUpdate.put(weightKey(13, 27), -4.39);
        weightUpdate.put(weightKey(13, 28), 1.52);
        weightUpdate.put(weightKey(13, 29), 1.61);
        weightUpdate.put(weightKey(13, 30), .52);
        weightUpdate.put(weightKey(13, 31), 1.13);
        weightUpdate.put(weightKey(13, 32), .16);
        weightUpdate.put(weightKey(14, 33), -1.2);
        weightUpdate.put(weightKey(14, 34), -1.09);
        weightUpdate.put(weightKey(14, 35), -1.48);
        weightUpdate.put(weightKey(14, 36), -.75);
        weightUpdate.put(weightKey(14, 37), -9.83);
        weightUpdate.put(weightKey(14, 38), -.39);
        weightUpdate.put(weightKey(14, 39), .04);
        weightUpdate.put(weightKey(14, 40), -.75);
        weightUpdate.put(weightKey(14, 41), -2.32);
        weightUpdate.put(weightKey(14, 42), 4.83);
        weightUpdate.put(weightKey(14, 43), .74);
        weightUpdate.put(weightKey(15, 44), .94);
        weightUpdate.put(weightKey(15, 45), -.45);
        weightUpdate.put(weightKey(15, 46), 1.17);
        weightUpdate.put(weightKey(15, 47), .98);
        weightUpdate.put(weightKey(15, 48), .97);
        weightUpdate.put(weightKey(15, 49), -1.21);
        weightUpdate.put(weightKey(15, 50), -.11);
        weightUpdate.put(weightKey(15, 51), -1.22);
        weightUpdate.put(weightKey(15, 52), -1.33);
        weightUpdate.put(weightKey(15, 53), -1.17);
        weightUpdate.put(weightKey(15, 54), -.06);
        weightUpdate.put(weightKey(16, 55), -2.87);
        weightUpdate.put(weightKey(16, 56), 14.57);
        weightUpdate.put(weightKey(16, 57), -5.81);
        weightUpdate.put(weightKey(16, 58), -4.43);
        weightUpdate.put(weightKey(16, 59), -4.31);
        weightUpdate.put(weightKey(16, 60), 2.69);
        weightUpdate.put(weightKey(16, 61), -19.92);
        weightUpdate.put(weightKey(16, 62), -2.05);
        weightUpdate.put(weightKey(16, 63), .16);
        weightUpdate.put(weightKey(16, 64), -.24);
        weightUpdate.put(weightKey(16, 65), -.21);
        weightUpdate.put(weightKey(17, 66), 8.26);
        weightUpdate.put(weightKey(17, 67), -12.2);
        weightUpdate.put(weightKey(17, 68), .89);
        weightUpdate.put(weightKey(17, 69), 1.65);
        weightUpdate.put(weightKey(17, 70), .27);
        weightUpdate.put(weightKey(17, 71), -19.46);
        weightUpdate.put(weightKey(17, 72), 1.68);
        weightUpdate.put(weightKey(17, 73), 6.67);
        weightUpdate.put(weightKey(17, 74), 1.44);
        weightUpdate.put(weightKey(17, 75), 1.76);
        weightUpdate.put(weightKey(17, 76), .98);
        weightUpdate.put(weightKey(18, 77), 4.09);
        weightUpdate.put(weightKey(18, 78), 4.21);
        weightUpdate.put(weightKey(18, 79), 12.59);
        weightUpdate.put(weightKey(18, 80), 2.79);
        weightUpdate.put(weightKey(18, 81), 2.24);
        weightUpdate.put(weightKey(18, 82), .19);
        weightUpdate.put(weightKey(18, 83), -.12);
        weightUpdate.put(weightKey(18, 84), -9.7);
        weightUpdate.put(weightKey(18, 85), 3.44);
        weightUpdate.put(weightKey(18, 86), 4.07);
        weightUpdate.put(weightKey(18, 87), .76);
        weightUpdate.put(weightKey(19, 88), -20.66);
        weightUpdate.put(weightKey(19, 89), -33.8);
        weightUpdate.put(weightKey(19, 90), 36.98);
        weightUpdate.put(weightKey(19, 91), -32.63);
        weightUpdate.put(weightKey(19, 92), -33.6);
        weightUpdate.put(weightKey(19, 93), -30.44);
        weightUpdate.put(weightKey(19, 94), -21.98);
        weightUpdate.put(weightKey(19, 95), 30.31);
        weightUpdate.put(weightKey(19, 96), .08);
    }
    
    /*private void getDataToTrain() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\iiscancinos\\Desktop\\sb\\src\\sb\\trainedData.txt"))) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                setIO(line.split(","), i); 
                i++;
                line = br.readLine();
            }
        }
    }*/
    public void setIO(String[] data, int index){
        for (int i = 0; i < data.length; i++) {
            if (i <= 9) {
                inputs[index][i] = Integer.parseInt(data[i].trim());
            }
            else{
                expectedOutputs[index][0] = Integer.parseInt(data[i].trim());
                resultOutputs[index][0] = -1;
            }
        }
    }
    
    void printResult()
    {
        System.out.println("NN example with xor training");
        for (int p = 0; p < inputs.length; p++) {
            System.out.print("INPUTS: ");
            for (int x = 0; x < layers[0]; x++) {
                System.out.print(inputs[p][x] + " ");
            }
 
            System.out.print("EXPECTED: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(expectedOutputs[p][x] + " ");
            }
 
            System.out.print("ACTUAL: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(resultOutputs[p][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
      
    public void printWeightUpdate() {
        System.out.println("printWeightUpdate, put this i trainedWeights() and set isTrained to true");
        // weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String w = df.format(con.getWeight());
                System.out.println("weightUpdate.put(weightKey(" + n.id + ", "
                        + con.id + "), " + w + ");");
            }
        }
        // weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String w = df.format(con.getWeight());
                System.out.println("weightUpdate.put(weightKey(" + n.id + ", "
                        + con.id + "), " + w + ");");
            }
        }
        System.out.println();
    }
 
    public void printAllWeights(){
        System.out.println("printAllWeights");
        // weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double w = con.getWeight();
                System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        // weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double w = con.getWeight();
                System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        System.out.println();
    }
}

