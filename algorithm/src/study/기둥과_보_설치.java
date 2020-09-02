package study;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 1. 기둥(0)
 * 1-1. 바닥이면 tru
 * 1-2. 아래에 기둥이 있으면 true
 * 1-3. 아래 보가 있으면 true (해당 위치 or 왼쪽 아래)
 * 2. 보(1)
 * 2-1. 아래 기둥이 있으면 true
 * 2-2. 오른쪽 아래 기둥이 있으면 true
 * 2-3. 양쪽에 보가 있으면 true
 */
public class 기둥과_보_설치 {

    public int[][] solution(int n, int[][] build_frame) {
        Set<Frame> frames = new LinkedHashSet<>();
        for (int[] buildFrame : build_frame) {
            Frame frame = new Frame(buildFrame[0], buildFrame[1], buildFrame[2]);
            if (buildFrame[3] == 1) {
                if (isBuildable(frames, frame)) frames.add(frame);
            } else {
                if (isDeletable(frames, frame)) frames.remove(frame);
            }
        }
        return sort(frames);
    }

    private boolean isBuildable(Set<Frame> frames, Frame f) {
        if (f.a == 0) {
            if (f.y == 0) return true;
            if (frames.contains(new Frame(f.x, f.y - 1, 0))) return true;
            if (frames.contains(new Frame(f.x, f.y, 1))) return true;
            return frames.contains(new Frame(f.x - 1, f.y, 1));
        } else {
            if (frames.contains(new Frame(f.x, f.y - 1, 0))) return true;
            if (frames.contains(new Frame(f.x + 1, f.y - 1, 0))) return true;
            return frames.contains(new Frame(f.x - 1, f.y, 1)) &&
                    frames.contains(new Frame(f.x + 1, f.y, 1));
        }
    }

    private boolean isDeletable(Set<Frame> frames, Frame f) {
        Set<Frame> copyFrames = new LinkedHashSet<>(frames);
        copyFrames.remove(f);
        for (Frame frame : copyFrames) {
            if (!isBuildable(copyFrames, frame)) return false;
        }
        return true;
    }

    private int[][] sort(Set<Frame> frames) {
        List<int[]> result = frames.stream()
                .sorted((o1, o2) -> {
                    if (o1.x != o2.x) return o1.x - o2.x;
                    else if (o1.y != o2.y) return o1.y - o2.y;
                    if (o1.a == 0) return -1;
                    if (o2.a == 0) return 1;
                    return 0;
                })
                .map(f -> new int[]{f.x, f.y, f.a})
                .collect(Collectors.toList());

        int[][] convertedResult = new int[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            convertedResult[i] = result.get(i);
        }
        return convertedResult;
    }
}

class Frame {
    int x;
    int y;
    int a;

    public Frame(int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return x == frame.x && y == frame.y && a == frame.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, a);
    }
}
