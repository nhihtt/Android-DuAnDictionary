package team1kdictionary.com.onekdictionary.hienthitu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import team1kdictionary.com.onekdictionary.R;
import team1kdictionary.com.onekdictionary.databinding.ActivityHienThiTuBinding;

public class HienThiTuActivity extends AppCompatActivity {
    ActivityHienThiTuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=  ActivityHienThiTuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        xuLyLayDuLieuTuBenKia();
    }
    private void xuLyLayDuLieuTuBenKia() {
        Intent intent= getIntent();
        binding.txtMean.setText(intent.getStringExtra("txtWord"));

    }
}
