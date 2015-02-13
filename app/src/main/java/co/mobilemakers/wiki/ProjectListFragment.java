package co.mobilemakers.wiki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProjectListFragment extends ListFragment {

    private static final String LOG_TAG = ProjectListFragment.class.getSimpleName();
    public static final String CATEGORY = "category";
    WikiPageService.ApiInterface mWikiPagesApiInterface;
    ArrayAdapter<CategoryMember> mAdapter;
    String mCategory;

    public ProjectListFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        WikiPageService wikiPageService;
        if (getArguments() != null) {
            Log.d(LOG_TAG, getArguments().getString(CATEGORY));
            wikiPageService = new WikiPageService(getArguments().getString(CATEGORY));
            mCategory = getArguments().getString(CATEGORY);
        } else {
            wikiPageService = new WikiPageService("Google");
            mCategory = "Google";
        }

        mWikiPagesApiInterface = wikiPageService.generateServiceInterface();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<CategoryMember> projects = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_project, R.id.text_view_category_name, projects);
        setListAdapter(mAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryMember selectedProject = (CategoryMember)mAdapter.getItem(position);
                String selectedCategory = selectedProject.getTitle();
                Intent intent = new Intent(getActivity(), PageListActivity.class);
                intent.putExtra(PageListActivity.CATEGORY, selectedCategory);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mWikiPagesApiInterface.getWikiCategories(WikiPageService.ACTION_VALUE,
                WikiPageService.LIST_VALUE, WikiPageService.CATEGORY_PREFIX + mCategory,
                WikiPageService.FORMAT_VALUE,
                new Callback<QueryResponse>() {
                    @Override
                    public void success(QueryResponse categoryMembers, Response response) {
                        if (response.getStatus() == 200) {
                            Log.w(LOG_TAG, "ERROR: downloading " +
                                    response.getBody().mimeType());
                            if (categoryMembers.getCategoryMembers() != null) {
                                mAdapter.clear();
                                mAdapter.addAll(categoryMembers.getCategoryMembers().getCategoryMembers());
                                mAdapter.notifyDataSetChanged();
                            } else Log.e(LOG_TAG, "Lista category members = null");
                        } else {
                            Log.e(LOG_TAG, "Project retrieval status problem: " + response.getReason());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.w(LOG_TAG, "ERROR: downloading " + error.getUrl());
                    }
                });
    }
}
