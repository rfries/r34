/*
 *  Copyright 2015 Functional Objects, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.funobjects.r34

import org.scalactic.{Every, Or}

import scala.concurrent.{Await, Future}

/**
 * A repository that supports updates and removal of entries.
 */
trait Store[K, V] extends Repository[K, V] {
  def putEntry(key: K, value: V): Future[Option[V] Or Every[Issue]]

  def removeEntry(key: K): Future[Option[V] Or Every[Issue]]

  def put(key: K, value: V): Future[Option[V] Or Every[Issue]] = putEntry(key, value)

  def remove(key: K): Future[Option[V] Or Every[Issue]] = removeEntry(key)

  def putSync(key: K, value: V): Option[V] Or Every[Issue] =
    Await.result(get(key), syncTimeout)

  def removeSync(key: K): Option[V] Or Every[Issue] =
    Await.result(get(key), syncTimeout)
}
