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

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.UUID;

public class ModelDataTestHelpers {

  /** Asserts that all fields on both Messages are the same. */
  public static void assertMessageEquals(Message expected, Message actual) {
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getConversationId(), actual.getConversationId());
    assertEquals(expected.getAuthorId(), actual.getAuthorId());
    assertEquals(expected.getContent(), actual.getContent());
    assertEquals(expected.getCreationTime(), actual.getCreationTime());
  }

  /**
   * Use this to create a fake Message to use in a unit test. When created it contains random data
   * in every field, and the individual methods can be used to set the test conditions. For example,
   * if the test needs specific author ID and content, then you could do:
   *
   * <pre>{@code
   * UUID fakeAuthor = UUID.randomUUID();
   * String fakeContent = "test message 1";
   * Message fakeMessage = new TestMessageBuilder().author(fakeAuthor).content(fakeContent).build();
   * }</pre>
   */
  public static class TestMessageBuilder {
    public TestMessageBuilder() {
      this.id = UUID.randomUUID();
      this.conversation = UUID.randomUUID();
      this.author = UUID.randomUUID();
      this.content = UUID.randomUUID().toString();
      this.creation = Instant.now();
    }

    public TestMessageBuilder id(UUID id) {
      this.id = id;
      return this;
    }

    public TestMessageBuilder conversation(UUID conversation) {
      this.conversation = conversation;
      return this;
    }

    public TestMessageBuilder author(UUID author) {
      this.author = author;
      return this;
    }

    public TestMessageBuilder content(String content) {
      this.content = content;
      return this;
    }

    public TestMessageBuilder creation(Instant creation) {
      this.creation = creation;
      return this;
    }

    public Message build() {
      return new Message(id, conversation, author, content, creation);
    }

    private UUID id;
    private UUID conversation;
    private UUID author;
    private String content;
    private Instant creation;
  }
}
