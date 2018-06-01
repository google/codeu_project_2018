package codeu.model.store.basic;

import static codeu.model.data.ModelDataTestHelpers.assertUserEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import codeu.model.data.ModelDataTestHelpers.TestUserBuilder;
import codeu.model.data.User;
import codeu.model.store.persistence.PersistentStorageAgent;

public class UserStoreTest {

  private UserStore userStore;
  private PersistentStorageAgent mockPersistentStorageAgent;

  @Before
  public void setup() {
    mockPersistentStorageAgent = Mockito.mock(PersistentStorageAgent.class);
    userStore = UserStore.getTestInstance(mockPersistentStorageAgent);
  }

  @Test
  public void testGetUser_byUsername_found() {
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().name("username_two").build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));

    User resultUser = userStore.getUser("username_two");

    assertUserEquals(user2, resultUser);
  }

  @Test
  public void testGetUser_byUsername_notFound() {
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));

    User resultUser = userStore.getUser("fake username");

    assertNull(resultUser);
  }

  @Test
  public void testGetUser_byId_found() {
    final UUID randomUserId = UUID.randomUUID();
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().id(randomUserId).build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));

    User resultUser = userStore.getUser(randomUserId);

    assertUserEquals(user2, resultUser);
  }

  @Test
  public void testGetUser_byId_notFound() {
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));

    User resultUser = userStore.getUser(UUID.randomUUID());

    assertNull(resultUser);
  }

  @Test
  public void testAddUser() {
    final List<User> userList = new ArrayList<>();
    userList.add(new TestUserBuilder().build());
    userList.add(new TestUserBuilder().build());
    userStore.setUsers(userList);
    
    final User inputUser = new TestUserBuilder().name("test_username").build();
    userStore.addUser(inputUser);

    User resultUser = userStore.getUser("test_username");
    assertEquals(inputUser, resultUser);
    Mockito.verify(mockPersistentStorageAgent).writeThrough(inputUser);
  }

  @Test
  public void testIsUserRegistered_true() {
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().name("username_two").build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));
    assertTrue(userStore.isUserRegistered("username_two"));
  }

  @Test
  public void testIsUserRegistered_false() {
    final User user1 = new TestUserBuilder().build();
    final User user2 = new TestUserBuilder().build();
    final User user3 = new TestUserBuilder().build();
    userStore.setUsers(Arrays.asList(user1, user2, user3));
    assertFalse(userStore.isUserRegistered("fake username"));
  }
}
