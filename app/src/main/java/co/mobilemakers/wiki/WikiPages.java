package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 13/02/2015.
 */
public class WikiPages {

    @SerializedName("search")
    List<WikiPage> wikiPages;

    public List<WikiPage> getWikiPages() {
        return wikiPages;
    }

    public void setWikiPages(List<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }
}
