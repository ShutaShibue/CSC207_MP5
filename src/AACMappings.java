import structures.AssociativeArray;
import structures.KeyNotFoundException;

public class AACMappings {
 
  String fileName;
  AssociativeArray<String,AACCategory> aa;
  AACCategory currenCategory;

  public AACMappings(String filename){
    fileName = filename;
    aa = new AssociativeArray<>();

    // make default category
    currenCategory = new AACCategory("default");
    aa.set("default", currenCategory);
  }

  public void add(String imageLoc, String Text){

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
    return currenCategory.getText(imageLoc);
  }

  public boolean isCategory(String imageLoc){
    
  }

  public void reset(){
    try {
      currenCategory = aa.get("default");
    } catch (KeyNotFoundException e) {}
  }

  public void writeToFile(String filename){
    
  }
}
