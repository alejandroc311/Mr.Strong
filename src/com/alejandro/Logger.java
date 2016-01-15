package com.alejandro;


import com.alejandro.Utilities.SerializationUtil;
import com.alejandro.model.Exercise;
import com.alejandro.model.Workout;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Logger {
    public static final String COMPLETE_KEY = "COMPLETE";
    public static final String INCOMPLETE_KEY = "INCOMPLETE";
    public static final String ADD_KEY = "ADD";
    public static final String CHECK_KEY = "CHECK";
    public static final String EXIT_KEY = "EXIT";




    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Workout> workoutArrayList = new ArrayList<Workout>();
        Logger logger = new Logger();
        TreeMap<String, Workout> treemap = new TreeMap<String, Workout>();
        Workout workout = new Workout();
        File file = new File("workout.txt");

        //im going to need to ask if the user wants to add a workout, close the program, or select a workout
        String userInput = checkUserIntention();

        //the switch statement goes through all the possible user inputs

        switch(userInput){
            case Logger.ADD_KEY:
                printInstructions();
                do{
                    logger.promptForExerciseData(workout);

                }while(!checkIfUserIsDone());
                //this will check if the file that holds all the data stored exists.
                  //if the file does exists then the treemap recovers all the information in it so that any other information may be added
                if(file.exists()){
                    treemap = (TreeMap<String,Workout>) SerializationUtil.deserialize(file);
                    treemap.put(workout.getDate(),workout);
                    SerializationUtil.serialize(treemap,file);
                    System.out.println("Workout saved in..." +file.getName());
                }
                //if it doesnt exist then it uses an empty treemap to start the storing of workouts
                  else{
                    treemap.put(workout.getDate(),workout);
                    SerializationUtil.serialize(treemap,file);
                    System.out.println("Workout saved in..." +file.getName());
                }
                break;

            case Logger.CHECK_KEY:
                //TODO

                try {

                    treemap = (TreeMap<String,Workout>) SerializationUtil.deserialize(file);
                    System.out.println("Deserializing from:..." + file.getName());

                } catch(EOFException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch(ClassCastException E){
                    E.printStackTrace();
                }
                //add so that the user views a list of their workouts and can choose which to browse
                //()showOrganizedWorkoutLog
                printWorkoutList(treemap,workoutArrayList);

                int temp = getUserChoiceofWorkout(treemap);

                //()askforUaerInput
                //()show workout
                showWorkoutSelected(temp,workoutArrayList);
                break;


            case Logger.EXIT_KEY:
                System.out.println("\nExiting program...");
                break;

        }
    }

//thid'll iterate through the treemap and show a list of all the workouts
    //I have to move all of the workouts from the treemap to a arraylist
    //since the user picks a number and the get() method of the treemap only takes keys and not indexes


    protected static void printWorkoutList(TreeMap<String,Workout> treeMap, ArrayList<Workout> arrayList){

            int i =0;
            for(Map.Entry<String,Workout> entry: treeMap.entrySet()){
                i++;
                System.out.println(i+"-)"+entry.getKey()+"\n");
                arrayList.add(entry.getValue());
            }



    }

// the user picks the index of the workout he/she wants to view
    //the index comes from the printqworkoutlist() method above

    protected static int getUserChoiceofWorkout(TreeMap<String,Workout> treeMap){
        Scanner scanner = new Scanner(System.in);

        int userinput;
        do{System.out.println("Please enter a number to see a workout");
            try{
            userinput = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e){
                userinput = 0;
            }
            if(userinput<0||userinput>treeMap.size()){
                userinput = 0;
            }
        }
        while(userinput==0);


        return userinput;

    }

// this method takes the user choice and finds the index from the arraylist and shows the entry
    protected static void showWorkoutSelected(int choice, ArrayList<Workout> arrayList){
        //the list shows the index starting at 1 instead of 0, so ineed to subtract a number to get the number correctly
        int temp;
        temp = choice - 1;
        for(Exercise exercise: arrayList.get(temp).getExerciseList()){
            System.out.println(exercise.getExerciseIdentity()+" : "+exercise.getWeightUsed()+" x "
                + exercise.getNumOfReps() + " x " +exercise.getNumOfSets()
            );
        }
    }

    //I'm using this method to explain to the user how to use the program
    protected static void printInstructions(){

        System.out.println("\nWelcome to Mr.Strong!\n");
        System.out.println("This program was developed to help powerlifters keep a log of their lifts.\n");
        System.out.println("Because of this, the program will only recognize the following lifts:\n");
        System.out.println("Squat, Bench, Deadlift, Press.\n");
        System.out.println("The program is case-sensitive, make sure the information is entered as stated above.\n");

    }



//this method asks the user for information about the lifts stores them in a workout object

    //the methods used here are all organized throught the page, its just to keep things cleaner and separate
    protected void promptForExerciseData(Workout workout){
        Exercise exercise = new Exercise();
        askForExerciseIdentity(exercise);
        askForNumsRelLifts(exercise);
        workout.getExerciseList().add(exercise);
    }

    //this will check to see if the user is done inputting the exercises he did, if he finished the program ends.
    protected static boolean checkIfUserIsDone(){
        Scanner scanner = new Scanner(System.in);
        boolean isUserDone = false;
        System.out.println("\nEnter: 'complete'" + ", if you are done. " +
                "If not, enter:'incomplete " + ".\n");
        String answer = scanner.nextLine();
        if(answer.trim().toUpperCase().equals(Logger.COMPLETE_KEY)){
            isUserDone = true;
        } else if(answer.trim().toUpperCase().equals(Logger.INCOMPLETE_KEY)){
            isUserDone = false;
        } else{
            checkIfUserIsDone();
        }
        return isUserDone;
    }

    //check if user wants to add, review, or close


    protected static String checkUserIntention(){
        String answer = "a";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease choose an option:\n" +
                "1-) Add a workout. Enter 'Add'.\n" +
                "2-) Check a workout Enter 'Check'.\n" +
                "3-) Exit the program. Enter 'Exit'\n");
        answer = scanner.nextLine();
        if(answer.trim().toUpperCase().equals(Logger.ADD_KEY) ||
                answer.trim().toUpperCase().equals(Logger.CHECK_KEY)||
                answer.trim().toUpperCase().equals(Logger.EXIT_KEY)){
            return answer.toUpperCase();
        }else{
            System.out.println("Incorrect input.");
            checkUserIntention();
        }


        return answer;


    }



    //all of this part is asking for the exercise data



        //this is the part that asks for exercise id


    protected void askForExerciseIdentity(Exercise exercise){
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("\nEnter a lift:\n");
            String exerciseIdentity = scanner.nextLine();
            if(exerciseIdentity.equals(exercise.SQUAT_KEY)){
                exercise.setExerciseIdentity(exercise.SQUAT_KEY);
            }else if(exerciseIdentity.equals(exercise.PRESS_KEY)){
                exercise.setExerciseIdentity(exercise.PRESS_KEY);
            }else if(exerciseIdentity.equals(exercise.BENCH_KEY)){
                exercise.setExerciseIdentity(exercise.BENCH_KEY);
            }else if(exerciseIdentity.equals(exercise.DEADLIFT_KEY)){
                exercise.setExerciseIdentity(exercise.DEADLIFT_KEY);
            }else {
                exercise.setExerciseIdentity(null);
                System.out.println("Please enter a valid exercise.");
            }}while(exercise.getExerciseIdentity() == null);
    }




    //this is the part that asks for numbers



    protected void askForNumsRelLifts(Exercise exercise){
        exercise.setWeightUsed(askForWeightUsed());
        exercise.setNumOfReps(askForNumOfReps());
        exercise.setNumOfSets(askForNumOfSets());
    }

    protected double askForWeightUsed(){
        Scanner scanner = new Scanner(System.in);
        double weightUsed;
        do{
            try{
                System.out.println("\nEnter weight used:\n");

                weightUsed = Double.parseDouble(scanner.nextLine());

            }catch(NumberFormatException e){

                System.out.println("\nPlease enter a valid number\n");
                weightUsed = 0;

            }

        } while(weightUsed == 0);
        return weightUsed;
    }

    protected double askForNumOfSets(){
        Scanner scanner = new Scanner(System.in);
        double numOfSets;
        do{
            try{
                System.out.println("\nEnter sets done:\n");
                numOfSets = Double.parseDouble(scanner.nextLine());

            }catch(NumberFormatException e){
                System.out.println("\nPlease enter a valid number\n");
                numOfSets = 0;
            }

        }while(numOfSets == 0);
        return numOfSets;
    }

    protected double askForNumOfReps(){
        Scanner scanner = new Scanner(System.in);
        double reps;
        do{
            try{
                System.out.println("\nEnter reps done:\n");
                reps = Double.parseDouble(scanner.nextLine());
            } catch(NumberFormatException e){
                System.out.println("\nPlease enter a valid number\n");
                reps = 0;
            }

        }while(reps == 0);
        return reps;
    }








}
/*
*
*
*
*
*
*/