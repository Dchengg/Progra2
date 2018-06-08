package com.example.poo.progra2.xml;


import android.content.Context;

import android.os.Environment;

import android.util.Log;
import android.util.Xml;


import com.example.poo.progra2.modelo.Practicante;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class PracticanteDAO  {

    private static String fileName =  "usuarios.xml";
    private File file;
    public static ArrayList<Practicante> practicantes = new ArrayList<Practicante>();

    public PracticanteDAO(Context context){
        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            Log.d("PracticanteDao", "External storage not available or you don't have permission to write");
        }else{
            file =  new File(context.getFileStreamPath(fileName).getPath());
            Log.d("PracticanteDao", context.getFileStreamPath(fileName).getPath());
            //Practicante nuevo = new Practicante("Diego","2017097666","123","31/3/99", "Santa ana", "ProfAsesor@gmail.com","ProfCurso@gmail.com","Google");
            //practicantes.add(nuevo);
        }
    }


    public void parseXml() throws XmlPullParserException,IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(fileInputStream, null);
        Practicante practicante = null;
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            String tag = parser.getName();
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    Log.d("PracticanteDao", "El xml empezo a leerse");
                    break;
                case XmlPullParser.START_TAG:
                    if(tag.equalsIgnoreCase("practicante")){
                        Log.d("PracticanteDAO","Practicante nuevo creado");
                        practicante = new Practicante();
                    }else if(practicante != null){
                        if(tag.equalsIgnoreCase("nombre")){
                            practicante.setNombre(parser.nextText());
                        }else if(tag.equalsIgnoreCase("carnet")){
                            practicante.setCarnet(parser.nextText());
                        }else if(tag.equalsIgnoreCase("contrasena")){
                            practicante.setContrasena(parser.nextText());
                        }else if(tag.equalsIgnoreCase("fecha")){
                            practicante.setFechaDeNacimiento(parser.nextText());
                        }else if(tag.equalsIgnoreCase("direccion")){
                            practicante.setDireccion(parser.nextText());
                        }else if(tag.equalsIgnoreCase("correoCurso")) {
                            practicante.setCorreoProfCurso(parser.nextText());
                        }else if(tag.equalsIgnoreCase("correoAsesor")) {
                            practicante.setCorreoProfAsesor(parser.nextText());
                        }else if(tag.equalsIgnoreCase("empresa")){
                            practicante.setEmpresa(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(tag.equalsIgnoreCase("practicante") && practicante != null){
                        Log.d("PracticanteDAO", "Practicante agregado a la lista");
                        practicantes.add(practicante);
                    }
                    break;
            }
            eventType = parser.next();
        }

    }

    public void writeXML(){
        try{
            FileOutputStream fileOutputStream =  new FileOutputStream(file);
            XmlSerializer xmlSerializer  = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"root");
            for(Practicante practicante:practicantes){
                xmlSerializer.startTag(null,"practicante");

                xmlSerializer.startTag(null,"nombre");
                xmlSerializer.text(practicante.getNombre());
                xmlSerializer.endTag(null, "nombre");

                xmlSerializer.startTag(null,"carnet");
                xmlSerializer.text(practicante.getCarnet());
                xmlSerializer.endTag(null,"carnet");

                xmlSerializer.startTag(null,"contrasena");
                xmlSerializer.text(practicante.getContrasena());
                xmlSerializer.endTag(null,"contrasena");

                xmlSerializer.startTag(null,"fecha");
                xmlSerializer.text(practicante.getFechaDeNacimiento());
                xmlSerializer.endTag(null,"fecha");

                xmlSerializer.startTag(null,"correoCurso");
                xmlSerializer.text(practicante.getCarnet());
                xmlSerializer.endTag(null,"correoCurso");

                xmlSerializer.startTag(null,"correoAsesor");
                xmlSerializer.text(practicante.getCarnet());
                xmlSerializer.endTag(null,"correoAsesor");

                xmlSerializer.startTag(null,"empresa");
                xmlSerializer.text(practicante.getEmpresa());
                xmlSerializer.endTag(null,"empresa");

                xmlSerializer.endTag(null,"practicante");
            }
            xmlSerializer.endTag(null,"root");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite =  writer.toString();
            fileOutputStream.write(dataWrite.getBytes());
            fileOutputStream.close();
            Log.d("PracticanteDao","El xml termino de escribirse sin problemas");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static boolean isExternalStorageReadOnly() {

        String extStorageState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {

            return true;

        }

        return false;

    }

    public static boolean isExternalStorageAvailable() {

        String extStorageState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {

            return true;

        }

        return false;

    }

}
