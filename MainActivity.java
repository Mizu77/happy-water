package com.happywater.agecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    EditText etDOB;
    Button btnCalculate;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDOB = findViewById(R.id.etDOB);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvDisplay = findViewById(R.id.tvDisplay);

        tvDisplay.setVisibility(View.GONE);

        btnCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View V)
            {
                try
                {
                    String strInput = etDOB.getText().toString().trim();
                    String strMonth = strInput.substring(0, 2);
                    String strDay = strInput.substring(3, 5);
                    String strYear = strInput.substring(6, 10);

                    tvDisplay.setText(getAge(Integer.parseInt(strMonth), Integer.parseInt(strDay), Integer.parseInt(strYear)));
                    tvDisplay.setVisibility(View.VISIBLE);
                }

                catch(IndexOutOfBoundsException e)
                {
                    Toast.makeText(MainActivity.this, "Invalid date or empty field", Toast.LENGTH_SHORT).show();
                }

                catch(NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this, "Invalid date or empty field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getAge(int intMonth, int intDay, int intYear)
    {
        Calendar clnDOB = Calendar.getInstance();
        Calendar clnCurrentDate = Calendar.getInstance();

        clnDOB.set(Calendar.YEAR, intYear);
        clnDOB.set(Calendar.MONTH, intMonth);
        clnDOB.set(Calendar.DAY_OF_YEAR, intDay);

        int intAge = 0;// = clnCurrentDate.get(Calendar.YEAR) - clnDOB.get(Calendar.YEAR);

        if(clnCurrentDate.get(Calendar.YEAR) < clnDOB.get(Calendar.YEAR))
        {
            Toast.makeText(MainActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();

            return null;
        }

        if(clnCurrentDate.get(Calendar.YEAR) > clnDOB.get(Calendar.YEAR))
        {
            intAge = clnCurrentDate.get(Calendar.YEAR) - clnDOB.get(Calendar.YEAR);

            if(clnCurrentDate.get(Calendar.MONTH) == clnDOB.get(Calendar.MONTH) && clnCurrentDate.get(Calendar.DAY_OF_WEEK_IN_MONTH) < clnDOB.get(Calendar.DAY_OF_WEEK_IN_MONTH))
            {
                intAge = intAge - 1;

                String strAge = String.valueOf(intAge);

                return strAge;
            }
        }

//        else if(clnDOB.get(Calendar.MONTH) > 12)
//        {
//            Toast.makeText(MainActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
//
//            return null;
//        }

        String strAge = String.valueOf(intAge);

        return strAge;
    }
}