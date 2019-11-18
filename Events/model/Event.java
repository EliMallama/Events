package model;

//class declaration
public class Event
{
   //declaration of attributes
    private String name;
    private int day;
    private int month;
    private int start;
    private int finish;
    private String responsableTeacher;
    private String school;
    private boolean started;
    private int people=0;

    /** Default Constructor for Event Class.
   * @param name
   * @param day
   * @param month
   * @param start
   * @param finish
   * @param responsableTeacher
   * @param school
   * @param people
	 */
    public Event(String name, int day, int month, int start, int finish,
            String responsableTeacher, String school, int people) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.start = start;
        this.finish = finish;
        this.responsableTeacher = responsableTeacher;
        this.school = school;
        this.started = false;
        this.people=people;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int Start) {
        this.start = Start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getResponsableTeacher() {
        return responsableTeacher;
    }

    public void setResponsableTeacher(String responsableTeacher) {
        this.responsableTeacher = responsableTeacher;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    
     public int getPeople() {
        return people;
    }
     
      public void setPeople(int people) {
        this.people = people;
    }
      
      
      public String info()
      {
      return "["+this.name+","+this.started+"]";
      }
  }