
public class Main {
    private static final String infilePath = "inputs/lorem.txt";
    private static final String outfilePath = "inputs/lorem.czip";
    public static void main(String[] args) {
        compareBytesLength(FileExtract.extractor(infilePath));
        FileExtract.compressor(infilePath);
        FileExtract.decompressor(outfilePath);
    }
    public static void compareBytesLength(String input){
        String encoded = Encoder.encoder(input);
        String[] bytes = Utils.get_bytes(input);
        System.out.println(("Encoded equals decoded: \n" + input.equals(Decoder.decoder(encoded))));
        System.out.println("Compressed bits : "+encoded.length());
        System.out.println("Normal bits : "+bytes.length * 8);
        System.out.println("Percentage lost : "+(double) Math.round(((1.0 -((double) (encoded.length())/(bytes.length * 8 )))*100) * 100) / 100 + "%");
    }
}