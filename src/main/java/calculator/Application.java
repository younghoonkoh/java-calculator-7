package calculator;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        System.out.println("press number.");

        try {
            String input = readInput();

            // 입력값 확인 (디버깅용)
            System.out.println("string: " + input);

            // \\n 문자열을 실제 줄바꿈 문자로 변환
            if (input.contains("\\n")) {
                input = input.replace("\\n", "\n");
            }

            int result = calculate(input);
            System.out.println("result: " + result);
        } catch (IOException e) {
            System.err.println("error.");
        } catch (IllegalArgumentException e) {
            System.err.println("wrong: " + e.getMessage());
        }
    }

    public static String readInput() throws IOException {
        StringBuilder inputBuilder = new StringBuilder();
        int charCode;

        // System.in.read()를 사용하여 한 글자씩 입력받기
        while ((charCode = System.in.read()) != -1 && charCode != '\n') {
            inputBuilder.append((char) charCode);
        }

        return inputBuilder.toString();
    }

    public static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = "[,|:]";  // 기본 구분자: , 또는 :
        String numbersPart = input;

        // 커스텀 구분자 처리
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("custom wrong.");
            }
            delimiter = input.substring(2, delimiterEndIndex); // 구분자 추출
            numbersPart = input.substring(delimiterEndIndex + 1); // 숫자 부분 추출
        }

        String[] numbers = numbersPart.split(delimiter);
        return sum(numbers);
    }

    private static int sum(String[] numbers) {
        int total = 0;
        for (String number : numbers) {
            try {
                total += Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("uninvalid.");
            }
        }
        return total;
    }
}
