public class Link {
    private String start;
    private String finish;
    private String tag;

    public Link(String start, String tag, String finish) {
        this.start = start;
        this.tag = tag;
        this.finish = finish;
    }    

    public String getStart() {
        return this.start;
    }

    public String getFinish() {
        return this.finish;
    }

    public String getTag() {
        return this.tag;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void print() {
        System.out.println("(" + this.start + "," + this.tag + "," + this.finish + ")");
    }
}
