/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.database.repository;


import java.util.List;

public interface Repository<T, ID> {

    T save(T entity);

    T update(T entity);

    T findOne(ID var1);

    boolean exists(ID var1);

    List<T> findAll();

    List<T> findAll(List<ID> var1);

    void delete(ID var1);

    void delete(Iterable<ID> var1);

    void deleteAll();

}
