package local.john;

import java.util.function.Consumer;

public abstract class TypeMatchable {

    public static class TypeMatcher {

        TypeMatchable reference;

        public TypeMatcher(TypeMatchable reference) {
            this.reference = reference;
        }

        public <T extends TypeMatchable> ThenDoer<T> ifMatch(Class<T> clazz) {
            boolean isMatch = clazz.equals(reference.getClass());
            return new ThenDoer<>((T) reference, isMatch, this);
        }
    }

    public static class ThenDoer<T extends TypeMatchable> {
        private final T reference;
        private final boolean isMatch;
        private final TypeMatcher parent;

        public ThenDoer(T reference, boolean isMatch, TypeMatcher parent) {
            this.isMatch = isMatch;
            this.reference = reference;
            this.parent = parent;
        }

        public TypeMatcher thenDo(Consumer<T> task) {
            if (this.isMatch) {
                task.accept(reference);
            }
            return parent;
        }
    }

    public TypeMatcher typeMatcher() {
        return new TypeMatcher(this);
    }
}
