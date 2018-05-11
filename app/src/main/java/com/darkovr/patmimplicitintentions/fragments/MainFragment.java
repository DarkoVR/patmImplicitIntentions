package com.darkovr.patmimplicitintentions.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.darkovr.patmimplicitintentions.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    @BindView(R.id.ivimagen)
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.btCalls,R.id.btEmail,R.id.btWebPage,R.id.btCamera,R.id.btSMS,R.id.btMaps,R.id.btDialNumber,R.id.btContacts,R.id.btMusic,R.id.btSettings})
    protected void onClick(View v)
    {
        Intent intent;

        switch (v.getId())
        {
            case R.id.btCalls://Llamada telefónica
                try {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4610000000"));
                    startActivity(intent);
                }catch (SecurityException e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btEmail://Correo
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");//mime ->Intercambio de información
                intent.putExtra(Intent.EXTRA_SUBJECT,"Asunto");
                intent.putExtra(Intent.EXTRA_TEXT,"Texto del mensaje");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"person@mail.com"});
                startActivity(intent);
                break;
            case R.id.btWebPage://Pagina Web
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.itcelaya.edu.mx"));
                startActivity(intent);
                break;
            case R.id.btCamera://Camara
                Intent infoto = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(infoto,6);
                break;
            case R.id.btSMS://Mensaje
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("4610000000",null,"Mensaje enviado desde intenciones implicitas!",null,null);
                Toast.makeText(getContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btMaps: // Maps
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:20.531584, -100.662431"));
                startActivity(intent);
                break;
            case R.id.btDialNumber: //Marcar un número
                try {
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4610000000"));
                    startActivity(intent);
                }catch (SecurityException e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btContacts: //Contactos
                Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intentContacts);
                break;
            case R.id.btMusic: //Música
                intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.putExtra(MediaStore.EXTRA_MEDIA_ARTIST, "");
                intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "");
                intent.putExtra(SearchManager.QUERY, "");
                startActivity(intent);
                break;
            case R.id.btSettings: //Configuración
                Intent intentSettings = new Intent(Settings.ACTION_SETTINGS);
                startActivityForResult(intentSettings, 0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 6 && resultCode == RESULT_OK){
            Bitmap objB = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(objB);
        }

    }
}