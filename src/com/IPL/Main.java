package com.IPL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("matches.csv");
        BufferedReader br=new BufferedReader(fr);
        String s;
        String []st;
        TreeMap<Integer,String[]> matches=new TreeMap<Integer,String[]>();
        TreeMap<String,Integer> p1=new TreeMap<String,Integer>();
        LinkedHashMap<String,Integer> p2=new LinkedHashMap<String,Integer>();
        LinkedHashMap<String,Integer> p3=new LinkedHashMap<String,Integer>();
        LinkedHashMap<String,String> id2016=new LinkedHashMap<String,String>();
        LinkedHashMap<String,String> id2015=new LinkedHashMap<String,String>();
        LinkedHashMap<Integer,String[]> deliveries=new LinkedHashMap<Integer,String[]>();
        LinkedHashMap<String,Integer> p4p=new LinkedHashMap<String,Integer>();
        LinkedHashMap<String,Integer> p4o=new LinkedHashMap<String,Integer>();
        LinkedHashMap<String,Float> p4=new LinkedHashMap<String,Float>();
        int i=0;
        while((s=br.readLine())!=null)
        {
            st=s.split(",");
            matches.put(i,st);
            //problem 1
            if(i>0)
                p1.put(st[1],p1.getOrDefault(st[1],0)+1);

            //problem 2
            if(i>0&& !st[10].isEmpty())
                p2.put(st[10],p2.getOrDefault(st[10],0)+1);


            //problem3
            if(st[1].equals("2016"))
                id2016.put(st[0],st[0]);

            //prablem4
            if(st[1].equals("2015"))
                id2015.put(st[0],st[0]);
            i++;
        }
        fr.close();
        br.close();
        fr=new FileReader("deliveries.csv");
        br=new BufferedReader(fr);
        i=0;
        String p="";
        while((s=br.readLine())!=null)
        {
            st = s.split(",");
            deliveries.put(i,st);
            if(i>0 && id2016.containsKey(st[0]))
                p3.put(st[3],p3.getOrDefault(st[3],0)+Integer.parseInt(st[16]) );
            //problem4
            String c=st[8];
            if(i>0 && id2015.containsKey(st[0])) {
                if (i > 0 && !p.equals(c))
                    p4o.put(st[8], p4o.getOrDefault(st[8], 0) + 1);
                p = c;
                if (i > 0)
                    p4p.put(st[8], p4p.getOrDefault(st[8], 0) + Integer.parseInt(st[17])-Integer.parseInt(st[11])-Integer.parseInt(st[12])-Integer.parseInt(st[14]));
            }
            i++;
        }
        float min=Float.MAX_VALUE;
        String smin="";
        for(String str:p4o.keySet())
        {
            float avg= ((float)p4p.get(str))/p4o.get(str);
            p4.put(str,avg);
            if(avg<=min)
            {
                min=avg;
                smin=str;
            }
        }
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.print(smin +" "+min);

    }
}
