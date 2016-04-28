/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.util.*;

/**
 *
 * @author iiscancinos
 */
public class ANN {
    /**
     * Global variables
     */
    int input_dimension = -1, hidden_dimension = -1, hidden_deepness = -1, output_dimension = -1;
    ArrayList<Neuron> inputs = new ArrayList<>(), hiddens = new ArrayList<>(), outputs = new ArrayList<>();
    Neuron bias = new Neuron(); 
    Boolean isTrained = false;
    // for weight update all
    final HashMap<String, Double> weightUpdate = new HashMap<>();
    Double learningRate = 0.01, momentum = 0.09, epsilon = 0.00000000001;
      // Inputs for xor problem
    final double inputs2[][] = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };
 
    // Corresponding outputs, xor training data
    final double expectedOutputs[][] = { { 0 }, { 1 }, { 1 }, { 0 } };
    double resultOutputs[][] = { { -1 }, { -1 }, { -1 }, { -1 } }; // dummy init
    double output[];        
    
    
    /**
     * Set inputs on the ANN
     * @param new_input_dimension 
     */
    public void set_input(int new_input_dimension){
        this.input_dimension = new_input_dimension;
    }
    
    /**
     * Set hidden layer dimension
     * @param new_hidden_dimension 
     */
    public void set_hidden_layer(int new_hidden_dimension){
        this.hidden_dimension = new_hidden_dimension;
    }
    
    /**
     * Set output layer dimension
     * @param new_output_dimension 
     */
    public void set_output_layer(int new_output_dimension){
        this.output_dimension = new_output_dimension;
    }
    
    /**
     * Set hidden layer deepness
     * @param new_hidden_deepness 
     */
    public void set_hidden_deepness(int new_hidden_deepness){
        this.hidden_deepness = new_hidden_deepness;
    }
    
    public void neural_network(){
        
        /**
         * Creating nodes for the three layers ANN
         */
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 1:
                    /* Inputs layer */
                    for (int j = 0; j < input_dimension; j++) {
                        inputs.add(new Neuron());
                    }   break;
                case 2:
                    /* Hidden layer */
                    for (int j = 0; j < hidden_dimension; j++) {
                        Neuron node = new Neuron();
                        node.addInConnectionsS(inputs); node.addBiasConnection(bias);
                        hiddens.add(node);
                    }   break;
                case 3:
                    /* Output layer */
                    for (int j = 0; j < output_dimension; j++) {
                        Neuron node = new Neuron();
                        node.addInConnectionsS(hiddens); node.addBiasConnection(bias);
                        outputs.add(node);
                    }   break;
                default:
                    break;
            }
        }
        
        /**
         * Generating random weights to connections on the ANN
         */
        inputs.stream().map((input) -> input.getAllInConnections()).forEach((connections) -> {
            connections.stream().forEach((connection) -> {
                connection.setWeight(Math.random());
            });
        });
        hiddens.stream().map((hidden) -> hidden.getAllInConnections()).forEach((connections) -> {
            connections.stream().forEach((connection) -> {
                connection.setWeight(Math.random());
            });
        });
        
         // reset id counters
        Neuron.counter = 0;
        Connection.counter = 0;
 
        if (isTrained) {
            trainedWeights();
            updateAllWeights();
        }
    }
    
      /**
     * 
     * @param inputsParam
     */
    public void setInput(double inputsParam[]) {
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setOutput(inputsParam[i]);
        }
    }
 
    public double[] getOutput() {
        double[] outputsFlag = new double[outputs.size()];
        for (int i = 0; i < outputs.size(); i++)
            outputsFlag[i] = outputs.get(i).getOutput();
        return outputsFlag;
    }
 
    /**
     * Calculate the output of the neural network based on the input The forward
     * operation
     */
    public void activate() {
        for (Neuron n : hiddens)
            n.calculateOutput();
        for (Neuron n : outputs)
            n.calculateOutput();
    }
    
    String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
 
    /**
     * Take from hash table and put into all weights
     */
    public void updateAllWeights() {
        // update weights for the output layer
        for (Neuron n : outputs) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
        // update weights for the hidden layer
        for (Neuron n : hiddens) {
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
         
        weightUpdate.put(weightKey(3, 0), 1.03);
        weightUpdate.put(weightKey(3, 1), 1.13);
        weightUpdate.put(weightKey(3, 2), -.97);
        weightUpdate.put(weightKey(4, 3), 7.24);
        weightUpdate.put(weightKey(4, 4), -3.71);
        weightUpdate.put(weightKey(4, 5), -.51);
        weightUpdate.put(weightKey(5, 6), -3.28);
        weightUpdate.put(weightKey(5, 7), 7.29);
        weightUpdate.put(weightKey(5, 8), -.05);
        weightUpdate.put(weightKey(6, 9), 5.86);
        weightUpdate.put(weightKey(6, 10), 6.03);
        weightUpdate.put(weightKey(6, 11), .71);
        weightUpdate.put(weightKey(7, 12), 2.19);
        weightUpdate.put(weightKey(7, 13), -8.82);
        weightUpdate.put(weightKey(7, 14), -8.84);
        weightUpdate.put(weightKey(7, 15), 11.81);
        weightUpdate.put(weightKey(7, 16), .44);
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
        for (Neuron n : outputs) {
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
        hiddens.stream().forEach((n) -> {
            ArrayList<Connection> connections = n.getAllInConnections();
            connections.stream().forEach((con) -> {
                double aj = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Neuron out_neu : outputs) {
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
            });
        });
    }
 
    void run(int maxSteps, double minError) {
        int i;
        // Train neural network until minError reached or maxSteps exceeded
        double error = 1;
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs2.length; p++) {
                setInput(inputs2[p]);
 
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
    }
}

