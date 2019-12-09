package com.example.savemygrade;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClassActivity extends AppCompatActivity {

    private EditText[] weightInputs;
    private EditText[] gradeInputs;
    private double[] weights;
    private double[] grades;
    private double desiredGrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        weightInputs = new EditText[]{
                findViewById(R.id.weight1),
                findViewById(R.id.weight2),
                findViewById(R.id.weight3),
                findViewById(R.id.weight4),
                findViewById(R.id.weight5)};

        gradeInputs = new EditText[]{
                findViewById(R.id.grade1),
                findViewById(R.id.grade2),
                findViewById(R.id.grade3),
                findViewById(R.id.grade4),
                findViewById(R.id.grade5)};

        weights = new double[weightInputs.length];
        grades = new double[gradeInputs.length];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = -1;
            grades[i] = -1;
        }

        Button calculate = findViewById(R.id.calulcate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView grade = findViewById(R.id.finalGrade);
                String text = "";

                for (int i = 0; i < weights.length; i++) {
                    weights[i] = -1;
                    grades[i] = -1;
                }

                int numWeights = 0;
                int numGrades = 0;

                for (int i = 0; i < weightInputs.length; i++) {
                    if (weightInputs[i].getText().toString().length() > 0) {
                        weights[i] = Double.parseDouble(weightInputs[i].getText().toString());
                        numWeights++;
                    }

                    if (gradeInputs[i].getText().toString().length() > 0) {
                        grades[i] = Double.parseDouble(gradeInputs[i].getText().toString());
                        numGrades++;
                    }
                }

                EditText desired = findViewById(R.id.desiredGrade);
                if (desired.getText().toString().length() > 0) {
                    desiredGrade = Double.parseDouble(desired.getText().toString());
                }

                if (desired.getText().toString().length() == 0) {
                    text = "Enter desired grade";
                }

                double total = 0;

                for (double val: weights) {
                    if (val > -1) {
                        total += val;
                    }
                }

                if (total != 100) {
                    text = "Category weights do not add up to 100%";
                }

                if (desiredGrade < 0 || desiredGrade > 100) {
                    text = "Please enter a valid desired grade!";
                }

                if (numGrades == numWeights) {
                    text = "This is your final grade";
                }

                TextView result = findViewById(R.id.finalGrade);

                if (total == 100 && (numGrades + 1 <= numWeights || numGrades == numWeights) &&
                        desiredGrade > -1 && desiredGrade <= 100) {
                    double theGrade = calculateGrade();
                    result.setText(theGrade + "");
                } else {
                    result.setText(text);
                }
            }
        });


        Button remove1 = findViewById(R.id.remove1);
        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout firstRow = findViewById(R.id.LinearLayout2);
                firstRow.setVisibility(View.GONE);
                weightInputs[0].setText("");
            }
        });

        Button remove2 = findViewById(R.id.remove2);
        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout secondRow = findViewById(R.id.LinearLayout3);
                secondRow.setVisibility(View.GONE);
                weightInputs[1].setText("");
            }
        });

        Button remove3 = findViewById(R.id.remove3);
        remove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout thirdRow = findViewById(R.id.LinearLayout4);
                thirdRow.setVisibility(View.GONE);
                weightInputs[2].setText("");
            }
        });

        Button remove4 = findViewById(R.id.remove4);
        remove4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout fourthRow = findViewById(R.id.LinearLayout5);
                fourthRow.setVisibility(View.GONE);
                weightInputs[3].setText("");
            }
        });

        Button remove5 = findViewById(R.id.remove5);
        remove5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout fifthRow = findViewById(R.id.LinearLayout6);
                fifthRow.setVisibility(View.GONE);
                weightInputs[4].setText("");
            }
        });



    }

    public double calculateGrade() {


        double gradeNeeded = 0;
        double lastWeight = 100;

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == -1) {
                lastWeight = weights[i];
            } else {
                gradeNeeded += grades[i] * (.01 * weights[i]);
            }
        }

        return (desiredGrade - gradeNeeded) / (lastWeight / 100);

    }

}
