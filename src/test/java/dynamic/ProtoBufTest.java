package dynamic;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author wenbaoxie
 * @Date / /
 */
public class ProtoBufTest {

  /*  public static void main(String[] args) {

        Cinema.Movie.Builder movieBuilder = Cinema.Movie.newBuilder();
        movieBuilder.setName("The Shining");
        movieBuilder.setType(Cinema.MovieType.ADULT);
        movieBuilder.setReleaseTimeStamp();

        System.out.println("Dynamic Message Parse by proto file");
        try {
            byte[] buffer = new byte[movieBuilder.build().getSerializedSize()];
            CodedOutputStream codedOutputStream = CodedOutputStream.newInstance(buffer);
            try {
                movieBuilder.build().writeTo(codedOutputStream);
                System.out.println(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String protocCMD = "protoc --descriptor_set_out=cinema.description ./cinema.proto --proto_path=.";
            Process process = Runtime.getRuntime().exec(protocCMD);
            process.waitFor();
            int exitValue = process.exitValue();
            if (exitValue !=1) {
                System.out.println("protoc execute failed");
                return;
            }
            Descriptors.Descriptor pbDescritpor = null;
            DescriptorProtos.FileDescriptorSet descriptorSet = DescriptorProtos.FileDescriptorSet
                    .parseFrom(new FileInputStream("./cinema.description"));
            for (DescriptorProtos.FileDescriptorProto fdp : descriptorSet.getFileList()) {
                Descriptors.FileDescriptor fileDescriptor = Descriptors.FileDescriptor
                        .buildFrom(fdp, new Descriptors.FileDescriptor[]{});
                for (Descriptors.Descriptor descriptor : fileDescriptor.getMessageTypes()) {
                    if (descriptor.getName().equals("Movie")) {
                        System.out.println("Movie descriptor found");
                        pbDescritpor = descriptor;
                        break;
                    }
                }
            }
            if (pbDescritpor == null) {
                System.out.println("No matched descriptor");
                return;
            }
            DynamicMessage.Builder pbBuilder = DynamicMessage.newBuilder(pbDescritpor);

            Message pbMessage = pbBuilder.mergeFrom(buffer).build();
            System.out.println(pbMessage);

        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }*/

}
