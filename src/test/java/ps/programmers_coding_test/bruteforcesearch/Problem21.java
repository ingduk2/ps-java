package ps.programmers_coding_test.bruteforcesearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 21 - 소수 찾기 - Level 2

풀이
- 숫자들로 가능한 조합들을 만든다
- 그 중 소수의 개수를 구한다
 */
public class Problem21 {

    static class Solution1 {
        public int solution(String numbers) {
            char[] arr = numbers.toCharArray();

            List<char[]> subsets = new ArrayList<>();
            generateSubsets(arr, new StringBuilder(), 0, subsets);

            List<char[]> permutations = new ArrayList<>();
            for (char[] subset : subsets) {
                generatePermutations(subset, new char[subset.length], new boolean[subsets.size()], 0, permutations);
            }

            Set<Integer> numberSet = new HashSet<>();
            for (char[] combination : permutations) {
                int num = Integer.parseInt(new String(combination));
                numberSet.add(num);
            }

            int result = 0;
            for (int num : numberSet) {
                if (isPrime(num)) {
                    result++;
                }
            }

            return result;
        }

        private void generateSubsets(char[] arr, StringBuilder temp, int start, List<char[]> subsets) {
            if (!(temp.length() == 0)) {
                subsets.add(temp.toString().toCharArray());
            }

            for (int i = start; i < arr.length; i++) {
                temp.append(arr[i]);
                generateSubsets(arr, temp, i + 1, subsets);;
                temp.deleteCharAt(temp.length() - 1);
            }
        }

        private void generatePermutations(char[] subset, char[] temp, boolean[] used, int depth, List<char[]> result) {
            if (depth == subset.length) {
                result.add(Arrays.copyOf(temp, temp.length));
                return;
            }

            for (int i = 0; i < subset.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    temp[depth] = subset[i];
                    generatePermutations(subset, temp, used, depth + 1, result);
                    used[i] = false;
                }
            }
        }

        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) return false;
            }

            return true;
        }
    }

    static class Solution2 {
        public int solution(String numbers) {
            Set<Integer> primes = new HashSet<>();
            int[] nums = numbers.chars().map(c -> c - '0').toArray();
            getPrimes(0, nums, new boolean[numbers.length()], primes);
            return primes.size();
        }

        private void getPrimes(int acc, int[] numbers, boolean[] isUsed, Set<Integer> primes) {
            if (isPrime(acc)) primes.add(acc);

            for (int i = 0; i < numbers.length; i++) {
                if (isUsed[i]) continue;
                int nextAcc = acc * 10 + numbers[i];

                isUsed[i] = true;
                getPrimes(nextAcc, numbers, isUsed, primes);
                isUsed[i] = false;
            }
        }

        private boolean isPrime(int num) {
            if (num <= 1) return false;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) return false;
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("17", 3),
                Arguments.of("011", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String numbers, int expected) {
        assertThat(new Solution1().solution(numbers)).isEqualTo(expected);
        assertThat(new Solution2().solution(numbers)).isEqualTo(expected);
    }
}
