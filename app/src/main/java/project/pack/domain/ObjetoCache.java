package project.pack.domain;

public class ObjetoCache<T> {
    private T value;

    // Constructor
    public ObjetoCache(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
