import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

import structures.AssociativeArray;
import structures.KeyNotFoundException;

public class AACMappings {
 
  String fileName;
  AssociativeArray<String,AACCategory> aa;
  AACCategory topAacCategory;
  AACCategory currenCategory;

  public AACMappings(String filename){
    fileName = filename;
    aa = new AssociativeArray<>();

    // make default category
    topAacCategory = new AACCategory("");
    currenCategory = topAacCategory;
    aa.set("", currenCategory);

    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] datas = data.split(" ");
        // Category
        if(!data.startsWith(">")){
          currenCategory = new AACCategory(datas[1]);
          topAacCategory.aa.set(datas[0], datas[1]);
          aa.set(datas[0], currenCategory);

        }
        else{
          currenCategory.aa.set(datas[0].replace(">", ""), String.join(" ",Arrays.copyOfRange(datas, 1, datas.length)));
        }

      }
      myReader.close();
      reset();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void add(String imageLoc, String Text){
    currenCategory.aa.set(imageLoc, Text);
  }

  /**
   * Gets the current category
   * @return
   */
  public String getCurrentCategory()
  {
    return currenCategory.name;
  }

  /**
   * Provides an array of all the images in the current category
   * @return
   */
  public String[] getImageLocs(){
    return currenCategory.getImages();
  }

  /**
   * Given the image location selected, it determines the associated text with the image.
   * @param imageLoc
   * @return
   */
  public String getText(String imageLoc){
    if(isCategory(imageLoc)){
      try {
        AACCategory cat = aa.get(imageLoc);
        currenCategory = cat;
        return cat.name;
      } catch (KeyNotFoundException e) {
        throw new ElementNotFoundException("Image was not found");
      }
    }
    else return currenCategory.getText(imageLoc);
  }

  public boolean isCategory(String imageLoc){
    return aa.hasKey(imageLoc);
  }

  public void reset(){
    try {
      currenCategory = aa.get("");
    } catch (KeyNotFoundException e) {}
  }

  public void writeToFile(String filename){
    try {
      PrintWriter pen = new PrintWriter(new File("test.txt"));

      for (String category : aa.keys()) {
        if(category == "") continue;
        AACCategory cat = aa.get(category);
        pen.write(category + " " + cat.name + "\n");

        for (String locString : cat.getImages()) {
          pen.write(">" + locString + " " + cat.getText(locString) + "\n");
        }
      }
      pen.close();
    } catch (Exception e) {
    }
  }
}