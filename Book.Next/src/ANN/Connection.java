/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

/**
 *
 * @author iiscancinos
 */
public class Connection {
    private double weight = 0, prevDeltaWeight = 0, deltaWeight = 0;
    Neuron neuStart = new Neuron(), neuEnd = new Neuron(); //Connected neurons
    static int counter = 0;
    final public int id;
    
    public Connection(Neuron fromN, Neuron toN){
        neuStart = fromN;
        neuEnd = toN;
        id = counter;
        counter++;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public void setWeight(double w){
        weight = w;
    }
    
    public void setDeltaWeight(double w){
        prevDeltaWeight = deltaWeight;
        deltaWeight = w;
    }
    
    public double getPrevDeltaWeight() {
        return prevDeltaWeight;
    }
 
    public Neuron getFromNeuron() {
        return neuStart;
    }
 
    public Neuron getToNeuron() {
        return neuEnd;
    }
}
