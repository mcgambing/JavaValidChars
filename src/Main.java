import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        writeBestestClassEver();
    }

    public static void writeChars() {
        StringBuilder startChars = new StringBuilder().append("Starting characters:");
        StringBuilder lettersOrDigits = new StringBuilder().append("Numbers & letters:");
        for (int ch = Character.MIN_CODE_POINT; ch <= Character.MAX_CODE_POINT; ch++) {
            char[] chars = Character.toChars(ch);
            for(char c : chars)
            {
                if(Character.isJavaIdentifierStart(c)) startChars.append(c);
                if(Character.isJavaLetterOrDigit(c)) lettersOrDigits.append(c);
            }
        }
        try (PrintWriter out = new PrintWriter("chars.txt")) {
            out.println(startChars.append("\n").append(lettersOrDigits));
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void writeBestestClassEver() {
        StringBuilder sb = new StringBuilder().append("public class Main {\n");
        for (int ch = Character.MIN_CODE_POINT; ch <= Character.MAX_CODE_POINT; ch++) {
            char[] chars = Character.toChars(ch);
            for(char c : chars)
            {
                if(Character.isJavaLetterOrDigit(c)) sb.append(String.format("        fabric%sknows%severything(String args[]){}",c,c)).append("\n");
            }
        }
        sb.append("}");
        try (PrintWriter out = new PrintWriter("Main.java")) {
            out.println(sb);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void writeBestClassEver() {
        StringBuilder sb = new StringBuilder().append("public class Main {\n");
        for (int ch = Character.MIN_CODE_POINT; ch <= Character.MAX_CODE_POINT; ch++) {
            char[] chars = Character.toChars(ch);
            for(char c : chars)
            {
                if(Character.isJavaIdentifierStart(c)) sb.append(String.format("        %smethod(String args[]){}",c)).append("\n");
            }
        }
        sb.append("}");
        try (PrintWriter out = new PrintWriter("Main.java")) {
            out.println(sb);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}