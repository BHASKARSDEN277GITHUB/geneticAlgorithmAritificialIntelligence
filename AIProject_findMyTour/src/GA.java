
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhaskar
 */
public class GA {
     /* GA parameters */
    private static   double mutationRate=0.015 ;
    private  static  int tournamentSize=5 ;
    private  static  boolean elitism =true;
    public static JTextArea outTextArea = new JTextArea();
    public static StringBuilder outString =new StringBuilder("");
    
    
    
    
    //creating a new constructor to pass values from previous class ..
    
    public GA(double mutationRate, int groupSize,JTextArea outTextArea)
    {
        this.mutationRate=mutationRate;
        this.tournamentSize=groupSize;
        this.outTextArea=outTextArea;
      //  startTime=System.currentTimeMillis();
        
    }
    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            
            System.out.println("Selecting parents for crossover \n");
           // outTextArea.append("Selecting parents for crossover \n");
           //   outTextArea.update(outTextArea.getGraphics());
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        
        //System.out.println("Begining the crossover \n ");
        //outTextArea.append("Begining the crossover \n ");
        
        //outTextArea.update(outTextArea.getGraphics());
        
        outString.append("Beginning Crossover \n");
        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // Loop and add the sub tour from parent1 to our child
        
        outString.append("Looping and adding sub tour from parent1 to child\n");
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        outString.append("Looping and adding sub tour from parent2 to child\n");
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        //System.out.println("Returning the child after crossover \n");
        outString.append("Returning child after crossover\n");
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour) {
        // Loop through tour cities
       
        /*outTextArea.append("Begining the mutation ( MUTATION RATE : "+mutationRate+" ) \n");
        outTextArea.update(outTextArea.getGraphics());
        System.out.println("Begining the mutation ( MUTATION RATE : "+mutationRate+" ) \n");/*/
        
        outString.append("Begining the mutation ( MUTATION RATE : "+mutationRate+" ) \n");
        
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
                
                //print tour ..
                
                
            }
        }
        //System.out.println("Mutation done \n");
    }

    // Selects candidate tour for crossover
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        /*outTextArea.append("Creating the tournament population \n");
        outTextArea.update(outTextArea.getGraphics());
        System.out.println("Creating the tournament population \n");*/
      
        outString.append("Creating the tournament population \n");
        
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
            
            
            //print ..
            
            
        }
        // Get the fittest tour
       // outString=outString+"Getting the fittest tour \n";
        //outTextArea.setText(outString);
        System.out.println("Getting the fittest tour \n");
        Tour fittest = tournament.getFittest();
        outString.append("Fittest tour till now : \n"+fittest.toString()+"\n");
        
        
        
        
        outTextArea.append("\n"+outString.toString()+"\n");
        outTextArea.update(outTextArea.getGraphics());
        
        
        return fittest;
    } 
}
