package com.jandraszyk.subreminder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.jandraszyk.subreminder.inputfilter.DecimalDigitsInputFilter;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;
import com.skydoves.colorpickerpreference.FlagView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewSubscriptionActivity extends AppCompatActivity {

    private TextInputEditText nameInput;
    private TextInputEditText dateInput;
    private TextInputEditText costInput;
    private ImageView subscriptionIcon;
    private Button addSubscriptionButton;
    private Button subscriptionColorButton;
    private String subName;
    private Integer startDate;
    private Double subCost;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subscription);
        nameInput = findViewById(R.id.name_edit);
        dateInput = findViewById(R.id.date_edit);
        costInput = findViewById(R.id.cost_edit);
        addSubscriptionButton = findViewById(R.id.add_subscription_button);
        subscriptionIcon = findViewById(R.id.iv_subscription_icon);
        subscriptionColorButton = findViewById(R.id.bt_subscription_color);

        costInput.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        addSubscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(nameInput.getText())) {
                    subName = nameInput.getText().toString();
                }
                if(!TextUtils.isEmpty(costInput.getText())) {
                    subCost = Double.parseDouble(costInput.getText().toString());
                }
                if(!TextUtils.isEmpty(dateInput.getText())) {
                    startDate = calendar.get(Calendar.DAY_OF_MONTH);
                }
                Intent intent = new Intent();
                intent.putExtra("NAME", subName);
                intent.putExtra("COST", subCost);
                intent.putExtra("DATE", startDate);
                setResult(2,intent);
                finish();
            }
        });

        subscriptionColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(getApplicationContext(),
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setTitle("Choose subscription color");
                builder.setPreferenceName("backgroundColor");
                builder.setPositiveButton("SELECT", new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {

                    }
                });
            }
        });

        subscriptionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
                updateInput();
            }
        };

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewSubscriptionActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateInput() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.GERMANY);
        dateInput.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
