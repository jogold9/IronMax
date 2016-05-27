package com.joshbgold.ironmax;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Exercises {

    public ArrayList<String> exercisesArrayList = new ArrayList<>();  //stores all the lifts

    private ArrayList<Integer> personalBestsArrayList = new ArrayList<>();  //stores personal bests in pounds

    public ArrayList<String> getExercisesArray() {   //returns the whole exercises arraylist
        return exercisesArrayList;
    }

    public ArrayList<Integer> getPersonalBestArray() {  //returns the whole personal bests arraylist
        return personalBestsArrayList;
    }

    public String getExercise(int position) {  //returns individual exercise from array
        return exercisesArrayList.get(position);
    }

    public void addExercise(String exercise) {
        exercisesArrayList.add(exercise);
    }

    public void removeExercise(int position) {
        exercisesArrayList.remove(position);
    }

    public void editExercise(int position, String exercise) {
        exercisesArrayList.set(position, exercise);
    }

    public int getExercisesArrayLength() {
        return exercisesArrayList.size();
    }


    public Integer getPersonalBest(int position) {  //returns individual personal best from array
        return personalBestsArrayList.get(position);
    }

    public void addPersonalBest(int personalBest) {
        personalBestsArrayList.add(personalBest);
    }

    public void removePersonalBest(int position) {
        personalBestsArrayList.remove(position);
    }

    public void editPersonalBest(int position, int personalBest) {
        personalBestsArrayList.set(position, personalBest);
    }

    public Exercises() { //constructor

        exercisesArrayList.add("Use settings menu to add lift");
        personalBestsArrayList.add(100);

        exercisesArrayList.add("front squat");
        personalBestsArrayList.add(135);
    }

}
