//update the user pref class
package com.example.assignment3.model;

public class UserPref {

    private int row;
    private int colum;
    private int submarines;

    private static UserPref instance;
//defaule
    private UserPref(int row, int colum,int submarine) {
        this.row = row;
        this.colum = colum;
        this.submarines = submarine;
    }
    public static UserPref getInstance(){
        if(instance == null){
            instance = new UserPref(4,6,6);

        }
        return instance;
    }

    public void reset(int row, int colum){
        this.row = row;
        this.colum = colum;
    }
//getter setter reset
    public void resetSubmarine(int submarine){
        this.submarines = submarine;
    }
    //getter setter reset
    public int getRow() {
        return row;
    }
    //getter setter reset
    public int getColum() {
        return colum;
    }
    //getter setter reset
    public int getSubmarine() {
        return submarines;
    }
}
