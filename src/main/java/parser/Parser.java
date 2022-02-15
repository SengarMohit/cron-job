package parser;

import java.util.List;

public interface Parser {

    public List<String> parse(String str)  throws  Exception;

    public boolean isValid(String str) throws Exception;
}
