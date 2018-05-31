package codeu.model.store.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import codeu.model.data.Message;
import codeu.model.data.ModelDataTestHelpers.TestMessageBuilder;
import codeu.model.store.persistence.PersistentStorageAgent;

import static codeu.model.data.ModelDataTestHelpers.assertMessageEquals;

public class MessageStoreTest {

  private MessageStore messageStore;
  private PersistentStorageAgent mockPersistentStorageAgent;

  private final UUID CONVERSATION_ID_ONE = UUID.randomUUID();
  private final UUID CONVERSATION_ID_TWO = UUID.randomUUID();

  @Before
  public void setup() {
    mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
    messageStore = MessageStore.getTestInstance(mockPersistentStorageAgent);
  }

  @Test
  public void testGetMessagesInConversation() {
    final Message message1 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    final Message message2 = new TestMessageBuilder().conversation(CONVERSATION_ID_TWO).build();
    final Message message3 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    messageStore.setMessages(Arrays.asList(message1, message2, message3));

    List<Message> resultMessages = messageStore.getMessagesInConversation(CONVERSATION_ID_ONE);

    Assert.assertEquals(2, resultMessages.size());
    assertMessageEquals(message1, resultMessages.get(0));
    assertMessageEquals(message3, resultMessages.get(1));
  }

  @Test
  public void testGetMessagesInConversationNoMessages() {
    final Message message1 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    final Message message2 = new TestMessageBuilder().conversation(CONVERSATION_ID_TWO).build();
    final Message message3 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    messageStore.setMessages(Arrays.asList(message1, message2, message3));

    UUID unusedConversationId = UUID.randomUUID();
    List<Message> resultMessages = messageStore.getMessagesInConversation(unusedConversationId);

    Assert.assertTrue(resultMessages.isEmpty());
  }

  @Test
  public void testAddMessage() {
    final Message message1 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    final Message message2 = new TestMessageBuilder().conversation(CONVERSATION_ID_ONE).build();
    final Message message3 = new TestMessageBuilder().conversation(CONVERSATION_ID_TWO).build();
    final List<Message> messageList = new ArrayList<>();
    messageList.add(message1);
    messageList.add(message2);
    messageStore.setMessages(messageList);

    messageStore.addMessage(message3);

    List<Message> resultMessages = messageStore.getMessagesInConversation(CONVERSATION_ID_TWO);
    Assert.assertEquals(1, resultMessages.size());
    assertMessageEquals(message3, resultMessages.get(0));
    Mockito.verify(mockPersistentStorageAgent).writeThrough(message3);
  }
}
