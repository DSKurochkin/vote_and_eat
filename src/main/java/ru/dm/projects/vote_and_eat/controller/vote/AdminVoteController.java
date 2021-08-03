package ru.dm.projects.vote_and_eat.controller.vote;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        return repository.getAll();
    }

    @GetMapping("/between")
    public List<Vote> getBetween(@Nullable @RequestParam LocalDate start,
                                 @Nullable @RequestParam LocalDate end) {
        Map<String, LocalDate> dateMap = VoteUtil.getExternal(start, end);
        return repository.getBetween(dateMap.get("start"), dateMap.get("end"));
    }

    public List<Vote> getByUser(String email) {
        return repository.getByUser(email);
    }

}
