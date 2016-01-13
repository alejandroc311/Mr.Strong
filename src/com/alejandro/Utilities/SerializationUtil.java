package com.alejandro.Utilities;

/**
 * Created by Alejandro on 12/13/2015.
 */
import com.alejandro.model.WorkoutLog;

import java.io.*;

/*
This is a custom Serialization utility class that will be solely used for storing the objects state(the workoutlog) into a file.
*/
public class SerializationUtil{
    public static WorkoutLog deserialize(File filename) throws IOException, ClassNotFoundException{
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        WorkoutLog workoutLog = null;
        try{
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            workoutLog = (WorkoutLog)ois.readObject();
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return workoutLog;
    }
    public static void serialize(WorkoutLog workoutlog, File filename) throws IOException{
        FileOutputStream fos = null;

        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(workoutlog);
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }}

