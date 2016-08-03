package com.chinaCEB.cebActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button bt= (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setAction("com.ceb.yaoyao");
                intent.setData(Uri.parse("http://testopen.cebbank.com/LifePayment/wap/apph5/index.html?uid=111&token=821AC16F25B863E2E8E63581D36241D1&telno=16578915632&type=0&canal=360&version=1.0.0&macValue=71811CDF090919250AD785F833A3D1C3"));
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                startActivity(intent);

            }
        });
    }
}
