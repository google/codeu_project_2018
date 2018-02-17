package codeu.model.store.persistence;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import java.time.Instant;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Contains tests of the PersistentStorageAgent class. Currently that class is just a pass-through
 * to PersistentDataStore, so these tests are pretty trivial. If you modify how
 * PersistentStorageAgent writes to PersistentDataStore, or if you swap out the backend to something
 * other than PersistentDataStore, then modify these tests.
 */
public class PersistentStorageAgentTest {

  private PersistentDataStore mockPersistentDataStore;
  private PersistentStorageAgent persistentStorageAgent;

  @Before
  public void setup() {
    mockPersistentDataStore = Mockito.mock(PersistentDataStore.class);
    persistentStorageAgent = PersistentStorageAgent.getTestInstance(mockPersistentDataStore);
  }

  @Test
  public void testLoadUsers() throws PersistentDataStoreException {
    persistentStorageAgent.loadUsers();
    Mockito.verify(mockPersistentDataStore).loadUsers();
  }

  @Test
  public void testLoadConversations() throws PersistentDataStoreException {
    persistentStorageAgent.loadConversations();
    Mockito.verify(mockPersistentDataStore).loadConversations();
  }

  @Test
  public void testLoadMessages() throws PersistentDataStoreException {
    persistentStorageAgent.loadMessages();
    Mockito.verify(mockPersistentDataStore).loadMessages();
  }

  @Test
  public void testWriteThroughUser() {
    User user = new User(UUID.randomUUID(), "test_username", Instant.now());
    persistentStorageAgent.writeThrough(user);
    Mockito.verify(mockPersistentDataStore).writeThrough(user);
  }

  @Test
  public void testWriteThroughConversation() {
    Conversation conversation =
        new Conversation(UUID.randomUUID(), UUID.randomUUID(), "test_conversation", Instant.now());
    persistentStorageAgent.writeThrough(conversation);
    Mockito.verify(mockPersistentDataStore).writeThrough(conversation);
  }

  @Test
  public void testWriteThroughMessage() {
    Message message =
        new Message(
            UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "test content", Instant.now());
    persistentStorageAgent.writeThrough(message);
    Mockito.verify(mockPersistentDataStore).writeThrough(message);
  }
}
