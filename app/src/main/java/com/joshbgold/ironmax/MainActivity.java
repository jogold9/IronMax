package com.joshbgold.ironmax;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ROW = 1;  //used for case statement statement to select menu item
    private RecyclerView recyclerView;
    public Exercises exercises = new Exercises();
    public ExerciseRow adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assert getSupportActionBar() != null;
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.barbell);
        actionBar.setTitle("  " + "Iron Max");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ExerciseRow(this, exercises);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD_ROW, 0, "Add New Row");
        menu.getItem(0).setIcon(R.drawable.plus);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int length;

        switch (item.getItemId()) {
            case 1:
                Toast msg = Toast.makeText(MainActivity.this, "Test code for adding an exercise", Toast.LENGTH_LONG);
                msg.show();

                exercises.addExercise("Some exercise");
                //exercises.addPersonalBest(500);

               //call notify data set changed method for the adapter
                adapter.notifyDataSetChanged();

                return super.onOptionsItemSelected(item);


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

