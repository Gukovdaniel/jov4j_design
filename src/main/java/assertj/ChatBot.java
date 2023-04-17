package assertj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

    public class ChatBot {
        private ArrayList<String> responses = new ArrayList<String>();
        private Random randomGenerator = new Random();

        public ChatBot(String fileName) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    responses.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getResponse() {
            int index = randomGenerator.nextInt(responses.size());
            return responses.get(index);
        }
    }

