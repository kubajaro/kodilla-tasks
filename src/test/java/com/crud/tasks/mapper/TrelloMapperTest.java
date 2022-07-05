package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void mapToBoard() {
        //given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Name", trelloListDtoList);

        //when
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);

        //then
        assertEquals(trelloBoardDto.getName(), trelloBoard.getName());

    }

    @Test
    void mapToList() {
        //given
        List<TrelloListDto> trelloListDtoList = List.of(new TrelloListDto("1", "name", false),
                new TrelloListDto("2", "name2", false),
                new TrelloListDto("3", "name3", false));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //then
        assertEquals(3, trelloLists.size());
    }

    @Test
    void mapToListDto() {
        //given
        List<TrelloList> trelloListList = List.of(new TrelloList("1", "name", false),
                new TrelloList("2", "name2", false),
                new TrelloList("3", "name3", false));

        //when
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //then
        assertEquals(3, trelloListDtoList.size());
    }

    @Test
    void mapToBoards() {
        //given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("1", "name", trelloListDtoList),
                new TrelloBoardDto("2", "name2", trelloListDtoList));

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    void mapToBoardsDto() {
        //given
        List<TrelloList> trelloListList = new ArrayList<>();
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "name", trelloListList),
                new TrelloBoard("2", "name2", trelloListList));

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //then
        assertEquals(2, trelloBoardDtos.size());
    }

    @Test
    void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}