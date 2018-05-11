package com.darkovr.patmimplicitintentions.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.darkovr.patmimplicitintentions.MainActivity;
import com.darkovr.patmimplicitintentions.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    @BindView(R.id.ivPhoto) ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.btCall,R.id.btWebPage,R.id.btEmail,R.id.btSms,R.id.btCamera}) void intentAction(View v) throws SecurityException{
        switch (v.getId()){
            case R.id.btCall:
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4610000000"));
                startActivity(intentCall);
                break;
            case R.id.btWebPage:
                Intent intentWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.itcelaya.edu.mx"));
                startActivity(intentWebPage);
                break;
            case R.id.btEmail:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setType("text/plain");
                intentEmail.putExtra(Intent.EXTRA_SUBJECT,"Title");
                intentEmail.putExtra(Intent.EXTRA_TEXT,"Message text");
                intentEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"person@mail.com"});
                startActivity(intentEmail);
                break;
            case R.id.btSms:
                SmsManager  smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("4610000000",null,"Mensaje desde intención implicita!"
                        ,null,null);
                Toast.makeText(getContext(),"Mensaje enviado!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btCamera:
                Intent intentCamera = new Intent("android:media:action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera,6);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==6 && resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        //Toast.makeText(getContext(),"Foto!",Toast.LENGTH_SHORT).show();
    }
}