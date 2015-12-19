package com.alejandro.model;

import com.alejandro.model.Exercise;
import com.alejandro.model.Workout;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

/*
This class is the actual log book that will be stored. It is a treemap, so it will be organized by key values.
The key will take a String value that will be the formatted date from the workout date member variable.
*/

public class WorkoutLog implements Serializable{


    public TreeMap < String , Workout > mWorkoutLog;

    // thia is the actual Workoutlog

    public WorkoutLog(){
        mWorkoutLog = new TreeMap<>();
    }
    public TreeMap < String, Workout> getWorkoutLog(){
        return mWorkoutLog;
    }


}