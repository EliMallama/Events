package ui;

import model.*;

import java.util.*;

public class Main {
    
    public static void main(String args[]){
        
        Scanner in=new Scanner(System.in);
        int cXR[]=new int[26];
        Icesi icesi=new Icesi();
        int option=0;
      
        
        do{
            
            System.out.println("Menu");
            System.out.println();
            System.out.println("1. agregar auditorio");
            System.out.println("2. agregar evento");
            System.out.println("3. reportar silla defectuosa");
            System.out.println("4. mostrar porcentaje de sillas defectuosas");
            System.out.println("5. iniciar evento");
            System.out.println("6. finalizar evento");
            System.out.println("7. salir");
        
            option=in.nextInt();
            
            switch(option){
            
                case 1: System.out.println("por favor digite el nombre del auditorio");
                        String name=in.nextLine();
                        System.out.println("por favor digite la ubicacion del auditorio");
                        String location=in.nextLine();
                    
                        if(icesi.verifyHall(name)==false)
                           {
                          
                           Hall hall=new Hall(name,location);
                         
                           int i= 0;
                             for( char cont='a'; cont <= 'z'; cont++,i++){

                               System.out.println("por favor digite un numero de sillas para la fila " + cont);
                              int num=in.nextInt();
                              cXR[i]=num;
                              }
                          
                           System.out.println("auditorios "+icesi.infoHalls());
                           System.out.println("eventos "+icesi.infoEvents());
                           }
                        else{
                        System.out.println("auditorios "+name+" ya existe");
                        }
                break;
                    
                case 2:  System.out.println("por favor digite el nombre del evento");
                    String nameE=in.nextLine();
                    System.out.println("por favor digite el mes es que se realizara el evento");
                    int month=in.nextInt();
                    System.out.println("por favor digite el dia en que se realizara el evento");
                    int day=in.nextInt();
                    System.out.println("por favor digite la hora de inicio del evento");
                    int start=in.nextInt();
                    System.out.println("por favor digite la hora de finalizacion del evento del evento");
                    int finish=in.nextInt();
                    System.out.println("por favor digite el numero de personas que asistiran al evento");
                    int people=in.nextInt();
                    
                    if(!nameE.equals("")){
                    if(month<=12 && month>=1){
                    if(day<=30 && day>=1){
                    if(start>=7 && finish<=20 && finish-start<12){
                    if(people>0){
                    boolean existe=icesi.verifyEvent(nameE, month, day, start, finish);
                    if(existe){
                    System.out.println("The event already exist");
                  }else {
                        System.out.println("por favor digite el nombre del profesor encargado");
                        String teacher=in.nextLine();
                        System.out.println("por favor digite el nombre de la facultad responsable");
                        String school=in.nextLine();
                        boolean created=icesi.createEvent(new Event(nameE,day,month,start,finish,teacher,school,people));
                     if(created==true)
              {
                  System.out.println("Event created");
              }
              else
              {
              System.out.println("Event NOT created");
              }
            }
             }else{System.out.println("Event NOT created");}
             }else{System.out.println("Event NOT created");}
             }else{System.out.println("Event NOT created");}
            }else{System.out.println("Event NOT created");}
            }else{System.out.println("Event NOT created");}

            System.out.println("auditorios "+icesi.infoHalls());
            System.out.println("eventos "+icesi.infoEvents());
        
        
                    
                    break;
                    
                case 3: System.out.println("digite el nombre del auditorio");
                    String nameA=in.nextLine();
                      Hall halle=icesi.hallGet(nameA);
        if(halle!=null)
        {
             System.out.println("digite la fila");
                    char row=in.nextLine().charAt(0);
                     System.out.println("digite el numero de la columna");
                    int pillar=in.nextInt();
                     System.out.println("digite la descripcion del daño");
                    String description=in.nextLine();
                    System.out.println(halle.reportDamageChair(row,pillar,description));
        
        icesi.updateHall(halle);
        }
        break;
        
                case 4:  System.out.println("digite el nombre del auditorio");
                    String nameB=in.nextLine();
       
        Hall halli=icesi.hallGet(nameB);
        System.out.println(halli.faultyPercentage());

                    break;
                    
                case 5: System.out.println("por favor digite el nombre del evento");
                    String nameD=in.nextLine();
                    
                     icesi.startEvent(nameD);
        System.out.println("auditorios start"+icesi.infoHalls());
        System.out.println("eventos start"+icesi.infoEvents());
                    
                    break;
                    
                case 6: System.out.println("por favor digite el nombre del evento");
                    String nameF=in.nextLine();
                    icesi.finishEvent(nameF);
       System.out.println("auditorios Finish"+icesi.infoHalls());
        System.out.println("eventos finish"+icesi.infoEvents());
      
        break;
                    
                case 7: 
                    
                    break;
            default: System.out.println("opcion no valida");
            
            break;
            }
        }while(option!=7);

    
    }
    
}

