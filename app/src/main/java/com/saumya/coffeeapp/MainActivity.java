package com.saumya.coffeeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String coffeeSelected = "Select";
    int selectedCoffeePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Espresso");
        categories.add("Latte");
        categories.add("Cappuccino");
        categories.add("Americano");
        categories.add("Decaf");
        categories.add("Macchiato");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        Button submitOrderButton = (Button)findViewById(R.id.submitOrderButton);
        submitOrderButton.setOnClickListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Map<String, String> coffeePrice = new HashMap<String, String>();
        coffeePrice.put("Espresso", "4");
        coffeePrice.put("Latte", "3");
        coffeePrice.put("Cappuccino", "6");
        coffeePrice.put("Americano", "5");
        coffeePrice.put("Decaf", "8");
        coffeePrice.put("Macchiato", "4");

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        coffeeSelected = item;
        // Showing selected spinner item
        if (item != "Select") {
            selectedCoffeePrice = Integer.parseInt(coffeePrice.get(item));
            Toast.makeText(parent.getContext(), "The price per cup for " + item + " is $" + coffeePrice.get(item) + ". Please select no of cups and submit order.", Toast.LENGTH_LONG).show();
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    /* This method is called when any of the activity's view components is clicked. */
    @Override
    public void onClick(View view) {
        String message;
        if (view != null) {
            EditText name   = (EditText)findViewById(R.id.Name);
            EditText numberOfCups = (EditText)findViewById(R.id.NumOfCups);
            String personsName = name.getText().toString();

            String numOfCupsOrderedStr = numberOfCups.getText().toString();
            int numOfCupsOrdered = Integer.parseInt(numOfCupsOrderedStr);

            if (coffeeSelected == "Select") {
                message = "Please select a coffee before submitting your order";
            } else {
                message = "Thank you " + personsName + ", for your order of " + numOfCupsOrdered + " cups of coffee. Your total is $" + selectedCoffeePrice*numOfCupsOrdered + " plus applicable taxes.";
            }

            Toast toast;
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        }



    }

}
