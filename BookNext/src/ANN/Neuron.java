/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.util.*;

/**
 *
 * @author jcdur
 */
public class Neuron {
    static int counter = 0;
    final public int id;  // auto increment, starts at 0
    Connection biasConnection;
    final double bias = -1;
    double output;
     
    ArrayList<Connection> Inconnections = new ArrayList<>();
    HashMap<Integer,Connection> connectionLookup = new HashMap<>();
    public Neuron(){        
        id = counter;
        counter++;
    }
    
     /**
     * Compute Oj = 
     */
    public void calculateOutput(){
     /*   double s = 0;
        for(Connection con : Inconnections){
            Neuron leftNeuron = con.getFromNeuron();
            double weight = con.getWeight();
            double a = leftNeuron.getOutput(); //output from previous layer
             
            s = s + (weight*a);
        }
        s = s + (biasConnection.getWeight()*bias);
         
        output = g(s);*/
    }
}
