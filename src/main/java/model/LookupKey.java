package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor

public class LookupKey {
    private int dstport;
    private String tag;


    @Override
    public String toString() {
        return dstport + " " + tag;
    }
}
