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
    ArrayList<Neuron> inputs = new ArrayList<>();
    ArrayList<Neuron> hiddens = new ArrayList<>();
    ArrayList<Neuron> outputs = new ArrayList<>();
            
    /**
     * Set inputs on the ANN
     * @param new_input 
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
            if (i == 1) {
                /* Inputs layer */
                for (int j = 0; j < input_dimension; j++) {
                  inputs.add(new Neuron());
                }
            }
            else if (i == 2) {
                /* Hidden layer */
                for (int j = 0; j < hidden_dimension; j++) {
                    hiddens.add(new Neuron());
                }
            }
            else if (i == 3) {
                /* Output layer */
                for (int j = 0; j < output_dimension; j++) {
                    outputs.add(new Neuron());
                }
            }
        }
    }
    
}

