using System;
using Gtk;
using JuegoBingo;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

        Panel panel = new Panel(vbox);
        Bombo bombo = new Bombo();

        buttonAdelante.Clicked += delegate {
            int numero = bombo.sacarBola();
            panel.Marcar(32);
            buttonAdelante.Sensitive = bombo.quedanBolas();
            Console.WriteLine("Pulsado boton adelante");
        };
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
