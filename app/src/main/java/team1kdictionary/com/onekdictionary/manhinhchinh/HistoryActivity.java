package team1kdictionary.com.onekdictionary.manhinhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import adapter.WordAdapter;
import team1kdictionary.com.model.Word;
import team1kdictionary.com.onekdictionary.R;
import team1kdictionary.com.onekdictionary.databinding.ActivityHistoryBinding;

public class HistoryActivity extends AppCompatActivity {

    WordAdapter adapterWord;
    ActivityHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeActivity();
        addControls();
    }

    private void changeActivity() {
        //Set Home Selected
        binding.navBottom.setSelectedItemId(R.id.history);
        //Perform ItemSelectedListener
        binding.navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case  R.id.history:
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.setting:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    //dữ liệu mẫu
    private void addWords() {
//        Word w=new Word("sada","dscd","dsdsd","sdsd");
//        Word w1=new Word("sdsdsdf","sda","dsdsd","sdsfdgfnhmkhujy");
//        Word w2=new Word("sfgfj","dsd","sdw","asfsdlflfdvd");
//        Word w3=new Word("sadsfsd","dssdsadcd","sdsd","dfdsgfdgfgbfbgf");
//        adapterWord.add(w);
//        adapterWord.add(w1);
//        adapterWord.add(w2);
//        adapterWord.add(w3);

    }

    private void addControls() {
//        //khởi tạo adapter
//        adapterWord=new WordAdapter(HistoryActivity.this, R.layout.word_item, MainActivity.itemsWordList);
//        //gán adapter cho listview
//        addWords();
//        binding.gvWordsList.setAdapter(adapterWord);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        final MenuItem mnSearch = menu.findItem(R.id.mnsearch);
        SearchView searchView = (SearchView) mnSearch.getActionView();
        searchView.setQueryHint("Nhập từ tìm kiếm ở đây");
        return super.onCreateOptionsMenu(menu);
    }
}
