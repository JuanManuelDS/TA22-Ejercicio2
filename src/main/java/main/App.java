package main;

import views.*;
import controllers.*;
import models.*;

public class App 
{
    public static void main( String[] args )
    {
        //--------------MODELO ------------------
        ConexionSQL csql = new ConexionSQL();
        Clientes c = new Clientes();
        Videos v = new Videos();

        ControladorDB cdb = new ControladorDB(csql, c, v);
        cdb.iniciarDB();

        //------------VISTA-------------------
        PanelOpciones po = new PanelOpciones();
        PanelFormularios pf = new PanelFormularios();
        ClienteFrame cframe = new ClienteFrame(po, pf);

        ControladorVista cv = new ControladorVista(cframe, po, pf);

        cv.iniciarVista();

    }
}