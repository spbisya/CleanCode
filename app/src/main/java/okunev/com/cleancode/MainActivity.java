package okunev.com.cleancode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import okunev.com.cleancode.helpers.DBHelper;
import okunev.com.cleancode.models.CustomModel;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView modelList = (RecyclerView) findViewById(R.id.modelList);
        mydb = new DBHelper(this);
        if (!getSharedPreferences("clean", 0).getBoolean("modelsCreated", false)) {
            for (int i = 0; i < 50; i++) {
                mydb.insertModel(new CustomModel("Name " + (i + 1), "Description " + (i + 1), "http://goodworkapps.com"));
            }
            getSharedPreferences("clean", 0).edit().putBoolean("modelsCreated", true).apply();
        }
        modelList.setLayoutManager(new LinearLayoutManager(this));
        modelList.setAdapter(new ModelAdapter(mydb.getAllModels()));
    }
}
