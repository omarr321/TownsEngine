package Engine;

public class StoryObject {
    private String name;
    private StoryMethods type;

    public StoryObject(String name, StoryMethods type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public StoryMethods getType() {
        return type;
    }
}
