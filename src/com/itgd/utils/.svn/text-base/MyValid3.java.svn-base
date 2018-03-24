package com.itgd.utils;

import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.*;

/**
* This class is use to change the special characters in a image name to insert it in to 
* mySQL database.
* 
* 
* @author Deepak Singh
* @version
*/

public class MyValid3 {

public MyValid3() {
}

public String doStrAppend(int tokenLen) {
String append = "";

if (tokenLen == 3)
append = "_ ";
if (tokenLen == 2)
append = "__ ";
if (tokenLen == 1)
append = "___ ";

return append;
}

public String doAddSlashesString(String serStr) {

StringBuffer stringbuffer = new StringBuffer();
if (serStr == null || serStr.length() == 0) {
return "";
}

for (StringTokenizer stringtokenizer = new StringTokenizer(serStr,
"'\"", true); stringtokenizer.hasMoreTokens();) {
String token = stringtokenizer.nextToken();

if (token.endsWith("'")) {
stringbuffer.append(token.substring(0, token.length() - 1));
stringbuffer.append("\\'");
} else if (token.endsWith("\"")) {
stringbuffer.append(token.substring(0, token.length() - 1));
stringbuffer.append("\\\"");
} else if (token.startsWith("'")) {
stringbuffer.append("\\'");
stringbuffer.append(token.substring(token.length() - 1, 0));
} else if (token.startsWith("\"")) {
stringbuffer.append("\\\"");
stringbuffer.append(token.substring(token.length() - 1, 0));
} else {
stringbuffer.append(token);
}
}
return stringbuffer.toString();
}

}