// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

/** Class representing a message. Messages are sent by a User in a Conversation. */
public class Message {

  private final UUID id;
  private final UUID conversation;
  private final UUID author;
  private final String content;
  private final Instant creation;

  /**
   * Constructs a new Message.
   *
   * @param id the ID of this Message
   * @param conversation the ID of the Conversation this Message belongs to
   * @param author the ID of the User who sent this Message
   * @param content the text content of this Message
   * @param creation the creation time of this Message
   */
  public Message(UUID id, UUID conversation, UUID author, String content, Instant creation) {
    this.id = id;
    this.conversation = conversation;
    this.author = author;
    this.content = content;
    this.creation = creation;
  }

  /** Returns the ID of this Message. */
  public UUID getId() {
    return id;
  }

  /** Returns the ID of the Conversation this Message belongs to. */
  public UUID getConversationId() {
    return conversation;
  }

  /** Returns the ID of the User who sent this Message. */
  public UUID getAuthorId() {
    return author;
  }

  /** Returns the text content of this Message. */
  public String getContent() {
    return content;
  }

  /** Returns the creation time of this Message. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Auto-generated. Must be updated if any fields are added to this class. */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((conversation == null) ? 0 : conversation.hashCode());
    result = prime * result + ((creation == null) ? 0 : creation.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /** Auto-generated. Must be updated if any fields are added to this class. */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Message other = (Message) obj;
    if (author == null) {
      if (other.author != null) return false;
    } else if (!author.equals(other.author)) return false;
    if (content == null) {
      if (other.content != null) return false;
    } else if (!content.equals(other.content)) return false;
    if (conversation == null) {
      if (other.conversation != null) return false;
    } else if (!conversation.equals(other.conversation)) return false;
    if (creation == null) {
      if (other.creation != null) return false;
    } else if (!creation.equals(other.creation)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }
}
