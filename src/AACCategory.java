import structures.AssociativeArray;

public class AACCategory {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  String name;
  AssociativeArray<String, String> aa;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+


  /**
   * Create object with name.
   */
  public AACCategory(String _name) {
    name = _name;
    aa = new AssociativeArray<String, String>();
  }//constructor AACCategory(String)

  // +------------------+--------------------------------------------
  // | Standard methods |
  // +------------------+

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   * 
   * @param imageLoc path of image
   * @param text
   */
  public void addItem(String imageLoc, String text) {
    aa.set(imageLoc, text);
  } // addItem(String, String)

  /**
   * Returns the name of category specified in constructor.
   * 
   * @return String
   */
  public String getCategory() {
    return this.name;
  } // getCategory()

  /**
   * Returns an array of all the images in the category
   * 
   * @return
   */
  public String[] getImages() {
    return aa.keys();
  } // getImages()

  /**
   * Returns the text associated with the given image loc in this category
   * 
   * @param imageLoc path to image
   * @return String text associated with image
   */
  public String getText(String imageLoc) {
    try {
      return aa.get(imageLoc);
    } catch (Exception e) {
      return "Error: Could not find text.";
    }
  } // getText(String)

  /**
   * Determines if the provided images is stored in the category
   * 
   * @param imageLoc path to image
   * @return boolean
   */
  public boolean hasImage(String imageLoc) {
    return aa.hasKey(imageLoc);
  } // hasImage(String)

} // class AACCategory
