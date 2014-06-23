
import java.io.InputStream;
import java.lang.Integer;
import java.util.Scanner;
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
public class HPTOUR_GA {
    
    
    public static String selectedPlaces =""; 
    public static int[] id = new int[20]; //array for id's of each place to visit ..
    public static JTextArea output = new JTextArea() ;
    //declaring default constraints for the genetic algorithm ..
    public static int population =20; //by default 50 is the population ..
    public static double mutationRate= (double)0.015;//by default 0.015 is the mutation rate ..
    public static int groupSize=5;//by default 5 is the group size ..
    public static JTextArea outTextArea=new JTextArea();
    // public static algoEvaluation console = new algoEvaluation();
    
    //constructor ..
    
    public HPTOUR_GA(String selectedPlaces,JTextArea out,double mutationRate,int groupSize,int population,JTextArea outTextArea)
    {
        this.selectedPlaces=selectedPlaces;
        this.output = out;
        this.mutationRate=mutationRate;
        this.groupSize=groupSize;
        this.population=population;
        this.outTextArea=outTextArea;
//        this.console=console;
    }
    
    /**
     *
     * @param selectedPlaces
     * @param out
     */
    public HPTOUR_GA(String selectedPlaces,JTextArea out,JTextArea outTextArea)
    {
        this.selectedPlaces=selectedPlaces;
        this.output = out;
        this.outTextArea=outTextArea;
        
        
//        this.console=console;
    }
    
    public void findOptimalTour()
    {
        
       String[] splitPlaces = selectedPlaces.split("\n");
        
        //read the identifiers corresponding to each city from the file identifiers ..
        
        
        
        String str ="";
        
        String[] splitA =null ;
        
        for(int i=0;i<splitPlaces.length;i++)
        {
            InputStream in = getClass().getResourceAsStream("resources/id.txt");
            Scanner  sc = new Scanner(in); //creating scanner for scanning the id.txt file ..
            
            
            //System.out.println("lol");
            while(sc.hasNext())
            {
                    str=sc.nextLine();
                    if(str.startsWith(splitPlaces[i]))
                    {
                        splitA=str.split(" ");
                        id[i]=Integer.parseInt(splitA[1]);
                       // System.out.println(id[i]);
                        break;
                    }
            }
        }
        
        // Create and add our cities
      
   
        for(int i=0;i<splitPlaces.length;i++)
        {
            City city = new City(id[i],splitPlaces[i]);
            TourManager.addCity(city);
        }
        System.out.println("done");
         // Initialize population
        Population pop = new Population(this.population, true);
        //algoEvaluation.outString="\n INITIALIZING THE POPULATION \n";
        //console.setVisible(true);
        //console.setoutput();
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 5 generations
       
        GA object = new GA(mutationRate,groupSize,outTextArea);
        //outTextArea.setText("");
        //outTextArea.update(outTextArea.getGraphics());
        System.out.println("done");
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 5; i++) {
            pop = GA.evolvePopulation(pop);
        }
        
        
       
        System.out.println("done");
        // Print final results
        String out ="";
        out = out+"TOTAL DISTANCE : "+" "+ pop.getFittest().getDistance();
        out = "\n\n"+out+"\n"+"SOLUTION : \n\n"+pop.getFittest();
        output.setText(out);
     
        TourManager.clear(); //removing cities from the arraylist<cities> to make it ready for next tour ..
    
        
    }
    
}
