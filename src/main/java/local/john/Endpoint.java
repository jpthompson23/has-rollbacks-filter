package local.john;

public class Endpoint extends ISavedTreeItem {
    private boolean rollback;

    public Endpoint(String name) {
        this(name, false);
    }

    public Endpoint(String name, boolean rollback) {
        super(name);
        this.rollback = rollback;
    }

    public boolean hasRollback() {
        return rollback;
    }

    public void setRollback(boolean rollback) {
        this.rollback = rollback;
    }

    public String toString() {
        return "<Endpoint(\"" + this.getName() + "\", " + this.hasRollback() + ")>";
    }
}
