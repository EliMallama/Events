package model;

import java.util.*;
import java.lang.Math; 

//class declaration
public class Hall {
    
    //declaration of attributes
    private String name;
    private String location;
    private boolean status;
    private int damagedChairs=0;
    private Chair chairs[][]=new Chair [26][];
    private int[] cXR=new int[26];
    private int totalCapacity=0;
    private ArrayList<String[][]> yearCalendar = new ArrayList<String[][]>();

    public ArrayList<String[][]> getYearCalendar() {
        return yearCalendar;
    }
     /** Default Constructor for Chair Class.
   * @param name
   * @param location
	  */
    public Hall(String name, String location) {
        this.name = name;
        this.location = location;
        this.status = true;
        for(int i=0;i<12;i++)
        {
           String monthCalendar [][]=new String [14][30];
           
           for (int x = 0; x < monthCalendar.length;x++) 
            {
		    	for (int y = 0; y < monthCalendar[x].length; y++) 
			    {
			        monthCalendar[x][y]="";
		    	}
			}
            yearCalendar.add(monthCalendar);
        }
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Chair[][] getChairs() {
        return chairs;
    }

    public void setChairs(Chair[][] chairs) {
        this.chairs = chairs;
    }
    
    public int getDamagedChairs(){
        return damagedChairs;
    }

    /** this method allows to create the required chairs to create halls
     * @param cXR this is the number of pilars for rows
     * @return void
     */
      public void createChairs(int cXR []) 
      {
      
      int i=0;
      int n=0;
      int cont3=0;
          for(char cont='a'; cont<= 'z'; cont++)
          {
          
              int numChair=cXR[i];
              chairs[n]=new Chair[numChair]; 
              
                for(int j=0; j<numChair; j++)
                {
                
                    chairs[i][j]=new Chair (cont,j);
                    
                    cont3++;
                }
                i++;
                n++;
          }
          this.cXR=cXR;
          this.totalCapacity=cont3;
      }
    
      /** this method is to report the  damaged chairs
       * the method allows to change chair's status in order to
       * notify if the chair is able or not
       * @param row
       * @param col
       * @param description
       * @return succes is the boolean variable that indicates the 
       * status of the chair
       */
    public boolean reportDamageChair(char row, int col, String description)
    {
        boolean success=true;
        
        int c=0;
        for(char i='a'; i<='z'; i++)
        {
            if(row != i)
            {
            c++;
            break;
            }
            else
            {
                break;
            }
        }
        
        if(c<26 && c>=0 )
        {
            
            if(col<=cXR[c])
            {
                chairs[c][col].setStatus(false);
                chairs[c][col].setDescription(description);
            }
            else
            {success=false;}
        
        }else{success=false;}
        return success;
    } 
    
    /** the method allows to calculate the percentage of damaged chairs
     * there will be trhee variables to save positions while traveling the matrix
     * and one of those variables will be to save the result
     * @return double percentage  
     */
    public double faultyPercentage(){
        
    
       double percentage=0;
          int cont=0;
         int cont2=0;
         
         
         for (int x = 0; x < 26;x++) 
         {
			for (int y = 0; y < chairs[x].length; y++) 
			{
			    cont++;
			 System.out.print("["+chairs[x][y].getStatus()+"]");
	         if(chairs[x][y].getStatus()==false)
	         cont2++;
			}
			System.out.println("\n");
		}
		percentage=(cont2*100)/cont;
		
		damagedChairs=cont2;
         
         return percentage;
    }
    
    /** this method is to calculate the actual capacity of the hall
     * excluding damaged chairs
     * @return an int which is the diference between the total capacity
     * and damaged chairs
     */
    public int realCapacity(){
        
        return totalCapacity-damagedChairs;
        
    }
    
    /** yearCalendar is an ArrayList with a matrix in each position which has a
     * diary function. But is just a normal set
     * @param nameEvents 
     */
    public void setYearCalendar(ArrayList<String[][]> nameEvents)
    {
        yearCalendar=nameEvents;
    }
    
    /** this method is to fill the hall ramdomly 
     * @return  boolean asigned let the user know if the chair is filled or fee
     */
    public boolean fillHall()
    {
        boolean asigned=false;
        int fila=(int)(Math.random()*25+0);
        int col=(int)(Math.random()*(cXR[fila]-1)+0);
        if(chairs[fila][col].getStatus()==true && chairs[fila][col].getAble()==true)
        {
            chairs[fila][col].setAble(false);
            asigned=true;
        }
        return asigned;
    }
    
    /** this method allows to change chairs status when the event is over
     * @return void
     */
    public void flushChairs()
    {
        for (int x = 0; x < chairs.length;x++) 
            {
		    	for (int y = 0; y < chairs[x].length; y++) 
			    {
			        if(chairs[x][y].getAble()==false && chairs[x][y].getStatus()==true)
			           {
			            chairs[x][y].setAble(true);
			           }
			    }
            }
			        
    }
    
     public String info()
      {
      return "["+this.name+", "+this.status+"]";
      }
}