package com.alejandro.model;

import java.io.Serializable;

/**
 * Created by Alejandro on 12/13/2015.
 */
public class Exercise implements Serializable{
    protected String mExerciseIdentity;
    protected double mNumOfReps;
    protected double mNumOfSets;
    protected double mTotalLoad;
    protected double mWeightUsed;
    public static final String SQUAT_KEY = "Squat";
    public static final String DEADLIFT_KEY = "Deadlift";
    public static final String PRESS_KEY = "Press";
    public static final String BENCH_KEY = "Bench";
    public Exercise(){}
    public double getNumOfReps(){
        return mNumOfReps;
    }
    public void setNumOfReps(double numOfReps){
        mNumOfReps = numOfReps;
    }
    public double getNumOfSets(){
        return mNumOfSets;
    }
    public void setNumOfSets(double numOfSets){
        mNumOfSets = numOfSets;
    }
    public double getWeightUsed(){
        return mWeightUsed;
    }
    public void setWeightUsed(double weightUsed){
        mWeightUsed = weightUsed;
    }
    public double getTotalLoad(){
        return mTotalLoad;
    }
    public void setTotalLoad(){
        double totalLoad;
        totalLoad = getWeightUsed() * getNumOfSets() * getNumOfReps();
        mTotalLoad = totalLoad;
    }
    public String getExerciseIdentity(){
        return mExerciseIdentity;
    }
    public void setExerciseIdentity(String exerciseIdentity){
        mExerciseIdentity = exerciseIdentity;
    }




}
