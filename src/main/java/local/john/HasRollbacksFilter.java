package local.john;

import java.util.ArrayList;
import java.util.List;

public class HasRollbacksFilter {

    public static void main(String[] args) {
        Group g1 = new Group("group1");
        Group g2 = new Group("group2");
        Group g3 = new Group("group3");
        Group g4 = new Group("group4");
        Group g5 = new Group("group5");

        Endpoint e1 = new Endpoint("ep1", true);
        Endpoint e2 = new Endpoint("ep2");
        Endpoint e3 = new Endpoint("ep3");
        Endpoint e4 = new Endpoint("ep4");
        Endpoint e5 = new Endpoint("ep5");
        Endpoint e6 = new Endpoint("ep6");
        Endpoint e7 = new Endpoint("ep7");
        Endpoint e8 = new Endpoint("ep8", true);
        Endpoint e9 = new Endpoint("ep9", true);

        List<ISavedTreeItem> roots = new ArrayList<>();
        g2.getChildren().add(e2);
        g1.getChildren().add(e1);
        g1.getChildren().add(g2);
        roots.add(g1);

        g3.getChildren().add(e3);
        g3.getChildren().add(e4);
        g3.getChildren().add(e5);
        roots.add(g3);

        g4.getChildren().add(e6);
        g5.getChildren().add(e7);
        g5.getChildren().add(e8);
        g4.getChildren().add(g5);
        roots.add(g4);

        roots.add(e9);
    }
}
