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

package codeu.integration;

import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class ConversationsIntegrationTest {

  private final LocalServiceTestHelper appEngineTestHelper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  private ServletRunner servletRunner;
  private ServletUnitClient servletClient;

  @Before
  @SuppressWarnings("deprecation")
  public void setup() throws IOException, SAXException {
    // HttpUnit 1.7.2 (the latest version) requires Jasper 6, which does not
    // support Java 9. We skip the integration tests on other platforms.
    assumeThat(System.getProperty("java.version"), startsWith("1.8."));

    appEngineTestHelper.setUp();

    // Unfortunately, the ServletRunner constructors that take java.io.File
    // (the non-deprecated ones) have a bug, so we have to use the two argument
    // string form.
    //
    // (See https://sourceforge.net/p/httpunit/mailman/message/27259892.)
    servletRunner = new ServletRunner("src/main/webapp/WEB-INF/web.xml", "");
    servletClient = servletRunner.newClient();

    // Start logged in as a test user.
    WebRequest loginRequest = new PostMethodWebRequest("http://dummy/login");
    loginRequest.setParameter("username", "ted");
    assertThat(servletClient.getResponse(loginRequest).getText(), containsString("Hello ted!"));

    // Create a default conversation.
    WebRequest createRequest = new PostMethodWebRequest("http://dummy/conversations");
    createRequest.setParameter("conversationTitle", "general");
    WebResponse createResponse = servletClient.getResponse(createRequest);
    assertThat(createResponse.getText(), containsString("<title>general"));
  }

  @After
  public void tearDown() {
    appEngineTestHelper.tearDown();
  }

  @Test
  public void testJoinConversation() throws IOException, SAXException {
    WebRequest joinRequest = new PostMethodWebRequest("http://dummy/conversations");
    joinRequest.setParameter("conversationTitle", "general");
    assertThat(servletClient.getResponse(joinRequest).getText(), containsString("<title>general"));
  }

  @Test
  public void testSendMessage() throws IOException, SAXException {
    WebRequest createRequest = new PostMethodWebRequest("http://dummy/chat/general");
    createRequest.setParameter("message", "Hi, this is Ted.");
    assertThat(
        servletClient.getResponse(createRequest).getText(),
        containsString("<strong>ted:</strong> Hi, this is Ted."));
  }
}
