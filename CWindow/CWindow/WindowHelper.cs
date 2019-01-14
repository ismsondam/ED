using System;
using Gtk;

namespace CWindow
{
    public class WindowHelper
    {
        public static bool Confirm(Window window, String msg)
        {
            MessageDialog messageDialog = new MessageDialog(
                window, DialogFlags.Modal, MessageType.Question,
                ButtonsType.YesNo, msg);

            messageDialog.Title = window.Title;
            ResponseType response = (ResponseType)messageDialog.Run();
            messageDialog.Destroy();

            return response == 0;
        }
    }
}
