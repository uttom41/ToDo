package com.uttom41mitra.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uttom41mitra.todo.adapter.DailyAdapter;
import com.uttom41mitra.todo.db.ExpensesDAO;
import com.uttom41mitra.todo.db.ModelValue;
import com.uttom41mitra.todo.db.OnClickEvent;
import com.uttom41mitra.todo.db.StringManipulation;
import com.uttom41mitra.todo.db.TaskDAO;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class ExpensesActivity extends AppCompatActivity implements OnClickEvent {

    ImageView backBtn,addBtn;
    EditText inputText;
    TextView saveBtn,canBtn;
    StringManipulation stringManipulation;
    String text,date,time;
    ExpensesDAO expensesDAO;
    RecyclerView taskRV;
    LinearLayout emptyl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        backBtn = findViewById(R.id.backexpensesbtn);
        addBtn = findViewById(R.id.addexpensesbtn);
        stringManipulation = new StringManipulation();
        expensesDAO = new ExpensesDAO(this);
        taskRV = findViewById(R.id.dailyRV);
        emptyl = findViewById(R.id.empty);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExpensesActivity.this,MainActivity.class));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ExpensesActivity.this, R.style.DialogThemeTwo);
                dialog.setContentView(R.layout.add_text_layout);
                dialog.setCancelable(false);

                inputText = dialog.findViewById(R.id.textET);
                saveBtn = dialog.findViewById(R.id.saveTV);
                canBtn = dialog.findViewById(R.id.cancelBtn);


                Window window = dialog.getWindow();
                assert window != null;
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.TOP);

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text = inputText.getText().toString().trim();
                        date = stringManipulation.getCurrentDate();
                        time = stringManipulation.getCurrentTime();
                        if (!TextUtils.isEmpty(text)){
                            ModelValue modelValue = new ModelValue();
                            modelValue.setText(text);
                            modelValue.setDate(date);
                            modelValue.setTime(time);

                            boolean status = expensesDAO.AddExpenses(modelValue);
                            if (status) {
                                Toast.makeText(ExpensesActivity.this, "saved", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Showdaily();
                            }
                            else
                                Toast.makeText(ExpensesActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }else {
                            inputText.setError("This field is empty!!");
                        }
                    }
                });

                canBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                dialog.show();
            }
        });
        Showdaily();
    }

    private void Showdaily() {
        ArrayList<ModelValue> dailyList = expensesDAO.GetAllValue();
        if (dailyList.size()>0){
            emptyl.setVisibility(View.GONE);

            DailyAdapter adapter = new DailyAdapter(this, dailyList,this::ClickEvent);
            taskRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            taskRV.setLayoutManager(new GridLayoutManager(this, 1, VERTICAL, false));
        }else {
            DailyAdapter adapter = new DailyAdapter(this, dailyList,this::ClickEvent);
            taskRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            taskRV.setLayoutManager(new GridLayoutManager(this, 1, VERTICAL, false));
            emptyl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void ClickEvent(int position) {
        boolean status = expensesDAO.DeleteDaily(position);
        if (status)
            Showdaily();
        //Toast.makeText(DailyActivity.this, position, Toast.LENGTH_SHORT).show();
    }
}