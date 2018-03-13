package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int x = 2;
    String complimenet = "";
    int total = x * 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView price = (TextView) findViewById(R.id.price_text_view);
        TextView quantity = (TextView) findViewById(R.id.quantity_text_view);
        quantity.setText(" " + x);
        price.setText("Total : " + NumberFormat.getCurrencyInstance().format(x * 5) + complimenet);


    }


    public void submitOrder(View view) {
        complimenet = "Thankyou!";
        TextView price = (TextView) findViewById(R.id.price_text_view);
        CheckBox checkBox = findViewById(R.id.notify_me_checkbox);
        CheckBox checkBox2 = findViewById(R.id.notify_me_checkbox2);
        EditText editText = findViewById(R.id.edit_text);
        String name = editText.getText().toString();
        if(name.length()==0){
            name="Dear Customer";
        }
        price.setText("Name : " + name + "\n"
                + "Add whispered Cream ? " + checkBox.isChecked() + "\n"
                + "Add Chocolate ? " + checkBox2.isChecked() + "\n"
                + "Quantity - " + x + "\n"
                + "Total : " + NumberFormat.getCurrencyInstance().format(total) + "\n" +
                complimenet
        );

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT,("Name : " + name + "\n"
                + "Add whispered Cream ? " + checkBox.isChecked() + "\n"
                + "Add Chocolate ? " + checkBox2.isChecked() + "\n"
                + "Quantity - " + x + "\n"
                + "Total : " + NumberFormat.getCurrencyInstance().format(total) + "\n" +
                complimenet) );
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Your Coffee");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public void increment(View view) {
        complimenet = "";
        TextView price = (TextView) findViewById(R.id.price_text_view);
        TextView textView = (TextView) findViewById(R.id.quantity_text_view);
        CheckBox checkBox = findViewById(R.id.notify_me_checkbox);
        CheckBox checkBox2 = findViewById(R.id.notify_me_checkbox2);
        x++;
        textView.setText("" + x);
        if (checkBox.isChecked() && (!checkBox2.isChecked())) {
            total = x * 5 + x * 1;
        } else if ((!checkBox.isChecked()) && (checkBox2.isChecked())) {
            total = x * 5 + x * 2;
        } else if ((checkBox.isChecked()) && (checkBox2.isChecked())) {
            total = x * 5 + x * 2 + x * 1;
        } else total = x * 5;

        price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));


    }

    public void decrement(View view) {
        complimenet = "";
        TextView price = (TextView) findViewById(R.id.price_text_view);
        TextView textView = (TextView) findViewById(R.id.quantity_text_view);
        CheckBox checkBox = findViewById(R.id.notify_me_checkbox);
        CheckBox checkBox2 = findViewById(R.id.notify_me_checkbox2);
        if (x <= 1) {
            Toast.makeText(this, "Order Couldn't be in 0.", Toast.LENGTH_LONG).show();
            return;
        }
        x--;
        if (checkBox.isChecked() && (!checkBox2.isChecked())) {
            total = x * 5 + x * 1;
        } else if ((!checkBox.isChecked()) && (checkBox2.isChecked())) {
            total = x * 5 + x * 2;
        } else if ((checkBox.isChecked()) && (checkBox2.isChecked())) {
            total = x * 5 + x * 2 + x * 1;
        } else total = x * 5;
        textView.setText(" " + x);
        price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));
    }

    public void chocolate(View view) {
        CheckBox checkBox = findViewById(R.id.notify_me_checkbox2);
        TextView price = (TextView) findViewById(R.id.price_text_view);
        if (checkBox.isChecked()) {
            total = total + (2 * x);
            price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));
        } else {
            total = total - (2 * x);
            price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));

        }

    }


    public void whipped(View view) {
        CheckBox checkBox = findViewById(R.id.notify_me_checkbox);
        TextView price = (TextView) findViewById(R.id.price_text_view);

        if (checkBox.isChecked()) {
            total = total + (1 * x);
            price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));
        } else {
            total = total - 1 * x;
            price.setText("Total : " + NumberFormat.getCurrencyInstance().format(total));

        }

    }
}
