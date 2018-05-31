package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

import org.junit.Assert;

public class ModelDataTestHelpers {

  public static void assertMessageEquals(Message expectedMessage, Message actualMessage) {
    Assert.assertEquals(expectedMessage.getId(), actualMessage.getId());
    Assert.assertEquals(expectedMessage.getConversationId(), actualMessage.getConversationId());
    Assert.assertEquals(expectedMessage.getAuthorId(), actualMessage.getAuthorId());
    Assert.assertEquals(expectedMessage.getContent(), actualMessage.getContent());
    Assert.assertEquals(expectedMessage.getCreationTime(), actualMessage.getCreationTime());
  }

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

    public TestMessageBuilder id(String content) {
      this.content = content;
      return this;
    }

    public TestMessageBuilder id(Instant creation) {
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
