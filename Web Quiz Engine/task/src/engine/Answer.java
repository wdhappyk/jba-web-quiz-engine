package engine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Answer {
    private Set<Integer> answer;

    public Answer() {
        this(null);
    }

    public Answer(Set<Integer> answer) {
        this.answer = answer == null ? new HashSet<>() : answer;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null && answer.size() == 0) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(this.answer, answer.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }
}
