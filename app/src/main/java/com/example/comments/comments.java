package com.example.comments;

public class comments {
    public String name,date,comment;
    public comments(){

    }
    public comments(String name,String date,String comment){
        this.name=name;
        this.date=date;
        this.comment=comment;
    }
    public String getName(){
        return name;
    }
    public String getComment(){
        return comment;
    }
    public String getDate(){
        return date;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setComment(String comment){
        this.comment=comment;
    }
    public void setDate(String date){
        this.date=date;
    }

}
