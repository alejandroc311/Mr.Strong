package com.alejandro.model;

/**
 * Created by Alejandro on 12/13/2015.
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/*
This class is the entry that will be loaded unto the "logbook." Each entry will have a list of exercises and the date it was created as member variables. Since the class implements an ArrayList as its exercise list adding exercises is easy.

*/

public class Workout implements Serializable{
    protected ArrayList<Exercise> mExerciseList;
    protected Date mDateCreated;
    public Workout(){
        mExerciseList = new ArrayList<>();
        mDateCreated = new Date();
    }
    public ArrayList<Exercise> getExerciseList(){
        return mExerciseList;
    }
    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(mDateCreated);
    }

}
