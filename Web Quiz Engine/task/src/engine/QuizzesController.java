package engine;

import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizzesController {
    private final QuizRepository repository;
    List<Quiz> quizList = new ArrayList<>();

    public QuizzesController(
            @Autowired QuizRepository repository
    ) {
        this.repository = repository;
    }

    @PostMapping("/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return repository.save(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        Optional<Quiz> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<HttpStatus> deleteQuizById(@PathVariable int id, Principal principal) {
        Quiz quiz = getQuizById(id);
        if (!quiz.getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quizzes")
    public List<Quiz> getAllQuiz() {
        return repository.findAll();
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable int id, @Nullable @RequestBody Answer answer) {
        Quiz quiz = getQuizById(id);
        if (Objects.equals(quiz.getAnswer(), answer)) {
            return new Result(true, "Congratulations, you're right!");
        }
        return new Result(false, "Wrong answer! Please, try again.");
    }
}
