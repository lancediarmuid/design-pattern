package buiderpattern;

public class McdonaldsRestaurant {

    // 不允许外部实例化该对象
    private McdonaldsRestaurant() {
    }

    private McdonaldsRestaurant(Builder builder) {
        this.yogurt = builder.yogurt;
        this.lettuce = builder.lettuce;
        this.ketchup = builder.ketchup;
        this.sugar = builder.sugar;
    }

    // 食物调料
    private String yogurt;
    private String lettuce;
    private String ketchup;
    private String sugar;

    // 建造者
    public static class Builder {
        // 食物调料
        private String yogurt;
        private String lettuce;
        private String ketchup;
        private String sugar;

        public McdonaldsRestaurant build() {
            return new McdonaldsRestaurant(this);
        }

        public Builder setYogurt(String yogurt) {
            this.yogurt = yogurt;
            return this;
        }

        public Builder setLettuce(String lettuce) {
            this.lettuce = lettuce;
            return this;
        }

        public Builder setKetchup(String ketchup) {
            this.ketchup = ketchup;
            return this;
        }

        public Builder setSugar(String sugar) {
            this.sugar = sugar;
            return this;
        }

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Yogurt: ").append(yogurt).append("\n");
        builder.append("Lettuce: ").append(lettuce).append("\n");
        builder.append("Ketchup: ").append(ketchup).append("\n");
        builder.append("Sugar: ").append(sugar).append("\n");
        return builder.toString();
    }
}