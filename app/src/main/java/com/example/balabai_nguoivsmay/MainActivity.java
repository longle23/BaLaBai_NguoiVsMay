package com.example.balabai_nguoivsmay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int manghinhbai[] = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Kết Quả");

        myDialog.setPositiveButton("Chơi tiếp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Lượt chơi mới", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        myDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        ImageView iv1 = findViewById(R.id.imageView1);
        ImageView iv2 = findViewById(R.id.imageView2);
        ImageView iv3 = findViewById(R.id.imageView3);
        ImageView iv4 = findViewById(R.id.imageView4);
        ImageView iv5 = findViewById(R.id.imageView5);
        ImageView iv6 = findViewById(R.id.imageView6);
        TextView tv_May = findViewById(R.id.tv_May);
        TextView tv_Nguoi = findViewById(R.id.tv_Nguoi);
        TextView tv_MayTinh = findViewById(R.id.textView_MayTinh);
        TextView tv_NguoiChoi = findViewById(R.id.textView_NguoiChoi);
        TextView tv_KetQua = findViewById(R.id.textView_KetQua);

        Button bt_rutbai = findViewById(R.id.button_rutbai);
        bt_rutbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Tính điểm cộng dồn
                int mayW = Integer.parseInt(tv_May.getText().toString().trim());
                int nguoiW = Integer.parseInt(tv_Nguoi.getText().toString().trim());
                if ((mayW + nguoiW) != 10) {

                    int[] value_may = layBaSoNgauNhien(0, 51);
                    iv1.setImageResource(manghinhbai[value_may[0]]);
                    iv2.setImageResource(manghinhbai[value_may[1]]);
                    iv3.setImageResource(manghinhbai[value_may[2]]);

                    int[] value_nguoi = layBaSoNgauNhien(0, 51);
                    iv4.setImageResource(manghinhbai[value_nguoi[0]]);
                    iv5.setImageResource(manghinhbai[value_nguoi[1]]);
                    iv6.setImageResource(manghinhbai[value_nguoi[2]]);

                    tv_MayTinh.setText(tinhKetQua(value_may));
                    tv_NguoiChoi.setText(tinhKetQua(value_nguoi));

                    if (tinhKetQuaTong(value_may) > tinhKetQuaTong(value_nguoi))
                        tv_KetQua.setText("Máy thắng !");
                    else if (tinhKetQuaTong(value_may) < tinhKetQuaTong(value_nguoi))
                        tv_KetQua.setText("Người Thắng !");
                    else
                        tv_KetQua.setText("Kết quả hòa !");

                    int nutMay = tinhKetQuaTong(value_may);
                    int nutNguoi = tinhKetQuaTong(value_nguoi);

                    if (nutMay > nutNguoi) {
                        mayW += 1;
                        tv_May.setText(String.valueOf(mayW));
                    } else if (nutMay < nutNguoi) {
                        nguoiW += 1;
                        tv_Nguoi.setText(String.valueOf(nguoiW));
                    }
                } else {
                    if (mayW > nguoiW) {
                        String mes = "THUA\n" + "Máy: " + mayW + "\n" + "Bạn: " + nguoiW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog = myDialog.create();
                        dialog.show();
                    } else if (nguoiW > mayW) {
                        String mes = "THẮNG\n" + "Bạn: " + nguoiW + "\n" + "Máy: " + mayW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog = myDialog.create();
                        dialog.show();
                    } else {
                        String mes = "HÒA\n" + "Bạn: " + nguoiW + "\n" + "Máy: " + mayW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog = myDialog.create();
                        dialog.show();
                    }
                }
            }
        });

    }


    //----------------------------------------------------
    private String tinhKetQua(int[] baso) {
        String ketqua = "";
        if (tinhSoTay(baso) == 3)
            ketqua = "Kết quả : 3 tây";
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                ketqua = "Kết quả bù, trong đó có " + tinhSoTay(baso) + "tây.";
            else
                ketqua = "Kết quả là " + (tong % 10) + " nút, trong đó có " + tinhSoTay(baso) + " tây.";
        }
        return ketqua;
    }

    private int tinhKetQuaTong(int[] baso) {
        if (tinhSoTay(baso) == 3)
            return 10;
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                return 0;
            else
                return tong % 10;
        }
    }

    //----------------------------------------------------
    private int tinhSoTay(int[] a) {
        int k = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }

    //----------------------------------------------------
    private int[] layBaSoNgauNhien(int min, int max) {
        int[] baso = new int[3];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!kiemTraTrung(k, baso))
                baso[i++] = k;
        } while (i < 3);
        return baso;
    }

    //---------------------------------------------------
    private boolean kiemTraTrung(int k, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == k)
                return true;
        return false;
    }

    // Lay gia tri ngau nhien
    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


}