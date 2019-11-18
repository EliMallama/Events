package model;

import java.util.ArrayList;

//class declaration
public class Icesi 
{
   //declaration of attributes
   private ArrayList <Event> events;
   private ArrayList <Hall> halls;
   
    /** Default Constructor for Chair Class.
	  */
   public Icesi()
   {
   events= new ArrayList <Event>();
   halls=new ArrayList <Hall>();
   }
   
   //getters and setters
   public ArrayList <Event> getEvents()
   {
   return events;
   }
   public ArrayList <Hall> getHalls()
   {
   return halls;
   }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }
   
    /** this method is to verify if the hall exist yet
     * if the hall doesn't exist, then the user can add it
     * @param hall1
     * @return boolean exist to say if the hall exists or not
     */
   public boolean verifyHall(String hall1){
       
       boolean exist=false;
       
       for(Hall hall:halls){
       
       if(hall1.equals(hall.getName())){
           
          exist=true; 
       }
       }

       return exist;
   }
   
   /**throughout the program the name will be asked several times
    * so the method is useful
    * @param name
    * @return a Hall object
    */
   public Hall hallGet(String name)
   {
    Hall halle=null;
    for(Hall hall:halls)
    {
        if(hall.getName().equals(name)==true)
        {
            halle=hall;
        }
    }
    return halle;
   }
   
   /** this method allows to update the Hall's ArrayList 
    * @param hall 
    * @return void
    */
   public void updateHall(Hall hall)
   {
    int cont=0;
    for(Hall halle:halls)
        {
            if(halle.getName().equals(hall.getName())==true)
            {
                halls.set(cont,hall);
            }
            cont++;
        }
   }
   
   
   
   /** same as verifyHall, the idea is not to repeat events, so this method
    * will compare important variables which define if the event has the same
    * features
    * @param name
    * @param month
    * @param day
    * @param start
    * @param finish
    * @return boolean a to say if the event is repeated
    */
   public boolean verifyEvent(String name, int month, int day, int start, int finish){
       
       boolean a=false;
       
       for (Event event:events){
           
           if(name.equals(event.getName()))
           {
               if(month==event.getMonth())
               {
                if(day==event.getDay())
                {
                    if(start==event.getStart() && finish==event.getFinish())
                    {

                       a=true;

                    }
                }
               }
           }
       } 
       return a;
   }
   
     

    public Hall addHall(Hall hall, Event event)
    {
               
               int day=event.getDay()-1;
               int month=event.getMonth()-1;
               int start=event.getStart()-7;
               int finish=event.getFinish()-7;
               int range=finish-start;
               ArrayList<String[][]> nameEvents=hall.getYearCalendar();
               String monthCalendar[][]=nameEvents.get(month);
               
               if(range==1)
               range=2;
               
               int cont=start;
                        for(int i=0;i<range;i++)
                        {
                            monthCalendar[cont][day]=event.getName();
                            cont++;
                            
                        }
                nameEvents.set(month,monthCalendar);
                hall.setYearCalendar(nameEvents);
                return hall;      
                
    }    
     
    /** this method allows to verify if the hall is available then te user can
     * add an event
     * @param hall
     * @param event
     * @return  boolean available
     */
    public boolean verifyAvailability(Hall hall, Event event)
    {
               boolean available=false;
               int day=event.getDay()-1;
               int month=event.getMonth()-1;
               int start=event.getStart()-7;
               int finish=event.getFinish()-7;
               int range=finish-start;
               ArrayList<String[][]> nameEvents=hall.getYearCalendar();
               String monthCalendar[][]=nameEvents.get(month);
               
               if(range==1)
               range=2;
               
                int cont=start;
                for(int i=0;i<range;i++)
                {
                    if (monthCalendar[cont][day].equals(""))
                    {
                    cont++;
                    }
                }
                
                if(cont==finish)
                {    
                available=true;
                }
                
                return available;
                
    }    
    
    /** after all required verifications the user will be able to create 
     * (complete the process to create) an event.
     * to create an event, it is necessary to have Hall's total capacity and
     * the people who will attend if one hall or more is needed
     * @param event
     * @return boolean added will define if the event was added to the even's 
     * ArrayList
     */
   public boolean createEvent(Event event)
   {


       int totalCapacityHalls=0;
       int people=event.getPeople();
       boolean added=false;
       
       for(Hall hall1:halls)
       {
           if(verifyAvailability(hall1, event)==true)
           {
           totalCapacityHalls+=hall1.realCapacity();
           }
       }
       
        System.out.println("capacidad total="+totalCapacityHalls);
       
       if(people <= totalCapacityHalls)
       {
           for(Hall hall:halls)
           {
               if(verifyAvailability(hall,event)==true)
               {
                   if(people>0)
                   {
                       int capacity=hall.realCapacity();
                       Hall h=addHall(hall,event);
                       hall.setYearCalendar(h.getYearCalendar());
                       people=people-capacity;
                       added=true;
                   }
               }
               
            }
            events.add(event);
        }
        
        return added;
    }
       
   /**this method is to change the status of the hall when the 
    * event is in process, then the hall cannot be used until the event is over
    * @param name it will be used to know wish event start
    */
   public void startEvent(String name)
   {
       Event event1=null;
       
       for(Event event:events){
           if(name.equals(event.getName())){
                event.setStarted(true);
                event1=event;
           }
       }
       if(event1!=null)
       {
          for(Hall hall:halls)
          {
              ArrayList<String[][]> yearEvents=hall.getYearCalendar();
              String monthCalendar[][]=yearEvents.get(event1.getMonth()-1);
              String eventName=monthCalendar[event1.getStart()][event1.getDay()-1];
              if(name.equals(eventName))
              {
                  hall.setStatus(false);
                  for(int i=0;i<event1.getPeople();)
                  {
                      if(hall.fillHall()==true)
                        i++;
                  }
              }
              
          }
      }
   }    
   
   /** this method indicates when the event is over (actually is to manually end
    * the event) 
    * the method also allows to change chair's and hall's status, everything 
    * will be able to use again
    * @param name 
    */
   public void finishEvent(String name)
   {
       Event event1=null;
       
       for(Event event:events){
           if(name.equals(event.getName())){
                event.setStarted(false);
                event1=event;
           }
       }
       if(event1!=null)
       {
            for(Hall hall:halls)
          {
              ArrayList<String[][]> yearEvents=hall.getYearCalendar();
              String monthCalendar[][]=yearEvents.get(event1.getMonth()-1);
              String eventName=monthCalendar[event1.getStart()][event1.getDay()-1];
              if(name.equals(eventName))
              {
                  hall.setStatus(true);
                  Hall h=removeEvent(hall,event1);
                  hall.flushChairs();
                  hall.setYearCalendar(h.getYearCalendar());
              }
              
          }
       }
   }
   
   /** this method is used in finishEvent to remove the event's name from the 
    * calendar which is the matrix inside yearCalendar used to write down an 
    * event in the hours it will occupy
    * @param hall
    * @param event
    * @return Hall an able hall
    */
    public Hall removeEvent(Hall hall, Event event)
    {
               
               int day=event.getDay()-1;
               int month=event.getMonth()-1;
               int start=event.getStart()-7;
               int finish=event.getFinish()-7;
               int range=finish-start;
               ArrayList<String[][]> nameEvents=hall.getYearCalendar();
               String monthCalendar[][]=nameEvents.get(month);
               
               if(range==1)
               range=2;
               
               int cont=start;
                        for(int i=0;i<range;i++)
                        {
                            monthCalendar[cont][day]="";
                            cont++;
                            
                        }
                        hall.setYearCalendar(nameEvents);
                        
                  return hall;      
                
    } 
   
   public String infoEvents(){
   String string="";
   for(Event event:events)
   {
       string+=event.info();
   }
   return string;
   }
   
   public String infoHalls(){
   String string="";
   for(Hall hall:halls)
   {
       string+=hall.info();
   }
   return string;
   }
}
