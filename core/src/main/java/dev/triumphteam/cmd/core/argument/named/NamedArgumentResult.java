/**
 * MIT License
 *
 * Copyright (c) 2019-2021 Matt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.cmd.core.argument.named;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class NamedArgumentResult implements Arguments {

    private final Map<String, Object> values;

    public NamedArgumentResult(@NotNull final Map<String, Object> values) {
        this.values = values;
    }

    @NotNull
    @Override
    public <T> Optional<T> get(final @NotNull String name, final @NotNull Class<T> type) {
        return (Optional<T>) Optional.ofNullable(values.get(name));
    }

    @Override
    public @NotNull <T> Optional<List<T>> getAsList(final @NotNull String name, final @NotNull Class<T> type) {
        final List<T> value = (List<T>) values.get(name);
        return Optional.ofNullable(value);
    }

    @Override
    public @NotNull <T> Optional<Set<T>> getAsSet(final @NotNull String name, final @NotNull Class<T> type) {
        final Set<T> value = (Set<T>) values.get(name);
        return Optional.ofNullable(value);
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "values=" + values +
                '}';
    }
}