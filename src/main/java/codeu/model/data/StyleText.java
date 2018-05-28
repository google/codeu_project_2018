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

/** Class representing a message. Messages are sent by a User in a Conversation. */
public class StyleText {

  public static String style(String message) {
    String messageWithout = message;
    while (messageWithout.contains("[b]") && messageWithout.contains("[/b]")) {
      int startB = messageWithout.indexOf("[b]");
      String beforeB = messageWithout.substring(0, startB);
      String afterB = messageWithout.substring(startB);
      beforeB = beforeB.replaceAll("\\[/b\\]", "");
      messageWithout = beforeB + afterB;
      startB = messageWithout.indexOf("[b]");
      messageWithout = messageWithout.replaceFirst("\\[b\\]", "");
      int endB = messageWithout.indexOf("[/b]", startB);
      messageWithout = messageWithout.replaceFirst("\\[/b\\]", "");
      if (endB >= 0) {
        messageWithout =
            messageWithout.substring(0, startB)
                + "<b>"
                + messageWithout.substring(startB, endB)
                + "</b>"
                + messageWithout.substring(endB);
      }
    }
    return messageWithout.replaceAll("\\[/?b\\]", "");
  }
}

