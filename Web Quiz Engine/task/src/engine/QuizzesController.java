package engine;

import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizzesController {
    private final QuizRepository quizRepository;
    private final QuizDecisionRepository quizDecisionRepository;
    private final UserRepository userRepository;

    public QuizzesController(
            @Autowired QuizRepository quizRepository,
            @Autowired QuizDecisionRepository quizDecisionRepository,
            @Autowired UserRepository userRepository
    ) {
        this.quizRepository = quizRepository;
        this.quizDecisionRepository = quizDecisionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, Principal principal) {
        final User currentUser = userRepository.findByUsername(principal.getName());
        quiz.setUser(currentUser);
        return quizRepository.save(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        Optional<Quiz> result = quizRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<HttpStatus> deleteQuizById(@PathVariable int id, Principal principal) {
        Quiz quiz = getQuizById(id);
        if (!quiz.getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        quizRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quizzes")
    public Page<Quiz> getAllQuiz(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        return getQuizzesPage(page);
    }

    @GetMapping("/quizzes/completed")
    public Page<QuizDecision> getCompletedQuizzes(@RequestParam(name = "page", defaultValue = "0") Integer page, Principal principal) {
        final User currentUser = userRepository.findByUsername(principal.getName());
        return getQuizDecisionsPage(currentUser, page);
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable int id, @Nullable @RequestBody Answer answer, Principal principal) {
        Quiz quiz = getQuizById(id);
        if (Objects.equals(quiz.getAnswer(), answer)) {
            final User currentUser = userRepository.findByUsername(principal.getName());
            QuizDecision res = new QuizDecision(new Date(), quiz, currentUser);
            quizDecisionRepository.save(res);
            return new Result(true, "Congratulations, you're right!");
        }
        return new Result(false, "Wrong answer! Please, try again.");
    }

    private Page<Quiz> getQuizzesPage(int page) {
        Pageable paging = PageRequest.of(page, 10);
        return quizRepository.findAll(paging);
    }

    private Page<QuizDecision> getQuizDecisionsPage(User user, int page) {
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return quizDecisionRepository.finalAllForUser(user, paging);
    }
}
