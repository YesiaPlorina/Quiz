package com.yesia.quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Hasil extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;

    @BindView(R.id.tv_hasil)
    TextView tvHasil;
    @BindView(R.id.tv_nilai)
    TextView tvNilai;
    @BindView(R.id.btn_ulangi)
    Button btnUlangi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        ButterKnife.bind(this);

        tvHasil.setText("Jawaban Benar : " + MainActivity.benar + "\n Jawaban Salah : " + MainActivity.salah);
        tvNilai.setText("" + MainActivity.hasil);

    }

    @OnClick(R.id.btn_ulangi)
    public void onViewClicked() {
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
