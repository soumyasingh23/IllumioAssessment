package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogEntry {
    private int dstport;
    private int protocolNumber;
    private String protocol;
    private String tag;

    public LogEntry() {
    }

    LookupKey getLookupKey() {
        return new LookupKey(this.getDstport(), this.getProtocol());
    }
}
