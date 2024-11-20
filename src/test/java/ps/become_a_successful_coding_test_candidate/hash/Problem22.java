package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 22 - 베스트 앨범
- 장르 별로 두 개씩
- 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
- 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
- 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
- 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

풀이
- 장르의 재생 총합으로 역순 정렬
- 장르 내에서 play 역순으로 2개
- 장르 내에서 재생 횟수가 같으면, 고유 번호가 낮은 노래를 먼저

 */
public class Problem22 {

    static class Solution1 {
        public static final class Album {
            private final int number;
            private final int play;

            public Album(int number, int play) {
                this.number = number;
                this.play = play;
            }

            public int number() {
                return number;
            }

            public int play() {
                return play;
            }
        }

        public int[] solution(String[] genres, int[] plays) {
            Map<String, Integer> genreMap = new HashMap<>();

            Map<String, List<Album>> albums = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                String genre = genres[i];
                int play = plays[i];
                albums.computeIfAbsent(genre, key -> new ArrayList<>()).add(new Album(i, play));
                genreMap.put(genre, genreMap.getOrDefault(genre, 0) + plays[i]);
            }

            Stream<Map.Entry<String, Integer>> sortedGenreMap = genreMap.entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

            List<Integer> result = new ArrayList<>();
            sortedGenreMap.forEach(
                    entry -> {
                        String genre = entry.getKey();
                        Stream<Album> sortedAlbumsStream = albums.get(genre).stream()
                                .sorted(Comparator.comparing(Album::play).reversed()
                                        .thenComparing(Album::number))
                                .limit(2);

                        sortedAlbumsStream.forEach(album -> result.add(album.number));
                    }
            );

            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"classic", "pop", "classic", "classic", "pop"},
                        new int[]{500, 600, 150, 800, 2500},
                        new int[]{4, 1, 3, 0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] genres, int[] plays, int[] expected) {
        assertThat(new Problem22.Solution1().solution(genres, plays)).isEqualTo(expected);
    }
}
