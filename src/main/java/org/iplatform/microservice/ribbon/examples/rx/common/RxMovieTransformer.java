/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.iplatform.microservice.ribbon.examples.rx.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.reactivex.netty.channel.ContentTransformer;

import java.nio.charset.Charset;

/**
 * @author Tomasz Bak
 */
public class RxMovieTransformer implements ContentTransformer<Movie> {
    @Override
    public ByteBuf call(Movie movie, ByteBufAllocator byteBufAllocator) {
        byte[] bytes = movie.toString().getBytes(Charset.defaultCharset());
        ByteBuf byteBuf = byteBufAllocator.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
