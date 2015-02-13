package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

public class CategoryMember {
    @SerializedName("pageid")
    private String pageId;

    @SerializedName("ns")
    private String ns;

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    @Override
    public String toString() {
        return title;
    }
}
