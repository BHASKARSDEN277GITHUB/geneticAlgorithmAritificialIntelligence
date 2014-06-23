
import java.io.InputStream;
import java.util.Scanner;
import java.lang.Integer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhaskar
 */
public class City {
    String cName = "" ;
    int id ;
    // Constructs a randomly placed city
    public City(){
        
    }
    
    // Constructs a city at chosen x, y location
    public City(int x, String name){
        this.id=x;
        this.cName=name;
    }
    
    
    // Gets the distance to given city
    public double distanceTo(City city){
       
        int a = this.id ;
        int b = city.id ;
        int distance = 1;
        String check = a+"-"+b;
        InputStream in = getClass().getResourceAsStream("resources/hpmap.txt");
        Scanner sc = new Scanner(in);
        String str ="";
        String[] splitA = null ;
       // System.out.println(splitA.length+"");
        int distance1 = 0 ;
        if(sc!=null)
        {
       while(sc.hasNext())
       {    
           str=sc.nextLine().toString();
           if(str.startsWith(check))
           {
               splitA=str.split(" ");
               //System.out.print(splitA[1]);
                distance1 = Integer.parseInt(splitA[1]);
               break ;
           }
       }
      // String str1;
       //str1 = splitA[1];
//       System.out.print(splitA[1]);
        //int distance1 = Integer.parseInt(str1);
        //System.out.println(distance+"");
       //return (double)((int)Integer.parseInt(splitA[1].toString()))  ;
       return distance1;
      
        }
       return distance ;
    }
    
    @Override
    public String toString(){
        return this.cName;
    }
}
