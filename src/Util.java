import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {
    private Util(){}



    public static void writeClassToFile(String name, Predicate<Character> predicate, Function<Character, String> methodName) {
        Util.writeCharsToFile(String.format("%s.java", name), String.format("public class %s {\n", name), "}", predicate, c -> methodName.apply(c)+"\n");
    }

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
