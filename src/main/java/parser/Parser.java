package parser;

import java.util.List;

public interface Parser {

    public List<String> Parse(String str)  throws  Exception;

    public boolean IsValid(String str) throws Exception;
}
