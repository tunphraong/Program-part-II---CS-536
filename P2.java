import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the C-- scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex

        if (Integer.valueOf(args[0]) == 0) {
            // run all tests
            testAllTokens();
            CharNum.num = 1;
            testIntLiteral("test_int_literal.in", "test_int_literal.out"); 
            CharNum.num = 1;
            testID("test_id.in", "test_id.out"); // test identifier
            CharNum.num = 1;
            testStringLiteral("test_string_lit.in", "test_string_lit.out");
            CharNum.num = 1;
        }
        else if (Integer.valueOf(args[0]) == 1) {
            testAllTokens();
            CharNum.num = 1;
        } else if (Integer.valueOf(args[0]) == 2) {
            testIntLiteral("test_int_literal.in", "test_int_literal.out"); 
            CharNum.num = 1;
        } else if (Integer.valueOf(args[0]) == 3) {
            testID("test_id.in", "test_id.out"); // test identifier
            CharNum.num = 1;
        } else if (Integer.valueOf(args[0]) == 4) {
            testStringLiteral("test_string_lit.in", "test_string_lit.out");
            CharNum.num = 1;
        }
    
        // ADD CALLS TO OTHER TEST METHODS HERE
    }

    private static void toKenInfo(String type, String val, Symbol token){
        int line = ((TokenVal)token.value).linenum;
        int tokenCharnum = ((TokenVal)token.value).charnum;
        System.out.print("line: " + line +"   char num: " + tokenCharnum+ "     "  + type);
        System.out.println("  content: "+ val);
    }

    private static void testLine(String input, String output) throws IOException {

    }

    private static void testID(String input, String output) throws IOException {
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader(input);
            outFile = new PrintWriter(new FileWriter(output));
        } catch (FileNotFoundException ex) {
            System.err.println(input +" not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println(output + " cannot be opened.");
            System.exit(-1);
        }
        
        Yylex scanner = new Yylex(inFile); // create scanner
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            if(token.sym == sym.ID){
                String id = ((IdTokenVal)token.value).idVal;
                toKenInfo("Identifier", id, token);
                outFile.println(id);
            }
            token = scanner.next_token();
            // outFile.print("\n");
        }
        outFile.close();
    }

    private static void testIntLiteral(String input, String output) throws IOException {
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader(input);
            // System.out.println("get here");
            outFile = new PrintWriter(new FileWriter(output));
        } catch (FileNotFoundException ex) {
            System.err.println(input + " not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println(output + " cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            if (token.sym == sym.INTLITERAL) {
                int val = ((IntLitTokenVal)token.value).intVal;
                outFile.println(((IntLitTokenVal)token.value).intVal);
                toKenInfo("Integer Literal", Integer.toString(val),token);
            }

            token = scanner.next_token();
        }

        outFile.close();
    }
    /**
     * [testStringLiteral description]
     * @param  fin         [input file]
     * @param  fout        [output file]
     * @throws IOException [in/out]
     */
    private static void testStringLiteral(String input, String output) throws IOException {
        
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {

        inFile = new FileReader(input);
        outFile = new PrintWriter(new FileWriter(output));
        } catch (FileNotFoundException ex) {
            System.err.println(input + " not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println(output + " cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            if(token.sym == sym.STRINGLITERAL) {
                String strVal = ((StrLitTokenVal)token.value).strVal;
                toKenInfo("String Literal", strVal, token);
                outFile.println(strVal);
            }
            token = scanner.next_token();
        }
        outFile.close();
    }


    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    

    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            // System.out.println("get here");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            // System.out.println("what is token: " + token.sym);
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
            case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;              
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                //outFile.print("SYM ID"); // I put this in
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:
                //outFile.print("SYM INT LITEREAL"); // I put this in
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                //outFile.print("SYM STRING LITEREAL"); // I put this in
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                // System.out.println("get here");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;              
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;  
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
            case sym.ASSIGN:
                outFile.println("=");
                break;
            default:
                outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
