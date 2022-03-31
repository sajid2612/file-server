package main.com.server.constant;

public class MultiPart {
        public PartType type;
        public String contentType;
        public String name;
        public String filename;
        public String value;
        public byte[] bytes;
}

enum PartType{
	TEXT,FILE
}
