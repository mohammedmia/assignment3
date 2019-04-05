/* 
   This program counts the frequency of words from the text file "lyrics.txt", then stores the frequency of each word into the "output.txt" file.
   
   CISC 3130 MY9
   Mohammed Mia */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

class frequencyList
{
// Sorts by value method
// https://beginnersbook.com/2014/07/how-to-sort-a-treemap-by-value-in-java/ 
public static <K, V extends Comparable<V>> Map<K, V> sortByValues (final Map<K, V> map) {
   Comparator<K> valueComparator = new Comparator<K>() {
      public int compare(K k1, K k2) {
         int compare = map.get(k1).compareTo(map.get(k2));
         if (compare == 0)
            return 1;
            else
               return compare;
      }
   };
               
   Map<K, V> sortedByValues = new TreeMap<K, V> (valueComparator);               
      sortedByValues.putAll(map);
      return sortedByValues;
}
     
public static void main (String[] args) throws Exception {
   
{
   // https://www.youtube.com/watch?v=UDLDWklX2YE
   /* Accesses file using file path */
      Path path = Paths.get(System.getProperty("user.dir")).resolve("lyrics.txt");
   
   /* Reads text file from working directory */               
      BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
      
   /* Map created to count frequency of string values */      
      Map <String , Integer> frequency = new HashMap<>();
   
   /* Reads line */  
      String line = reader.readLine();
      while(line !=null) {
         if(!line.trim().equals("")){ // Removes white space and indicates seperation between words
            String [] words = line.split(" ");   
            
            for(String word : words) {
               if(word == null || word.trim().equals("")){ // Ignores empty words
                  continue;
                  }
            /* Changes words to lowercase, and removes punctuation marks */
               String processed = word.toLowerCase();
               processed = processed.replace(",","");
               processed = processed.replace("!","");
               processed = processed.replace("\"","");
               processed = processed.replace(")","");
               processed = processed.replace("(","");
               processed = processed.replace(".","");
               processed = processed.replace("?","");
            
            /* Checks Map (frequency) for the word and increments its value (frequency of the word), and replaces the value if the word exists */  
               if(frequency.containsKey(processed)) {
                  frequency.put(processed, frequency.get(processed) + 1);
               } else {
                  frequency.put(processed, 1);
               }   
            } 
               line = reader.readLine(); // Reads next line
        }
     } 
   
   // Creates output file
      File outputFile = new File("output.txt");
   // Checks if files exists, if not, a file is created
      if(!outputFile.exists()) {
        outputFile.createNewFile();
        }       
   // sortByValues method is called        
        Map sortedMap = sortByValues(frequency);
   // Set is created to store sorted data        
        Set set = sortedMap.entrySet();
   // Iterator is created to iterate through data         
        Iterator i = set.iterator();
   // PrintWriter to write into the text file
      PrintWriter pw = new PrintWriter(outputFile);        
        while(i.hasNext()) {
        Map.Entry me = (Map.Entry)i.next();
        pw.println(me.getValue() + ":\t" + me.getKey()); // Writes the value (word count) and the word itself inside the "output.txt" file
        }
        pw.close();
      }
        System.out.println("Message: Frequency of words was saved to \"output.txt\"");
   } 
}
   
