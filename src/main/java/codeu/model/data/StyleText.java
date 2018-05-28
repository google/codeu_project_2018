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
  private static String[] styleTags = {"b", "i", "u"};

  public static String style(String message) {
    String messageWithout = message;
    for (String tag : styleTags) {
      messageWithout = style(messageWithout, tag);
    }
    return messageWithout;
  }

  private static String style(String message, String tag) {
    String messageWithout = message;
    while (messageWithout.contains("[" + tag + "]") && messageWithout.contains("[/" + tag + "]")) {
      int startB = messageWithout.indexOf("[" + tag + "]");
      String beforeB = messageWithout.substring(0, startB);
      String afterB = messageWithout.substring(startB);
      beforeB = beforeB.replaceAll("\\[/" + tag + "\\]", "");
      messageWithout = beforeB + afterB;
      startB = messageWithout.indexOf("[" + tag + "]");
      messageWithout = messageWithout.replaceFirst("\\[" + tag + "\\]", "");
      int endB = messageWithout.indexOf("[/" + tag + "]", startB);
      messageWithout = messageWithout.replaceFirst("\\[/" + tag + "\\]", "");
      if (endB >= 0) {
        messageWithout =
            messageWithout.substring(0, startB)
                + "<"
                + tag
                + ">"
                + messageWithout.substring(startB, endB)
                + "</"
                + tag
                + ">"
                + messageWithout.substring(endB);
      }
    }
    return messageWithout.replaceAll("\\[/?" + tag + "\\]", "");
  }
}

// this [b] message [/b] contains [i] and runs [/i] every [u] single tags [/u] in our list
