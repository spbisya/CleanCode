package okunev.com.cleancode;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import okunev.com.cleancode.adapters.ModelAdapter;
import okunev.com.cleancode.helpers.DBHelper;
import okunev.com.cleancode.models.CustomModel;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb;
    Boolean isItemsShowed = false;
    RecyclerView modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelList = (RecyclerView) findViewById(R.id.modelList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                isItemsShowed = true;
                if (!newText.equals(""))
                    search(newText);
                else if (newText.equals("")) {
                    modelList.setAdapter(new ModelAdapter(mydb.getAllModels()));
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                isItemsShowed = true;
                search(query);
                // this is your adapter that will be filtered
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
        MenuItem menuItem = menu.findItem(R.id.search);
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                isItemsShowed = true;
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                isItemsShowed = false;
                modelList.setAdapter(new ModelAdapter(mydb.getAllModels()));
                return true;
            }
        });
        return true;
    }

    private void search(String query) {
        mydb = new DBHelper(this);
        ArrayList<CustomModel> elements = mydb.getModelsByQuery(query);
        Collections.sort(elements, new Comparator<CustomModel>() {
            @Override
            public int compare(CustomModel element, CustomModel t1) {
                return Integer.parseInt(element.getName().replace("Name ", "")) -
                        Integer.parseInt(t1.getName().replace("Name ", ""));
            }
        });
        ModelAdapter modelsAdapter = new ModelAdapter(elements);
        mydb.close();
        modelList.setAdapter(modelsAdapter);
    }
}
