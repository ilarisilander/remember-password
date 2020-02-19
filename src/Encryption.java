public class Encryption implements Crypto {


    @Override
    public byte[] encrypt(byte[] data) {
        byte[] enc = new byte[data.length];

        for(int i = 0; i < data.length; i++) {
            if(i % 2 == 0) {
                enc[i] = (byte) (data[i] + 1);
            }
            else{
                enc[i] = (byte)(data[i] - 1);
            }
        }
        return enc;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] dec = new byte[data.length];

        for(int i = 0; i < data.length; i++) {
            if(i % 2 == 0) {
                dec[i] = (byte) (data[i] - 1);
            }
            else{
                dec[i] = (byte)(data[i] + 1);
            }
        }
        return dec;
    }
}
