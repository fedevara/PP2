package project.pack.domain;

public class ObjetoCache<T> {
    private T value;
    private long Creado;
    // Constructor
    public ObjetoCache(T value) {
        this.value = value;
        this.Creado = System.currentTimeMillis();
    }

    public T getValue() {
        this.Creado = System.currentTimeMillis();
        return value;
    }

    public long getCreado() {
        return Creado;
    }
}
