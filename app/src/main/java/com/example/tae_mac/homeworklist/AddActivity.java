package com.example.tae_mac.homeworklist;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void addClicked(View v){
        EditText inTitle = (EditText)findViewById(R.id.inTitle);
        EditText inCourse = (EditText)findViewById(R.id.inCourse);
        EditText inDes = (EditText)findViewById(R.id.inDes);

        String sInTitle = inTitle.getText().toString();
        String sInCourse = inCourse.getText().toString();
        String sInDes = inDes.getText().toString();

        if (sInTitle.trim().length() == 0 || sInCourse.trim().length() == 0){
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Please fill in the blank. *",Toast.LENGTH_SHORT);
            t.show();
        }
        else {
            Intent result = new Intent();
            result.putExtra("inTitle", sInTitle);
            result.putExtra("inCourse", sInCourse);
            result.putExtra("inDes", sInDes);
            result.putExtra("inDes", sInDes);
            this.setResult(RESULT_OK, result);
            this.finish();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
