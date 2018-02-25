package com.marekdudek.progtax;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@ToString
final class Bracket
{
    public final BigDecimal percentage;
    public final BigDecimal amount;
}
