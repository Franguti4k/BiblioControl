package BiblioControl;

/**
 * Clase Main
 * Contiene el metodo main
 */
public class Main {

    /**
     * Metodo main
     * @param args argumentos del metodo main
     */
    public static void main(String[] args) {
        // Cargamos los datos de los archivos y creamos la interfaz gráfica inicial
        Admin.getInstance().inicializarSistema();
        new InterfazBiblioControl();
    }

}