package co.mobilemakers.wiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.EnumSet;

/**
 * A placeholder fragment containing a simple view.
 */
public class WikiFragment extends Fragment {
    EditText mEditTextCategory;
    private Button mButtonSearchCategory;
    private enum ValidateTextViews {
        Category
    }

    EnumSet<ValidateTextViews> enumTextViewSet = EnumSet.noneOf(ValidateTextViews.class);

    public class ContentWatcher implements TextWatcher {
        ValidateTextViews mField;

        public ContentWatcher(ValidateTextViews field) {
            mField = field;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().isEmpty()) {
                enumTextViewSet.remove(mField);
            } else {
                enumTextViewSet.add(mField);
            }

            changeButtonStatus();
        }
    }

    public WikiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wiki_category, container, false);
        prepareViews(rootView);

        setViewEvents();

        return rootView;
    }

    private void setViewEvents() {
        mEditTextCategory.addTextChangedListener(new ContentWatcher(ValidateTextViews.Category));
        mButtonSearchCategory.setEnabled(false);
        mButtonSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProjectListActivity.class);
                intent.putExtra("category", mEditTextCategory.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void prepareViews(View rootView) {
        mEditTextCategory = (EditText)rootView.findViewById(R.id.edit_text_category);
        mEditTextCategory.addTextChangedListener(new ContentWatcher(ValidateTextViews.Category));
        mButtonSearchCategory = (Button) rootView.findViewById(R.id.button_search_category);
    }

     private void changeButtonStatus () {
        if (enumTextViewSet.containsAll(EnumSet.allOf(ValidateTextViews.class))) {
            mButtonSearchCategory.setEnabled(true);
        } else {
            mButtonSearchCategory.setEnabled(false);
        }
    }
}
