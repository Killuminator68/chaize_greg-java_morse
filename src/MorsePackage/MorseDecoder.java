package MorsePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MorseDecoder {
    
    // Tableau des codes Morse
    private static final String[] MORSE_CODES = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                                                 ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", 
                                                 ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", 
                                                 ".----", "..---", "...--", "....-", ".....", "-....", "--...", 
                                                 "---..", "----.", "-----", "/", ""};
    // Tableau des lettres correspondantes
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                                             "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
                                             "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " ", ""};
    
    public static void main(String[] args) {
        // Noms des fichiers d'entrée et de sortie
        String inputFileName = "morse.txt";
        String outputFileName = "morse_decoded.txt";
        
        // Décode le texte Morse du fichier d'entrée
        String decodedText = decodeMorseFromFile(inputFileName);
        // Enregistre le texte décodé dans le fichier de sortie
        saveDecodedMorseToFile(decodedText, outputFileName);
        
        // Affiche le texte décodé dans la console
        System.out.println(decodedText);
    }
    
    // Décode une chaîne de caractères en Morse et retourne la chaîne décodée
    private static String decodeMorse(String morseCode) {
        StringBuilder sb = new StringBuilder();
        String[] words = morseCode.trim().split(" ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                int index = findIndex(letter);
                if (index != -1) {
                    sb.append(LETTERS[index]);
                }
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    
    // Trouve l'index d'un code Morse dans le tableau MORSE_CODES
    private static int findIndex(String morseCode) {
        for (int i = 0; i < MORSE_CODES.length; i++) {
            if (MORSE_CODES[i].equals(morseCode)) {
                return i;
            }
        }
        return -1;
    }
    
    // Décode le texte Morse du fichier d'entrée et retourne le texte décodé
    private static String decodeMorseFromFile(String inputFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(decodeMorse(line)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    // Enregistre le texte Morse décodé dans un fichier de sortie
    private static void saveDecodedMorseToFile(String decodedText, String outputFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            // Écrire le texte décodé dans le fichier de sortie
            bw.write(decodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
