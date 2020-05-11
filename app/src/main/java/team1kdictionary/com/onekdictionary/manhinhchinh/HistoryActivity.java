package team1kdictionary.com.onekdictionary.manhinhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import adapter.WordAdapter;
import team1kdictionary.com.model.MyCustomDialog;
import team1kdictionary.com.model.Word;
import team1kdictionary.com.onekdictionary.R;
import team1kdictionary.com.onekdictionary.databinding.ActivityHistoryBinding;


import static team1kdictionary.com.onekdictionary.manhinhchinh.MainActivity.tuDaTimKiem;

public class HistoryActivity extends AppCompatActivity {

    WordAdapter adapterWord;
    ActivityHistoryBinding binding;
    SQLiteDatabase database=null;
    String DATABASE_NAME="TuDienAnhviet.sqlite";
    String DB_PATH_SUFFIX="/databases/";
    GridView gvWordList;
    WordAdapter allWordAdapter;
    public static List<Word> itemsWordList = new ArrayList<>();
    private static List<Word> tempHistoryWord = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeActivity();
        addWords();
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

    private void addWords() {
        itemsWordList.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        Cursor c = database.rawQuery("Select * From data Where history = 1", null);
        while (c.moveToNext()) {
            int id=c.getInt(0);
            String word = c.getString(1);
            String mean = c.getString(2);

            Word vocabulary = new Word(word, null, null, mean, null);
                itemsWordList.add(vocabulary);
//           allWordAdapter.add(vocabulary);

        }

        c.close();

    }

    private void addControls() {
//        //khởi tạo adapter
//        adapterWord=new WordAdapter(HistoryActivity.this, R.layout.word_item, MainActivity.itemsWordList);
//        //gán adapter cho listview
//        addWords();
//        binding.gvWordsList.setAdapter(adapterWord);

        gvWordList = findViewById(R.id.gvWordsList);
        allWordAdapter = new WordAdapter(HistoryActivity.this, R.layout.word_item, itemsWordList);
        gvWordList.setAdapter(allWordAdapter);
        MyCustomDialog.setWordForDialog(itemsWordList, gvWordList, HistoryActivity.this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        final MenuItem mnSearch = menu.findItem(R.id.mnsearch);
        SearchView searchView = (SearchView) mnSearch.getActionView();
        searchView.setQueryHint("Nhập từ tìm kiếm ở đây");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for (int i = 0; i < itemsWordList.size(); i++) {
                    if (itemsWordList.get(i).getEng().startsWith(query)) {
                        tempHistoryWord.add(itemsWordList.get(i));
                    }
                }
                MyCustomDialog.setWordForDialog(tempHistoryWord, gvWordList, HistoryActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                allWordAdapter.getFilter().filter(s);
                if (s.compareTo("") == 0) {
                    MyCustomDialog.setWordForDialog(itemsWordList, gvWordList, HistoryActivity.this);
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
