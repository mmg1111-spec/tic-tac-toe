package com.example.a120181849;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class rule_main extends AppCompatActivity {
    ImageView imageview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule_main);
        imageview=(ImageView) findViewById(R.id.picture);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.picture:
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(rule_main.this);
                        alertBuilder.setTitle("항목중에 하나를 선택하세요.");
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(rule_main.this, android.R.layout.select_dialog_item);
                        adapter.add("처음");
                        adapter.add("3x3");
                        adapter.add("5x5");
                        adapter.add("7x7");
                        alertBuilder.setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String strName = adapter.getItem(id);
                                        AlertDialog.Builder innBuilder = new AlertDialog.Builder(rule_main.this);
                                        innBuilder.setMessage(strName);
                                        innBuilder.setTitle("당신이 선택한 것은 ");
                                        innBuilder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                                            public void onClick( DialogInterface dialog, int which) {
                                                if(strName=="3x3"){
                                                    Intent intent = new Intent(getApplicationContext(),first_main.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                }
                                                if(strName=="5x5"){
                                                    Intent intent = new Intent(getApplicationContext(),second_main.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                }   if(strName=="7x7"){
                                                    Intent intent = new Intent(getApplicationContext(),third_main.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                } if(strName=="처음"){
                                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                    startActivity(intent);
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                        innBuilder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            }
                                        });
                                        innBuilder.setCancelable(false);
                                        innBuilder.show();

                                    }
                                });
                        alertBuilder.show();
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
