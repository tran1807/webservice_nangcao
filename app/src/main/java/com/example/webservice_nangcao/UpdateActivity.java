package com.example.webservice_nangcao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    EditText edtMaNV, edtTenNV, edtSDT;
    Button btnUpdate, btnThoat, btnChonhinh, btnChuphinh;
    ImageView imgAnh;
    // Cach thu 2 de cap nhat data file update
//    int id = 0;
    String url = "http://lenhutran1807-001-site1.htempurl.com/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        anhXa();

        Intent intent = getIntent();
        NhanVien nhanVien = (NhanVien) intent.getSerializableExtra("dataNhanVien");

//        id = nhanVien.getMaNV();
        edtMaNV.setText(nhanVien.getMaNV() + "");
        edtTenNV.setText(nhanVien.getTenNV());
        edtSDT.setText(nhanVien.getSDT());
    }

    private void anhXa() {
        edtMaNV = (EditText) findViewById(R.id.editTextManvsua);
        edtTenNV = (EditText) findViewById(R.id.editTextTennvsua);
        edtSDT = (EditText) findViewById(R.id.editTextSdtsua);

        btnUpdate = (Button) findViewById(R.id.buttonThemsua);
        btnThoat = (Button) findViewById(R.id.buttonThoatsua);
        btnChonhinh = (Button) findViewById(R.id.buttonChonhinhsua);
        btnChuphinh = (Button) findViewById(R.id.buttonChuphinhsua);

        imgAnh = (ImageView) findViewById(R.id.imageViewAnhsua);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateNhanVien(url);
            }
        });

    }

    private void UpdateNhanVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("update data successful")) {
                    Toast.makeText(UpdateActivity.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(UpdateActivity.this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateActivity.this, "Xay ra loi!!!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Loi!\n" + error.toString());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // update data
                params.put("MaNV", edtMaNV.getText().toString());
//                params.put("MaNV", String.valueOf(id));
                params.put("TenNV", edtTenNV.getText().toString().trim());
                params.put("SDT", edtSDT.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}