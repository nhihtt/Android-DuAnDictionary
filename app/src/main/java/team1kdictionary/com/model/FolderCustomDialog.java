package team1kdictionary.com.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import team1kdictionary.com.onekdictionary.R;

public class FolderCustomDialog extends Dialog {
    TextView textView;
    Activity context;

    public FolderCustomDialog(@NonNull Activity context) {
        super(context);
        this.context = context;
        setContentView(R.layout.list_folder);
        addControls();
        addEvents();
    }

    private void addEvents() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Hello");
            }
        });
    }

    private void addControls() {
        textView = findViewById(R.id.txtTest);
    }
}
