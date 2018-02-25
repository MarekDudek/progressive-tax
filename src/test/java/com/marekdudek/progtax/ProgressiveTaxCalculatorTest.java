package com.marekdudek.progtax;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.marekdudek.progtax.ProgressiveTaxCalculator.CALCULATOR;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class ProgressiveTaxCalculatorTest
{
    private final static Bracket BRACKET_1 = Bracket.builder().
            amount(new BigDecimal(2_000)).
            percentage(new BigDecimal(0)).build();
    private final static Bracket BRACKET_2 = Bracket.builder().
            amount(new BigDecimal(5_000)).
            percentage(new BigDecimal("0.1")).build();
    private final static Bracket BRACKET_3 = Bracket.builder().
            amount(new BigDecimal(10_000)).
            percentage(new BigDecimal("0.2")).build();
    private final static Bracket BRACKET_4 = Bracket.builder().
            amount(new BigDecimal(20_000)).
            percentage(new BigDecimal("0.3")).build();
    private final static Bracket BRACKET_5 = Bracket.builder().
            amount(new BigDecimal(30_000)).
            percentage(new BigDecimal("0.5")).build();
    private final static Bracket BRACKET_6 = Bracket.builder().
            amount(new BigDecimal(Integer.MAX_VALUE)).
            percentage(new BigDecimal("0.8")).build();

    private final static List<Bracket> BRACKETS =
            newArrayList(BRACKET_1, BRACKET_2, BRACKET_3, BRACKET_4, BRACKET_5, BRACKET_6);

    private final static BigDecimal EPSILON = new BigDecimal("0.001");

    @Test
    public void tax_for_negative_amount_is_zero()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(-10), BRACKETS);
        // then
        assertThat(tax, closeTo(ZERO, EPSILON));
    }

    @Test
    public void tax_for_zero_amount_is_zero()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(0), BRACKETS);
        // then
        assertThat(tax, closeTo(ZERO, EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_first_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(1_111), BRACKETS);
        // then
        assertThat(tax, closeTo(ZERO, EPSILON));
    }

    @Test
    public void tax_for_amount_at_first_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(2_000), BRACKETS);
        // then
        assertThat(tax, closeTo(ZERO, EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_second_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(3_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(100), EPSILON));
    }

    @Test
    public void tax_for_amount_at_the_end_of_second_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(5_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(300), EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_third_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(8_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(900), EPSILON));
    }

    @Test
    public void tax_for_amount_at_the_end_of_third_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(10_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(1_300), EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_fourth_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(12_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(1_900), EPSILON));
    }

    @Test
    public void tax_for_amount_at_the_end_of_fourth_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(20_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(4_300), EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_fifth_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(24_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(6_300), EPSILON));
    }

    @Test
    public void tax_for_amount_at_the_end_of_fifth_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(30_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(9_300), EPSILON));
    }

    @Test
    public void tax_for_amount_in_middle_of_last_bracket_is_accurate()
    {
        // when
        final BigDecimal tax = CALCULATOR.calculateTax(new BigDecimal(34_000), BRACKETS);
        // then
        assertThat(tax, closeTo(new BigDecimal(12_500), EPSILON));
    }
}
