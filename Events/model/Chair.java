package model;

//class declaration
public class Chair 
{
    //declaration of attributes
    private char row;
    private int pillar;
    private boolean status;
    private boolean able;
    private String description;
    
    /** Default Constructor for Chair Class.
   * @param row
   * @param pillar
	  */
    
    public Chair(char row, int pillar) {
        this.row = row;
        this.pillar = pillar;
        this.status=true;
        this.able=true;
    }
    
    //getters and setters
    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getPillar() {
        return pillar;
    }

    public void setPillar(int pillar) {
        this.pillar = pillar;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }
    
    public String getDescription() {
        return description;}

    public void setDescription(String description) {
        this.description = description;
    }
  
}