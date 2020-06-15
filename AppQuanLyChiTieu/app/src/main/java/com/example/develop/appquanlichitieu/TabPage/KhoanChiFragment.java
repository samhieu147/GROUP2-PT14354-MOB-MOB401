package com.example.develop.appquanlichitieu.TabPage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.appquanlichitieu.Database.DatabaseKhoanChi;
import com.example.develop.appquanlichitieu.Database.DatabaseLoaiChi;
import com.example.develop.appquanlichitieu.Database.DatabaseTaiKhoan;
import com.example.develop.appquanlichitieu.Model.KhoangChi;
import com.example.develop.appquanlichitieu.Model.LoaiChi;
import com.example.develop.appquanlichitieu.Model.TaiKhoan;
import com.example.develop.appquanlichitieu.R;
import com.example.develop.appquanlichitieu.ViewHolder.AdapterSpinnerChi;
import com.example.develop.appquanlichitieu.ViewHolder.AdaterSpinnerTenTaiKhoan;
import com.example.develop.appquanlichitieu.ViewHolder.KhoanChiAdapter;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;


public class KhoanChiFragment extends Fragment {
    TextView txtTongChi;
    RecyclerView recyclerView_KhoangChi;

    FloatingActionButton btnFabKhoanChi;

    RecyclerView.LayoutManager layoutManager;
    Spinner TaikhoanSp,LoaiThuSp;
    MaterialEditText edtSoTien,edtMoTa;

    FButton btnNgay,BtnHienTai;
    TextView txtBatLoi;
    DatabaseLoaiChi databaseLoaiChi;
    DatabaseKhoanChi databaseKhoanChi;
    List<LoaiChi> listdata;
    List<KhoangChi> listKhoanChi;
    KhoanChiAdapter apdater;
    AdapterSpinnerChi adapterSpinnerChi;
    List<TaiKhoan> listTaiKhoan;
    DatabaseTaiKhoan databaseTaiKhoan;
    AdaterSpinnerTenTaiKhoan adapterTenTaiKhoan;
    int pos=0;
    int t=0;
    boolean err=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_khoan_chi,container,false);
        databaseLoaiChi=new DatabaseLoaiChi(getContext());

        databaseKhoanChi=new DatabaseKhoanChi(getContext());

        databaseTaiKhoan=new DatabaseTaiKhoan(getContext());
        txtTongChi=view.findViewById(R.id.tongchi);
        txtBatLoi=view.findViewById(R.id.batloi);
        recyclerView_KhoangChi=view.findViewById(R.id.recyclerview_khoanthu);
        recyclerView_KhoangChi.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());

        recyclerView_KhoangChi.setLayoutManager(layoutManager);
        btnFabKhoanChi=view.findViewById(R.id.fabkhoanthu);
        listdata=new ArrayList<>();
        listTaiKhoan=new ArrayList<>();
        listKhoanChi=new ArrayList<>();

        listTaiKhoan=databaseTaiKhoan.getTaiKhoan();
        listdata=databaseLoaiChi.getLoaiChi();

        btnFabKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listTaiKhoan.size()<=0 || listdata.size()<=0)
                    txtBatLoi.setVisibility(View.VISIBLE);
                else
                    ShowDialog();

            }
        });

        LoadListKhoanChi();
        return view;
    }

    private void ShowDialog() {

        final AlertDialog.Builder aLertDialog=new AlertDialog.Builder(getContext());
        aLertDialog.setTitle("Thêm Khoảng Chi ");
        aLertDialog.setMessage("Vui lòng nhập đủ thông tin!");
        aLertDialog.setCancelable(false);
        LayoutInflater inflater=this.getLayoutInflater();

        View view_Add=inflater.inflate(R.layout.new_themkhoanthu_layout,null);

        btnNgay=view_Add.findViewById(R.id.btnNgay);
        BtnHienTai=view_Add.findViewById(R.id.btnHienTai);
        TaikhoanSp=view_Add.findViewById(R.id.loaitaikhoanSpinner);
        LoaiThuSp=view_Add.findViewById(R.id.loaithuSpinner);
        edtSoTien=view_Add.findViewById(R.id.edtSoTien);
        edtMoTa=view_Add.findViewById(R.id.edtMota);


        adapterTenTaiKhoan=new AdaterSpinnerTenTaiKhoan(getContext(),R.layout.item_spinner_taikhoan,listTaiKhoan);
        TaikhoanSp.setAdapter(adapterTenTaiKhoan);
        adapterTenTaiKhoan.notifyDataSetChanged();

        TaikhoanSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                pos=listTaiKhoan.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        adapterSpinnerChi=new AdapterSpinnerChi(getContext(),R.layout.item_spinner_loaichi,listdata);
        LoaiThuSp.setAdapter(adapterSpinnerChi);
        adapterSpinnerChi.notifyDataSetChanged();

        aLertDialog.setIcon(R.drawable.ic_add_circle_outline_black_24dp);

        getCurrentDate();
        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate();
            }
        });
        aLertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        aLertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                aLertDialog.setCancelable(false);
            }
        });
        aLertDialog.setView(view_Add);
        final AlertDialog dialog=aLertDialog.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhoangChi khoangChi=new KhoangChi();

                String Ngay=btnNgay.getText().toString();
                String TaiKhoan=TaikhoanSp.getSelectedItem().toString();
                String SoTien=edtSoTien.getText().toString();
                String MoTa=edtMoTa.getText().toString();
                final String LoaiThu=LoaiThuSp.getSelectedItem().toString();

                khoangChi.setNgayChi(Ngay);
                khoangChi.setTaiKhoanChi(TaiKhoan);
                khoangChi.setSoTienChi(SoTien);
                khoangChi.setMoTaChi(MoTa);
                khoangChi.setLoaiChi(LoaiThu);

                if(Ngay.equals("Chọn Ngày") || Ngay==null){
                    err=false;
                    Toast.makeText(getContext(), "Vui Lòng Chọn Ngày", Toast.LENGTH_SHORT).show();


                }
                else if(SoTien.equals("")){
                    err=false;
                    Toast.makeText(getContext(), "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                }
                if(err==true){
                    long check=databaseKhoanChi.AddKhoancHI(khoangChi);
                    if (check>0) {
                        for (final TaiKhoan tk : listTaiKhoan) {
                            if (tk.getId() == pos) {

                                t = Integer.parseInt(tk.getSoTienTaiKhoan()) - Integer.parseInt(edtSoTien.getText().toString());
                                tk.setSoTienTaiKhoan(String.valueOf(t));
                                databaseTaiKhoan.UpdateLoaiThu(tk);
                                Toast.makeText(getContext(), "Chi Thành Công", Toast.LENGTH_SHORT).show();
                                LoadListKhoanChi();
                            }
                        }
                    }else {
                        Toast.makeText(getContext(), "Fail!!!", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }

            }
        });
    }


    private void LoadListKhoanChi() {
        listKhoanChi=databaseKhoanChi.getKhoangChi();
        apdater=new KhoanChiAdapter(listKhoanChi,getContext());
        recyclerView_KhoangChi.setAdapter(apdater);
        apdater.notifyDataSetChanged();
        NumberFormat fmt;
        Locale locale;
        locale = new Locale("vi", "VN");
        fmt = NumberFormat.getCurrencyInstance(locale);
        int total=0;
        for (KhoangChi khoangChi: listKhoanChi){
            total+=(Integer.parseInt(khoangChi.getSoTienChi()));

        }
        txtTongChi.setText(fmt.format(total));

    }

    public void ChooseDate(){
        final Calendar calendar=Calendar.getInstance();
        //Date
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        int Month=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                btnNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },Year,Month,Day);
        datePickerDialog.show();
    }
    public void getCurrentDate(){
        BtnHienTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                //Time
                int seconds=calendar.get(Calendar.SECOND);
                int minute=calendar.get(Calendar.MINUTE);
                int hours=calendar.get(Calendar.HOUR);
                //Date
                int Month;
                int Day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int Year=calendar.get(Calendar.YEAR);
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(calendar1.getTime());
                if(month<13){
                    month=month+1;
                }
                String Date=Day+"/"+month+"/"+Year;
                String Time=hours+":"+minute;
                btnNgay.setText(currentDate);

            }
        });
    }
}

