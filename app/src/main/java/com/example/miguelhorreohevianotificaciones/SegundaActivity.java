package com.example.miguelhorreohevianotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    EditText editText;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast toast = Toast.makeText(SegundaActivity.this, editText.getText(),
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress()<5) {
                    Toast toast = Toast.makeText(SegundaActivity.this,
                            "Inferior", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (seekBar.getProgress()==5) {
                   Toast toast = Toast.makeText(SegundaActivity.this, "Centro",
                           Toast.LENGTH_SHORT);
                   toast.show();
                }
                if (seekBar.getProgress()>5) {
                    Toast toast = Toast.makeText(SegundaActivity.this, "Superior",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });






    }
}