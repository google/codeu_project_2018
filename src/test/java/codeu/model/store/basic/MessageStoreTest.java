package codeu.model.store.basic;

import static codeu.model.data.ModelDataTestHelpers.assertMessageEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import codeu.model.data.Message;
import codeu.model.data.ModelDataTestHelpers.TestMessageBuilder;
import codeu.model.store.persistence.PersistentStorageAgent;

public class MessageStoreTest {

  private MessageStore messageStore;
  private PersistentStorageAgent mockPersistentStorageAgent;

  private final UUID CONVERSATION_ID_ONE = UUID.randomUUID();
  private final UUID USER_ONE = UUID.randomUUID();

  @Before
  public void setup() {
    mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
    messageStore = MessageStore.getTestInstance(mockPersistentStorageAgent);
  }

  @Test
  public void testGetMessagesInConversation() {
    final Message message1 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    final Message message2 = new TestMessageBuilder().build();
    final Message message3 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    messageStore.setMessages(Arrays.asList(message1, message2, message3));

    List<Message> resultMessages = messageStore.getMessagesInConversation(CONVERSATION_ID_ONE);

    assertEquals(2, resultMessages.size());
    Map<UUID, Message> resultMessagesSet = new HashMap<>();
    for (Message resultMessage : resultMessages) {
      resultMessagesSet.put(resultMessage.getId(), resultMessage);
    }
    assertMessageEquals(message1, resultMessagesSet.get(message1.getId()));
    assertMessageEquals(message3, resultMessagesSet.get(message3.getId()));
  }

  @Test
  public void testGetMessagesInConversation_noMessagesFound() {
    final Message message1 = new TestMessageBuilder().build();
    final Message message2 = new TestMessageBuilder().build();
    final Message message3 = new TestMessageBuilder().build();
    messageStore.setMessages(Arrays.asList(message1, message2, message3));

    UUID unusedId = UUID.randomUUID();
    List<Message> resultMessages = messageStore.getMessagesInConversation(unusedId);

    assertTrue(resultMessages.isEmpty());
  }

  @Test
  public void testGetMessagesByUser() {
    final Message message1 = new TestMessageBuilder().build();
    final Message message2 = new TestMessageBuilder().author(USER_ONE).build();
    final Message message3 = new TestMessageBuilder().author(USER_ONE).build();
    messageStore.setMessages(Arrays.asList(message1, message2, message3));

    List<Message> resultMessages = messageStore.getMessagesByUser(USER_ONE);

    assertEquals(2, resultMessages.size());
    Map<UUID, Message> resultMessagesSet = new HashMap<>();
    for (Message resultMessage : resultMessages) {
      resultMessagesSet.put(resultMessage.getId(), resultMessage);
    }
    assertMessageEquals(message2, resultMessagesSet.get(message2.getId()));
    assertMessageEquals(message3, resultMessagesSet.get(message3.getId()));
  }

  @Test
  public void testAddMessage() {
    final List<Message> messageList = new ArrayList<>();
    messageList.add(new TestMessageBuilder().build());
    messageList.add(new TestMessageBuilder().build());
    messageStore.setMessages(messageList);

    final Message message3 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    messageStore.addMessage(message3);

    List<Message> resultMessages = messageStore.getMessagesInConversation(CONVERSATION_ID_ONE);
    assertEquals(1, resultMessages.size());
    Map<UUID, Message> resultMessagesSet = new HashMap<>();
    for (Message resultMessage : resultMessages) {
      resultMessagesSet.put(resultMessage.getId(), resultMessage);
    }
    assertMessageEquals(message3, resultMessagesSet.get(message3.getId()));
    Mockito.verify(mockPersistentStorageAgent).writeThrough(message3);
  }
}
