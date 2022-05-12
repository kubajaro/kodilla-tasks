package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    TrelloClient trelloClient;

    @Mock
    RestTemplate restTemplate;

    @Mock
    TrelloUrl trelloUrl;

    @Test
    void shouldFetchTrelloBoards() throws URISyntaxException {
        //given
        when(trelloUrl.getTrelloUrl()).thenReturn(new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all"));

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());

        when(restTemplate.getForObject(trelloUrl.getTrelloUrl(), TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> fetchTrelloBoard = trelloClient.getTrelloBoards();

        //then
        assertEquals(1, fetchTrelloBoard.size());
        assertEquals("test_id", fetchTrelloBoard.get(0).getId());
        assertEquals("Kodilla", fetchTrelloBoard.get(0).getName());
        assertEquals(new ArrayList<>(), fetchTrelloBoard.get(0).getLists());
    }

    @Test
    void shouldCreateCard() throws URISyntaxException {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        when(trelloUrl.postTrelloUrl(trelloCardDto)).thenReturn(new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id"));

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(trelloUrl.postTrelloUrl(trelloCardDto), null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //when
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //then
        assertEquals("1", newCard.getId());
        assertEquals("test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

}