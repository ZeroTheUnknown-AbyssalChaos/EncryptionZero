package EncryptionZero.elements;

import EncryptionZero.base.SecurityConfig;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;

public class KeyPair {
    private final Key publicKey;
    private final Key privateKey;

    public KeyPair(Key publicKey, Key privateKey){
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public KeyPair(String keyGen){
        String pubKey =  SecurityConfig.INSTANCE.getSecurityKey(keyGen);
        char[] genKeyChars = keyGen.toCharArray();
        ArrayUtils.reverse(genKeyChars);
        String privKey = SecurityConfig.INSTANCE.getSecurityKey(String.valueOf(genKeyChars));
        String byteKey = byteKey(pubKey, privKey);
        this.publicKey = new Key(pubKey, byteKey);
        this.privateKey = new Key(privKey, byteKey);
    }

    private String byteKey(String pub, String priv){
        byte[] tPub = pub.getBytes();
        byte[] tPriv = priv.getBytes();
        byte[] newPriv = new byte[tPub.length];

        if (tPriv.length > tPub.length){
            System.arraycopy(tPriv, 0, newPriv, 0, newPriv.length);
        } else if (tPriv.length < tPub.length) {
            for (int i = 0; i < newPriv.length; i++){
                int k = i;
                while (k >= tPriv.length){
                    k -= tPriv.length;
                }
                newPriv[i] = tPriv[k];
            }
        } else {
            newPriv = tPriv;
        }


        byte[] key = new byte[newPriv.length];

        for (int i = 0; i < newPriv.length; i++){
            key[i] = (byte) (tPub[i] - newPriv[i]);
        }

        return new String(key, StandardCharsets.US_ASCII);

    }

    public final String publicKey(){
        return publicKey.key();
    }
    public final String privateKey(){
        return privateKey.key();
    }
    public final String genKey(){
        if (publicKey.gen().equals(privateKey.gen())) return publicKey.gen();
        else return privateKey.gen();
    }

    @Override
    public String toString() {
        return "KeyPair{" +
                "publicKey=" + publicKey +
                ", privateKey=" + privateKey +
                ", genKey=" + genKey() +
                '}';
    }
}
