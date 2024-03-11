package org.vamae.services.mappers;

import org.vamae.models.dto.PlayerDto;
import org.vamae.models.Player;
import org.vamae.services.Table;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {
    public static PlayerDto toDto(Player player) {
        return PlayerDto.builder()
                .id(player.getId())
                .hand(CardMapper.toDto(player.getHand()))
                .pokerHand(PokerHandMapper.toDto(player.getPokerHand()))
                .chips(player.getChips())
                .currentBet(player.getCurrentBet())
                .build();
    }

    public static Player toPlayer(PlayerDto player, Table table) {
        return Player.builder()
                .id(player.id())
                .table(table)
                .hand(CardMapper.toCard(player.hand()))
                .pokerHand(PokerHandMapper.toPokerHand(player.pokerHand()))
                .chips(player.chips())
                .currentBet(player.currentBet())
                .build();
    }

    public static List<PlayerDto> toDto(List<Player> players) {
        return players.stream()
                .map(PlayerMapper::toDto)
                .toList();
    }

    public static List<Player> toPlayer(List<PlayerDto> players, Table table) {
        return players.stream()
                .map(player -> toPlayer(player, table))
                .collect(Collectors.toList());
    }
}
