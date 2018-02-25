package com.marekdudek.progtax;

import java.math.BigDecimal;

final class BigDecimalComparison
{
    static boolean smallerOrEqual(final BigDecimal a, final BigDecimal b)
    {
        return a.compareTo(b) <= 0;
    }
}
