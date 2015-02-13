package co.mobilemakers.wiki;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PageListFragment extends ListFragment {

    final static String CATEGORY = "category";
    private static final String LOG_TAG = PageListFragment.class.getSimpleName() ;
    ArrayAdapter<WikiPage> mAdapter;
    private String mCategory = "";
    private WikiPageService.ApiInterface mWikiPageApiInterface;

    public PageListFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveProjectId();
        prepareListView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        WikiPageService wikiPageService = new WikiPageService();
        mWikiPageApiInterface = wikiPageService.generateServiceInterface();
    }

    private void prepareListView() {
        List<WikiPage> tasks = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_task,
                R.id.text_view_task_name, tasks);
        setListAdapter(mAdapter);
    }

    private void retrieveProjectId() {
        if(getArguments().containsKey(CATEGORY)){
            mCategory = getArguments().getString(CATEGORY);
        }else {
            mCategory = "";
        }
        Log.d(LOG_TAG, "Title: " + mCategory);
    }

    @Override
    public void onStart() {
        super.onStart();
        mWikiPageApiInterface.getWikiPages(WikiPageService.ACTION_VALUE,
                WikiPageService.SEARCH_LIST_VALUE, mCategory,
                WikiPageService.SRPROP_VALUE, WikiPageService.FORMAT_VALUE,
                new Callback<QueryWikiPagesResponse>() {
                    @Override
                    public void success(QueryWikiPagesResponse queryWikiPages, Response response) {
                        if (response.getStatus() == 200) {
                            if (queryWikiPages.getQueryWikiPages().getWikiPages() != null) {
                                mAdapter.clear();
                                mAdapter.addAll(queryWikiPages.getQueryWikiPages().getWikiPages());
                                mAdapter.notifyDataSetChanged();
                            } else Log.d(LOG_TAG, "Es null: " + response.getUrl());
                        } else {
                            Log.d(LOG_TAG, "Otro Codigo");
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.w(LOG_TAG, "Error: " + error.getUrl());
                    }
                });
    }
}
