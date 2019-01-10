using System;
using Gtk;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

        // Crear boton mediante código
        Button button = new Button(Stock.Close);
        button.Visible = true;
        vbox1.Add(button);
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }

    protected void OnBotonAceptarClicked(object sender, EventArgs e)
    {
        LabelSaludo.Text = "Hola, " + entry1.Text;
    }
}
