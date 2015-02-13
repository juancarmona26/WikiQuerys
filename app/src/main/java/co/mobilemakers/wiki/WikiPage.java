package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan on 11/02/2015.
 */
public class WikiPage {
    @SerializedName("ns")
    private String ns;

    @SerializedName("title")
    private String title;

    @SerializedName("timestamp")
    private String timestamp;

    public WikiPage() {
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Page Title=" + title;
    }
}
