import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        writeMethodsBeginningWithInvalidCharacters("ClassesMayNotBeginWithThisIdentifier");
    }




    public static void writeClassInterlacedWithValidCharacters() {
        writeClassToFile("InterlacedOk", Character::isJavaLetterOrDigit, c -> String.format("void a%sb(){}", c));
    }

    public static void writeMethodsBeginningWithInvalidCharacters() {
        writeClassToFile("BeginningNotOk", c -> !Character.isJavaIdentifierStart(c), c -> String.format("void %sa(){}", c));
    }

    public static void writeClassBeginningWithValidCharacters() {
        writeClassToFile("BeginningOk", Character::isJavaIdentifierStart, c -> String.format("void %sa(){}", c));
    }

    public static void writeClassToFile(String name, Predicate<Character> predicate, Function<Character, String> methodName) {
        StringBuilder sb = new StringBuilder().append(String.format("\"public class %s {\\n\"", name));
        for (int ch = Character.MIN_CODE_POINT; ch <= Character.MAX_CODE_POINT; ch++) {
            char[] chars = Character.toChars(ch);
            for(char c : chars)
            {
                if(predicate.test(c)) sb.append(methodName.apply(c)).append("\n");
            }
        }
        sb.append("}");
        recklessFileWrite(String.format("%s.java",name), sb.toString());
    }

    public static void writeValidCharsToFile() {
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

        recklessFileWrite("validchars.txt", startChars.append(lettersOrDigits).toString());
    }

    public static void recklessFileWrite(String name, String contents) {
        try (PrintWriter out = new PrintWriter(name)) {
            out.println(contents);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

}