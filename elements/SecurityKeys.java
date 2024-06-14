package EncryptionZero.elements;

import EncryptionZero.base.SecurityConfig;
import EncryptionZero.text.TextFormat;

import java.nio.charset.StandardCharsets;

class SecurityKeys {

    private static final SecurityConfig config = SecurityConfig.INSTANCE;
    private final KeyPair keys;

    public SecurityKeys(String genKey){
        this.keys = new KeyPair(genKey);
    }

    public final Key publicKey(){

        String collKey = config.decrypt(keys.publicKey(), keys.privateKey());

        return new Key(collKey, keys.genKey());
    }

    private String privateKey(){
        return keys.privateKey();
    }

    public final String encrypt(String data){
        return encrypt(data, publicKey());
    }
    public final String encrypt(String data, Key key){

        String pKey = key.key();
        String gKey = key.gen();
        return config.encrypt(data, bytePubKey(pKey, gKey));
    }
    public final String decrypt(String cryptData){
        String collKey = config.decrypt(keys.publicKey(), keys.privateKey());
        Key cryptKey = new Key(collKey, keys.genKey());
        return config.decrypt(cryptData, bytePubKey(cryptKey.key(), keys.genKey()));
    }
    private String bytePubKey(String key, String gen){
        byte[] tPub = key.getBytes();
        byte[] tPriv = gen.getBytes();
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


        byte[] res = new byte[newPriv.length];

        for (int i = 0; i < newPriv.length; i++){
            res[i] = (byte) (tPub[i] - newPriv[i]);
        }

        return new String(res, StandardCharsets.US_ASCII);
    }

    private String bytePrivKey(String key, String gen){
        byte[] tPub = key.getBytes();
        byte[] tPriv = gen.getBytes();
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


        byte[] res = new byte[newPriv.length];

        for (int i = 0; i < newPriv.length; i++){
            res[i] = (byte) (tPub[i] + newPriv[i]);
        }

        return new String(res, StandardCharsets.US_ASCII);
    }


    public final String sign(String data){
        String cryptKey = config.decrypt(keys.publicKey(), keys.privateKey());
        return config.encrypt(data, bytePrivKey(cryptKey, keys.genKey()));
    }

    public final String identify(String signedData){
        return identify(signedData, publicKey());
    }
    public final String identify(String signedData, Key key){
        return config.decrypt(signedData, bytePrivKey(key.key(), key.gen()));
    }

    public final void print(String message, String cMsg, String dcMsg, String sMsg, String iMsg){
        System.out.println("\r\n"+
                TextFormat.DEFAULT_COLOR + "This Message: " +
                TextFormat.FONT_GREEN + message +
                TextFormat.DEFAULT_COLOR + "\r\nThis Public Key: '" +
                TextFormat.FONT_BLUE + publicKey().key() +
                TextFormat.DEFAULT_COLOR + "', This Private Key: '" +
                TextFormat.FONT_BLUE + privateKey() +
                TextFormat.DEFAULT_COLOR + "'\r\n");

        System.out.println("Crypted Message: " + cMsg);
        System.out.println("Decrypted Message: " + dcMsg);
        System.out.println("Singed Message: " + sMsg);
        System.out.println("Identified Message: " + iMsg);
        System.out.println();
    }
    /*
    K1 = A + A + B
    K2 = A + B + B
    K3 = K1 + K2 + B = (2A + B) + (A + 2B) + B = 3A + 4B




     */

    @Override
    public String toString() {
        return "SecurityKeys{" +
                "publicKey=" + publicKey() +
                ", privateKey=" + privateKey() +
                ", keys=" + keys +
                '}';
    }
}
