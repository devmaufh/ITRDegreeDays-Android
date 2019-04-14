package com.devmaufh.itrdegreedays.Activities;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.Database.InsectViewModel;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;
import com.devmaufh.itrdegreedays.R;
import com.devmaufh.itrdegreedays.Utilities.DegreeDaysUtilities;

import java.util.Calendar;
import java.util.List;

public class Register_in extends AppCompatActivity {
    private TextInputLayout tlName,tlTU,tlTL,tlTMax,tlTMin;
    private TextInputEditText edName,edTU,edTL,edTMax,edTMin;
    private MaterialButton btnSave,btnCurrentWeather;
    private InsectViewModel mInsectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_in);
        setToolbar();
        bindUI();
        btnSave.setOnClickListener(v->{
            if(validateFields()){
                if(checkTemps(edTU,edTL,edTMax,edTMin)){
                    insertIntoDatabase(edName.getText().toString(),Double.parseDouble(edTU.getText().toString()),Double.parseDouble(edTL.getText().toString()),DegreeDaysUtilities.getCurrentDate(),Double.parseDouble(edTMin.getText().toString()),Double.parseDouble(edTMax.getText().toString()));
                    finish();
                }
            }
        });
        btnCurrentWeather.setOnClickListener(v->{
            Toast.makeText(this, "Testing :v", Toast.LENGTH_SHORT).show();
        });
    }
    private void bindUI(){
        tlName=(TextInputLayout)findViewById(R.id.sr_layTexIn);
        tlTU=(TextInputLayout)findViewById(R.id.sr_layTexITU);
        tlTL=(TextInputLayout)findViewById(R.id.sr_layTexITL);
        tlTMax=(TextInputLayout)findViewById(R.id.sr_laytMax);
        tlTMin=(TextInputLayout)findViewById(R.id.sr_laytMin);
        edName=(TextInputEditText) findViewById(R.id.sh_edName);
        edTU=(TextInputEditText) findViewById(R.id.sh_edTU);
        edTL=(TextInputEditText) findViewById(R.id.sh_edTL);
        edTMax=(TextInputEditText) findViewById(R.id.sh_edtMax);
        edTMin=(TextInputEditText) findViewById(R.id.sh_edtMin);
        btnSave=(MaterialButton) findViewById(R.id.sr_btnAdd);
        btnCurrentWeather=(MaterialButton)findViewById(R.id.sr_btnCons);
    }
    private boolean validateFields(){
        if(!checkEmpty(edName.getText().toString()) &&checkLeng(edName.getText().toString(),40)){ //Check if text is not null
            tlName.setErrorEnabled(false);
            if(!checkEmpty(edTU.getText().toString()) &&checkLeng(edTU.getText().toString(),2)){
                tlTU.setErrorEnabled(false);
                if(!checkEmpty(edTL.getText().toString()) &&checkLeng(edTL.getText().toString(),2)){
                    tlTL.setErrorEnabled(false);
                    if(!checkEmpty(edTMax.getText().toString()) &&checkLeng(edTMax.getText().toString(),2)){
                        tlTMax.setErrorEnabled(false);
                        if(!checkEmpty(edTMin.getText().toString()) &&checkLeng(edTMin.getText().toString(),2)){
                            tlTMin.setErrorEnabled(false);
                            return true;
                        }else{
                            tlTMin.setError(getResources().getText(R.string.camporeq));
                            edTMin.requestFocus();
                            return false;
                        }
                    }else{
                        tlTMax.setError(getResources().getText(R.string.camporeq));
                        edTMax.requestFocus();
                        return false;
                    }
                }else{
                    tlTL.setError(getResources().getText(R.string.camporeq));
                    edTL.requestFocus();
                    return false;
                }
            }else{
                tlTU.setError(getResources().getText(R.string.camporeq));
                edTU.requestFocus();
                return false;
            }
        }else{
            tlName.setError(getResources().getText(R.string.camporeq));
            edName.requestFocus();
            return false;
        }
    }
    private boolean checkLeng(String text, int maxLength){
        return text.length()<=maxLength;
    }
    private boolean checkEmpty(String text){
        return TextUtils.isEmpty(text);
    }
    private void setToolbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.reg_ir_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_24dp);
        setTitle(getResources().getString(R.string.registrar));
    }
    private boolean checkTemps(TextInputEditText iMax,TextInputEditText iMin,TextInputEditText tMax,TextInputEditText tMin){
        if(Double.parseDouble(iMax.getText().toString())>Double.parseDouble(iMin.getText().toString())){
            if(Double.parseDouble(tMax.getText().toString())>Double.parseDouble(tMin.getText().toString())){
                return true;
            }else{
                Toast.makeText(this, getResources().getText(R.string.validaTmax_max_tmin), Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this, getResources().getText(R.string.validaTu_max_tl), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void insertIntoDatabase(String iName,double tu,double tl, String date,double tmin,double tmax){
        mInsectViewModel= ViewModelProviders.of(this).get(InsectViewModel.class);
        InsectEntity insect= new InsectEntity();
        insect.setName(iName);
        insect.setTu(tu);
        insect.setTl(tl);
        DatesEntity dateE= new DatesEntity();
        dateE.setId(date);
        dateE.setTMax(tmax);
        dateE.setTMin(tmin);
        mInsectViewModel.insertInsect(insect);
        mInsectViewModel.insertDate(dateE);
    }

}