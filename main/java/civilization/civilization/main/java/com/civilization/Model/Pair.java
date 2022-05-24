package civilization.civilization.main.java.com.civilization.Model;

public class Pair<P, Q> {
    private P key;
    private Q value;

    public Pair(P e1, Q e2) {
        this.key = e1;
        this.value = e2;
    }

    public P getKey() {
        return key;
    }

    public void setKey(P key) {
        this.key = key;
    }

    public Q getValue() {
        return value;
    }

    public void setValue(Q value) {
        this.value = value;
    }

}

