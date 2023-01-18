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
package dev.triumphteam.cmd.core.processor;

import dev.triumphteam.cmd.core.extention.CommandExtensions;
import dev.triumphteam.cmd.core.extention.annotation.ProcessorTarget;
import dev.triumphteam.cmd.core.extention.meta.CommandMeta;
import dev.triumphteam.cmd.core.extention.meta.MetaKey;
import dev.triumphteam.cmd.core.extention.registry.RegistryContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Abstracts most of the "extracting" from sub command annotations, allows for extending.
 * <br/>
 * I know this could be done better, but couldn't think of a better way.
 * If you do please PR or let me know on my discord!
 *
 * @param <S> The sender type.
 */
@SuppressWarnings("unchecked")
public final class ParentCommandProcessor<S> extends AbstractCommandProcessor<S> {

    private final Class<?> klass;

    ParentCommandProcessor(
            final @NotNull Object invocationInstance,
            final @NotNull Class<?> klass,
            final @NotNull RegistryContainer<S> registryContainer,
            final @NotNull CommandExtensions<?, S> commandExtensions,
            final @NotNull CommandMeta parentMeta
    ) {
        super(invocationInstance, klass, registryContainer, commandExtensions, parentMeta);

        this.klass = klass;
    }

    @Override
    public @NotNull CommandMeta createMeta() {
        final CommandMeta.Builder meta = new CommandMeta.Builder(getParentMeta());

        // Defaults
        meta.add(MetaKey.NAME, getName());
        meta.add(MetaKey.DESCRIPTION, getDescription());

        // Process all the class annotations
        processAnnotations(getCommandExtensions(), klass, ProcessorTarget.PARENT_COMMAND, meta);
        processCommandMeta(getCommandExtensions(), klass, ProcessorTarget.PARENT_COMMAND, meta);
        // Return modified meta
        return meta.build();
    }
}