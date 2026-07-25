import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static final Predicate<Character> defaultPredicate = c -> !(Character.isAlphabetic(c) || Character.getType(c) == Character.NON_SPACING_MARK || Character.getType(c) == Character.COMBINING_SPACING_MARK || Character.getType(c) == Character.ENCLOSING_MARK || Character.getType(c) == Character.FORMAT || Character.getType(c) == Character.CONTROL);

    public static void main(String[] args) {
        writeMethodsBeginningWithInvalidCharacters();
        writeClassInterlacedWithValidCharacters();
        writeClassBeginningWithValidCharacters();
        writeValidCharsToFile("valid_part", c -> Character.isJavaIdentifierPart(c) && defaultPredicate.test(c));
        writeValidCharsToFile("valid_start", c -> Character.isJavaIdentifierStart(c) && defaultPredicate.test(c));
    }


    public static void writeClassInterlacedWithValidCharacters() {
        writeClassToFile("InterlacedOk", c -> Character.isJavaIdentifierPart(c) && defaultPredicate.test(c), c -> String.format("void a%sb(){}", c));
    }

    public static void writeMethodsBeginningWithInvalidCharacters() {
        writeClassToFile("BeginningNotOk", c -> !Character.isJavaIdentifierStart(c) && defaultPredicate.test(c), c -> String.format("void %sa(){}", c));
    }

    public static void writeClassBeginningWithValidCharacters() {
        writeClassToFile("BeginningOk", c -> Character.isJavaIdentifierStart(c) && defaultPredicate.test(c), c -> String.format("void %sa(){}", c));
    }

    public static void writeValidCharsToFile(String name, Predicate<Character> predicate) {
        writeCharsToFile(String.format("%s.txt", name), "", "", predicate, String::valueOf);
    }

    public static void writeClassToFile(String name, Predicate<Character> predicate, Function<Character, String> methodName) {
        writeCharsToFile(String.format("%s.java", name), String.format("public class %s {\n", name), "}", predicate, c -> methodName.apply(c)+"\n");
    }


    //HELPERS

    public static void writeCharsToFile(String fileName, String beginning, String end, Predicate<Character> predicate, Function<Character, String> line) {
        StringBuilder sb = new StringBuilder().append(beginning);

        for (int ch = Character.MIN_CODE_POINT; ch <= Character.MAX_CODE_POINT; ch++) {
            char[] chars = Character.toChars(ch);
            for(char c : chars)
            {
                if(predicate.test(c)) sb.append(line.apply(c));
            }
        }

        sb.append(end);
        recklessFileWrite(fileName, sb.toString());
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