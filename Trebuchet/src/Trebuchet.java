import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trebuchet {
    public static void main(String[] args) {
        List<String> inputStrings = getInputString();
        List<Integer> numsFromStringList = extractFirstAndLast(inputStrings);
        Integer sumOfAllIntegers = sumOfInts(numsFromStringList);

        System.out.println("The sum is " + sumOfAllIntegers);
    }

    private static List<String> getInputString() {
        String filePath = "input.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> inputStrings = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                inputStrings.add(line);
            }

            bufferedReader.close();
            fileReader.close();

            return inputStrings;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read input file: " + e.getMessage());
        }
    }

    private static List<Integer> extractFirstAndLast(List<String> inputStrings) {
        List<Integer> numsFromStringList = new ArrayList<>();
        List<String> replacedWordsList = new ArrayList<>();
        Map<String, String> wordToNumberMap = Map.ofEntries(
                Map.entry("one", "o1e"),
                Map.entry("two", "t2o"),
                Map.entry("three", "t3e"),
                Map.entry("four", "f4r"),
                Map.entry("five", "f5e"),
                Map.entry("six", "s6x"),
                Map.entry("seven", "s7n"),
                Map.entry("eight", "e8t"),
                Map.entry("nine", "n9e"),
                Map.entry("zero", "z0o"));

        for (String s : inputStrings) {
            for (Map.Entry<String, String> entry : wordToNumberMap.entrySet()) {
                s = s.replaceAll(entry.getKey(), entry.getValue());
            }
            replacedWordsList.add(s);
        }

        for (String s : replacedWordsList) {
            String strNums = s.replaceAll("[a-zA-Z]", "");
            String firstAndLastNumString;
            char firstNum = strNums.charAt(0);
            char lastNum = strNums.charAt(strNums.length() - 1);
            firstAndLastNumString = String.valueOf(firstNum) + String.valueOf(lastNum);

            numsFromStringList.add(Integer.parseInt(firstAndLastNumString));
        }

        return numsFromStringList;
    }

    private static Integer sumOfInts(List<Integer> numsFromStringList) {
        Integer sum = 0;

        for (Integer num : numsFromStringList) {
            sum += num;
        }

        return sum;
    }
}