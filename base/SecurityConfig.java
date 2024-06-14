package EncryptionZero.base;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public final class SecurityConfig {

    public static final SecurityConfig INSTANCE = new SecurityConfig();

    private SecurityConfig(){
        for (int i = 0; i < codeKeys.length; i++){
            for (int j = 0; j < codeKeys[i].length; j++){
                codeKeys[i][j] = RandomStringUtils.random(
                        new Random().nextInt(32), SecurityForm.getCodeAdvancedTable()
                );
            }
        }
    }

    public final String[][] codeKeys = new String[20][10];


    public String getSecurityKey(String key){
        if (key == null || key.isEmpty()) return null;

        int primIdx = key.length() / 10;
        int secIdx = key.length() % 10;

        String insecureKey = SecurityForm.advancedEncode(key, codeKeys[primIdx][secIdx]);
        int triIdx = primIdx % 10;
        return SecurityForm.advancedEncode(insecureKey, codeKeys[secIdx][triIdx]);
    }
    public String encrypt(String key, String codeValue){
        return SecurityForm.advancedEncode(key, codeValue);
    }
    public String decrypt(String cryptKey, String codeValue){
        return SecurityForm.advancedDecode(cryptKey, codeValue);
    }
}
