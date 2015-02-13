package co.mobilemakers.wiki;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Juan on 11/02/2015.
 */
public class CategoryMembers {
    @SerializedName("categorymembers")
    private List<CategoryMember> categoryMembers;

    public List<CategoryMember> getCategoryMembers() {
        return categoryMembers;
    }

    public void setCategoryMembers(List<CategoryMember> categoryMembers) {
        this.categoryMembers = categoryMembers;
    }
}
