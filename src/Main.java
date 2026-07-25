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
        Util.writeClassToFile("InterlacedOk", c -> Character.isJavaIdentifierPart(c) && defaultPredicate.test(c), c -> String.format("void a%sb(){}", c));
    }

    public static void writeMethodsBeginningWithInvalidCharacters() {
        Util.writeClassToFile("BeginningNotOk", c -> !Character.isJavaIdentifierStart(c) && defaultPredicate.test(c), c -> String.format("void %sa(){}", c));
    }

    public static void writeClassBeginningWithValidCharacters() {
        Util.writeClassToFile("BeginningOk", c -> Character.isJavaIdentifierStart(c) && defaultPredicate.test(c), c -> String.format("void %sa(){}", c));
    }

    public static void writeValidCharsToFile(String name, Predicate<Character> predicate) {
        Util.writeCharsToFile(String.format("%s.txt", name), "", "", predicate, String::valueOf);
    }
}