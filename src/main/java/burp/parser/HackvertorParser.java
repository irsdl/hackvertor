/* HackvertorParser.java */
/* Generated By:JavaCC: Do not edit this line. HackvertorParser.java */
package burp.parser;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.ArrayList;

public class HackvertorParser implements HackvertorParserConstants {

    private static String getTokenText(Token first, Token cur) {
    Token t;
    StringBuffer sb = new StringBuffer();

    for (t=first; t != cur.next; t = t.next) {
      if (t.specialToken != null) {
        Token tt=t.specialToken;
        while (tt.specialToken != null)
          tt = tt.specialToken;
        for (; tt != null; tt = tt.next)
          sb.append(tt.image);
      };
      sb.append(t.image);
    };
    return sb.toString();
    }

    public static LinkedList<Element> parse(String string) throws ParseException {
        HackvertorParser parser = new HackvertorParser(new StringReader(string));
        LinkedList<Element> elementList = parser.Input();

        for (Element e : elementList) {
            System.out.println(e.getClass() + " : " + e);
        }

        return elementList;
    }

  final public LinkedList<Element> Input() throws ParseException {    try {
LinkedList<Element> s = new LinkedList<Element>();
    LinkedList<Element> e;
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case TAG_START:
        case ENDTAG_START:
        case TEXT:
        case LESSTHAN:
        case ST_ERR:
        case IT_ERR:
        case QUOTED_STRING_VAL:
        case LITERAL_VAL:
        case COMMA:
        case ARGS_END:
        case ARG_ERR:{
          ;
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        e = ElementSequence();
s.addAll(e);
      }
      jj_consume_token(0);
{if ("" != null) return s;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("Input");
    }
}

  final public LinkedList<Element> ElementSequence() throws ParseException {    try {
LinkedList<Element> elements = new LinkedList<Element>();
 LinkedList<Element> nested = new LinkedList<Element>();
 Element e;
 Token text;
      try {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case TAG_START:{
          elements = StartTag();
{if ("" != null) return elements;}
          break;
          }
        case ENDTAG_START:{
          elements = CloseTag();
{if ("" != null) return elements;}
          break;
          }
        case LESSTHAN:{
          jj_consume_token(LESSTHAN);
elements.add(new Element.TextElement("<")); {if ("" != null) return elements;}
          break;
          }
        case TEXT:
        case ST_ERR:
        case IT_ERR:
        case QUOTED_STRING_VAL:
        case LITERAL_VAL:
        case COMMA:
        case ARGS_END:
        case ARG_ERR:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case TEXT:{
            text = jj_consume_token(TEXT);
            break;
            }
          case ST_ERR:{
            text = jj_consume_token(ST_ERR);
            break;
            }
          case IT_ERR:{
            text = jj_consume_token(IT_ERR);
            break;
            }
          case ARG_ERR:{
            text = jj_consume_token(ARG_ERR);
            break;
            }
          case QUOTED_STRING_VAL:{
            text = jj_consume_token(QUOTED_STRING_VAL);
            break;
            }
          case LITERAL_VAL:{
            text = jj_consume_token(LITERAL_VAL);
            break;
            }
          case COMMA:{
            text = jj_consume_token(COMMA);
            break;
            }
          case ARGS_END:{
            text = jj_consume_token(ARGS_END);
            break;
            }
          default:
            jj_la1[1] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
elements.add(new Element.TextElement(text.image)); {if ("" != null) return elements;}
          break;
          }
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } catch (ParseException ex) {
{if ("" != null) return elements;} //Catch Exception for EOF

      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("ElementSequence");
    }
}

  final public LinkedList<Element> StartTag() throws ParseException {    try {
LinkedList<Element> elements = new LinkedList<Element>();
    ArrayList<String> args = new ArrayList<String>();
    Token t;
    Token identifier = null;
    String arg;
    Token firstToken = getToken(1);
      t = jj_consume_token(TAG_START);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FUNCTION_NAME:{
        identifier = jj_consume_token(FUNCTION_NAME);
        break;
        }
      case TAG_START:
      case ENDTAG_START:
      case TEXT:
      case LESSTHAN:
      case ST_ERR:
      case IT_ERR:
      case QUOTED_STRING_VAL:
      case LITERAL_VAL:
      case COMMA:
      case ARGS_END:
      case ARG_ERR:{
        elements = ElementSequence();
elements.addFirst(new Element.TextElement(getTokenText(firstToken, t))); {if ("" != null) return elements;}
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ARGS_START:{
        try {
          jj_consume_token(ARGS_START);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case QUOTED_STRING_VAL:
          case LITERAL_VAL:{
            arg = Argument();
args.add(arg);
            label_2:
            while (true) {
              switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
              case COMMA:{
                ;
                break;
                }
              default:
                jj_la1[4] = jj_gen;
                break label_2;
              }
              jj_consume_token(COMMA);
              arg = Argument();
args.add(arg);
            }
            break;
            }
          default:
            jj_la1[5] = jj_gen;
            ;
          }
          jj_consume_token(ARGS_END);
        } catch (ParseException e) {
token_source.SwitchTo(DEFAULT);
            elements.addFirst(new Element.TextElement(getTokenText(firstToken, getToken(0))));
            elements.addAll(ElementSequence());
            {if ("" != null) return elements;}
        }
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TAG_END:{
        jj_consume_token(TAG_END);
elements.add(new Element.StartTag(identifier.image, args)); {if ("" != null) return elements;}
        break;
        }
      case SELF_CLOSE_TAG_END:
      case SELF_CLOSE_TAG_END_AT:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case SELF_CLOSE_TAG_END:{
          jj_consume_token(SELF_CLOSE_TAG_END);
          break;
          }
        case SELF_CLOSE_TAG_END_AT:{
          jj_consume_token(SELF_CLOSE_TAG_END_AT);
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
elements.add(new Element.SelfClosingTag(identifier.image, args)); {if ("" != null) return elements;}
        break;
        }
      default:
        jj_la1[8] = jj_gen;
elements.addFirst(new Element.TextElement(getTokenText(firstToken, getToken(0)))); elements.addAll(ElementSequence()); {if ("" != null) return elements;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("StartTag");
    }
}

  final public String Argument() throws ParseException {    try {
Token t;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case QUOTED_STRING_VAL:{
        t = jj_consume_token(QUOTED_STRING_VAL);
{if ("" != null) return t.image.substring(1, t.image.length() - 1);}
        break;
        }
      case LITERAL_VAL:{
        t = jj_consume_token(LITERAL_VAL);
{if ("" != null) return t.image;}
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("Argument");
    }
}

  final public LinkedList<Element> CloseTag() throws ParseException {    try {
LinkedList<Element> elements = new LinkedList<Element>();
    Token t;
    Token firstToken = getToken(1);
      t = jj_consume_token(ENDTAG_START);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FUNCTION_NAME:{
        t = jj_consume_token(FUNCTION_NAME);
        break;
        }
      case TAG_START:
      case ENDTAG_START:
      case TEXT:
      case LESSTHAN:
      case ST_ERR:
      case IT_ERR:
      case QUOTED_STRING_VAL:
      case LITERAL_VAL:
      case COMMA:
      case ARGS_END:
      case ARG_ERR:{
        elements = ElementSequence();
elements.addFirst(new Element.TextElement(getTokenText(firstToken, t))); {if ("" != null) return elements;}
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TAG_END:{
        jj_consume_token(TAG_END);
elements.add(new Element.EndTag(t.image)); {if ("" != null) return elements;}
        break;
        }
      default:
        jj_la1[11] = jj_gen;
elements.addFirst(new Element.TextElement(getTokenText(firstToken, getToken(0))));
            elements.addAll(ElementSequence());
            {if ("" != null) return elements;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("CloseTag");
    }
}

  /** Generated Token Manager. */
  public HackvertorParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0xfc2f00,0xfc2400,0xfc2f00,0xfc3f00,0x200000,0x180000,0x4000,0x18000,0x38000,0x180000,0xfc3f00,0x20000,};
	}

  {
      enable_tracing();
  }
  /** Constructor with InputStream. */
  public HackvertorParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public HackvertorParser(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new HackvertorParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public HackvertorParser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new HackvertorParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new HackvertorParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public HackvertorParser(HackvertorParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(HackvertorParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   trace_token(token, "");
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	   trace_token(token, " (in getNextToken)");
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[25];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 12; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 25; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

/** Enable tracing. */
  final public void enable_tracing() {
	 trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
	 trace_enabled = false;
  }

  protected void trace_call(String s) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Call:	" + s);
	 }
	 trace_indent = trace_indent + 2;
  }

  protected void trace_return(String s) {
	 trace_indent = trace_indent - 2;
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Return: " + s);
	 }
  }

  protected void trace_token(Token t, String where) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Consumed token: <" + tokenImage[t.kind]);
	   if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t.image) + "\"");
	   }
	   System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
	 }
  }

  protected void trace_scan(Token t1, int t2) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Visited token: <" + tokenImage[t1.kind]);
	   if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t1.image) + "\"");
	   }
	   System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
	 }
  }

}
