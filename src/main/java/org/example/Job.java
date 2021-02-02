package org.example;

public class Job {

    private String title;

    public Job() {
        this.title = "no job title";
    }
    public Job(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                '}';
    }
}
