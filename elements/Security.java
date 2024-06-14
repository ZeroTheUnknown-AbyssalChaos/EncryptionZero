package EncryptionZero.elements;

public final class Security extends SecurityKeys {
    public Security(String genKey) {
        super(genKey);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("SecurityKeys", "Security");
    }

}
