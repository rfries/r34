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

package org.funobjects.r34.modules

import akka.actor.{Props, Actor, ActorLogging, ActorSystem}
import akka.persistence.PersistentActor
import akka.stream.FlowMaterializer
import org.funobjects.r34.{Repository, ResourceModule}
import org.funobjects.r34.auth.SimpleUser

import scala.concurrent.ExecutionContext

/**
 * Local user DB
 */
class LocalUsers()(implicit sys: ActorSystem, exec: ExecutionContext, flows: FlowMaterializer)
  extends StorageModule[SimpleUser]("user")(sys, exec, flows) {

  override val routes = None

  //override def repository: Repository[String, SimpleUser] = ???


  override def isDeleted(entity: SimpleUser): Boolean = entity.isDeleted

  /**
   * Returns a copy of the entity which has been marked for deletion.
   */
  override def deleted(entity: SimpleUser): SimpleUser = entity.copy(isDeleted = true)
}
