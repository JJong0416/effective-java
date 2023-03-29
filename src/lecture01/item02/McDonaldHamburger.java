package lecture01.item02;

import java.util.Objects;

public class McDonaldHamburger extends Hamburger {

    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Hamburger.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }


        @Override
        Hamburger build() {
            return new McDonaldHamburger(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public McDonaldHamburger(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
