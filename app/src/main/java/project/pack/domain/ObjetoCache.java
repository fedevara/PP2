package project.pack.domain;

public class ObjetoCache<T> {
    private T value;
    private long creado = System.currentTimeMillis();

    // Constructor
    public ObjetoCache(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public long getCreado(){
        return creado;
    }
}
