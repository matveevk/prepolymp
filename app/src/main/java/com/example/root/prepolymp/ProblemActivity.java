package com.example.root.prepolymp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProblemActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.root.prepolymp.MESSAGE";
    public static ArrayList<Problem> problems= new ArrayList<>();

    // id, text, answer, form, difficulty, origins

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_view);
        /*
        // stretching the linearLayout to what we need (and problemText)
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutProblemInfo);
        int width = getResources().getDisplayMetrics().widthPixels / 10 * 9;
        int height = linearLayout.getHeight();
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        */
            // OR
        /*
        // another option
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutProblemInfo);
        linearLayout.setMinimumWidth(screenWidth() / 10 * 9);
        TextView tv = (TextView)findViewById(R.id.problemText);
        tv.setWidth(screenWidth() / 10 * 9);
        */

        Problem problem = new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888", "алгебра");
        problems.add(problem);

        showProblem(problem);

        openInfo(problem);

        checkAns(problem);
    }

    public void showProblem(Problem problem) {
        TextView problemText = (TextView)findViewById(R.id.problemText);
        problemText.setText(problem.text);
        TextView problemInfo = (TextView)findViewById(R.id.problemInfo);
        problemInfo.setText(getString(R.string.problem_data, problem.id, problem.form, problem.diff));
    }

    public void openInfo(final Problem problem) {
        ImageButton infoImageButton = (ImageButton)findViewById(R.id.infoImageButton);
        infoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProblemInfo.class);
                intent.putExtra(EXTRA_MESSAGE, problem.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void checkAns(final Problem problem) {
        final EditText userAns = (EditText)findViewById(R.id.insertAnswer);
        Button checkAnsButton = (Button)findViewById(R.id.checkButton);
        checkAnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = userAns.getText().toString();
                TextView checkResult = (TextView)findViewById(R.id.checkResult);
                if (s.equals(problem.ans)) {
                    checkResult.setTextColor(Color.parseColor("#66BB6A"));
                    checkResult.setText("Верно!");
                } else {
                    checkResult.setTextColor(Color.RED);
                    checkResult.setText("Неверно!");
                }
            }
        });
    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
