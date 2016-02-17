/**
 * Created by lniu on 11/23/15.
 * Code is from https://github.com/linkedin/gobblin/blob/master/gobblin-utility/src/main/java/gobblin/util/AvroUtils.java
 * However, original code doesn't support union and array type.
 * Added support for union and array here.
 */

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

public class AvroUtils {
    private static final String FIELD_LOCATION_DELIMITER = ".";
    private static final String UNION_INDICATOR = "union";
    private static final String ARRAY_INDICATOR = "array";
    public static Optional<Schema> getFieldSchema(Schema schema, String fieldLocation) {
        Preconditions.checkNotNull(schema);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fieldLocation));

        Splitter splitter = Splitter.on(FIELD_LOCATION_DELIMITER).omitEmptyStrings().trimResults();
        List<String> pathList = Lists.newArrayList(splitter.split(fieldLocation));

        if (pathList.size() == 0) {
            return Optional.absent();
        }

        return AvroUtils.getFieldSchemaHelper(schema, pathList, 0);
    }

    /**
     * Helper method that does the actual work for {@link #getFieldSchema(Schema, String)}
     * @param schema passed from {@link #getFieldSchema(Schema, String)}
     * @param pathList passed from {@link #getFieldSchema(Schema, String)}
     * @param field keeps track of the index used to access the list pathList
     * @return the schema of the field
     */
    private static Optional<Schema> getFieldSchemaHelper(Schema schema, List<String> pathList, int field) {
        String curr = pathList.get(field);
        if(curr.equals(UNION_INDICATOR)) {
            Schema s1 = schema.getTypes().get(0);
            Schema s2 = schema.getTypes().get(1);
            Schema notNullSchema = s1.getType() != Schema.Type.NULL ? s1 : s2;
            if((field + 1) == pathList.size()) {
                return Optional.fromNullable(notNullSchema);
            } else {
                return getFieldSchemaHelper(notNullSchema, pathList, ++field);
            }
        } else if(curr.equals(ARRAY_INDICATOR)) {
            Schema elementSchema = schema.getElementType();
            if((field + 1) == pathList.size()) {
                return Optional.fromNullable(elementSchema);
            } else {
                return getFieldSchemaHelper(elementSchema, pathList, ++field);
            }
        }

        if (schema.getField(pathList.get(field)) == null) {
            return Optional.absent();
        }
        if ((field + 1) == pathList.size()) {
            return Optional.fromNullable(schema.getField(pathList.get(field)).schema());
        } else {
            return AvroUtils.getFieldSchemaHelper(schema.getField(pathList.get(field)).schema(), pathList, ++field);
        }
    }

    /**
     * Given a GenericRecord, this method will return the field specified by the path parameter. The fieldLocation
     * parameter is an ordered string specifying the location of the nested field to retrieve. For example,
     * field1.nestedField1 takes the the value of the field "field1", and retrieves the field "nestedField1" from it.
     * @param record is the record to retrieve the field from
     * @param fieldLocation is the location of the field
     * @return the value of the field
     */
    public static Optional<Object> getFieldValue(GenericRecord record, String fieldLocation) {
        Preconditions.checkNotNull(record);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fieldLocation));

        Splitter splitter = Splitter.on(FIELD_LOCATION_DELIMITER).omitEmptyStrings().trimResults();
        List<String> pathList = splitter.splitToList(fieldLocation);

        if (pathList.size() == 0) {
            return Optional.absent();
        }

        return AvroUtils.getFieldHelper(record, pathList, 0);
    }

    /**
     * Helper method that does the actual work for {@link #getFieldValue(GenericRecord, String)}
     * @param data passed from {@link #getFieldValue(GenericRecord, String)}
     * @param pathList passed from {@link #getFieldValue(GenericRecord, String)}
     * @param field keeps track of the index used to access the list pathList
     * @return the value of the field
     */
    private static Optional<Object> getFieldHelper(Object data, List<String> pathList, int field) {
        if (data == null) {
            return Optional.absent();
        }

        if ((field + 1) == pathList.size()) {
            return Optional.fromNullable(((GenericData.Record) data).get(pathList.get(field)));
        } else {
            return AvroUtils.getFieldHelper(((GenericData.Record) data).get(pathList.get(field)), pathList, ++field);
        }
    }
}
