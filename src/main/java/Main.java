import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
/**
 * Created by lniu on 9/21/15.
 */

public class Main {

    // List<T> traverseJson(String p, JsonNode node, T.class)
    //
    public void test() {
        try {
            Schema.Parser parser = new Schema.Parser();
            Schema testSchema = parser.parse(new File("./src/avro/testSchema"));
            GenericRecordBuilder recordBuilder = new GenericRecordBuilder(testSchema);
            GenericRecord record = recordBuilder.set("first", "Lu").set("last", "Niu").build();

            File file = new File("users.avro");
            DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(testSchema);
            DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
            dataFileWriter.create(testSchema, file);
            dataFileWriter.append(record);
            dataFileWriter.close();

            DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(testSchema);
            DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
            GenericRecord user = null;
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves us from
                // allocating and garbage collecting many objects for files with
                // many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void TestUserProfile() {
        try {
            Schema.Parser parser = new Schema.Parser();
            Schema userProfileSchema = parser.parse(new File("./src/avro/finalUserProfile"));
            DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(userProfileSchema);
            DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("./two_users.avro"), datumReader);
            GenericRecord user = null;
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves us from
                // allocating and garbage collecting many objects for files with
                // many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void TestSchema() {
        try {
            Schema.Parser parser = new Schema.Parser();
            Schema userProfileSchema = parser.parse(new File("./src/avro/finalUserProfile"));
            Schema testSchema = parser.parse(new File("./src/avro/testSchema"));
            // set enum type
            Schema arraySchema = parser.parse(new File("./src/avro/arraySchema"));
            GenericRecordBuilder arrayRecordBuilder = new GenericRecordBuilder(arraySchema);


            // JsonNode root = getJsonFromString("{\"first\":\"John\",\"middle\":\"X\",\"last\":\"Doe\"}");
            GenericRecordBuilder recordBuilder = new GenericRecordBuilder(testSchema);
            GenericRecord record = recordBuilder.set("first", "Lu").set("last", "Niu").build();

            File file = new File("users.avro");
            DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(testSchema);
            DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
            dataFileWriter.create(testSchema, file);
            dataFileWriter.append(record);
            dataFileWriter.close();

            DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(testSchema);
            DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
            GenericRecord user = null;
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves us from
                // allocating and garbage collecting many objects for files with
                // many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonNode getJsonFromString(String str) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode root = objectMapper.readTree(str);
        return root;
    }

    private static JsonNode traverseJson(String p, JsonNode node) {
        if (node == null) {
            return null;
        }
        Path path = Paths.get(p);
        // leaf node
        if (path.getNameCount() == 1) {
            return node.get(path.getName(0).toString());
        } else {
            JsonNode child = node.get(path.getName(0).toString());
            return traverseJson(path.subpath(1, path.getNameCount()).toString(), child);
        }
    }

    public static void main(String[] args) {
        TestSchema();
        TestUserProfile();
        String input = "{\"intervention\":\n"
                       + "\n"
                       + "    { \n"
                       + "      \"id\":\"3\",\n"
                       + "              \"subject\":\"dddd\",\n"
                       + "              \"details\":\"dddd\",\n"
                       + "              \"beginDate\":\"2012-03-08T00:00:00+01:00\",\n"
                       + "              \"endDate\":\"2012-03-18T00:00:00+01:00\",\n"
                       + "              \"campus\":\n"
                       + "                       { \n"
                       + "                         \"id\":\"2\",\n"
                       + "                         \"name\":\"paris\"\n"
                       + "                       }\n"
                       + "           }\n"
                       + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        try {
            /*/
            dataMap.putAll(objectMapper.readValue(input, Map.class));
            HashMap<String, Object> identityMap = (HashMap<String, Object>)dataMap.get("identity");
            System.out.println(identityMap.get("cookie").toString());
            */

            JsonNode root = objectMapper.readTree(input);
            JsonNode intervention = traverseJson("/intervention", root);
            JsonNode id = traverseJson("intervention/id", root);
            JsonNode alsoId = traverseJson("/id", intervention);
            JsonNode campus_name = traverseJson("/intervention/campus/name", root);
            System.out.println(id.asText() + "  " + alsoId.asText() + "  " + campus_name.asText());
            /*
            JsonNode root = objectMapper.readTree(input);
            String identity = root.get("identity").get("cookie").asText();
            JsonNode segments = root.get("segments").get("data");
            Iterator<JsonNode> segmentInterator = segments.elements();
            while(segmentInterator.hasNext()) {
                JsonNode node = segmentInterator.next();
                JsonNode catlist = node.get("catlist");
                Iterator<JsonNode> catListIterator = catlist.elements();
                while(catListIterator.hasNext()) {
                    JsonNode segment = catListIterator.next();
                    System.out.println(segment.get("cat").asLong() + "  " + segment.get("exp").asText());

                }
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
