package Backend.Utils;

public class Helpers {
    public int generator(){
        int min = 1;
        int max = 1000000000;
        int randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);

        return randomNumber;
    }
}
