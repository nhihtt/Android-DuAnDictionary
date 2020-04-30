package team1kdictionary.com.onekdictionary.luyentap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import team1kdictionary.com.onekdictionary.R;

public class LuyenTapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_tap);
        Button btnStarQuiz=findViewById(R.id.btnStartQuiz);
        btnStarQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent= new Intent(LuyenTapActivity.this,QuizActivity.class);
        startActivity(intent);
    }

}
