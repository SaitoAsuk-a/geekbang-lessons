/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geekbang.thinking.in.spring.aop.overview;

import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 默认 {@link EchoService} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@Configuration // @Configuration 需要 @ComponentScan -> ConfigurationClassPostProcessor
// CGLIB 代理对象
public class DefaultEchoService implements EchoService {
    private static Map<Integer, Integer> passCountMap = new HashMap<>();

    @Override
    public String echo(String message) {
        passCountMap.put(1, null);
        passCountMap.put(1, getHashMapValueOrDefault(passCountMap, 1, 0) + 1);
        System.out.println("passCountMap = " + passCountMap);
        passCountMap.remove(1);
        passCountMap.put(1, getHashMapValueOrDefault(passCountMap, 1, 0) + 1);
        System.out.println("passCountMap = " + passCountMap);
        return "[ECHO] " + message;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> integers = new PriorityQueue<>(100, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });


    }

    public <K, V> V getHashMapValueOrDefault(Map<K, V> hashMap, K key, V defaultValue) {
        V v = hashMap.get(key);
        return v == null ? defaultValue : v;
    }
}
