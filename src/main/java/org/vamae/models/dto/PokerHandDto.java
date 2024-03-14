package org.vamae.models.dto;

import org.vamae.models.enums.Combination;

import java.util.List;

public record PokerHandDto(Combination combination, List<CardDto> cards) {
}
