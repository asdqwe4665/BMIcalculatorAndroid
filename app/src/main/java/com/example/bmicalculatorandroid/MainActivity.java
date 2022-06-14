package com.example.bmicalculatorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstName;
    EditText lastName;
    EditText age;
    SeekBar height;
    TextView selectedHeight;
    RadioGroup genderButton;
    RadioGroup bodyFrameButton;
    EditText actualWeight;
    TextView BMITextView;
    TextView BodyStatus;
    TextView IdealWeightTextOutput;
    TextView ActualWeightTextOutput;
    double slimness = 0;
    double heightInCm;
    double idealWeight;
    double bmi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.FirstNameEditText);
        lastName = findViewById(R.id.LastNameEditText);
        age = findViewById(R.id.ageEditTextNumber);

        height = (SeekBar) findViewById(R.id.HightSeekBar);
        selectedHeight = (TextView) findViewById(R.id.selectedHightTextView);

        genderButton=findViewById(R.id.GenderGroup);
        bodyFrameButton=findViewById(R.id.BodyFrameGroup);

        actualWeight =findViewById(R.id.ActualWeightEditTextNumber);

        BMITextView=findViewById(R.id.BMITextView);
        BodyStatus =findViewById(R.id.BodyStatusTextView);
        IdealWeightTextOutput =findViewById(R.id.IdialWeightTextViewOutput);
        ActualWeightTextOutput =findViewById(R.id.ActualWeightTextViewOutput);

        height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedHeight.setText("selected height = " + height.getProgress() + " cm");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.SubmitButton:     //when you press the submit button the submit() function come in action
            {
                submit(v);
                break;
            }
            case R.id.ClearButton:      //when you press the clear button all of the inputs zeroed
            {
                BMITextView.setText("---");
                BodyStatus.setText("---");
                IdealWeightTextOutput.setText("---");
                ActualWeightTextOutput.setText("---");

                firstName.setText("");
                lastName.setText("");
                age.setText("");

                height.setProgress(140);

                genderButton.clearCheck();
                bodyFrameButton.clearCheck();

                actualWeight.setText("");
                break;
            }
        }
    }

    public void submit(View v)
    {
        if (bodyFrameButton.getCheckedRadioButtonId()==R.id.LargeRadioButton)
        {
            slimness =1.1;
        }
        if (bodyFrameButton.getCheckedRadioButtonId()==R.id.MediumRadioButton)
        {
            slimness =1;
        }
        if (bodyFrameButton.getCheckedRadioButtonId()==R.id.SmallRadioButton)
        {
            slimness =0.9;
        }

        heightInCm= height.getProgress()*0.01;

         bmi=Integer.parseInt(actualWeight.getText().toString())/(Math.pow(heightInCm,2));
         idealWeight= (height.getProgress()-100+(Integer.parseInt(age.getText().toString())/10))*0.9* slimness;

         BMITextView.setText(String.valueOf(bmi));
         BodyStatus.setText(weightStatus(bmi));
         IdealWeightTextOutput.setText(String.valueOf(idealWeight));
         ActualWeightTextOutput.setText(actualWeight.getText().toString());
    }
    public String weightStatus(double bmi)
    {
        if (bmi<15)
        {
            return "Anorexic ";
        }
        if (bmi>=15 && bmi<=18.5)
        {
            return "Underweight ";
        }
        if (bmi>=18.5 && bmi<=24.9)
        {
            return "Normal ";
        }
        if (bmi>=25 && bmi<=29.9)
        {
            return "Overweight  ";
        }
        if (bmi>=30 && bmi<=35)
        {
            return "Obese  ";
        }
        if (bmi>=35)
        {
            return "Extreme Obese  ";
        }
        return "";
    }

}