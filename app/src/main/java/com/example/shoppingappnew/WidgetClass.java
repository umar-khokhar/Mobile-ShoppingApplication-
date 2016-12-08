package com.example.shoppingappnew;


public class WidgetClass {

    private String title;
    private String description;

    public WidgetClass()
    {
        title = "";
        description = "";
    }

    public WidgetClass(String atitle, String adescription)
    {
        title = atitle;
        description = adescription;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String atitle)
    {
        title = atitle;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String adescription)
    {
        description = adescription;
    }


    public String toString()
    {
        String temp;

        temp = title + " " + description ;

        return temp;
    }

}
