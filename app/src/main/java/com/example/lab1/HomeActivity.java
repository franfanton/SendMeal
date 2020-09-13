package com.example.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;
import android.widget.Button;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
        Toolbar toolbarprincipal = (Toolbar) findViewById(R.id.toolbarprincipal);
        setSupportActionBar(toolbarprincipal);
        

    }

    private void setSupportActionBar(Toolbar toolbarprincipal) {
    }
}
