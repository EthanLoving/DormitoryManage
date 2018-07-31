package per.crystal.dormmanage.util;

import java.util.UUID;

public class IDGenerator {

    public static String getPrimaryKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
