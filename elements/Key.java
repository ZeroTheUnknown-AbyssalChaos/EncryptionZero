package EncryptionZero.elements;

public record Key(String key, String gen) {

    public Key {
        assert (key != null && gen != null);
    }

    @Override
    public String toString() {
        return "Key{" +
                "key='" + key + '\'' +
                ", gen='" + gen + '\'' +
                '}';
    }
}
