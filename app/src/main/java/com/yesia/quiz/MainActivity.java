package com.yesia.quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_soal)
    ImageView ivSoal;
    @BindView(R.id.tv_soal)
    TextView tvSoal;
    @BindView(R.id.rb_pilihan_a)
    RadioButton rbPilihanA;
    @BindView(R.id.rb_pilihan_b)
    RadioButton rbPilihanB;
    @BindView(R.id.rb_pilihan_c)
    RadioButton rbPilihanC;
    @BindView(R.id.rb_pilihan_d)
    RadioButton rbPilihanD;
    @BindView(R.id.rg_pilihan)
    RadioGroup rgPilihan;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_penjelasan)
    TextView tvPenjelasan;

    int nomor = 0;
    public static int hasil, benar, salah;
    public static MediaPlayer mediaPlayer;

    String[] pertanyaan_kuis = new String[]{
            "1. salah satu personil snsd yang keluar dari groupnya pada tahun 2014, dan saya merasa sangat sedih adalah?",
            "2. Siapakah biasku di DBSK?",
            "3. Yang Bukan Anggota Blackpink antara lain?",
            "4. Aku harus nyanyi lagu apa hari minggu?",
            "5. ITZY adalah salah satu girlsband naungan agensi?"
    };

    String[] jawaban_kuis = new String[]{
            "A. Yuri", "B. Tifany", "C. Jessica", "D. Krystal",
            "A. Junsu", "B. Yunho", "C. Jaejoong", "D.Changmin",
            "A. Jennie", "B. Lisa", "C. Rose", "D. Jia",
            "A. kada tahu", "B. molla", "C. terserah", "D. apa aja deh boleh",
            "A. YG", "B. JYP", "C. SM", "D. Starship",
    };

    String[] jawaban_benar = new String[]{
            "C. Jessica",
            "A. Junsu",
            "D. Jia",
            "B. molla",
            "B. JYP",
    };
    String[] penjelasan_jawaban = new String[]{
            "Jessica adalah bias saya",
            "Junsu juga adalah bias saya",
            "Aggota Blackpink adalah Jennie, Jisoo, Lisa dan Rose",
            "Ya molla, siapa yang tau coba",
            "ITZY adalah jebolan agensi JYP",
    };
    int[] suaraSoal = new int[]{

            R.raw.listening_part_one_q_satu,
            R.raw.listening_part_one_q_dua,
            R.raw.listening_part_one_q_tiga,
            R.raw.listening_part_one_q_empat,
            R.raw.listening_part_one_q_lima
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvSoal.setText(pertanyaan_kuis[nomor]);
        rbPilihanA.setText(jawaban_kuis[0]);
        rbPilihanB.setText(jawaban_kuis[1]);
        rbPilihanC.setText(jawaban_kuis[2]);
        rbPilihanD.setText(jawaban_kuis[3]);


        //reset nilai ketika mengulang soal
        rgPilihan.check(0);
        benar = 0;
        salah = 0;


    }


    private void playSound(int arg) {

        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Masuk Exception", Toast.LENGTH_SHORT).show();
        }


        if (nomor < pertanyaan_kuis.length) {
            mediaPlayer = MediaPlayer.create(this, suaraSoal[nomor]);
        }
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

    }

    public void next() {


        if (rbPilihanA.isChecked() || rbPilihanB.isChecked() || rbPilihanC.isChecked() || rbPilihanD.isChecked()) {

            RadioButton jawabanUser = (RadioButton) findViewById(rgPilihan.getCheckedRadioButtonId());
            String ambilJawabanUser = jawabanUser.getText().toString();

            //reset jawaban pilihan pada radioButton
            rgPilihan.check(0);

            if (ambilJawabanUser.equalsIgnoreCase(jawaban_benar[nomor])) {
                benar++;
                tvPenjelasan.setText("BENAR\n karena : \n"+penjelasan_jawaban[nomor]);
            } else {
                salah++;
                tvPenjelasan.setText("SALAH\n jawaban benar adalah : \n"+jawaban_benar[nomor]+"\nkarena\t"+penjelasan_jawaban[nomor]);
            }

            nomor++;
            if (nomor < pertanyaan_kuis.length) {
                tvSoal.setText(pertanyaan_kuis[nomor]);
                rbPilihanA.setText(jawaban_kuis[(nomor * 4) + 0]);
                rbPilihanB.setText(jawaban_kuis[(nomor * 4) + 1]);
                rbPilihanC.setText(jawaban_kuis[(nomor * 4) + 2]);
                rbPilihanD.setText(jawaban_kuis[(nomor * 4) + 3]);

            } else {
                hasil = benar * 20;
                Intent hasil = new Intent(getApplicationContext(), Hasil.class);
                startActivity(hasil);
            }

        } else {

            Toast.makeText(this, "Pilih Jawaban", Toast.LENGTH_LONG).show();
        }

    }


    @OnClick({R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                next();

                break;

        }
    }
}
