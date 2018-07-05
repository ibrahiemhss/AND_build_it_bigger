package com.example.javaprovider;

import java.util.Random;

public class GetJokeFromJava {

    private String[] getAllJokes() {

        return new String[]
                {"Wikipedia: I know everything! \n" +
                        "Google: I have everything! \n" +
                        "Facebook: I know everybody! \n" +
                        "Internet: Without me you are nothing!\n" +
                        "Electricity: Keep talking bitches!"

                        , "Girls are like Internet Domain names, the ones I like are already taken."

                        , "A system administrator has 2 problems:\n" +
                        "- dumb users\n" +
                        "- smart users"

                        , "Q: How many programmers does it take to change a light bulb?\n" +
                        "A: None, that's a hardware problem."

                        , "Programmer.\n" +
                        "A machine that turns coffee into code."

                        , "Why was the computer tired when he got home? \n" +
                        "Because he had a hard drive."

                        , "Programmer.\n" +
                        "A person who fixed a problem that you don't know you have, in a way you don't understand."

                        , "A programmer had a problem.\n" +
                        "He decided to use Java.\n" +
                        "He now has a ProblemFactory."

                        , "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\""

                        , "Q: How many Object Oriented programmers does it take to change a lightbulb?\n" +
                        "A: None, they send it a message, and it changes itself."

                        , "Q: Why do Java programmers have to wear glasses?\n" +
                        "A: Because they don't C#."

                        , "I had a programming problem and decided to use regular expressions to solve it.\n" +
                        "Now I have two problems."

                        , "Algorithm.\n" +
                        "Word used by programmers when they don't want to explain what they did."

                        , "Once upon a time, a computer programmer drowned at sea. \n" +
                        "Many were on the beach and heard him cry out, “F1! F1!”, but no one understood."

                        , "What's an extroverted IT professional? \n" +
                        "One who looks at your shoes while he's talking to you, instead of his own."

                        , "How do two programmers make money?\n" +
                        "One writes viruses, the other anti-viruses."};
    }

    public String tellJoke() {
        int idx = new Random().nextInt(getAllJokes().length);
        return (getAllJokes()[idx]);
    }
}
