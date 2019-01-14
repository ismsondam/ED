using System;
namespace CWindow
{
    public partial class MyWindow : Gtk.Window
    {
        public MyWindow() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
        }
    }
}
