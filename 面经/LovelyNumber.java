Lovely Number 

public class Main {

    public static void main(String[] args) {
        System.out.println(countLovelyNumber(9,19));
    }
    private static int countLovelyNumber(int n, int m) {
        return partition(m) - partition(n) + 1;
    }


    private static int partition(int number) {
        char[] arr = Integer.toString(number).toCharArray();
        int result = count(arr.length - 1, 0) + 1;
        // uncertain
        for (int i = 1; i <= arr.length - 1; i++) {
            if (arr[i - 1] - '0' == 0) {
                continue;
            }
            result += (arr[i - 1] - 1 - '0') * count(arr.length - i,i);
        }
        // process the last digit
        int lastChoice = arr[arr.length - 1] - '0' + 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (lastChoice == 0) {
                break;
            }
            if (arr[i] <= arr[arr.length - 1]) {
                lastChoice--;
            }
        }
        result += lastChoice;
        return result;
    }

    // 1000 - 6999 call 6 * count(3,1)
    private static int count(int digits, int digitsUsed) {
        int result = 1;
        for (int i = 0; i < digits; i++) {
            result *= (10 - digitsUsed - i);
        }
        return result;
    }
}
