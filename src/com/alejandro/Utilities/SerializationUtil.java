package com.alejandro.Utilities;

/**
 * Created by Alejandro on 12/13/2015.
 */
import com.alejandro.model.Workout;
import com.alejandro.model.WorkoutLog;

import java.io.*;
import java.util.TreeMap;

/*
This is a custom Serialization utility class that will be solely used for storing the objects state(the workoutlog) into a file.
*/
public class SerializationUtil{
    public static TreeMap<String, Workout> deserialize(File filename) throws IOException, ClassNotFoundException{
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        TreeMap< String, Workout> treeMap = null;
        try{
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            treeMap = (TreeMap<String, Workout>) ois.readObject();
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return treeMap;
    }
    public static void serialize(TreeMap<String,Workout> treeMap, File filename) throws IOException{
        FileOutputStream fos = null;

        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(treeMap);
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }}

