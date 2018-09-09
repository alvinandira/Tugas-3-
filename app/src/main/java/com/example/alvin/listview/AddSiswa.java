package com.example.alvin.listview;

/**
 * Created by Alvin on 04/09/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSiswa extends AppCompatActivity {

    @BindView(R.id.edit_name)
    EditText editName;

    @BindView(R.id.edit_address)
    EditText editAddress;

    @BindView(R.id.img_add_profile)
    ImageView imgAddProfile;

    @BindView(R.id.btn_add)
    Button btnAdd;

    private SiswaModel mSiswaModel;
    private ArrayList<Image> mImageLibrary = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_siswa);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_add_profile)
    public void onViewClickedImage(View view) {
        ImagePicker.with(this)
                .setFolderMode(true)
                .setMaxSize(10)
                .setMultipleMode(false)
                .setCameraOnly(false)
                .setFolderTitle("Albums")
                .setSelectedImages(mImageLibrary)
                .setKeepScreenOn(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            mImageLibrary = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            Glide.with(this).load(mImageLibrary.get(0).getPath()).into(imgAddProfile);
        }
    }

    @OnClick(R.id.btn_add)
    public void onViewClickedAdd(View view) {
        if (
                !editName.getText().toString().isEmpty() &&
                        !editAddress.getText().toString().isEmpty() &&
                        !mImageLibrary.isEmpty()) {

            mSiswaModel = new SiswaModel();

            mSiswaModel.setNama(editName.getText().toString());
            mSiswaModel.setAlamat(editAddress.getText().toString());
            mSiswaModel.setPathFoto(mImageLibrary.get(0).getPath());

            SiswaApp.db.userDao().insertAll(mSiswaModel);

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

}
