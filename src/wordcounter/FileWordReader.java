package wordcounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Elton Vinh and Thien Van
 * CS 146 Data Structures and Algorithms
 * Fall 2015
 * Department of Computer Science
 * San Jose State University 
 *
 * FileWordReader reads words from a file one-by-one, converting to lowercase
 * and eliminating punctuation.  You can read a file in using:
 * 
 * /**
 * @author Ron Mak edited by Elton Vinh and Thien Van
 *
 */
class FileWordReader {
    StreamTokenizer tok;

    public FileWordReader(String file) throws FileNotFoundException,
            IOException {
        tok = new StreamTokenizer(new BufferedReader(new InputStreamReader(
                new FileInputStream(file))));
        tok.eolIsSignificant(false);
        tok.lowerCaseMode(true);
        tok.wordChars('a', 'z');
        tok.wordChars('A', 'Z');
        String ws = " \t.,!;:-[].,;!?*+-=\\|/(){}\"[]><'";
        for (int i = 0; i < ws.length(); i++) {
            tok.whitespaceChars(ws.charAt(i), ws.charAt(i));
        }
    }

    public String nextWord() throws IOException {
        int ttype = tok.nextToken();
        while (ttype != StreamTokenizer.TT_WORD
                && ttype != StreamTokenizer.TT_EOF)
            ttype = tok.nextToken();
        if (ttype == StreamTokenizer.TT_EOF)
            return null;
        return tok.sval;
    }
};
