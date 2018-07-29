package net.pupunha.poc.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.NativeProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schemagen.ProtobufSchemaGenerator;
import com.google.common.collect.Sets;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

//        ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());

        Employee employee = Employee.builder()
                .name("Will")
                .age(33)
                .boss(null)
                .emails(new String[]{"will.lobato@gmail.com"})
                .policy(Sets.newHashSet(Policy.builder().name("TESTE").build()))
                .build();

//        byte[] protobufData = mapper.writer(schema)
//                .writeValueAsBytes(employee);

        ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());
        ProtobufSchemaGenerator gen = new ProtobufSchemaGenerator();
        mapper.acceptJsonFormatVisitor(Employee.class, gen);
        ProtobufSchema schemaWrapper = gen.getGeneratedSchema();
        NativeProtobufSchema nativeProtobufSchema = schemaWrapper.getSource();

        String asProtofile = nativeProtobufSchema.toString();

        System.out.println(asProtofile);

//        String fileName = args[0];

        FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
        fileOutputStream.write(asProtofile.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

//        Object v = null;
//        v.notify();

    }
}
