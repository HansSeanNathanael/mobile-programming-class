package com.example.anothersimplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private Button buttonCalculate = null;
    private Button buttonExit = null;
    private TextInputEditText textInputEditTextMathExpression = null;
    private TextView labelOutputMathExpression = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.buttonCalculate = (Button)findViewById(R.id.buttonCalculate);
        this.buttonExit = (Button)findViewById(R.id.buttonExit);
        this.textInputEditTextMathExpression = (TextInputEditText)findViewById(R.id.textInputEditTextMathExpression);
        this.labelOutputMathExpression = (TextView)findViewById(R.id.labelOutputMathExpression);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == MainActivity.this.buttonExit) {
                    MainActivity.this.finish();
                    System.exit(0);
                }
                else if (v == MainActivity.this.buttonCalculate) {
                    String mathExpression = textInputEditTextMathExpression.getText().toString();
                    try {
                        Expression expression = new ExpressionBuilder(mathExpression).build();
                        MainActivity.this.labelOutputMathExpression.setText(String.valueOf(expression.evaluate()));
                    }
                    catch (Exception e) {
                        MainActivity.this.labelOutputMathExpression.setText(R.string.calculation_error);
                    }
                }
            }
        };

        this.buttonExit.setOnClickListener(buttonListener);
        this.buttonCalculate.setOnClickListener(buttonListener);
    }
}