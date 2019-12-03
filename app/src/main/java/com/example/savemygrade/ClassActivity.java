package com.example.savemygrade;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
