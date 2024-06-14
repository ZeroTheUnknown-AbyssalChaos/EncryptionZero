package EncryptionZero.text;

public interface Color {
    String
            COLOR_RESET = "\033[0m",
            FONT_BLACK = "\033[30m",
            FONT_RED = "\033[31m",
            FONT_GREEN = "\033[32m",
            FONT_GOLD = "\033[33m",
            FONT_BLUE = "\033[34m",
            FONT_PINK = "\033[35m",
            FONT_CYAN = "\033[36m",
            FONT_GRAY = "\033[37m",
            FONT_WHITE = "\033[38m",
            FONT_UNKNOWN = "\033[39m",
            BACK_BLACK = "\033[40m",
            BACK_RED = "\033[41m",
            BACK_GREEN = "\033[42m",
            BACK_GOLD = "\033[43m",
            BACK_BLUE = "\033[44m",
            BACK_PINK = "\033[45m",
            BACK_CYAN = "\033[46m",
            BACK_GRAY = "\033[47m",
            BACK_WHITE = "\033[48m",
            BACK_UNKNOWN = "\033[49m";

    String DEFAULT_COLOR = COLOR_RESET;
    String DEFAULT_FONT = FONT_WHITE;
    String DEFAULT_BACK = BACK_WHITE;
}
