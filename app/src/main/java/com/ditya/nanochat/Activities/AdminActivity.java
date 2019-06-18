package com.ditya.nanochat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ditya.nanochat.R;

public class AdminActivity extends AppCompatActivity {

    private Button tombolprof;
    private Button tombolanalisa;
    private Button tombolbantuan;
    private Button tombolpower;

    @Override
    public void onBackPressed() {
        // do nothing.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tombolprof=(Button)findViewById(R.id.buttonprof);
        tombolanalisa=(Button)findViewById(R.id.buttonanalisa);
        tombolpower=(Button)findViewById(R.id.buttonpower);
        tombolbantuan=(Button)findViewById(R.id.buttonbantuan);

        tombolbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(AdminActivity.this,Navigasi2Activity.class )));
            }
        });

        tombolprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(AdminActivity.this,ProfileActivity.class )));
            }
        });

        tombolpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(AdminActivity.this,PowerActivity.class )));
            }
        });
//

        tombolanalisa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent loginActivity = new Intent(getApplicationContext(), Home.class);
                startActivity(loginActivity);
                finish();
            }
        });

    }
}
