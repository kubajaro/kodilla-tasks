package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/trello")
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoardDtos = trelloClient.getTrelloBoards().stream()
                .filter(o -> o.getId()!=null)
                .filter(o -> o.getName().toLowerCase().contains("kodilla"))
                .collect(Collectors.toList());

        return ResponseEntity.ok(trelloBoardDtos);
    }

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard> createdTrelloCards(@RequestBody TrelloCardDto trelloCardDto) {
        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));
    }
}
