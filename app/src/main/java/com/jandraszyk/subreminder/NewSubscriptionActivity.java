package com.jandraszyk.subreminder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.jandraszyk.subreminder.inputfilter.DecimalDigitsInputFilter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import top.defaults.colorpicker.ColorPickerPopup;

public class NewSubscriptionActivity extends AppCompatActivity {

    private TextInputEditText nameInput;
    private TextInputEditText dateInput;
    private TextInputEditText costInput;
    private ImageView subscriptionIconView;
    private Button addSubscriptionButton;
    private Button subscriptionColorButton;
    private String subName;
    private Integer startDate;
    private Integer subscriptionColor = Color.YELLOW;
    private Integer subscriptionIconId;
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
        subscriptionIconView = findViewById(R.id.iv_subscription_icon);
        subscriptionColorButton = findViewById(R.id.bt_subscription_color);

        costInput.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        subscriptionColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerPopup.Builder(getApplicationContext())
                        .initialColor(Color.RED)
                        .enableBrightness(true)
                        .enableAlpha(true)
                        .okTitle("Select")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(false)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                subscriptionColorButton.setBackgroundColor(color);
                                subscriptionColor = color;
                            }
                        });
            }
        });

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
                intent.putExtra("COLOR", subscriptionColor);
                intent.putExtra("ICON", subscriptionIconId);
                setResult(2,intent);
                finish();
            }
        });



        subscriptionIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(NewSubscriptionActivity.this, IconSelectActivity.class),3);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3) {
            subscriptionIconId = data.getIntExtra("ICON", 1);
            subscriptionIconView.setImageResource(subscriptionIconId);

        }
    }
}
