package buiderpattern;

public class BuilderRunner {
    // 建造者模式测试
    public static void main(String[] args) {
        McdonaldsRestaurant.Builder builder = new McdonaldsRestaurant.Builder();

        McdonaldsRestaurant restaurant = builder
                .setYogurt("酸奶+1")
                .setLettuce("生菜+1")
                .setKetchup("番茄酱+1")
                .setSugar("白糖+1")
                .build();

        System.out.println(restaurant.toString());
    }

}