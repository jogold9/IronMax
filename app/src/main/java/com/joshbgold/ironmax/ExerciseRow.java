package com.joshbgold.ironmax;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExerciseRow extends RecyclerView.Adapter<ExerciseRow.ExerciseViewHolder> {

    String exerciseName = "burpee";
    String exercisePR = "100";  // user's personal record for this exercise in pounds
    Exercises exercises = new Exercises();

    private Context context;

    public ExerciseRow(Context context, Exercises exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        ArrayList<String> exercisesArray = exercises.getExercisesArray();
        holder.bindExercises(exercisesArray.get(position));
    }

    @Override
    public int getItemCount() {
        return exercises.getExercisesArrayLength();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseNameTextView;
        public TextView personalRecordTextView;
        public ImageView edit_Icon;
        public ImageView percentages_Icon;
        public ImageView trash_Icon;
        public ImageView plus_icon;
        public ImageView facebook_icon;
        public ImageView twitter_icon;

        public ExerciseViewHolder(View itemView) {
            super(itemView);

            exerciseNameTextView = (TextView) itemView.findViewById(R.id.list_item_exercise_textview);
            personalRecordTextView = (TextView) itemView.findViewById(R.id.list_item_amount_textview);
            edit_Icon = (ImageView) itemView.findViewById(R.id.list_item_pencil);
            percentages_Icon = (ImageView) itemView.findViewById(R.id.list_item_percent);
            trash_Icon = (ImageView) itemView.findViewById(R.id.list_item_trash);
            plus_icon = (ImageView) itemView.findViewById(R.id.list_item_plus);
            facebook_icon = (ImageView) itemView.findViewById(R.id.list_item_facebook);
            twitter_icon = (ImageView) itemView.findViewById(R.id.list_item_twitter);

            View.OnClickListener plus = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // allow user to add a new exercise and personal best
                    exercises.addExercise("Some exercise");
                    exercises.addPersonalBest(500);
                    notifyDataSetChanged();
                }
            };

            View.OnClickListener edit = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();  //use getAdapterPosition() if getLayoutPosition causes a problem

                    if (position == 0) {  //prevent user from deleting the first row.
                        Toast.makeText(context, "Sorry, example row cannot be edited.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        editRow(position);  //edit the row at the current position
                        notifyDataSetChanged();
                    }
                }
            };

            View.OnClickListener percentages = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get exercise name
                    exerciseName = exerciseNameTextView.getText().toString();
                    exercisePR = personalRecordTextView.getText().toString();

                    //show percentages layout
                    startPercentagesActivity(exerciseName, exercisePR);
                }
            };

            View.OnClickListener trash = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();  //use getAdapterPosition() if getLayoutPosition causes a problem

                    if (position == 0) {  //prevent user from deleting the first row.
                        Toast.makeText(context, "Sorry, example row cannot be deleted.", Toast.LENGTH_LONG).show();
                    } else {
                        exercises.removeExercise(position);
                        exercises.removePersonalBest(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, exercises.getExercisesArrayLength());
                    }
                }
            };

                plus_icon.setOnClickListener(plus);
                edit_Icon.setOnClickListener(edit);
                percentages_Icon.setOnClickListener(percentages);
                trash_Icon.setOnClickListener(trash);
            }

        public void bindExercises(String exercises) {
            Exercises exercisesObject = new Exercises();
            exerciseNameTextView.setText(exercisesObject.getExercise(getAdapterPosition()));
            personalRecordTextView.setText((exercisesObject.getPersonalBest(getAdapterPosition())).toString() + " pounds");
        }

    }

    private void startPercentagesActivity(String some_exercise, String personal_record) {
        Intent intent = new Intent(context, PercentagesActivity.class);
        intent.putExtra("exerciseName", some_exercise);
        intent.putExtra("personalRecord", personal_record);
        context.startActivity(intent);
    }

    protected void editRow(final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final View textEntryView = layoutInflater.inflate(R.layout.text_entry, null);
        final EditText liftName = (EditText) textEntryView.findViewById(R.id.liftNameEditText);
        final EditText PersonalBestInPounds = (EditText) textEntryView.findViewById(R.id.personalBestEditText);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setIcon(R.mipmap.barbell)
                .setTitle("Please make your changes:")
                .setView(textEntryView)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {


                                //retrieve the user's input
                                String lift = liftName.getText().toString();
                                int personalBest = Integer.parseInt(PersonalBestInPounds.getText().toString());

                                //save the user's input to the appropriate arrays
                                exercises.editExercise(position, lift);
                                exercises.editPersonalBest(position, personalBest);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });
        alert.show();
    }
}
