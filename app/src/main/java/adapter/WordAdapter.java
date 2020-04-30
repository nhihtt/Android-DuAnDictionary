package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import team1kdictionary.com.model.Word;
import team1kdictionary.com.onekdictionary.R;

public class WordAdapter extends ArrayAdapter<Word> {

    Activity context;
    int resource;
    public WordAdapter(@NonNull Activity context, int resource) {

        super(context, resource);
        this.context=context;
        this.resource=resource;
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);
        Word w = getItem(position);
        TextView txtWord=view.findViewById(R.id.txtWord);
        TextView txtPronounce=view.findViewById(R.id.txtPronounce);
        TextView txtType=view.findViewById(R.id.txtType);
        TextView txtMeaning=view.findViewById(R.id.txtMeaning);
        txtWord.setText(w.getEng());
        txtPronounce.setText(w.getPronounce());
        txtType.setText(w.getType());
        txtMeaning.setText(w.getMeaning());
        return view;
    }

}
