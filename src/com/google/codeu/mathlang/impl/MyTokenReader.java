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

package com.google.codeu.mathlang.impl;

import java.io.IOException;
import java.util.*;

import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.parsing.TokenReader;
import com.google.codeu.mathlang.core.tokens.*;

// MY TOKEN READER
//
// This is YOUR implementation of the token reader interface. To know how
// it should work, read src/com/google/codeu/mathlang/parsing/TokenReader.java.
// You should not need to change any other files to get your token reader to
// work with the test of the system.
public final class MyTokenReader implements TokenReader {

  public MyTokenReader(String source) {
    // Your token reader will only be given a string for input. The string will
    // contain the whole source (0 or more lines).
    //this.source = source;
  }

  @Override
  public Token next() throws IOException {
    // Most of your work will take place here. For every call to |next| you should
    // return a token until you reach the end. When there are no more tokens, you
    // should return |null| to signal the end of input.

    // If for any reason you detect an error in the input, you may throw an IOException
    // which will stop all execution.

    String result = "";
    String input = "";
    Scanner scnr = new Scanner(input);
    while(scnr.hasNext() && Character.isWhitespace(input.charAt(0))) {
      input = input.substring(1);
    }
    if(!scnr.hasNext()) {
      return null;
    } else {
      int i = 0;
      while(scnr.hasNext() && !Character.isWhitespace(input.charAt(i))) {
        result += input.charAt(i);
        i++;
      }
      boolean isNumber = true;
      int j = 0;
      while(!Character.isDigit(result.charAt(j)) && j < result.length()) {
        j++;
        isNumber = false;
      }
      boolean isLetter = true;
      j = 0;
      while(!Character.isAlphabetic(result.charAt(j)) && j < result.length()) {
        j++;
        isLetter = false;
      }
      if(isNumber) {
        return new NumberToken(Double.parseDouble(result));
      } else if(isLetter) {
        return new NameToken(result);
      } else if(result.length() == 1) {
        return new SymbolToken(result.charAt(0));
      } else {
        return new StringToken(result);
      }
    }
  }
}
