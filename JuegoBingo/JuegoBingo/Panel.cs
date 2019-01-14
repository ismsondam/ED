using System.Collections.Generic;
using Gtk;
using JuegoBingo;

namespace JuegoBingo
{
    public class Panel
    {
        static uint rows = 9;
        static uint columns = 10;

        IList<Button> buttons = new List<Button>();
        public Panel(VBox vbox)
        {
            Table table = new Table(rows, columns, true);
            int index = 0;
            for (uint row = 0; row < rows; row++)
            {
                for (uint col = 0; col < columns; ++col)
                {
                    index++;
                    Button button = new Button();
                    button.Label = index.ToString();
                    table.Attach(button, col, col + 1, row, row + 1);
                    buttons.Add(button);
                }
            }

            table.ShowAll();
            vbox.Add(table);
        }

        public void Marcar(int num)
        {
            buttons[num - 1].ModifyBg(StateType.Normal, new Gdk.Color(0, 255, 0));
        }
    }
}
