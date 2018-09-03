import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class CaesarCipher{

    //reading the file with the encoded message
    static String readFile(Charset encoding)
      throws IOException {
          byte[] encoded = Files.readAllBytes(Paths.get("./message.txt"));
          return new String(encoded, encoding);
    }

    //method to decipher the text
    static String decipher(String original_text, int key){
        StringBuilder deciphered_text = new StringBuilder();

        //loop to scroll through all text characters
        for(int i = 0; i < original_text.length(); i++){
            //getting the x character in i position
            int char_in_ascii = original_text.charAt(i);

            //checking if the character it's a lowercase or uppercase letter
            if(char_in_ascii >= 97 && char_in_ascii <= 122){
                //removing from current character the key passed by while loop in main method
                char_in_ascii -= key;
                //checking if the operation gives a char out of range
                if(char_in_ascii < 97)
                    //correcting the current character
                    char_in_ascii += 26;
                    //add the character to string
                deciphered_text.append((char)char_in_ascii);
            //checking and making the same operations as before
            } else if (char_in_ascii >= 65 && char_in_ascii <= 90){
                char_in_ascii -= key;
                if(char_in_ascii < 65)
                    char_in_ascii += 26;
                deciphered_text.append((char)char_in_ascii);
            } else{
                //if isn't a char of latin alphabetc, just continue
                deciphered_text.append((char)char_in_ascii);
            }
        }
        return deciphered_text.toString();
    }

    public static void main(String[] args) throws IOException {
        int key_size = 1;
        String correct = "n";
        String original_text = readFile(Charset.defaultCharset());
        System.out.println(original_text);
        System.out.println(original_text.length());
        Scanner in = new Scanner(System.in);

        //loop to go through the 26 possibilities
        while(correct.equals("n") && key_size < 26){
            System.out.println("\n##### Do you recognize this text?\n");
            String deciphered_text = decipher(original_text, key_size);
            System.out.println(deciphered_text);
            System.out.println(key_size);
            System.out.print("\n##### s/n?: ");
            correct = in.next();
            key_size++;
        }

        if(key_size != 26)
            System.out.println("\n##### The correct text was modified in "+key_size+" characters");
        else
            System.out.println("\n##### Closing the application without a answer");
    }

}
