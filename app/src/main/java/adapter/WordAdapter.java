package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import team1kdictionary.com.model.Word;
import team1kdictionary.com.onekdictionary.R;

public class WordAdapter extends BaseAdapter implements Filterable {

    Activity context;
    int resource;
    private List<Word> itemsWordList;
    private List<Word> itemsWordListFilter;

    public WordAdapter() {
    }

    public WordAdapter(@NonNull Activity context, int resource, List<Word> itemsWordList) {
        this.context=context;
        this.resource=resource;
        this.itemsWordList = itemsWordList;
        this.itemsWordListFilter = itemsWordList;
    }

    @Override
    public int getCount() {
        return itemsWordListFilter.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.word_item, null);


        ImageView imgLove = view.findViewById(R.id.imgLove);
        TextView txtWord = view.findViewById(R.id.tvWord);
        TextView txtMean = view.findViewById(R.id.txtMean);
        imgLove.setImageResource(itemsWordListFilter.get(position).getIsFavorite());
        txtWord.setText(itemsWordListFilter.get(position).getEng());
        txtMean.setText(itemsWordListFilter.get(position).getMeaning());

        return view;
    }


    /**
     * @return
     */
    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = itemsWordList.size();
                    filterResults.values = itemsWordList;
                } else {
                    String searchStr = constraint.toString().toLowerCase();
                    List<Word> resultData = new ArrayList<>();
                    for (Word itemsWord : itemsWordList) {
                        if (itemsWord.getEng().startsWith(searchStr)) {
                            resultData.add(itemsWord);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsWordListFilter = (List<Word>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
