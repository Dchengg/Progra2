package com.example.poo.progra2.xml;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlParser {


    public void parseXml(InputStream inputStream) throws XmlPullParserException,IOException{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, null);
        String text = "";
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            String tag = parser.getName();
            switch (eventType){
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if(tag.equalsIgnoreCase("id")){

                    }
                break;
            }
            eventType = parser.next();
        }

    }
}
