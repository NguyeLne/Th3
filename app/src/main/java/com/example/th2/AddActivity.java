package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.th2.dal.SQLHelper;
import com.example.th2.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spNganhHoc;
    private EditText tvName, tvDate , tvHocPhi;
    private RadioButton rdactive, rdnotActive;
    private Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        tvDate.setOnClickListener(this);
    }
    private void initView(){
        spNganhHoc = findViewById(R.id.spNganhHoc);
        tvName = findViewById(R.id.tvName);
        tvHocPhi = findViewById(R.id.tvHocPhi);
        tvDate = findViewById(R.id.tvDate);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        rdactive = findViewById(R.id.radio_active);
        rdnotActive = findViewById(R.id.radio_notActive);
        spNganhHoc.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.chuoiluachon)));
    }
    @Override
    public void onClick(View view) {
        if( view == tvDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day  = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m > 8){
                        date = d + "/" + (m+1) + "/" + y;

                    }
                    else{
                        date = d + "/0" + (m+1) + "/" + y;

                    }
                    tvDate.setText(date);
                }
            },year, month, day);
            dialog.show();

        }
        if(view == btCancel){
            finish();
        }
        if(view == btUpdate){
            String name = tvName.getText().toString();
            Double hocphi = Double.parseDouble(tvHocPhi.getText().toString());
            String nganhHoc = spNganhHoc.getSelectedItem().toString();
            String date = tvDate.getText().toString();
            String active = "active";
            if(rdnotActive.isActivated()){
                active = "Not active";
            }
            if(!name.isEmpty() ){
                Item i = new Item(name, date, nganhHoc, active, hocphi);
                SQLHelper db= new SQLHelper((this));
                db.addItem(i);
                finish();
            }

        }

    }
}