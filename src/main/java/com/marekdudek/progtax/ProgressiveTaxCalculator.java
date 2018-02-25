package com.marekdudek.progtax;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static com.marekdudek.progtax.BigDecimalComparison.smallerOrEqual;
import static java.math.BigDecimal.ZERO;

class ProgressiveTaxCalculator
{
    static final ProgressiveTaxCalculator CALCULATOR = new ProgressiveTaxCalculator();

    BigDecimal calculateTax(final BigDecimal amount, final List<Bracket> brackets)
    {
        return calculateTax(amount, brackets.iterator(), ZERO, ZERO);
    }

    private BigDecimal calculateTax(final BigDecimal amount, final Iterator<Bracket> brackets, final BigDecimal taxSoFar, final BigDecimal previousAmount)
    {
        if (smallerOrEqual(amount, previousAmount))
            return taxSoFar;

        final Bracket current = brackets.next();

        final BigDecimal currentAmount = amount.min(current.amount);
        final BigDecimal amountToTax = currentAmount.subtract(previousAmount);

        final BigDecimal tax = current.percentage.multiply(amountToTax);

        return calculateTax(amount, brackets, taxSoFar.add(tax), current.amount);
    }
}
