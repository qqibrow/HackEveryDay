import com.sun.prism.impl.Disposer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.avro.Schema;

/**
 * Created by lniu on 11/6/15.
 */
public class UserProfileSchemaConverter {
    ArrayList<HashMap<String, String>> all = new ArrayList<HashMap<String, String>>();


    public class Node {
        private String name;
        private String type;
        private Node elementNode;
        private ArrayList<Node> children = new ArrayList<Node>();

        public Node(String name, String type) {
            this.name = name;
            this.type = type;
        }
        public Node(String name, ArrayList<Node> children) {
            this.name = name;
            this.type = "record";
            this.children = children;
        }
        public Node(String name, Node elementNode) {
            this.name = name;
            this.type = "array";
            this.elementNode = elementNode;
        }

        public ArrayList<Node> getElementTypes() {
            ArrayList<Node> array = new ArrayList<Node>();
            getElementTypesRecursive(this, array);
            return array;
        }

        private void getElementTypesRecursive(Node node, ArrayList<Node> types) {
            if(node.elementNode != null) {
                types.add(node.elementNode);
            }
            for(Node n : node.children) {
                getElementTypesRecursive(n, types);
            }
        }
    }
    public Set<String> outputLeafRecords(Schema schema) throws Exception {
        Set<String> records = new HashSet<String>();
        outputLeafRecordsRecursive("", schema, records);
        return records;
    }

    public void outputLeafRecordsRecursive(String prev, Schema schema, Set<String> records) throws Exception {
            //String name = schema.getName();
            //String type = schema.getType().toString();
            //String curr = name + ":" + type;
            //String currPath = prev + "/" + curr;
            //currPath += isTransparentRecord(schema)? ":BLANK" : "";
            switch (schema.getType()) {
                case RECORD:
                    if(isLeafRecord(schema)) {
                        records.add(prev + ":" + schema.getFields().size());
                    } else {
                        for(Schema.Field field: schema.getFields()) {
                            if(!isPrimitiveType(field.schema())) {
                                outputLeafRecordsRecursive(prev + "." + field.name(), field.schema(), records);
                            }
                        }
                    }
                    break;
                case ARRAY:
                    Schema elementSchema = schema.getElementType();
                    if(isPrimitiveType(elementSchema)) {
                        //records.add(currPath + "/" + elementSchema.getType().toString());
                    } else {
                        outputLeafRecordsRecursive(prev + ".array", schema.getElementType(), records);
                    }
                    break;
                case UNION:
                    if(schema.getTypes().size() != 2) {
                        throw new Exception("number of schema in union is not 2.");
                    }
                    Schema s1 = schema.getTypes().get(0);
                    Schema s2 = schema.getTypes().get(1);
                    if(s1.getType() != Schema.Type.NULL && s2.getType() != Schema.Type.NULL) {
                        throw new Exception("types in union are all not null.");
                    }
                    Schema notNullSchema = s1.getType() != Schema.Type.NULL ? s1 : s2;
                    outputLeafRecordsRecursive(prev + ".union", notNullSchema, records);
                    //outputLeafRecordsRecursive(prev, notNullSchema, records);



            }

    }
    public boolean isLeafRecord(Schema schema) {
        if (schema.getType() != Schema.Type.RECORD) {
            return false;
        }
        for (Schema.Field field : schema.getFields()) {
            if(!isPrimitiveType(field.schema()) && !isNullOrSomeUnion(field.schema())) {
                return false;
            }

        }
        return true;
    }

    public boolean isNullOrSomeUnion(Schema schema) {
        if(schema.getType() == Schema.Type.UNION && schema.getTypes().size() == 2 &&
            (schema.getTypes().get(0).getType() == Schema.Type.NULL || schema.getTypes().get(1).getType() == Schema.Type.NULL)) {
            Schema s1 = schema.getTypes().get(0);
            Schema s2 = schema.getTypes().get(1);
            Schema notNullSchema = s1.getType() != Schema.Type.NULL ? s1 : s2;
            return isPrimitiveType(notNullSchema);
        }
        return false;
    }

    public boolean isNullOrContainerUnion(Schema schema) {
        if(schema.getType() == Schema.Type.UNION && schema.getTypes().size() == 2 &&
           (schema.getTypes().get(0).getType() == Schema.Type.NULL || schema.getTypes().get(1).getType() == Schema.Type.NULL)) {
            Schema s1 = schema.getTypes().get(0);
            Schema s2 = schema.getTypes().get(1);
            Schema notNullSchema = s1.getType() != Schema.Type.NULL ? s1 : s2;
            return !isPrimitiveType(notNullSchema);
        }
        return false;
    }
    public boolean isPrimitiveType(Schema schema) {
        return schema.getType() == Schema.Type.STRING ||
               schema.getType() == Schema.Type.BYTES ||
               schema.getType() == Schema.Type.LONG ||
               schema.getType() == Schema.Type.INT ||
               schema.getType() == Schema.Type.FLOAT ||
               schema.getType() == Schema.Type.DOUBLE ||
               schema.getType() == Schema.Type.BOOLEAN ||
               schema.getType() == Schema.Type.NULL ||
               schema.getType() == Schema.Type.ENUM;
    }

    public boolean isTransparentRecord(Schema schema) {
        if(schema.getType() != Schema.Type.RECORD) {
            return false;
        }
        return HasAtLeastOneType(schema) == false;
    }

    public boolean HasAtLeastOneType(Schema recordSchema) {
        for(Schema.Field field: recordSchema.getFields()) {
            if(isPrimitiveType(field.schema()) || isNullOrSomeUnion(field.schema()))
                return true;
        }
        return false;
    }

    public Node toAnotherSchema(Schema schema, String name) throws Exception, IOException {
        switch (schema.getType()) {
            case STRING:
            case BYTES:
            case LONG:
            case INT:
            case FLOAT:
            case DOUBLE:
            case BOOLEAN:
                return new Node(name, schema.getType().toString());
            case NULL:
                throw new Exception("should not be null");
            case RECORD:
                ArrayList<Node> array = new ArrayList<Node>();
                for (Schema.Field field : schema.getFields()) {
                    array.add(toAnotherSchema(field.schema(), field.name()));
                }
                return new Node(name, array);
            case ARRAY:
                return new Node(name, toAnotherSchema(schema.getElementType(), name.toUpperCase()));
            case UNION:
                if(schema.getTypes().size() != 2) {
                    throw new Exception("number of schema in union is not 2");
                }
                Schema s1 = schema.getTypes().get(0);
                Schema s2 = schema.getTypes().get(1);
                Schema notNullSchema = s1.getType() != Schema.Type.NULL ? s1 : s2;
                return toAnotherSchema(notNullSchema, name);
                // no types in union matches the json type.
            case ENUM:
                return new Node(name, "enum " + schema.getEnumSymbols());
        }
        return null;
    }
}
