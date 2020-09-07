package factorypattern.dependencycircle.commonobject;

public class A {
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    private B b;
}
