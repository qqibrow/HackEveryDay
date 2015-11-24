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
import java.util.List;

public class JsonToAvro {

    public GenericRecord toAvroString(Schema schema, JsonNode jsonNode) {
        GenericRecordBuilder builder = new GenericRecordBuilder(schema);
        if (schema.getType() == Schema.Type.STRING) {
            if (jsonNode.getNodeType() == JsonNodeType.STRING) {
                //builder.set()
            } else {
                System.err.println(String.format("schema type is %s  but json type is %s", schema.getType().toString(),
                                                 jsonNode.getNodeType().toString()));
            }

        }
        return null;
    }

    public Object primitiveTypeJsonToAvro(Schema schema, JsonNode node) throws Exception, IOException {
        switch (schema.getType()) {
            case STRING:
                 if(node.isTextual()) {
                     return node.textValue();
                 }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case BYTES:
                assert false: "bytes is not supported yet";
            case LONG:
            case INT:
                if(node.isInt() || node.isNumber())  {
                    return node.intValue();
                }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case FLOAT:
                if(node.isNumber()) {
                    return node.floatValue();
                }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case DOUBLE:
                if( node.isNumber()) {
                    return node.doubleValue();
                }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case BOOLEAN:
                if( node.isBoolean()) {
                    return node.booleanValue();
                }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case NULL:
                if( node.isNull()) {
                    return null;
                }
                throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
            case RECORD:
                GenericRecordBuilder builder = new GenericRecordBuilder(schema);
                if(node.getNodeType() != JsonNodeType.OBJECT) {
                    throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
                }
                for (Schema.Field field : schema.getFields()) {
                    JsonNode childNode = node.get(field.name());
                    if (childNode == null) {
                        if (field.defaultValue() != null) {
                            continue;
                        }
                        System.err.println("trying to find " + field.name() + " field in json  but failed");
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
                if(node.getNodeType() != JsonNodeType.ARRAY) {
                    throw new Exception("schema type is " + schema.getType().toString() + " but Json type is " + node.getNodeType().toString());
                }
                GenericData.Array<Object> array = new GenericData.Array<Object>(node.size(), schema);
                for(final JsonNode childNode: node) {
                    array.add(primitiveTypeJsonToAvro(schema.getElementType(), childNode));
                }
                return array;
            case UNION:
                for(Schema schemaInUnion : schema.getTypes()) {
                    try {
                        Object res = primitiveTypeJsonToAvro(schemaInUnion, node);
                        return res;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
                // no types in union matches the json type.
            throw new Exception("no types in union matches the json type");
            case ENUM:
                List<String> symbols = schema.getEnumSymbols();
                if(node.getNodeType() != JsonNodeType.STRING) {
                    throw new Exception("Schema type is ENUM and node type is not string. no way to represent enum symbol");
                }
                String symbol = node.asText();
                if(symbols.contains(symbol)) {
                    return new GenericData.EnumSymbol(schema, symbol);
                } else {
                    throw new Exception("Json string doesn't match any symbol in Enum");
                }
        }
        return null;
    }

    public Object jsonToAvro(Schema schema, JsonNode jsonNode) throws Exception, IOException{
        return primitiveTypeJsonToAvro(schema, jsonNode);
    }
}
