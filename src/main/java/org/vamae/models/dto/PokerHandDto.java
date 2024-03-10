package org.vamae.models.dto;

import java.util.List;

public record PokerHandDto(String combination, List<CardDto> cards) {
}
