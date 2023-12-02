import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CubeConundrum {
    private static final Integer RED_DICE_IN_BAG = 12;
    private static final Integer GREEN_DICE_IN_BAG = 13;
    private static final Integer BLUE_DICE_IN_BAG = 14;

    public static void main(String[] args) throws Exception {
        Boolean isTest = false;
        Boolean firstPartOfChallenge = false;

        List<String> inputStrings = getInputString(isTest);

        if (firstPartOfChallenge) {
            // PART 1
            List<Integer> idsOfValidGames = getValidGameIds(inputStrings);
            Integer sumOfValidGamesIds = getSumOfInts(idsOfValidGames);
            System.out.println("Total sum of valid games' IDs is " + sumOfValidGamesIds);
        } else {
            // PART 2
            List<Integer> powersOfMinCubeSet = getPowersOfCubesetsPerGame(inputStrings);
            Integer sumOfPowers = getSumOfInts(powersOfMinCubeSet);
            System.out.println("Total sum of powers of cubes is " + sumOfPowers);
        }
    }

    private static List<String> getInputString(Boolean isTest) {
        String filePath = isTest == true ? "testInput.txt" : "input.txt";

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

    private static List<Integer> getValidGameIds(List<String> inputStrings) {
        List<Integer> validGameIds = new ArrayList<>();

        for (String s : inputStrings) {
            Integer gameId = 0;
            Integer numOfDiceDrawn = 0;
            Boolean isValidGame = true;

            List<String> cubesInGame = Arrays.asList(s.split("[:,;]"));
            for (String string : cubesInGame) {
                if (string.contains("Game")) {
                    gameId = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                } else if (string.contains("red")) {
                    numOfDiceDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (numOfDiceDrawn > RED_DICE_IN_BAG) isValidGame = false;
                } else if (string.contains("green")) {
                    numOfDiceDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (numOfDiceDrawn > GREEN_DICE_IN_BAG) isValidGame = false;
                } else if (string.contains("blue")) {
                    numOfDiceDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (numOfDiceDrawn > BLUE_DICE_IN_BAG) isValidGame = false;
                }
            }
            if (isValidGame)
                validGameIds.add(gameId);
        }

        return validGameIds;
    }

    private static List<Integer> getPowersOfCubesetsPerGame(List<String> inputStrings) {
        List<Integer> powersOfMinCubeSet = new ArrayList<>();

        for (String s : inputStrings) {
            Integer gameId = 0;
            Integer minRedCubes = 0;
            Integer minGreenCubes = 0;
            Integer minBlueCubes = 0;

            List<String> cubesInGame = Arrays.asList(s.split("[:,;]"));
            for (String string : cubesInGame) {
                Integer cubesDrawn = 0;
                if (string.contains("Game")) {
                    gameId = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                } else if (string.contains("red")) {
                    cubesDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (cubesDrawn > minRedCubes) minRedCubes = cubesDrawn;
                } else if (string.contains("green")) {
                    cubesDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (cubesDrawn > minGreenCubes) minGreenCubes = cubesDrawn;
                } else if (string.contains("blue")) {
                    cubesDrawn = Integer.parseInt(string.replaceAll("[a-zA-Z]", "").trim());
                    if (cubesDrawn > minBlueCubes) minBlueCubes = cubesDrawn;
                }
            }

            powersOfMinCubeSet.add(minRedCubes * minGreenCubes * minBlueCubes);
        }

        return powersOfMinCubeSet;
    }

    private static Integer getSumOfInts(List<Integer> intsToSum) {
        Integer totalSum = 0;

        for (Integer i : intsToSum) {
            totalSum += i;
        }

        return totalSum;
    }
}
