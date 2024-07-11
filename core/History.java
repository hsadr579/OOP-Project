package core;

import java.util.ArrayList;

public class History {
    public static int page;
    static ArrayList<String> pages;
    static String[] data;

    public static void createTable()
    {
        data=DB.getGamesHistorySortedByOpponent(Session.getInstance().getLoggedUser());
        pages.clear();
        String temp="";
        int j=0;
        for(String i:data)
        {
            temp+=i+"\n";
            j++;
            if(j>10)
            {
                j=0;
                pages.add(temp);
                temp="";
            }
        }
        page=1;
        show();
    }
    public static void setPage(int p)
    {
        if(p<=pages.size())
        {
            page=p;
        }
        show();
    }
    public static void next()
    {
        if(page+1<=pages.size())
        {
            page++;
        }
        show();
    }

    public static void prev()
    {
        if(page-1>0)
        {
            page--;
        }
        show();
    }
    public static void show()
    {
        Session.getInstance().setOutput(pages.get(page-1));
    }
    public static void sort()
    {
        data=DB.getGamesHistorySortedByWin(Session.getInstance().getLoggedUser(),true);
        pages.clear();
        String temp="";
        int j=0;
        for(String i:data)
        {
            temp+=i+"\n";
            j++;
            if(j>10)
            {
                j=0;
                pages.add(temp);
                temp="";
            }
        }
        page=1;
        show();
    }
}
