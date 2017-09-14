


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Utilidades {

        
     public static void validarCampo(KeyEvent evt,int tipo,int maxLength){
        char tecla;
        
        JTextField txt=(JTextField)evt.getSource();
        if(txt.getText().length()==maxLength){
            evt.consume();
            return;
        }
                
        tecla=evt.getKeyChar();
        switch(tipo){
            //Entero    
            case 1:
                if(!Character.isDigit(tecla)){
                    evt.consume();
                }
                break;
                
            //Letras y espacio
            case 2:
                if(!Character.isLetter(tecla) && !Character.isWhitespace(tecla)){
                    evt.consume();
                }
                break;
            
            //Letras y numeros y espacios
            case 3:
                if(!Character.isLetterOrDigit(tecla) && !Character.isWhitespace(tecla)){
                    evt.consume();
                }
                break;
             
            //numeros con guion
            case 4:
                if(!Character.isDigit(tecla) && tecla!='-'){
                    evt.consume();
                }
                break;
            
            //Letras y espacio
            case 5:
                if(!Character.isLetter(tecla)){
                    evt.consume();
                }
                break;
            
            //Letras, numeros, guion, espacio y numeral#  (direcciones)
            case 6:
                if(!Character.isLetterOrDigit(tecla) && !Character.isWhitespace(tecla) && tecla!='-' && tecla!='#' ){
                    evt.consume();
                }
                break;
               
           //emails
            case 7:
                if(!Character.isLetterOrDigit(tecla) && tecla!='-' && tecla!='_'  && tecla!='.'  && tecla!='@' ){
                    evt.consume();
                }
                break;
                       //Letras y numeros
            case 8:
                if(!Character.isLetterOrDigit(tecla) ){
                    evt.consume();
                }
            //Letras, numeros, punto y espacios
            case 9:
                if(!Character.isLetterOrDigit(tecla) && !Character.isWhitespace(tecla) && tecla!='.' ){
                    evt.consume();
                }
                break;
            //numeros, punto
            case 10:
                if(Character.isLetter(tecla)){                    
                    mostrarMensaje(null, "No ha ingresado un dato válido", "Advertencia", 3);
                    evt.consume();
                }else if(!Character.isDigit(tecla) && tecla!='.' ){
                    evt.consume();
                }
                break;

        } 
    }
     
    public static boolean validarFecha(String fecha) {
 
    if (fecha == null)
        return false;
     
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
    if (fecha.trim().length() != dateFormat.toPattern().length())
        return false;
 
    dateFormat.setLenient(false);
 
    try {
        dateFormat.parse(fecha.trim());
    }
     catch (ParseException pe) {
        return false;
     }
     return true;
 }
   
    public static void enter(ActionEvent evt){
        ((Component)evt.getSource()).transferFocus();
    }
    
    public static void ganarFoco(FocusEvent evt){
        JTextField txt=(JTextField)evt.getSource();   
        txt.setBackground(new Color(255,255,159));
    }
    
    public static void perderFoco(FocusEvent evt){
        JTextField txt=(JTextField)evt.getSource(); 
        txt.setBackground(Color.white);
    }
    
    public static void ganarFocoP(FocusEvent evt){
        JPasswordField txt=(JPasswordField)evt.getSource();   
        txt.setBackground(new Color(255,255,159));
    }
    
    public static void perderFocoP(FocusEvent evt){
        JPasswordField txt=(JPasswordField)evt.getSource(); 
        txt.setBackground(Color.white);
    }
    
    public static void mostrarMensaje(Component parent,Object mensaje,String titulo,int tipoMensaje){
        int tipo=JOptionPane.INFORMATION_MESSAGE;
        switch (tipoMensaje){
            case 1:
                tipo=JOptionPane.INFORMATION_MESSAGE;
                break;
            case 2:
                tipo=JOptionPane.ERROR_MESSAGE;
                break;
            case 3:
                tipo=JOptionPane.WARNING_MESSAGE;
                break;   
        }
        JOptionPane.showMessageDialog(parent, mensaje, titulo, tipo);
    }
    
    public static void Msgbox(Object mensaje){
        JOptionPane.showMessageDialog(null, String.valueOf(mensaje), "msgbox", 1);
    }
    
    public static boolean mostrarConfirmacion(Component parent,Object mensaje,String titulo){
        if(JOptionPane.showConfirmDialog(parent, mensaje, titulo, JOptionPane.YES_NO_OPTION)==0)
            return true;
        return false;
    }
    
     public static String dateToString(Date date) {
        SimpleDateFormat FECHA=new SimpleDateFormat("yyyy-MM-dd");
        return date==null ? "" : FECHA.format(date);
    }
    
    public static String fechaAMD_DMA(String fecha) {
        SimpleDateFormat FECHA=new SimpleDateFormat("dd/MM/yyyy");
        return fecha==null ? "" : FECHA.format(fechaToDateAMD(fecha));
    }
    
    public static String fechaDMA_AMD(String fecha) {
        SimpleDateFormat FECHA=new SimpleDateFormat("yyyy/MM/dd");
        return fecha==null ? "" : FECHA.format(fechaToDateDMA(fecha));
    }
    
    public static String fechaD_M_AtoA_M_D(String fecha) {
        SimpleDateFormat FECHA=new SimpleDateFormat("yyyy-MM-dd");
        return fecha==null ? "" : FECHA.format(fechaToDateDMA(fecha));
    }
    
    public static Date fechaToDateAMD(String fecha){
        int anio=Integer.parseInt(fecha.substring(0,4));
        int mes=Integer.parseInt(fecha.substring(5,7));
        int dia=Integer.parseInt(fecha.substring(8,10));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        calendar.set(anio,mes-1,dia,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
    
    public static Date fechaToDateDMA(String fecha){
        int dia=Integer.parseInt(fecha.substring(0,2));
        int mes=Integer.parseInt(fecha.substring(3,5));
        int anio=Integer.parseInt(fecha.substring(6,10));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        calendar.set(anio,mes-1,dia,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
    
    public static String obtenerHora(String hora) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        SimpleDateFormat HORA=new SimpleDateFormat("h:mm:ss");
        return HORA.format(calendar.getTime());
    }
    
    public static Date obtenerFecha(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        return calendar.getTime();
     }
   
    public static int compararFechas(String fecha1,String fecha2){
        return fechaToDateAMD(fecha1).compareTo(fechaToDateAMD(fecha2));
    }
    
    public static Date bloquearFechaToDateDMA(String fecha){
        int dia=Integer.parseInt(fecha.substring(0,2));
        int mes=Integer.parseInt(fecha.substring(3,5));
        int anio=Integer.parseInt(fecha.substring(6,10));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00"), new Locale("es", "Colombia"));
        calendar.set(anio,mes-1,dia+1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

   public static String ruta(int i){
       String ruta="";
       switch(i){
           case 1:
                ruta="c:/windows/";
                break;
        }
       return ruta;
   }
   
       public static void campoEspecial(KeyEvent evt,int tipo) {
        char tecla;
        String letra="";
        String textocaja="";
        String textoconagregado="";
        JTextField txt=(JTextField)evt.getSource();
        tecla=evt.getKeyChar();
        int longitud;
                    evt.consume();
        
        switch(tipo){
            case 1://millares                 
                    textocaja=txt.getText().trim();
                    textocaja=textocaja.replace(",", "");
                    if(Character.isDigit(tecla)){
                        textocaja+=String.valueOf(tecla);
                    }     
                    longitud=textocaja.length();
                    if(tecla==KeyEvent.VK_DELETE){
                        textocaja="0";
                    }
                    if(longitud<4){
                        textoconagregado=textocaja;
                        txt.setText(textoconagregado);
                    }else{
                        int i;
                        for(i=textocaja.length();i>3;i-=3){
                            textoconagregado=","+textocaja.substring(i-3, i)+textoconagregado;
                        }   
                        textoconagregado=textocaja.substring(0, i)+textoconagregado;
                        txt.setText(textoconagregado);
                    }   
                break;
            
            case 2://fecha                 
                textocaja=txt.getText().trim();
                textocaja=textocaja.replace("-", "");
                if(tecla==KeyEvent.VK_BACK_SPACE || tecla==KeyEvent.VK_DELETE){
                    if(textocaja.length()<8){
                            for(int i=textocaja.length();i<8;i++){
                                textocaja+="0";
                            }
                        }
                    txt.setText(textoconagregado);
                }else if(Character.isDigit(tecla)){
                        textocaja+=String.valueOf(tecla); 
                        longitud=textocaja.length();
                        textocaja=textocaja.substring(longitud-8,longitud);                        
                    }     
                    String amo=textocaja.substring(0,4);
                    String mes=textocaja.substring(4,6);
                    String dia=textocaja.substring(6,8);
                    textocaja=amo+"-"+mes+"-"+dia;
                    textoconagregado=textocaja;
                        txt.setText(textoconagregado);
                break;
               
                
                case 3://porcentaje          
                textocaja=txt.getText().trim();
                textocaja=textocaja.replace("%", "");
                if(tecla==KeyEvent.VK_BACK_SPACE){                    
                    txt.setText("0%");
                }else if(Character.isDigit(tecla) || tecla=='.'){
                    if(textocaja.equals("0")){
                        textocaja="";
                    }
                    if(tecla=='.'){
                        if(textocaja.equals("")){
                            textocaja="0";
                        }
                    }
                    textocaja+=String.valueOf(tecla);
                    if(Double.parseDouble(textocaja)>100){
                        textocaja="0";
                    }
                    textoconagregado=textocaja+"%";
                    txt.setText(textoconagregado);
                }
                break;
        
                case 4: //fecha2         
                    textocaja=txt.getText().trim();
                    textocaja=textocaja.replace("-", "");
                    if(Character.isDigit(tecla)){
                        textocaja+=String.valueOf(tecla);
                    }     
                    longitud=textocaja.length();
                    if(longitud>6){
                        textoconagregado=textocaja.substring(0, 4)+"-"+textocaja.substring(4, 6)+"-"+textocaja.substring(6);
                        txt.setText(textoconagregado);
                    }else if(longitud>4){
                        textoconagregado=textocaja.substring(0, 4)+"-"+textocaja.substring(4);
                        txt.setText(textoconagregado);
                    }else{
                        textoconagregado=textocaja;
                        txt.setText(textoconagregado);
                    }
                   break; 

        }
    }
       
    public static String crearCodigo(){
        String codigo;
        Date now = new Date();  	
        codigo=String.valueOf(now.getTime());
        return codigo;
    }
    
    public static  String millares(String textocaja){
        String textoconagregado="";
         if(textocaja.indexOf(".")>0){           
                textocaja=textocaja.substring(0, textocaja.indexOf("."));
            }
        int longitud=textocaja.length();
        if(longitud<4){
            textoconagregado=textocaja;
            return textoconagregado;
        }else{
            int i;
            for(i=textocaja.length();i>3;i-=3){
                textoconagregado=","+textocaja.substring(i-3, i)+textoconagregado;
            }   
            textoconagregado=textocaja.substring(0, i)+textoconagregado;
            return textoconagregado;
        }
    }
    
    public static boolean abrirArchivoPdfUrl(String url){
        File file = new File(url);
        String path = file.getAbsolutePath();
        String comando[]={"D:/Sicad/fr/FoxitReaderPortable.exe", path};

        try {
        Process proc = Runtime.getRuntime().exec(comando);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean existeArchivo(String ruta){
        File fichero = new File(ruta);
        if (fichero.exists())
            return true;
        else
            return false;
    }
    
    public static String mes(String fecha){
        String mes="";
        String datos[]=fecha.split("-");
        mes=datos[1];
        switch(mes){
            case "01": mes="ene"; break;
            case "02": mes="feb"; break;
            case "03": mes="mar"; break;
            case "04": mes="abr"; break;
            case "05": mes="may"; break;
            case "06": mes="jun"; break;
            case "07": mes="jul"; break;
            case "08": mes="ago"; break;
            case "09": mes="sep"; break;
            case "10": mes="oct"; break;
            case "11": mes="nov"; break;
            case "12": mes="dic"; break;

        }
        return mes;
    }
    
    public static String amo(String fecha){
        String amo="";
        String datos[]=fecha.split("-");
        amo=datos[0];
        return amo;
    }

    /**
     * @return the rutaPdf
     */
    public static String getRutaPdf() {
        return "D:/Sicad/Reportes/reporte.pdf";
    }

    private static String getRutaReportes() {
        return "d:/sicad/reportes/";
    }
   public static String leer(String ruta){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String res="";

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            res= linea.toString().trim();
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return res;
    }
    public static boolean escribir(String ruta,String cadena){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            pw.println(cadena);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return true;
    }
    
   static Vector leerAVector(String ruta) {
      Vector vector= new Vector();
      String res="";
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            vector.add(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return vector;
    }
   
   public static String MD5() {
        String s = Utilidades.crearCodigo();
        MessageDigest m;
         try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            return String.valueOf(new BigInteger(1,m.digest()).toString(16));
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
             return s;
         }        
    }
   
   public static boolean copiarArchivos(String origen, String destino) { 
            try {
                    InputStream in = new FileInputStream(origen);
                    OutputStream out = new FileOutputStream(destino);

                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();
                    return true;
            } catch (IOException ioe){
                    ioe.printStackTrace();
                    return false;
            }
        }
}

