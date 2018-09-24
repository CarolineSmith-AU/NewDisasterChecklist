package cls0097.auburn.edu.newdisasterchecklist;

public class ListItem2 {

    private String item;
    private String description;

    private String count;


    public ListItem2(String item, String description, String count) {
        this.item = item;
        this.description = description;

        this.count = count;

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
