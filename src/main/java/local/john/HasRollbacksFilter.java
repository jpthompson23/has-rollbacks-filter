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

        printAllNodes(roots);
        // System.out.println();
        // List<ISavedTreeItem> filtered = filterForHasRollbacks(roots);
        // System.out.println(filtered);
    }

    private static void printAllNodes(List<ISavedTreeItem> roots) {
        printAllNodes(roots, "");
    }

    private static void printAllNodes(List<ISavedTreeItem> nodes, String indent) {
        for (ISavedTreeItem node : nodes) {
            node.typeMatcher()
                .ifMatch(Group.class)
                    .thenDo(
                        (Group group) -> {
                            System.out.println(indent + group.toString());
                            printAllNodes(group.getChildren(), indent + "\t");
                        })
                .ifMatch(Endpoint.class)
                    .thenDo(
                        (Endpoint endpoint) -> {
                            System.out.println(indent + endpoint.toString());
                        });
        }
    }

    private static List<ISavedTreeItem> filterForHasRollbacks(List<ISavedTreeItem> nodes) {
        List<ISavedTreeItem> res = new ArrayList<>();
        for (ISavedTreeItem node : nodes) {
            filterForHasRollbacks(node, res);
        }
        return res;
    }

    private static boolean filterForHasRollbacks(ISavedTreeItem node, List<ISavedTreeItem> results) {
        if (node instanceof Endpoint) {
            Endpoint eNode = (Endpoint) node;
            if (eNode.hasRollback()) {
                results.add(eNode);
                return true;
            } else {
                return false;
            }
        } else if (node instanceof Group) {
            Group gNode = (Group) node;
            boolean anyRollbacks = false;
            List<ISavedTreeItem> childResults = new ArrayList<>();
            for (ISavedTreeItem child : gNode.getChildren()) {
                if (filterForHasRollbacks(child, childResults)) {
                    anyRollbacks = true;
                }
            }
            if (anyRollbacks) {
                results.add(gNode);
            }
            results.addAll(childResults);
            return anyRollbacks;
        } else {
            throw new RuntimeException("Node is neither Group nor Endpoint.");
        }
    }
}
