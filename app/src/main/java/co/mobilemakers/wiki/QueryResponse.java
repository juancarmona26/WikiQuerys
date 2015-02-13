package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 13/02/2015.
 */
public class QueryResponse {

    @SerializedName("query")
    private CategoryMembers categoryMembers;

    public CategoryMembers getCategoryMembers() {
        return categoryMembers;
    }

    public void setCategoryMembers(CategoryMembers categoryMembers) {
        this.categoryMembers = categoryMembers;
    }
}