package status;

public enum StatusCode {
	
  ADD_SUCCESS(true, "Successfully added a bookmark."),
  ADD_DUPLICATE_BOOKMARK(false, "Cannot add items with duplicate name."),
  ADD_NULL_BOOKMARK(false, "Cannot add null item.");

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
