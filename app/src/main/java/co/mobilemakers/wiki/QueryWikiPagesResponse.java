package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 13/02/2015.
 */
public class QueryWikiPagesResponse {
    @SerializedName("query")
    WikiPages queryWikiPages;

    public WikiPages getQueryWikiPages() {
        return queryWikiPages;
    }

    public void setQueryWikiPages(WikiPages queryWikiPages) {
        this.queryWikiPages = queryWikiPages;
    }
}
