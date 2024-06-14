package EncryptionZero.base;


import org.apache.commons.lang3.ArrayUtils;

public final class SecurityForm {

    //#region codeTables
    private static final char[] codeTable = new char[]{
            'U','N','I','V','E','R','S','A','L','B','C','D','F','G','H','J','K','M','O','P','Q','T','W','X','Y','Z'
    };
    private static final char[] codeBigTable = new char[]{
            'U','N','I','V','E','R','S','A','L','B','C','D','F','G','H','J','K','M','O','P','Q','T','W','X','Y','Z',
            'u','n','i','v','e','r','s','a','l','b','c','d','f','g','h','j','k','m','o','p','q','t','w','x','y','z'
    };
    private static final char[] codeAdvancedTable = new char[]{
            'U','N','I','V','E','R','S','A','L','B','C','D','F','G','H','J','K','M','O','P','Q','T','W','X','Y','Z',
            'u','n','i','v','e','r','s','a','l','b','c','d','f','g','h','j','k','m','o','p','q','t','w','x','y','z',
            '#',',','.','!','?','+','*','/','(',')','{','}','[',']','=','<','>','|','$','@','€','%','&','~','§',
            ';',':','_','0','1','2','3','4','5','6','7','8','9','\"','\\',' ','\''//,'-'
    };
    //#endregion

    //#region getIndexes & getChars for codeTables
    private static int getIndex(char c){
        for (int i = 0; i < codeTable.length; i++){
            if (codeTable[i] == Character.toUpperCase(c)) return i;
        }
        return -1;
    }
    private static int getBigIndex(char c){
        for (int i = 0; i < codeBigTable.length; i++){
            if (codeBigTable[i] == c) return i;
        }
        return -1;
    }
    private static int getAdvancedIndex(char c){
        for (int i = 0; i < codeAdvancedTable.length; i++){
            if (codeAdvancedTable[i] == c) return i;
        }
        return -1;
    }
    private static char getChar(int index){
        if (index < 0) return ' ';
        while (index >= codeTable.length) index -= codeTable.length;
        return codeTable[index];
    }
    private static char getBigChar(int index){
        if (index < 0) return ' ';
        while (index >= codeBigTable.length) index -= codeBigTable.length;
        return codeBigTable[index];
    }
    public static char getAdvancedChar(int index){
        if(index < 0) return ' ';
        while (index >= codeAdvancedTable.length) index -= codeAdvancedTable.length;
        return codeAdvancedTable[index];
    }
    //#endregion

    //#region assistant methods & assistant enum
    private static char[] fillCodeCharsFullLength(char[] codeChars, int length){
        try {
            char[] result = new char[length];

            if (codeChars.length == result.length) return codeChars;
            if (codeChars.length > result.length) {
                System.arraycopy(codeChars, 0, result, 0, result.length);
            } else {
                for (int i = 0, j = 0; i < result.length && j < codeChars.length; i++) {
                    result[i] = codeChars[j];
                    j++;
                    if (j >= codeChars.length) j -= codeChars.length;
                }
            }
            return result;
        } catch (Exception e){
            return null;
        }
    }
    private enum Form {
        Default, Big, Advanced
    }

    private static String encode(char[] message, char[] codeWord,Form form){
        char[] result = new char[message.length];
        switch (form){
            case Default:{
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getIndex(message[i]);
                    if (indexMessageChars != -1){
                        result[i] = getChar(indexMessageChars + getIndex(codeWord[i]));
                    } else {
                        result[i] = getChar(indexMessageChars);
                    }
                }
                break;
            }
            case Big:{
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getBigIndex(message[i]);
                    if (indexMessageChars != -1){
                        result[i] = getBigChar(indexMessageChars + getBigIndex(codeWord[i]));
                    } else {
                        result[i] = getBigChar(indexMessageChars);
                    }
                }
                break;
            }
            case Advanced:{
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getAdvancedIndex(message[i]);
                    if (indexMessageChars != -1){
                        result[i] = getAdvancedChar(indexMessageChars + getAdvancedIndex(codeWord[i]));
                    } else {
                        result[i] = getAdvancedChar(indexMessageChars);
                    }
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result);

        return sb.toString();
    }
    private static String decode(char[] message, char[] codeWord,Form form){
        char[] result = new char[message.length];
        switch (form){
            case Default: {
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getIndex(message[i]);
                    if (indexMessageChars != -1){
                        indexMessageChars -= getIndex(codeWord[i]);
                        if (indexMessageChars < 0) indexMessageChars += codeTable.length;
                        result[i] = getChar(indexMessageChars);
                    } else {
                        result[i] = getChar(indexMessageChars);
                    }
                }
                break;
            }
            case Big:{
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getBigIndex(message[i]);
                    if (indexMessageChars != -1){
                        indexMessageChars -= getBigIndex(codeWord[i]);
                        if (indexMessageChars < 0) indexMessageChars += codeBigTable.length;
                        result[i] = getBigChar(indexMessageChars);
                    } else {
                        result[i] = getBigChar(indexMessageChars);
                    }
                }
                break;
            }
            case Advanced:{
                for (int i = 0; i < result.length; i++){
                    int indexMessageChars = getAdvancedIndex(message[i]);
                    if (indexMessageChars != -1){
                        indexMessageChars -= getAdvancedIndex(codeWord[i]);
                        if (indexMessageChars < 0) indexMessageChars += codeAdvancedTable.length;
                        result[i] = getAdvancedChar(indexMessageChars);
                    } else {
                        result[i] = getAdvancedChar(indexMessageChars);
                    }
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result);

        return sb.toString();
    }
    //#endregion

    //#region encoder & decoder

    //#region default encoder / decoder
    public static String encode(String message, String codeword){
        char[] messageChars = message.toUpperCase().toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeword.toUpperCase().toCharArray(), messageChars.length);
        return encode(messageChars, codeWordChars, Form.Default);
    }
    public static String decode(String message, String codeword){
        char[] messageChars = message.toUpperCase().toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeword.toUpperCase().toCharArray(), messageChars.length);
        return decode(messageChars, codeWordChars, Form.Default);
    }
    //#endregion

    //#region big encoder / decoder
    public static String bigEncode(String message, String codeword){
        char[] messageChars = message.toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeword.toCharArray(), messageChars.length);
        return encode(messageChars, codeWordChars, Form.Big);
    }
    public static String bigDecode(String message, String codeword){
        char[] messageChars = message.toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeword.toCharArray(), messageChars.length);
        return decode(messageChars, codeWordChars, Form.Big);
    }
    //#endregion

    //#region advanced encoder / decoder
    public static String advancedEncode(String message, String codeWord){
        char[] messageChars = message.toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeWord.toCharArray(), messageChars.length);
        return encode(messageChars, codeWordChars, Form.Advanced);
    }
    public static String advancedDecode(String message, String codeWord){
        char[] messageChars = message.toCharArray();
        char[] codeWordChars = fillCodeCharsFullLength(codeWord.toCharArray(), messageChars.length);
        return decode(messageChars, codeWordChars, Form.Advanced);
    }
    //#endregion
    //#endregion

    //#region table chars

    public static char[] getCodeTable(){
        char[] clone = codeTable.clone();
        ArrayUtils.shuffle(clone);
        return clone;
    }
    public static char[] getCodeBigTable(){
        char[] clone = codeBigTable.clone();
        ArrayUtils.shuffle(clone);
        return clone;
    }
    public static char[] getCodeAdvancedTable(){
        char[] clone = codeAdvancedTable.clone();
        ArrayUtils.shuffle(clone);
        return clone;
    }

    //#endregion




}
