package co.mobilemakers.wiki;

import android.util.Log;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class WikiPageService {

    public final static String WIKIPEDIA_API_URL = "http://en.wikipedia.org/w";
    public final static String CATEGORIES_ENDPOINT = "/api.php";
    public final static String ACCEPTED_DATA = "application/json";
    public final static String ACTION_PARAM = "action";
    public final static String ACTION_VALUE = "query";
    public final static String LIST_PARAM = "list";
    public final static String LIST_VALUE = "categorymembers";
    public final static String CAT_MEMB_PARAMETER = "cmtitle";
    public final static String CATEGORY_PREFIX = "Category:";
    public final static String FORMAT_PARAMETER = "format";
    public final static String FORMAT_VALUE = "json";
    public final static String SEARCH_LIST_VALUE = "search";
    public final static String SRSEARCH_PARAM = "srsearch";
    public final static String SRPROP_PARAM = "srprop";
    public final static String SRPROP_VALUE = "timestamp";
    private static final String LOG_TAG = WikiPageService.class.getSimpleName();

    private String mCategory;

    public WikiPageService() {
    }

    public interface ApiInterface {
        @GET(CATEGORIES_ENDPOINT)
        void getWikiCategories(@Query(ACTION_PARAM) String action, @Query(LIST_PARAM) String list,
                               @Query(CAT_MEMB_PARAMETER) String categoryMember, @Query(FORMAT_PARAMETER) String format,
                               Callback<QueryResponse> callback);
        @GET(CATEGORIES_ENDPOINT)
        void getWikiPages(@Query(ACTION_PARAM) String action,
                                @Query(LIST_PARAM) String list,
                                @Query(SRSEARCH_PARAM) String srSearch,
                                @Query(SRPROP_PARAM) String srProp,
                                @Query(FORMAT_PARAMETER) String format,
                                Callback<QueryWikiPagesResponse> callback);
    }

    public WikiPageService(String category) {
        mCategory = category;

    }
    public ApiInterface generateServiceInterface (){
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(WIKIPEDIA_API_URL).
                setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        Log.d(LOG_TAG, "request: " + request.toString());
                        request.addHeader("Accept" , ACCEPTED_DATA);
                    }
                });
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ApiInterface.class);
    }
}
