package com.amayadream.clouddriver.common.cache;

/**
 * Redis Key生成工具
 * @author : Amayadream
 * @date :   2017-08-21 09:43
 */
public class KeyGenerator {

    private static final String SPLIT_CHAR = "_";
    private static final String PREFIX = "clouddriver";

    public static String build(String tableName, String columnName, String version, String meta, String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX).append(SPLIT_CHAR)
                .append(tableName).append(SPLIT_CHAR)
                .append(columnName).append(SPLIT_CHAR)
                .append(version).append(SPLIT_CHAR);
        for (String field : fields) {
            sb.append(field).append(SPLIT_CHAR);
        }
        sb.append(meta);
        return sb.toString();
    }

}
