package local.john;

import java.util.ArrayList;
import java.util.List;

public class Group extends ISavedTreeItem {
    private List<ISavedTreeItem> children = new ArrayList<>();

    public Group(String name) {
        super(name);
    }

    public List<ISavedTreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<ISavedTreeItem> children) {
        this.children = children;
    }
}
