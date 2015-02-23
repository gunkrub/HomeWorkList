package com.example.tae_mac.homeworklist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    DBHelper helper;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       showListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xmla
        switch (item.getItemId()) {
            case R.id.action_edit:
                editActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addActivity(View v) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(i, 88);
    }

    private void editActivity(){
        Intent i = new Intent(MainActivity.this, EditActivity.class);
       startActivityForResult(i, 99);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                String inTitle = data.getStringExtra("inTitle");
                String inCourse = data.getStringExtra("inCourse");
                String inDes = data.getStringExtra("inDes");
                //String dueDate = data.getStringExtra("dueDate");
                //String priority = data.getStringExtra("priority");

                DBHelper myDB = new DBHelper(this);
                myDB.InsertData(inTitle, inCourse, inDes);
                showListView();

                Toast.makeText(MainActivity.this, "Add Successfull. ",
                        Toast.LENGTH_SHORT).show();
            }
        }
        Log.d("homeWork", "onActivityResult");




        //if(requestCode == 99){
            //if(resultCode == RESULT_OK){
              //  String courseTitle = data.getStringExtra("courseTitle");
               // String topic = data.getStringExtra("topic");
               // String shortDes = data.getStringExtra("shortDes");
               // String fullDes = data.getStringExtra("fullDes");
               // String dueDate = data.getStringExtra("dueDate");
               // String priority = data.getStringExtra("priority");
            //}
       // }

    }

    public void showListView(){
        helper = new DBHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM homeWork;",
                null);

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"inTitle", "inDes"},
                new int[] {android.R.id.text1, android.R.id.text2},
                0);

        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        {
            Log.d("todo", id + " is clicked");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}

