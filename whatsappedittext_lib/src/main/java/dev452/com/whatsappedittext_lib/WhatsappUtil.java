package dev452.com.whatsappedittext_lib;

import java.util.ArrayList;

/**
 * Created by Debojyoti Singha on 09,January,2019.
 */
public class WhatsappUtil {
    public static class Flag {

        int start; int end; char flag;

        public Flag(int start, int end, char flag) {
            this.start = start;
            this.end = end;
            this.flag = flag;
        }
    }

    public static final char NEW_LINE = '\n';
    public static final char SPACE = ' ';
    public static final char BOLD_FLAG = '*';
    public static final char STRIKE_FLAG = '~';
    public static final char ITALIC_FLAG = '_';


    public static final int INVALID_INDEX = -1;


    public static boolean isFlagged(CharSequence text, int index) {
        if(index > INVALID_INDEX && index < text.length()) {
            char c = text.charAt(index);
            return c == SPACE || c == NEW_LINE || c == BOLD_FLAG || c == ITALIC_FLAG || c == STRIKE_FLAG;
        }
        return true;
    }


    public static String getText(ArrayList<Character> characters) {
        char[] chars = new char[characters.size()];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = characters.get(i);
        }
        return new String(chars);
    }


    public static boolean hasFlagSameLine(CharSequence sequence, char flag, int fromIndex) {

        for(int i=fromIndex;i<sequence.length();i++) {
            char c = sequence.charAt(i);
            if(c == NEW_LINE) {
                return false;
            }

            if(c == flag ) {
                return i != fromIndex;
            }
        }

        return false;
    }
}
