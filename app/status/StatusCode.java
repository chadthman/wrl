package status;

public enum StatusCode {
	
  ADD_SUCCESS(true, "Successfully added a bookmark."),
  ADD_DUPLICATE_BOOKMARK(false, "Cannot add bookmarks with duplicate url."),
  ADD_NULL_BOOKMARK(false, "Cannot add null bookmark."),
  
  REMOVE_SUCCESS(true, "Successfully removed a bookmark."),
  REMOVE_NULL_BOOKMARK(false, "Cannot remove null bookmark."),
  
  TOGGLE_SUCCESS(true, "Successfully toggle a bookmark."),
  TOGGLE_NULL_BOOKMARK(false, "Cannot toggle null bookmark.");
  
  private final Boolean success;
  private final String description;

  private StatusCode(Boolean success, String description) {
    this.success = success;
    this.description = description;
  }

  public String getDescription() {
     return description;
  }

  public boolean getSuccess() {
     return success;
  }

  @Override
  public String toString() {
    return description;
  }
}
