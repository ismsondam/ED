
// This file has been generated by the GUI designer. Do not modify.

public partial class MainWindow
{
	private global::Gtk.VBox vbox5;

	private global::Gtk.Button button;

	protected virtual void Build()
	{
		global::Stetic.Gui.Initialize(this);
		// Widget MainWindow
		this.Name = "MainWindow";
		this.Title = global::Mono.Unix.Catalog.GetString("MainWindow");
		this.WindowPosition = ((global::Gtk.WindowPosition)(4));
		// Container child MainWindow.Gtk.Container+ContainerChild
		this.vbox5 = new global::Gtk.VBox();
		this.vbox5.Name = "vbox5";
		this.vbox5.Spacing = 6;
		// Container child vbox5.Gtk.Box+BoxChild
		this.button = new global::Gtk.Button();
		this.button.CanFocus = true;
		this.button.Name = "button";
		this.button.UseUnderline = true;
		this.button.Label = global::Mono.Unix.Catalog.GetString("Mensaje");
		this.vbox5.Add(this.button);
		global::Gtk.Box.BoxChild w1 = ((global::Gtk.Box.BoxChild)(this.vbox5[this.button]));
		w1.Position = 0;
		w1.Expand = false;
		w1.Fill = false;
		this.Add(this.vbox5);
		if ((this.Child != null))
		{
			this.Child.ShowAll();
		}
		this.DefaultWidth = 400;
		this.DefaultHeight = 300;
		this.Show();
		this.DeleteEvent += new global::Gtk.DeleteEventHandler(this.OnDeleteEvent);
	}
}
