package com.example.webservice_nangcao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends AppCompatActivity {
    EditText edtManv,edtTennv,edtSdt;
    Button btnThem,btnThoat,btnChonhinh,btnChuphinh;
    ImageView imgAnhThem;
    String urlInsert  = "http://lenhutran1807-001-site1.htempurl.com/insert.php";
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        anhxa();
        addEvent();

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themNhanVien(urlInsert);
            }
        });


    }

    private void addEvent() {
            btnChuphinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takePhoto();
                }
            });
            btnChonhinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choosePhoto();
                }
            });

    }
    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);

    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgAnhThem.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgAnhThem.setImageBitmap(bitmap);
            }
        }
    }


    private void anhxa() {
        edtManv = (EditText) findViewById(R.id.editTextManvthem);
        edtTennv = (EditText) findViewById(R.id.editTextTennvthem);
        edtSdt = (EditText) findViewById(R.id.editTextSdtthem);
        btnThem = (Button) findViewById(R.id.buttonThemthem);
        btnThoat = (Button) findViewById(R.id.buttonThoatthem);
        btnChonhinh = (Button) findViewById(R.id.buttonChonhinhthem);
        btnChuphinh = (Button) findViewById(R.id.buttonChuphinhthem);
        imgAnhThem = (ImageView) findViewById(R.id.imageViewAnhThem);
    }


    private void themNhanVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("insert data successful")) {
                            Toast.makeText(InsertActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(InsertActivity.this, MainActivity.class));
                        } else
                            Toast.makeText(InsertActivity.this, "Thêm không thành công", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InsertActivity.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();
                        Log.d("AAA", "Lỗi\n" + error.toString());
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("MaNV",edtManv.getText().toString());
                params.put("TenNV",edtTennv.getText().toString());
                params.put("SDT",edtSdt.getText().toString());
               // params.put("Anhnv", imgAnhThem.getImageAlpha());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}

