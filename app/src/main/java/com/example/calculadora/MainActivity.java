package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solution, result;

    MaterialButton btnC, btnAc, btnOpenBracket, btnCloseBracket, btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals,
    btnDot, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution = findViewById(R.id.text_view_result);
        result = findViewById(R.id.text_view_solution);

        assingId(btnC, R.id.button_c);
        assingId(btnAc, R.id.button_ac);
        assingId(btnOpenBracket, R.id.button_open_bracket);
        assingId(btnCloseBracket, R.id.button_close_bracket);
        assingId(btnDivide, R.id.button_divide);
        assingId(btnMultiply, R.id.button_multiply);
        assingId(btnPlus, R.id.button_plus);
        assingId(btnMinus, R.id.button_minus);
        assingId(btnEquals, R.id.button_equals);
        assingId(btnDot, R.id.button_dot);
        assingId(btn1, R.id.button_1);
        assingId(btn2, R.id.button_2);
        assingId(btn3, R.id.button_3);
        assingId(btn4, R.id.button_4);
        assingId(btn5, R.id.button_5);
        assingId(btn6, R.id.button_6);
        assingId(btn7, R.id.button_7);
        assingId(btn8, R.id.button_8);
        assingId(btn9, R.id.button_9);
        assingId(btn0, R.id.button_0);
    }

    void assingId(MaterialButton btn, int id){

        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if (buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;

        }

        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }

    String getResult(String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1, null).toString();

            if (finalResult.endsWith(".0")){

                finalResult = finalResult.replace(".0","");
            }

            return finalResult;
        }catch (Exception e){
            return "Err";
        }

    }
}