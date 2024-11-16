package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LookupEntry {
    private int dstport;
    private String protocol;
    private String tag;

    public LookupEntry() {
    }
}
