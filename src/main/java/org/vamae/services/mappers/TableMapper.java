package org.vamae.services.mappers;

import org.vamae.models.dto.TableDto;
import org.vamae.services.Table;
import org.vamae.services.states.*;

public class TableMapper {
    public static TableDto toDto(Table table) {
        return TableDto.builder()
                .settings(table.getSettings())
                .players(PlayerMapper.toDto(table.getPlayers()))
                .deck(table.getDeck())
                .currentBet(table.getCurrentBet())
                .pot(table.getPot())
                .cards(table.getCards())
                .state(table.getState().toString())
                .currentPlayerIndex(table.getCurrentPlayerIndex())
                .lastPlayerIndex(table.getState().getLastPlayerIndex())
                .build();
    }

    public static Table toTable(TableDto table) {
        Table result = Table.builder()
                .settings(table.settings())
                .deck(table.deck())
                .currentBet(table.currentBet())
                .pot(table.pot())
                .cards(table.cards())
                .currentPlayerIndex(table.currentPlayerIndex())
                .build();

        result.setPlayers(PlayerMapper.toPlayer(table.players(), result));

        GameState state = fromString(table.state(), result);
        state.setLastPlayerIndex(table.lastPlayerIndex());
        result.setState(state);

        return result;
    }

    private static GameState fromString(String state, Table table) {
        return switch (state) {
            case "PreFlop" -> new PreFlopState(table);
            case "Flop" -> new FlopState(table);
            case "Turn" -> new TurnState(table);
            case "River" -> new RiverState(table);
            case "Showdown" -> new ShowdownState(table);
            default -> new WaitingState(table);
        };
    }
}
