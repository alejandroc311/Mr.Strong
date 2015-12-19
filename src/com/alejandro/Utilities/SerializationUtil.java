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
    public static Object deserialize(File filename) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(filename);
        Object obj = new Object();
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        while(fis.available()>0){
            obj = ois.readObject();

        }

        ois.close();
        return obj;

    }
    public static void serialize(Object object, File filename) throws IOException{
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();

    }}

