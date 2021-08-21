package local.john;

public abstract class ISavedTreeItem extends TypeMatchable {

    private final String name;

    public ISavedTreeItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "<" + this.getClass().getSimpleName() + "(\"" + this.name + "\")>";
    }
}
