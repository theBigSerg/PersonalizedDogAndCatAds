/*
 * Problem 2.3.1 Sell My Pet Food
 * 
 * V1.0
 * 6/1/2019
 * Copyright(c) 2019 PLTW to present. All rights reserved
 */
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.*;

/**
 * A DataCollector class to manage social media posts
 */
public class DataCollector
{
  private ArrayList<String> socialMediaPosts;
  private ArrayList<String> targetWords;
  private Scanner sc;
  private int currentPost;
  private int currentTargetWord;
  private ArrayList<String> identifiedWords;
  private ArrayList<String> catWords;
  private ArrayList<String> dogWords;
  private ArrayList<String> dogPeople;
  private ArrayList<String> catPeople;
  private ArrayList<Integer> indexesUsed;
  private boolean duplicate;

  public DataCollector()
  {
    socialMediaPosts = new ArrayList<String>();
    targetWords = new ArrayList<String>();
    currentPost = 0;
    currentTargetWord = 0;
    identifiedWords = new ArrayList<String>();
    catWords = new ArrayList<String>();
    dogWords = new ArrayList<String>();
    catPeople = new ArrayList<String>();
    dogPeople = new ArrayList<String>();
    indexesUsed = new ArrayList<Integer>();

  }

  /**
   * Gather the data contained in the files socialMediaPostsFilename and
   * targetWordsFilename (including punctuation), with words separated by a single
   * space
   * 
   * @param socialMediaPostsFilename the name of the file containing social media posts
   * @param targetWordsFilename the name of the file containing the target words
   */
  public void setData(String socialMediaPostsFilename, String targetWordsFilename) {
    // read in the social media posts found in socialMediaPosts
    // a try is like an if statement, "throwing" an error if the body of the try fails
    try
    {
      sc = new Scanner(new File(socialMediaPostsFilename));
      while (sc.hasNextLine())
      {
        // String method trim removes whitespace before and after a string
        String temp = sc.nextLine().trim();
        //System.out.println(temp);
        this.socialMediaPosts.add(temp);
      }
    } catch (Exception e) { System.out.println("Error reading or parsing" + socialMediaPosts + "\n" + e); }

    // read in the target words in targetWords
    try
    {
      sc = new Scanner(new File(targetWordsFilename));
      while (sc.hasNextLine())
      {
        // String method trim removes whitespace before and after a string
        this.targetWords.add(sc.nextLine().trim());
      }
    } catch (Exception e) { System.out.println("Error reading or parsing" + targetWords + "\n" + e); }
  }

  /**
   * Get the next post in socialMediaPosts with words separated by a single space, 
   * or "NONE" if there is no more data.
   * 
   * @return a string containing one of the lines in socialMediaPosts
   */
  public String getNextPost()
  {
    if (currentPost < socialMediaPosts.size())
    {
      this.currentPost++;
      return socialMediaPosts.get(currentPost - 1);
    }
    else
    {
      return "NONE";
    }
  }

  /**
   * Get the next line in targetWords, with words separated by a space,
   * or "NONE" if there is no more data.
   * 
   * @return a string containing one of the lines in targetWords
   */
  public String getNextTargetWord()
  {
    if (currentTargetWord < targetWords.size())
    {
      this.currentTargetWord++;
      return targetWords.get(currentTargetWord - 1);
    }
    else
    {
      this.currentTargetWord = 0;
      return "NONE";
    }
  }

  /**
   * Create a File named filename and stores all the usernames to target
   * 
   * @param filename The name to save the file, must include .txt
   * @param usernames A string containing the usernames of people to target,
   * usernames are separated by a space.
   */
  public void prepareAdvertisement(String filename, String usernames, String advertisement)
  {
    try
    {
      FileWriter fw = new FileWriter(filename);
      // Strin method split splits a string based on the provided token
      // and returns an array of individual substrings
      for (String un : usernames.split(" "))
      {
          fw.write("@" + un + " " + advertisement +"\n");
      }
      fw.close();
    }
    catch (IOException e)
    {
        System.out.println("Could not write to file. " + e);
    }
  }

  /**
   * Print the array of posts
   */
  public void printAllPosts()
  {
    for (String post : this.socialMediaPosts)
      System.out.println(post);
  }

  /**
   * Print the array of target words
   */
  public void printAllTargetWords()
  {
    for (String word : this.targetWords)
      System.out.println(word);
  }

  public void containWords(){
    for(String post : this.socialMediaPosts){
      //System.out.println(post);
      for(String word : this.targetWords){
        //System.out.println(word);
        if(post.toLowerCase().indexOf(word.toLowerCase())>=0){
          //System.out.println(post);
          //System.out.println(word);
          identifiedWords.add(post);
          identifiedWords.add(word);
          
        }
      }
    }
    //System.out.println(identifiedWords.toString());
    //System.out.println(identifiedWords.size());
    //System.out.println(identifiedWords.get(55));
  }

  public void setDogAndCat(){
    dogWords.add("dog");
    //dogWords.add("doggie");
    catWords.add("cat");
    catWords.add("kitt");
    dogWords.add("wag");
    dogWords.add("fur");
    dogWords.add("bark");
    catWords.add("meow");
    catWords.add("purr");
    dogWords.add("pup");
    catWords.add("litter");
    dogWords.add("fluffy");
    dogWords.add("energetic");
    catWords.add("lazy");
    catWords.add("whisker");
    catWords.add("tail");
    dogWords.add("tail");
    dogWords.add("obedient");
    dogWords.add("happy");
    catWords.add("mean");
    catWords.add("mischevious");
    dogWords.add("cute");
    catWords.add("cute");
    dogWords.add("huggable");
    catWords.add("animal");
    dogWords.add("animal");
    dogWords.add("treat");
    dogWords.add("chew");
    dogWords.add("wolf");
  }

  public void animalDivision(){
    setDogAndCat();
    for(int i = 0; i < identifiedWords.size()-1; i+=2){
      System.out.println(i);
      for(String dogs : dogWords){
        if(dogs.equals(identifiedWords.get(i+1))){
          for(int j = 0; j < indexesUsed.size()-1; j++){
            if(i+1 == indexesUsed.get(j)){
              duplicate = true;
            }
          }
          if(duplicate == false){
          String personDog = identifiedWords.get(i).substring(0, identifiedWords.get(i).indexOf(" "));
          dogPeople.add(personDog);
        }
        indexesUsed.add(i+1);
        }
      }
      for(String cats : catWords){
        if(cats.equals(identifiedWords.get(i+1))){
          for(int j = 0; j < indexesUsed.size()-1; j++){
            if(i+1 == indexesUsed.get(j)){
              duplicate = true;
            }
          }
          if(duplicate == false){
            String personCat = identifiedWords.get(i).substring(0, identifiedWords.get(i).indexOf(" "));
            catPeople.add(personCat);
        }
        indexesUsed.add(i+1);

          
        }
      }
    }
  //System.out.println(dogPeople.toString());
  //System.out.println(catPeople.toString());
  }

  public void catAdvertisement(){
    System.out.println("\tAre you looking for healthier cat food at a cheaper rate?");
    System.out.println("Well you are in luck! Our cat food comes in 5 different mixes and sizes.");
    System.out.println("Visit Good Boy Food's website to learn more or buy our product.");
  }

  public void dogAdvertisement(){
    System.out.println("\tAre you looking for healthier cat food at a cheaper rate?");
    System.out.println("Well you are in luck! Our dog food comes in 5 different mixes and sizes.");
    System.out.println("Visit Good Boy Food's website to learn more or buy our product.");
  }

  public void personalizedAd(){
    for(String dogs : dogPeople){
      System.out.println("Dear "+dogs+",");
      System.out.println("\n\n");
      dogAdvertisement();
      System.out.println("-------------------------------");
    }
    for(String cats : catPeople){
      System.out.println("Dear "+cats+",");
      System.out.println("\n\n");
      catAdvertisement();
      System.out.println("-----------------------------");
    }
  }
}