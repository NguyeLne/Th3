package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.th2.adapter.ViewPagerAdapter;
import com.example.th2.dal.SQLHelper;
import com.example.th2.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spNganhHoc;
    private EditText eName, eDate , eHocPhi;
    private RadioButton rdactive, rdnotActive;
    private Button btUpdate, btBack, btRemove;
    private Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eName.setText(item.getName());
        eDate.setText(item.getDate());
        eHocPhi.setText(String.valueOf(item.getHocphi()));
        int p = 0;
        for(int i = 0; i < spNganhHoc.getCount(); i++){
            if(spNganhHoc.getItemAtPosition(i).toString().equalsIgnoreCase(item.getNganh())){
                p = i;
                break;
            }
        }
        spNganhHoc.setSelection(p);
        if(item.getActive().equals("active")){
            rdactive.setChecked(true);
        }else{
            rdnotActive.setChecked(true);
        }

    }
    private void initView() {
        spNganhHoc = findViewById(R.id.spNganhHoc);
        eName = findViewById(R.id.tvName);
        eHocPhi = findViewById(R.id.tvHocPhi);
        eDate = findViewById(R.id.tvDate);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);
        rdactive = findViewById(R.id.radio_active);
        rdnotActive = findViewById(R.id.radio_notActive);
        spNganhHoc.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.chuoiluachon)));

    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day  = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m > 8){
                        date = d + "/" + (m+1) + "/" + y;

                    }
                    else{
                        date = d + "/0" + (m+1) + "/" + y;

                    }
                    eDate.setText(date);
                }
            },year, month, day);
            dialog.show();
        }
        if(view == btBack){
            finish();
        }
        if(view == btUpdate){
            String name = eName.getText().toString();
            Double hocphi =Double.parseDouble(eHocPhi.getText().toString());
            String nganhHoc = spNganhHoc.getSelectedItem().toString();
            String active = "active";
            if(rdnotActive.isActivated()){
                active = "Not active";
            }
            String d = eDate.getText().toString();
            if(!name.isEmpty() ){
                int id = item.getId();
                System.out.println("Update" + id);
                Item i = new Item(id,name, d, nganhHoc, active,hocphi );
                SQLHelper db1 = new SQLHelper(this);
                db1.update(i);
                finish();
            }
        }
        if(view == btRemove){
            int id = item.getId();
            System.out.println("Delete" + id);
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa " + item.getName() + " khong?");
            builder.setIcon(R.drawable.ic_remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLHelper db1 = new SQLHelper(getApplicationContext());
                    db1.delete(id);
                    System.out.println("Delete thanh cong");
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}