package com.cognizant.refreshlist.com.cognizant.utils;

import java.util.ArrayList;

/**
 * Created by Thamil on 11/26/2015.
 *
 *
 *
 * The Class is to handle All the data retrieved from the server
 */
public class ListviewDataModel {

    private ArrayList<String> titleList=new ArrayList<>();
    private ArrayList<String> descriptionList=new ArrayList<>();
    private ArrayList<String> imageUrlList=new ArrayList<>();

    public void setTitleList(String title)
    {
        titleList.add(title);
    }

    public void setDescriptionList(String desc)
    {
        descriptionList.add(desc);
    }

    public void setImageUrlList(String url)
    {
        imageUrlList.add(url);
    }

    public ArrayList<String> getTitle()
    {
        return titleList;
    }

    public ArrayList<String> getDescription()
    {
        return descriptionList;
    }

    public ArrayList<String> getImagtUrl()
    {
        return imageUrlList;
    }
    public void clearData()
    {
        titleList.clear();
        descriptionList.clear();
        imageUrlList.clear();
    }
}
