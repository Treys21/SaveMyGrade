package com.example.savemygrade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        weights = new double[]{-1, -1, -1, -1 ,-1};
        grades = new double[]{-1, -1, -1, -1, -1};



        Button calculate = findViewById(R.id.calulcate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < weightInputs.length; i++) {
                    if (weightInputs[i].getText().toString().length() > 0) {
                        weights[i] = Double.parseDouble(weightInputs[i].getText().toString());
                    }

                    if (gradeInputs[i].getText().toString().length() > 0) {
                        grades[i] = Double.parseDouble(gradeInputs[i].getText().toString());
                    }
                }




                EditText desired = findViewById(R.id.desiredGrade);
                if (desired.getText().toString().length() > 0) {
                    desiredGrade = Double.parseDouble(desired.getText().toString());
                }

                int numWeights = 0;
                int numGrades = 0;

                for (double num: weights) {
                    if (num > -1) {
                        numWeights++;
                    }
                }

                for (double num: grades) {
                    if (num > -1) {
                        numGrades++;
                    }
                }

                if (numWeights == 5 && numGrades == 4 && desiredGrade > -1 && desiredGrade <= 100) {
                    calculateGrade();
                }

                calculateGrade();
            }
        });


        Button remove1 = findViewById(R.id.remove1);
        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout firstRow = findViewById(R.id.LinearLayout2);
                firstRow.setVisibility(View.GONE);
                //((EditText) firstRow.findViewById(R.id.weight1)).setText("");

                //((EditText) firstRow.findViewById(R.id.grade1)).setText("");

            }
        });

        Button remove2 = findViewById(R.id.remove2);
        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout secondRow = findViewById(R.id.LinearLayout3);
                secondRow.setVisibility(View.GONE);

                //((EditText) secondRow.findViewById(R.id.weight2)).setText("");
                //((EditText) secondRow.findViewById(R.id.grade2)).setText("");

            }
        });

        Button remove3 = findViewById(R.id.remove3);
        remove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout thirdRow = findViewById(R.id.LinearLayout4);
                thirdRow.setVisibility(View.GONE);

                //((EditText) thirdRow.findViewById(R.id.weight3)).setText("");
                //((EditText) thirdRow.findViewById(R.id.grade3)).setText("");

            }
        });

        Button remove4 = findViewById(R.id.remove4);
        remove4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout fourthRow = findViewById(R.id.LinearLayout5);
                fourthRow.setVisibility(View.GONE);

               // ((EditText) fourthRow.findViewById(R.id.weight4)).setText("");
                //((EditText) fourthRow.findViewById(R.id.grade4)).setText("");

            }
        });

        Button remove5 = findViewById(R.id.remove5);
        remove5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout fifthRow = findViewById(R.id.LinearLayout6);
                fifthRow.setVisibility(View.GONE);

                //((EditText) fifthRow.findViewById(R.id.weight5)).setText("");
                //((EditText) fifthRow.findViewById(R.id.grade5)).setText("");
            }
        });



    }

    public void calculateGrade() {


        double gradeNeeded = 0;
        double lastWeight = 100;

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == -1) {
                lastWeight = weights[i];
            } else {
                gradeNeeded += grades[i] * (.01 * weights[i]);
            }
        }

        gradeNeeded = (desiredGrade - gradeNeeded) / (lastWeight / 100);

        TextView grade = findViewById(R.id.finalGrade);
        grade.setText(gradeNeeded + "");
    }

}
