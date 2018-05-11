package com.darkovr.patmimplicitintentions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.darkovr.patmimplicitintentions.fragments.MainFragment;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_main, new MainFragment())
                .commit();
    }
}
