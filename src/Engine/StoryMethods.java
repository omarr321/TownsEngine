package Engine;

public enum StoryMethods {
    SCENE(new String[] {"addText", "addOption"}),
    S_START(new String[] {"addTitle", "addDesc", "addText", "addOption"}),
    SAVE_POINT(new String[] {"addText", "link"}),
    TEXT_BLOCK(new String[] {"addText", "link"}),
    DEAD_END(new String[] {"addText"}),
    OPTION(new String[] {"addText", "link"})
    ;

    private final String[] list;
    StoryMethods(String[] list) {
        this.list = list;
    }

    public String[] getList() {
        return list;
    }
}
