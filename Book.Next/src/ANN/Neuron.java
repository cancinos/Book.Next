/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author iiscancinos
 */
public class Neuron {
    static int counter = 0; 
    private final int id; // auto increment value
    private final double bias = -1;
    private double output;
    ArrayList<Connection> connectedTo = new ArrayList<>();
    HashMap<Integer, Connection> connectionDictionary = new HashMap<>();
    
    
    public Neuron(){
        id = counter;
        counter++;
    }
    
    // Output = 
    
    
}
