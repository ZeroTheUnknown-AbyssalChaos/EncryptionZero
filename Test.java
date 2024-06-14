package EncryptionZero;

import EncryptionZero.base.SecurityConfig;
import EncryptionZero.elements.Security;
import EncryptionZero.text.TextFormat;

public class Test implements TextFormat{
    static final SecurityConfig config = SecurityConfig.INSTANCE;

    static void giveOptions(){
        Test test = new Test();

        System.out.println(FORMAT_RESET);

        test.printLine('#');
        System.out.println("# 1: static methods of 'giveExample' + 'useExample'");
        System.out.println("# 2: alle public "+ FONT_PINK +"config"+DEFAULT_COLOR+" methoden und felder, die für SecuirityKey ebenfalls genutzt wurden / nutzbar waren");
        System.out.println("# 3: alle public felder und methoden die SecurityKeys zur verfügung bietet");
        System.out.println("# 4: "+ FONT_PINK +"Security"+DEFAULT_COLOR+" nutzt ["+ FONT_PINK +"KeyPair"+DEFAULT_COLOR+
                ", "+ FONT_PINK +"Key"+DEFAULT_COLOR+
                "], "+ FONT_PINK +"KeyPair"+DEFAULT_COLOR+" nutzt ebenfalls ["+ FONT_PINK +"Key"+DEFAULT_COLOR+"]");
        System.out.println("# 5: Alle Methoden die public sind in den genutzten Klassen, sind frei zur verfügung." +
                "\r\n#\t\tprivate Methoden sind exklusive, da über UI klassen gearbeitet werden kann, " +
                "um absoluten zugriff zu verweigern auf private methoden und felder,\r\n#\t\twie auch einsicht der " +
                "objecte die es nicht möglich machen sollen");

        test.printLine( '#');
    }

    static void giveExample(){
        Test test = new Test();
        System.out.println("=== EXAMPLE ===");
        test.printLine('+');
        System.out.println("\r\n" + FONT_BLUE + "static void useExample() {\r\n" +
                        "\tSystem.out.println(\"\\/ EXAMPLE \\/\");" + "\r\n" +
                        "\tTest test = new Test();" + "\r\n" + "\r\n" +

                        "\tSecurity keysA = new Security(\"HelloWorld\");" + "\r\n" +
                        "\tSecurity keysB = new Security(\"Monarch\");" + "\r\n" + "\r\n" +

                        "\ttest.printLine();" + "\r\n" + "\r\n" +

                        "\tSystem.out.println(BLUE+\"B sended an A\"+DEFAULT_COLOR);" +  "\r\n" + "\r\n" +

                        "\tString messageA = \"Willi kommt am Sonntag\";" + "\r\n" +
                        "\tString cMsgA = keysB.encrypt(messageA, keysA.publicKey());" + "\r\n" +
                        "\tString dcMsgA = keysA.decrypt(cMsgA);" + "\r\n" +
                        "\tString sMsgA = keysA.sign(messageA);" + "\r\n" +
                        "\tString iMsgA = keysB.identify(sMsgA, keysA.publicKey());" + "\r\n" +
                        "\tkeysA.print(messageA, cMsgA, dcMsgA, sMsgA, iMsgA);" + "\r\n" + "\r\n" +

                        "\ttest.printLine();" + "\r\n" +
                        "\tSystem.out.println(BLUE+\"A sended an A\"+DEFAULT_COLOR);" + "\r\n" + "\r\n" +

                        "\tString cMsgA2 = keysA.encrypt(messageA, keysA.publicKey());" + "\r\n" +
                        "\tString dcMsgA2 = keysA.decrypt(cMsgA2);" + "\r\n" +
                        "\tkeysA.print(messageA, cMsgA2, dcMsgA2, sMsgA, iMsgA);" + "\r\n" + "\r\n" +

                        "\ttest.printLine();" + "\r\n" +
                        "\tSystem.out.println(BLUE+\"A sended an B\"+DEFAULT_COLOR);" + "\r\n" + "\r\n" +

                        "\tString messageB = \"Ich komme am Sonntag auch\";" + "\r\n" +
                        "\tString cMsgB = keysA.encrypt(messageA, keysB.publicKey());" + "\r\n" +
                        "\tString dcMsgB = keysB.decrypt(cMsgA);" + "\r\n" +
                        "\tString sMsgB = keysB.sign(messageA);" + "\r\n" +
                        "\tString iMsgB = keysA.identify(sMsgA, keysA.publicKey());" + "\r\n" +
                        "\tkeysB.print(messageB, cMsgB, dcMsgB, sMsgB, iMsgB);" + "\r\n" + "\r\n" +

                        "\ttest.printLine(200);" + "\r\n" +
                        "\tSystem.out.println(\"Decrypting attempt with Public key\");" + "\r\n" +
                        "\tString cM = config.decrypt(cMsgA, keysA.publicKey().key());" + "\r\n" +
                        "\tSystem.out.println(GOLD + cM + DEFAULT_COLOR);" + "\r\n}" + DEFAULT_COLOR
        );

        test.printLine('+');
    }

    static void useExample(){
        System.out.println("\\/ EXAMPLE \\/");
        Test test = new Test();

        Security keysA = new Security("HelloWorld");
        Security keysB = new Security("Monarch");
        test.printLine();
        System.out.println(FONT_BLUE +"B sended an A"+DEFAULT_COLOR);

        String messageA = "Willi kommt am Sonntag";
        String cMsgA = keysB.encrypt(messageA, keysA.publicKey());
        String dcMsgA = keysA.decrypt(cMsgA);
        String sMsgA = keysA.sign(messageA);
        String iMsgA = keysB.identify(sMsgA, keysA.publicKey());
        keysA.print(messageA, cMsgA, dcMsgA, sMsgA, iMsgA);
        System.out.println("Receiver :: Sender");
        keysB.print(messageA, cMsgA, dcMsgA, sMsgA, iMsgA);

        test.printLine();
        System.out.println(FONT_BLUE +"A sended an A"+DEFAULT_COLOR);

        String cMsgA2 = keysA.encrypt(messageA, keysA.publicKey());
        String dcMsgA2 = keysA.decrypt(cMsgA2);
        keysA.print(messageA, cMsgA2, dcMsgA2, sMsgA, iMsgA);
        System.out.println("Sender and Receiver :: Neither Sender or Receiver");
        keysB.print(messageA, cMsgA2, dcMsgA2, sMsgA, iMsgA);

        test.printLine();
        System.out.println(FONT_BLUE +"A sended an B"+DEFAULT_COLOR);

        String messageB = "Ich komme am Sonntag auch";
        String cMsgB = keysA.encrypt(messageA, keysB.publicKey());
        String dcMsgB = keysB.decrypt(cMsgA);
        String sMsgB = keysB.sign(messageA);
        String iMsgB = keysA.identify(sMsgA, keysA.publicKey());
        keysA.print(messageB, cMsgB, dcMsgB, sMsgB, iMsgB);
        System.out.println("Sender :: Receiver");
        keysB.print(messageB, cMsgB, dcMsgB, sMsgB, iMsgB);

        test.printLine(200);
        System.out.println("Decrypting attempt with Public key");
        String cM = config.decrypt(cMsgA, keysA.publicKey().key());
        System.out.println(FONT_GOLD + cM + DEFAULT_COLOR);
    }

    public static void main(String[] args) {
        giveOptions();
        giveExample();
        useExample();
    }
}
