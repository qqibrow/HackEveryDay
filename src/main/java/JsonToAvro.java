/**
 * Created by lniu on 10/2/15.
 */

import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.Schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;

public class JsonToAvro {

    public GenericRecord toAvroString(Schema schema, JsonNode jsonNode) {
        GenericRecordBuilder builder = new GenericRecordBuilder(schema);
        if (schema.getType() == Schema.Type.STRING) {
            if (jsonNode.getNodeType() == JsonNodeType.STRING) {
                //builder.set()
            } else {
                System.err.println(String.format("schema type is %s but json type is %s", schema.getType().toString(),
                                                 jsonNode.getNodeType().toString()));
            }

        }
        return null;
    }

    public Object primitiveTypeJsonToAvro(Schema schema, JsonNode node) throws IOException {
        switch (schema.getType()) {
            case STRING:
                assert node.isTextual();
                return node.textValue();
            case BYTES:
                assert false: "bytes is not supported yet";
            case LONG:
                assert node.isInt();
                return node.intValue();
            case INT:
                assert node.isInt();
                return node.intValue();
            case FLOAT:
                assert node.isNumber();
                return node.floatValue();
            case DOUBLE:
                assert node.isNumber();
                return node.doubleValue();
            case BOOLEAN:
                assert node.isBoolean();
                return node.booleanValue();
            case NULL:
                assert node.isNull();
                return null;
            case RECORD:
                GenericRecordBuilder builder = new GenericRecordBuilder(schema);
                assert node.getNodeType() == JsonNodeType.OBJECT;
                for (Schema.Field field : schema.getFields()) {
                    JsonNode childNode = node.get(field.name());
                    if (childNode == null) {
                        if (field.defaultValue() != null) {
                            continue;
                        }
                        System.err.println("trying to find " + field.name() + " field in json but failed");
                        return null;
                    } else {
                        try {
                            builder.set(field, primitiveTypeJsonToAvro(field.schema(), childNode));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return builder.build();
            case ARRAY:
                assert node.getNodeType() == JsonNodeType.ARRAY : "avro data is array type but json data is not";
                GenericData.Array<Object> array = new GenericData.Array<Object>(node.size(), schema);
                for(final JsonNode childNode: node) {
                    array.add(primitiveTypeJsonToAvro(schema.getElementType(), childNode));
                }
                return array;
            case UNION:
                for(Schema schemaInUnion : schema.getTypes()) {
                    Object res = primitiveTypeJsonToAvro(schemaInUnion, node);
                    if(res != null) {
                        return res;
                    }
                }
                // no types in union matches the json type.
                return null;
        }
        return null;
    }

    public Object jsonToAvro(Schema schema, JsonNode jsonNode) throws IOException{
        return primitiveTypeJsonToAvro(schema, jsonNode);
    }
}
