package programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

public class 베스트_앨범 {

    public int[] solution(String[] genres, int[] plays) {
        Map<Genre, List<Song>> songs = init(genres, plays);
        sort(songs);

        List<Genre> sortedGenres = getSortedGenres(songs);
        List<Integer> result = grouping(songs, sortedGenres);
        return convertResult(result);
    }

    private Map<Genre, List<Song>> init(String[] reqGenres, int[] reqPlays) {
        Map<Genre, List<Song>> result = new HashMap<>();
        Map<String, Genre> genres = new HashMap<>();
        for (int number = 0; number < reqGenres.length; number++) {
            String genreName = reqGenres[number];
            Genre genre = genres.computeIfAbsent(genreName, (key) -> new Genre(genreName));
            genre.sumPlays(reqPlays[number]);
            List<Song> songs = result.computeIfAbsent(genre, (key) -> new ArrayList<>());
            songs.add(new Song(number, reqPlays[number]));
        }
        return result;
    }

    private void sort(Map<Genre, List<Song>> songs) {
        songs.forEach((key, value) -> value.sort((o1, o2) -> o1.plays == o2.plays ? o1.number - o2.number : o2.plays - o1.plays));
    }

    private List<Genre> getSortedGenres(Map<Genre, List<Song>> songs) {
        return songs.keySet()
                .stream()
                .sorted((o1, o2) -> o2.plays - o1.plays)
                .collect(Collectors.toList());
    }

    private List<Integer> grouping(Map<Genre, List<Song>> songs, List<Genre> sortedGenres) {
        List<Integer> result = new ArrayList<>();
        for (Genre genre : sortedGenres) {
            result.addAll(songs.get(genre)
                    .stream()
                    .map(s -> s.number).limit(2)
                    .collect(Collectors.toList()));
        }
        return result;
    }

    private int[] convertResult(List<Integer> result) {
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    class Genre {

        String name;
        int plays;

        public Genre(String name) {
            this.name = name;
            this.plays = 0;
        }

        public void sumPlays(int plays) {
            this.plays += plays;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Genre genre = (Genre) o;
            return Objects.equals(name, genre.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    class Song {
        int number;
        int plays;

        public Song(int number, int plays) {
            this.number = number;
            this.plays = plays;
        }
    }
}
