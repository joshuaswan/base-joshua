package com.joshua.base.config.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import static com.google.common.base.CharMatcher.*;

/**
 * Created by joshua on 3/29/17.
 */
public class Size {
    public static enum Unit{
        KB(1024 * 8, "K", "KILOBYTE", "KILOBYTES"),
        MB(1024 * KB.bits, "M", "MEGABYTE", "MEGABYTES"),
        GB(1024 * MB.bits, "G", "GIGABYTE", "GIGABYTES");

        private final long bits;
        private final ImmutableSet<String> representation;

        private Unit(long bits,String... representation){
            this.bits = bits;
            this.representation = ImmutableSet.copyOf(representation);
        }

        private static Optional<Unit> parse(final String name){
            return Iterables.tryFind(ImmutableSet.copyOf(Unit.values()), new Predicate<Unit>() {
                @Override
                public boolean apply(@Nullable Unit input) {
                    return input.toString().equals(name) || input.representation.contains(name);
                }
            });
        }
    }

    private final int quantity;
    @NotNull
    private final Unit unit;

    public Size(int quantity,Unit unit){
        this.quantity = quantity;
        this.unit = unit;
    }

    public long value() {
        return (long) (quantity * unit.bits);
    }

    @JsonCreator
    public static Size valueOf(String text) {
        String trimmed = WHITESPACE.removeFrom(text).trim();
        return new Size(quantity(trimmed), unit(trimmed));
    }

    private static int quantity(String trimmed){
        try {
            return Integer.parseInt(JAVA_LETTER.trimTrailingFrom(trimmed));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid size format");
        }
    }

    private static Unit unit(String trimmed){
        Optional<Unit> unit = Unit.parse(DIGIT.trimLeadingFrom(trimmed).toUpperCase());
        Preconditions.checkArgument(unit.isPresent(), "invalid size format");
        return unit.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size = (Size) o;

        if (quantity != size.quantity) return false;
        return unit == size.unit;

    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    @JsonValue
    public String toString() {
        return "Size{" +
                "quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }
}
