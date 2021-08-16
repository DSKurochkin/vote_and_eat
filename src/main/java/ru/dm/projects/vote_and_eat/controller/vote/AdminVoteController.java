package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.Vote;
import ru.dm.projects.vote_and_eat.util.VoteUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.vote.AbstractVoteController.VOTE_URL;

@RestController
@RequestMapping(value = ADMIN_URL + VOTE_URL)
public class AdminVoteController extends AbstractVoteController {

    @GetMapping
    public List<Vote> getAll() {
        return service.getAll();
    }

    @GetMapping("/filter")
    public List<Vote> getBetween(@Nullable @RequestParam LocalDate start,
                                 @Nullable @RequestParam LocalDate end) {
        Map<String, LocalDate> dateMap = VoteUtil.getExternal(start, end);
        return service.getBetween(start, end);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Long id) throws Exception {
        return service.get(id);
    }

    @GetMapping("/by")
    public List<Vote> getByUser(@RequestParam String email) {
        return service.getByUsersEmail(email);
    }

}
