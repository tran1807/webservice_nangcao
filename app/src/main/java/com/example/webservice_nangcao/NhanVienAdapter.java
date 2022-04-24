package com.example.webservice_nangcao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(MainActivity context, int layout, List<NhanVien> nhanVienList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        ImageButton imgButtonDelete, imgButtonUpdate;
        TextView txtManv, txtTennv, txtSdt;
        ImageView imgAnh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtManv = (TextView) view.findViewById(R.id.textViewwManv);
            holder.txtTennv = (TextView) view.findViewById(R.id.textViewwTennv);
            holder.txtSdt = (TextView) view.findViewById(R.id.textViewwSdt);
            holder.imgAnh = (ImageView) view.findViewById(R.id.imageViewAnhh);

            holder.imgButtonUpdate = (ImageButton) view.findViewById(R.id.ImagebuttonUpdate);
            holder.imgButtonDelete = (ImageButton) view.findViewById(R.id.ImagebuttonDelete);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        NhanVien nhanVien = nhanVienList.get(i);
        holder.txtManv.setText("ID:" + nhanVien.getMaNV());
        holder.txtTennv.setText("TEN:"+nhanVien.getTenNV());
        holder.txtSdt.setText("SDT:"+nhanVien.getSDT());
        holder.imgAnh.setImageResource(nhanVien.getAnh());

        holder.imgButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("dataNhanVien", nhanVien);
                context.startActivity(intent);
            }
        });
        holder.imgButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(nhanVien.getTenNV(), nhanVien.getMaNV());
            }
        });

        return view;
    }

    private void delete(String name, int manv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Ban co muon xoa sinh vien " + name + " khong?");
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.deleteNhanVien(manv);
            }
        });
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
