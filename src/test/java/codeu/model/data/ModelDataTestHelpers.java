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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.UUID;

public class ModelDataTestHelpers {

  /** Asserts that all fields on both Messages are the same. */
  public static void assertMessageEquals(Message expected, Message actual) {
    if (expected == null) {
      assertNull(actual);
    } else {
      assertNotNull("Message not found", actual);
      assertEquals(expected.getId(), actual.getId());
      assertEquals(expected.getConversationId(), actual.getConversationId());
      assertEquals(expected.getAuthorId(), actual.getAuthorId());
      assertEquals(expected.getContent(), actual.getContent());
      assertEquals(expected.getCreationTime(), actual.getCreationTime());
    }
  }

  /**
   * Use this to create a fake Message to use in a unit test. When created it contains random data
   * in every field, and the individual methods can be used to set the test conditions. For example,
   * if the test needs specific author ID and content, then you could do:
   *
   * <pre>{@code
   * UUID fakeAuthor = UUID.randomUUID();
   * String fakeContent = "test message 1";
   * Message fakeMessage = new TestMessageBuilder().withAuthorId(fakeAuthor).withContent(fakeContent).build();
   * }</pre>
   */
  public static class TestMessageBuilder {
    private UUID id;
    private UUID conversationId;
    private UUID authorId;
    private String content;
    private Instant creationTime;

    public TestMessageBuilder() {
      this.id = UUID.randomUUID();
      this.conversationId = UUID.randomUUID();
      this.authorId = UUID.randomUUID();
      this.content = UUID.randomUUID().toString();
      this.creationTime = Instant.now();
    }

    public TestMessageBuilder withId(UUID id) {
      this.id = id;
      return this;
    }

    public TestMessageBuilder withConversationId(UUID conversationId) {
      this.conversationId = conversationId;
      return this;
    }

    public TestMessageBuilder withAuthorId(UUID authorId) {
      this.authorId = authorId;
      return this;
    }

    public TestMessageBuilder withContent(String content) {
      this.content = content;
      return this;
    }

    public TestMessageBuilder withCreationTime(Instant creationTime) {
      this.creationTime = creationTime;
      return this;
    }

    public Message build() {
      return new Message(id, conversationId, authorId, content, creationTime);
    }
  }
}
