package programmers.greedy;

import java.util.ArrayList;
import java.util.List;

public class 체육복 {

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        List<Student> students = new ArrayList<>();
        //학생 생성
        for (int i = 0; i < n; i++) {
            students.add(new Student());
        }

        //잃어버린 학생 체크
        for (int lostStudentIndex : lost) {
            students.get(lostStudentIndex - 1).lost();
        }

        //여유분 있는 학생 체크
        for (int reserveStudentIndex : reserve) {
            students.get(reserveStudentIndex - 1).extra();
        }

        //체육 수업 들을 수 있는 학생 카운터
        for (int i = 0; i < n; i++) {
            Student student = students.get(i);
            int left = i - 1;
            int right = i + 1;
            if (student.extra) {
                if (i == 0 && !students.get(right).clothes) {
                    students.get(right).give();
                } else if (i == n - 1 && !students.get(left).clothes) {
                    students.get(left).give();
                    answer++;
                } else if (i > 0 && i < n - 1) {
                    if (!students.get(left).clothes) {
                        students.get(left).give();
                        answer++;
                    } else if (!students.get(right).clothes) {
                        students.get(right).give();
                    }
                }
            }

            if (student.clothes) {
                answer++;
            }
        }

        return answer;
    }

    class Student {
        boolean clothes = true;
        boolean extra = false;

        public void lost() {
            this.clothes = false;
        }

        public void extra() {
            if (!this.clothes) {
                this.clothes = true;
                return;
            }
            this.extra = true;
        }

        public void give() {
            this.clothes = true;
        }
    }
}