package ru.yolkin.autoparts.model.entity.autodoc;

import lombok.Builder;

@Builder
public record Mark(
    double avgMark,
    int cntMark
) {

}
